package com.example.nutritiontracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritiontracking.Utility.Utility;
import com.example.nutritiontracking.db.FoodLogDatabaseHelper;
import com.example.nutritiontracking.db.entity.FoodLog;

public class FoodLogUpdate extends AppCompatActivity {
    int id;

    FoodLogDatabaseHelper db;
    FoodLog foodlog;

    TextView txtView_updateDetailFoodName, txtView_updateCalories, txtView_update_fat_amount,
            txtView_update_s_fat_amount, txtView_update_t_fat_amount, txtView_update_cholesterol_amount,
            txtView_update_sodium_amount, txtView_update_carbohydrate_amount, txtView_update_fiber_amount,
            txtView_update_sugar_amount, txtView_update_protein_amount, txtView_update_vitamin_a_amount,
            txtView_update_vitamin_c_amount, txtView_update_vitamin_d_amount, txtView_update_calcium_amount,
            txtView_update_iron_amount, txtView_update_potassium_amount;

    EditText editTxt_updateQuantity;

    Spinner spinner_updateMeal;

    Button btn_today_updateDatePicker, btn_update_log;

    //double serving_weight_grams;
    double original_calories, calories;
    double original_total_fat, total_fat;
    double original_saturated_fat, saturated_fat;
    double original_t_fat, t_fat;
    double original_cholesterol, cholesterol;
    double sodium, total_carbohydrate, dietary_fiber, sugars, protein;
    double original_sodium, original_total_carbohydrate, original_dietary_fiber, original_sugars, original_protein;
    double vitamin_a, vitamin_c, vitamin_d, calcium, iron, potassium;
    double original_vitamin_a, original_vitamin_c, original_vitamin_d, original_calcium, original_iron, original_potassium;
    double serving_in_grams, serving_qty, original_serving_qty, displayed_quantity;
    double ratio;

    Boolean isFocused;  //set quantity EditText focus status

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_log_update);
        isFocused = false;  //for the Quantity EditText

        txtView_updateDetailFoodName = findViewById(R.id.txtView_updateDetailFoodName);
        editTxt_updateQuantity = findViewById(R.id.editTxt_updateQuantity);
        spinner_updateMeal = findViewById(R.id.spinner_updateMeal);
        btn_today_updateDatePicker =findViewById(R.id.btn_today_updateDatePicker);
        btn_update_log = findViewById(R.id.btn_update_log);
        txtView_updateCalories = findViewById(R.id.txtView_updateCalories);
        txtView_update_fat_amount = findViewById(R.id.txtView_update_fat_amount);
        txtView_update_s_fat_amount = findViewById(R.id.txtView_update_s_fat_amount);
        txtView_update_t_fat_amount = findViewById(R.id.txtView_update_t_fat_amount);
        txtView_update_cholesterol_amount = findViewById(R.id.txtView_update_cholesterol_amount);
        txtView_update_sodium_amount = findViewById(R.id.txtView_update_sodium_amount);
        txtView_update_carbohydrate_amount = findViewById(R.id.txtView_update_carbohydrate_amount);
        txtView_update_fiber_amount = findViewById(R.id.txtView_update_fiber_amount);
        txtView_update_sugar_amount = findViewById(R.id.txtView_update_sugar_amount);
        txtView_update_protein_amount = findViewById(R.id.txtView_update_protein_amount);
        txtView_update_vitamin_a_amount = findViewById(R.id.txtView_update_vitamin_a_amount);
        txtView_update_vitamin_c_amount = findViewById(R.id.txtView_update_vitamin_c_amount);
        txtView_update_vitamin_d_amount = findViewById(R.id.txtView_update_vitamin_d_amount);
        txtView_update_calcium_amount = findViewById(R.id.txtView_update_calcium_amount);
        txtView_update_iron_amount = findViewById(R.id.txtView_update_iron_amount);
        txtView_update_potassium_amount = findViewById(R.id.txtView_update_potassium_amount);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");

        db = new FoodLogDatabaseHelper(this);
        foodlog = db.getFoodLog(id);

        switch(foodlog.getMeal()) {
            case "Basket":
                spinner_updateMeal.setSelection(0);
                break;
            case "Lunch":
                spinner_updateMeal.setSelection(1);
                break;
            case "Dinner":
                spinner_updateMeal.setSelection(2);
                break;
            case "Others":
                spinner_updateMeal.setSelection(3);
                break;
        }

        serving_in_grams = foodlog.getServing_in_grams();
        original_serving_qty = foodlog.getQuantity();
        original_calories = foodlog.getCalories();
        original_total_fat = foodlog.getTotal_fat();
        original_saturated_fat = foodlog.getS_fat();
        original_t_fat = foodlog.getT_fat();
        original_cholesterol = foodlog.getCholesterol();
        original_sodium = foodlog.getSodium();
        original_total_carbohydrate = foodlog.getTotal_carbohydrates();
        original_dietary_fiber = foodlog.getDietary_fiber();
        original_sugars = foodlog.getSugars();
        original_protein = foodlog.getProtein();
        original_vitamin_a = foodlog.getVitamin_a();
        original_vitamin_c = foodlog.getVitamin_c();
        original_vitamin_d = foodlog.getVitamin_d();
        original_calcium = foodlog.getCalcium();
        original_iron = foodlog.getIron();
        original_potassium = foodlog.getPotassium();

        calories = original_calories;
        total_fat = original_total_fat;
        saturated_fat = original_saturated_fat;
        t_fat  = original_t_fat;
        cholesterol = original_cholesterol;
        sodium = original_sodium;
        total_carbohydrate = original_total_carbohydrate;
        dietary_fiber = original_dietary_fiber;
        sugars = original_sugars;
        protein = original_protein;
        vitamin_a = original_vitamin_a;
        vitamin_c  = original_vitamin_c;
        vitamin_d = original_vitamin_d;
        calcium  = original_calcium;
        iron  = original_iron;
        potassium  = original_potassium;


        txtView_updateDetailFoodName.setText(foodlog.getFood_name());
        //txtView_updateDetailFoodName.setText(Long.toString(foodlog.getFoodLog_id()));

        displayed_quantity = original_serving_qty * serving_in_grams;
        editTxt_updateQuantity.setText(Double.toString(displayed_quantity));
        btn_today_updateDatePicker.setText(foodlog.getLog_date());
        txtView_updateCalories.setText(Double.toString(original_calories));
        txtView_update_fat_amount.setText(Double.toString(original_total_fat));
        txtView_update_s_fat_amount.setText(Double.toString(original_saturated_fat));
        txtView_update_t_fat_amount.setText(Double.toString(original_t_fat));
        txtView_update_cholesterol_amount.setText(Double.toString(original_cholesterol));
        txtView_update_sodium_amount.setText(Double.toString(original_sodium));
        txtView_update_carbohydrate_amount.setText(Double.toString(original_total_carbohydrate));
        txtView_update_fiber_amount.setText(Double.toString(original_dietary_fiber));
        txtView_update_sugar_amount.setText(Double.toString(original_sugars));
        txtView_update_protein_amount.setText(Double.toString(original_protein));
        txtView_update_vitamin_a_amount.setText(Double.toString(original_vitamin_a));
        txtView_update_vitamin_c_amount.setText(Double.toString(original_vitamin_c));
        txtView_update_vitamin_d_amount.setText(Double.toString(original_vitamin_d));
        txtView_update_calcium_amount.setText(Double.toString(original_calcium));
        txtView_update_iron_amount.setText(Double.toString(original_iron));
        txtView_update_potassium_amount.setText(Double.toString(original_potassium));


        editTxt_updateQuantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        editTxt_updateQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFocused) {
                    String text = editTxt_updateQuantity.getText().toString().trim();
                    double newQuantity = 0;

                    if (!text.isEmpty()) {
                        try {
                            newQuantity = Double.parseDouble(text);
                            if (newQuantity > 0) {
                                ratio = newQuantity / displayed_quantity;

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


                                txtView_updateCalories.setText(Double.toString(calories) + " Calories");
                                txtView_update_fat_amount.setText(Double.toString(total_fat) + " g");
                                txtView_update_s_fat_amount.setText(Double.toString(saturated_fat) + " g");
                                txtView_update_t_fat_amount.setText(Double.toString(t_fat) + " g");
                                txtView_update_cholesterol_amount.setText(Double.toString(cholesterol) + " mg");
                                txtView_update_sodium_amount.setText(Double.toString(sodium) + " mg");
                                txtView_update_carbohydrate_amount.setText(Double.toString(total_carbohydrate) + " g");
                                txtView_update_fiber_amount.setText(Double.toString(dietary_fiber) + " g");
                                txtView_update_sugar_amount.setText(Double.toString(sugars) + " g");
                                txtView_update_protein_amount.setText(Double.toString(protein) + " g");
                                txtView_update_vitamin_a_amount.setText(Double.toString(vitamin_a) + " IU");
                                txtView_update_vitamin_c_amount.setText(Double.toString(vitamin_c) + " mg");
                                txtView_update_vitamin_d_amount.setText(Double.toString(vitamin_d) + " IU");
                                txtView_update_calcium_amount.setText(Double.toString(calcium) + " mg");
                                txtView_update_iron_amount.setText(Double.toString(iron) + " mg");
                                txtView_update_potassium_amount.setText(Double.toString(potassium) + " mg");
                            }

                        } catch (NumberFormatException e) {
                            Toast.makeText(FoodLogUpdate.this, "Data update failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });


        btn_update_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serving_qty = Double.parseDouble(editTxt_updateQuantity.getText().toString()) / serving_in_grams;

                foodlog.setLog_date(btn_today_updateDatePicker.getText().toString());
                foodlog.setMeal(spinner_updateMeal.getSelectedItem().toString());
                foodlog.setQuantity(serving_qty);
                //foodlog.setServing_ieggrams(Double.parseDouble(editTxt_updateQuantity.getText().toString()));
                foodlog.setCalories(calories);
                foodlog.setTotal_fat(total_fat);
                foodlog.setS_fat(saturated_fat);
                foodlog.setT_fat(t_fat);
                foodlog.setCholesterol(cholesterol);
                foodlog.setSodium(sodium);
                foodlog.setTotal_carbohydrates(total_carbohydrate);
                foodlog.setDietary_fiber(dietary_fiber);
                foodlog.setSugars(sugars);
                foodlog.setProtein(protein);
                foodlog.setVitamin_a(vitamin_a);
                foodlog.setVitamin_c(vitamin_c);
                foodlog.setVitamin_d(vitamin_d);
                foodlog.setCalcium(calcium);
                foodlog.setIron(iron);
                foodlog.setPotassium(potassium);

                int i = db.updateFoodLog(foodlog);
                if (i > 0) {
                    Toast.makeText(FoodLogUpdate.this, "Data updated", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(FoodLogUpdate.this, "Data update failed", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}