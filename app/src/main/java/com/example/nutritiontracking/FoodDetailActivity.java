package com.example.nutritiontracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nutritiontracking.DataModel.NutritionModel;
import com.example.nutritiontracking.Utility.Utility;
import com.example.nutritiontracking.Utility.VolleySingleton;
import com.example.nutritiontracking.db.FoodLogDatabaseHelper;
import com.example.nutritiontracking.db.entity.FoodLog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodDetailActivity extends AppCompatActivity {

    ImageView foodImage;
    TextView detailFoodName;
    EditText quantity;
    TextView displayCalories;
    TextView details;

    String foodname;
    String nix_item_id;

    Button add_to_log_btn, datePicker_btn;

    TextView fat_amount, s_fat_amount, t_fat_amount;
    TextView cholesterol_amount, sodium_amount,carbohydrates_amount, fiber_amount, sugars_amount,
            protein_amount, vitamin_a_amount, vitamin_c_amount, vitamin_d_amount, calcium_amount,
            iron_amount, potassium_amount;

    Spinner spinnerMeal;

    String user_name, log_date, food_name, brand_name;
    double serving_qty, original_quantity;
    String unit;
    double serving_weight_grams;
    double original_calories, calories;
    double original_total_fat, total_fat;
    double original_saturated_fat, saturated_fat;
    double original_t_fat, t_fat;
    double original_cholesterol, cholesterol;
    double sodium, total_carbohydrate, dietary_fiber, sugars, protein;
    double original_sodium, original_total_carbohydrate, original_dietary_fiber, original_sugars, original_protein;
    double vitamin_a, vitamin_c, vitamin_d, calcium, iron, potassium;
    double original_vitamin_a, original_vitamin_c, original_vitamin_d, original_calcium, original_iron, original_potassium;

    Boolean isFocused;  //set quantity EditText focus status

    String imgPath;

    FoodLog foodLog;

    RecyclerView nutritionDetailRecycler;

    List<NutritionModel> nutritionList = new ArrayList<>();

    //3- Adapter
    //NutritionRecyclerAdapter adapter;

    //private RequestQueue mQueue;

    private FoodLogDatabaseHelper db;

    private DatePickerDialog datePickerDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_food_detail);

        foodImage = findViewById(R.id.imgView_foodimage);
        detailFoodName = findViewById(R.id.txtView_updateDetailFoodName);
        quantity = findViewById(R.id.editTxt_quantity);
        displayCalories = findViewById(R.id.txtView_calories);
        details = findViewById(R.id.txt_View_details);
        fat_amount = findViewById(R.id.txtView_fat_amount);
        s_fat_amount = findViewById(R.id.txtView_s_fat_amount);
        t_fat_amount = findViewById(R.id.txtView_t_fat_amount);
        cholesterol_amount = findViewById(R.id.txtView_cholesterol_amount);
        sodium_amount = findViewById(R.id.txtView_sodium_amount);
        carbohydrates_amount = findViewById(R.id.txtView_carbohydrate_amount);
        fiber_amount = findViewById(R.id.txtView_fiber_amount);
        sugars_amount = findViewById(R.id.txtView_sugar_amount);
        protein_amount = findViewById(R.id.txtView_protein_amount);
        vitamin_a_amount = findViewById(R.id.txtView_vitamin_a_amount);
        vitamin_c_amount = findViewById(R.id.txtView_vitamin_c_amount);
        vitamin_d_amount = findViewById(R.id.txtView_vitamin_d_amount);
        calcium_amount = findViewById(R.id.txtView_calcium_amount);
        iron_amount = findViewById(R.id.txtView_iron_amount);
        potassium_amount = findViewById(R.id.txtView_potassium_amount);
        //nutritionDetailRecycler = findViewById(R.id.nutritionDetailRecycler);
        add_to_log_btn = findViewById(R.id.btn_add_to_log);
        spinnerMeal = findViewById(R.id.spinner_meal);

        isFocused = false;  //set quantity EditText focus status


        Bundle bundle = getIntent().getExtras();
        foodname = bundle.getString("food_name");
        nix_item_id = bundle.getString("nix_item_id");

        db = new FoodLogDatabaseHelper(this);

        //Log.d("Date: ", datePicker_btn.getText().toString());

        add_to_log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serving_qty = Double.parseDouble(quantity.getText().toString()) / serving_weight_grams;
                long id = db.insertFood_log("test", datePicker_btn.getText().toString(),spinnerMeal.getSelectedItem().toString(),food_name, brand_name,
                        unit, serving_qty,serving_weight_grams, calories, total_fat, saturated_fat, t_fat, cholesterol, sodium,
                        total_carbohydrate, dietary_fiber, sugars, protein, vitamin_a, vitamin_c, vitamin_d, calcium, iron, potassium);
                //Log.d("Log inserted, ID :", String.valueOf(id));
                if (id == -1) {
                    Toast.makeText(v.getContext(), "Failed to insert record to database!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(v.getContext(), "Food logged", Toast.LENGTH_LONG).show();
                }//db.getAllFoodLog();
            }
        });

        quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    isFocused = false;
                    //Toast.makeText(FoodDetailActivity.this, "Not Focused",Toast.LENGTH_SHORT).show();
                } else {
                    isFocused = true;
                    //Toast.makeText(FoodDetailActivity.this, "Focused",Toast.LENGTH_SHORT).show();
                }

            }
        });

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //if (isFocused) {
                    String text = quantity.getText().toString().trim();
                    double newQuantity = 0;

                    if (!text.isEmpty()) {
                        try {
                            newQuantity = Double.parseDouble(text);
                            double ratio = newQuantity / original_quantity;
                            serving_qty = ratio;

                            calories = Utility.round(original_calories * ratio, 1);
                            total_fat = Utility.round( original_total_fat * ratio, 1);
                            saturated_fat = Utility.round(original_saturated_fat * ratio, 1);
                            t_fat  = Utility.round(original_t_fat * ratio, 1);
                            cholesterol = Utility.round( original_cholesterol * ratio, 1);
                            sodium = Utility.round( original_sodium * ratio, 1);
                            total_carbohydrate = Utility.round( original_total_carbohydrate * ratio, 1);
                            dietary_fiber = Utility.round(original_dietary_fiber * ratio, 1);
                            sugars = Utility.round( original_sugars * ratio, 1);
                            protein = Utility.round( original_protein * ratio, 1);
                            vitamin_a = Utility.round( original_vitamin_a * ratio, 1);
                            vitamin_c  = Utility.round( original_vitamin_c * ratio, 1);
                            vitamin_d = Utility.round( original_vitamin_d * ratio, 1);
                            calcium  = Utility.round( original_calcium * ratio, 1);
                            iron  = Utility.round(original_iron * ratio, 1);
                            potassium  = Utility.round( original_potassium * ratio, 1);


                            displayCalories.setText(Double.toString(calories) + " Calories");
                            fat_amount.setText(Double.toString(total_fat) + " g");
                            s_fat_amount.setText(Double.toString(saturated_fat) + " g");
                            t_fat_amount.setText(Double.toString(t_fat) + " g");
                            cholesterol_amount.setText(Double.toString(cholesterol) + " mg");
                            sodium_amount.setText(Double.toString(sodium) + " mg");
                            carbohydrates_amount.setText(Double.toString(total_carbohydrate) + " g");
                            fiber_amount.setText(Double.toString(dietary_fiber) + " g");
                            sugars_amount.setText(Double.toString(sugars) + " g");
                            protein_amount.setText(Double.toString(protein) + " g");
                            vitamin_a_amount.setText(Double.toString(vitamin_a) + " IU");
                            vitamin_c_amount.setText(Double.toString(vitamin_c) + " mg");
                            vitamin_d_amount.setText(Double.toString(vitamin_d) + " IU");
                            calcium_amount.setText(Double.toString(calcium) + " mg");
                            iron_amount.setText(Double.toString(iron) + " mg");
                            potassium_amount.setText(Double.toString(potassium) + " mg");
                        } catch (NumberFormatException e) {
                            Toast.makeText(FoodDetailActivity.this, "Data processing failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            //}
        });

        jsonParse();    //read data from API
        //details.setText(bundle.getString("food_name") + ", " + bundle.getString("nix_item_id"));

        initDatePicker();
        datePicker_btn = findViewById(R.id.btn_today_datePicker);
        datePicker_btn.setText(Utility.getTodayDate());
        datePicker_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = Utility.makeDateString(day, month, year);
                datePicker_btn.setText(date);
            }
        };
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }
    private void openDatePicker() {
        datePickerDialog.show();
    }

    //get data from API
    private void jsonParse() {

        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        // + "&detailed=true"
        try {
            // Instantiate the RequestQueue
            RequestQueue queue = VolleySingleton.getInstance(this).getRequestQueue();

            // Define the headers
            final HashMap<String, String> headers = new HashMap<>();
            headers.put("Context-Type", "application/json");
            headers.put("x-app-id", "e8938e5f");
            headers.put("x-app-key", "d8708f1ba07eeb826dc13562a35c3b5b");

            // Define the request body
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("query", foodname);
            jsonBody.put("timezone", "Canada/Western");

            // Create the request
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response", response.toString());
                            //details.setText(response.toString());
                            try {
                                JSONArray jsonArray = response.getJSONArray("foods");
                                String text_msg ="";
                                for (int i = 0; i< jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    food_name = jsonObject.optString("food_name", "");
                                    brand_name = jsonObject.optString("brand_name", "");
                                    serving_qty = jsonObject.optDouble("serving_qty", 0);
                                    unit = jsonObject.optString("serving_unit", "");
                                    serving_weight_grams = jsonObject.optDouble("serving_weight_grams", 0);
                                    original_calories = jsonObject.optDouble("nf_calories", 0);

                                    original_total_fat = jsonObject.optDouble("nf_total_fat", 0);
                                    original_saturated_fat = jsonObject.optDouble("nf_saturated_fat", 0);
                                    original_cholesterol = jsonObject.optDouble("nf_cholesterol", 0);
                                    original_sodium = jsonObject.optDouble("nf_sodium", 0);
                                    original_total_carbohydrate = jsonObject.optDouble("nf_total_carbohydrate", 0);
                                    original_dietary_fiber = jsonObject.optDouble("nf_dietary_fiber", 0);
                                    original_sugars = jsonObject.optDouble("nf_sugars", 0);
                                    original_protein = jsonObject.optDouble("nf_protein", 0);
                                    original_potassium = jsonObject.optDouble("nf_potassium", 0);

                                    JSONObject photoObject = jsonObject.optJSONObject("photo");
                                    if (photoObject != null) {
                                        imgPath = photoObject.optString("highres", "");
                                    } else {
                                        imgPath = "";
                                    }

                                    //to get the data in full nutrient fields
                                    JSONArray fullNutrientsArray = jsonObject.getJSONArray("full_nutrients");
                                    Log.d("full_nutrients", fullNutrientsArray.toString());
                                    for (int j = 0; j < fullNutrientsArray.length(); j++) {
                                        JSONObject nutrientObject = fullNutrientsArray.getJSONObject(j);
                                        int attrId = nutrientObject.getInt("attr_id");
                                        if (attrId == 605) {
                                            original_t_fat = nutrientObject.optDouble("value", 0);
                                        }
                                        if (attrId == 318) {
                                            original_vitamin_a = nutrientObject.optDouble("value", 0);
                                        }
                                        if (attrId == 401) {
                                            original_vitamin_c = nutrientObject.optDouble("value", 0);
                                        }
                                        if (attrId == 324) {
                                            original_vitamin_d = nutrientObject.optDouble("value", 0);
                                        }
                                        if (attrId == 301) {
                                            original_calcium = nutrientObject.optDouble("value", 0);
                                        }
                                        if (attrId == 303) {
                                            original_iron = nutrientObject.optDouble("value", 0);
                                        }
                                    }
                                }
                                detailFoodName.setText(food_name);
                                quantity.setText(Double.toString(serving_weight_grams));
                                original_quantity = serving_weight_grams;
                                displayCalories.setText(Double.toString(original_calories) + " Calories");
                                fat_amount.setText(Double.toString(original_total_fat) + " g");
                                s_fat_amount.setText(Double.toString(original_saturated_fat) + " g");
                                t_fat_amount.setText((Double.toString(original_t_fat)) + " g");
                                cholesterol_amount.setText(Double.toString(original_cholesterol) + " mg");
                                sodium_amount.setText(Double.toString(original_sodium) + " mg");
                                carbohydrates_amount.setText(Double.toString(original_total_carbohydrate) + " g");
                                fiber_amount.setText(Double.toString(original_dietary_fiber) + " g");
                                sugars_amount.setText(Double.toString(original_sugars) + " g");
                                protein_amount.setText(Double.toString(original_protein) + " g");
                                vitamin_a_amount.setText(Double.toString(original_vitamin_a) + " IU");
                                vitamin_c_amount.setText(Double.toString(original_vitamin_c) + " mg");
                                vitamin_d_amount.setText(Double.toString(original_vitamin_d) + " IU");
                                calcium_amount.setText(Double.toString(original_calcium) + " mg");
                                iron_amount.setText(Double.toString(original_iron) + " mg");
                                potassium_amount.setText(Double.toString(original_potassium) + " mg");

                                Picasso.get().load(imgPath).into(foodImage);

                                double ratio = 1;
                                calories = Utility.round(original_calories * ratio, 1);
                                total_fat = Utility.round( original_total_fat * ratio, 1);
                                saturated_fat = Utility.round(original_saturated_fat * ratio, 1);
                                t_fat  = Utility.round(original_t_fat * ratio, 1);
                                cholesterol = Utility.round( original_cholesterol * ratio, 1);
                                sodium = Utility.round( original_sodium * ratio, 1);
                                total_carbohydrate = Utility.round( original_total_carbohydrate * ratio, 1);
                                dietary_fiber = Utility.round(original_dietary_fiber * ratio, 1);
                                sugars = Utility.round( original_sugars * ratio, 1);
                                protein = Utility.round( original_protein * ratio, 1);
                                vitamin_a = Utility.round( original_vitamin_a * ratio, 1);
                                vitamin_c  = Utility.round( original_vitamin_c * ratio, 1);
                                vitamin_d = Utility.round( original_vitamin_d * ratio, 1);
                                calcium  = Utility.round( original_calcium * ratio, 1);
                                iron  = Utility.round(original_iron * ratio, 1);
                                potassium  = Utility.round( original_potassium * ratio, 1);


                            } catch (JSONException e) {
                                details.setText(e.toString());
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            details.setText(error.toString());

                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    return headers;
                }
            };

            // Add the request to the RequestQueue
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}