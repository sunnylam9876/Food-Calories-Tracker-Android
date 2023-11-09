package com.example.nutritiontracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritiontracking.Utility.Utility;
import com.example.nutritiontracking.db.FoodLogDatabaseHelper;
import com.example.nutritiontracking.db.PersonalDatabaseHelper;
import com.example.nutritiontracking.db.entity.FoodLog;
import com.example.nutritiontracking.db.entity.Personal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView txtViewBudgetValue, txtViewIntakeValue, txtViewRemainingValue, txtViewBreakfastValue;
    TextView txtViewLunchValue, txtViewDinnerValue, txtViewOthersValue, txtViewCarbsValue;
    TextView txtViewProteinValue, txtViewFatValue, txtViewWeightValue, txtViewHeightValue;
    TextView txtViewBMIValue;

    TextView txtViewTipsText;
    Button btnMainDatePicker;

    private FoodLogDatabaseHelper db;
    FoodLog foodLog;
    List<FoodLog> foodLogList_all = new ArrayList<>();
    List<FoodLog> foodLogList_whole_day = new ArrayList<>();
    List<FoodLog> foodLogList_breakfast = new ArrayList<>();
    List<FoodLog> foodLogList_lunch = new ArrayList<>();
    List<FoodLog> foodLogList_dinner = new ArrayList<>();
    List<FoodLog> foodLogList_others = new ArrayList<>();

    private PersonalDatabaseHelper db_personal;
    Personal personal;

    FloatingActionButton addFood;
    int lastAction;
    float dX, dY;

    private DatePickerDialog datePickerDialog;

//---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openTodayDetails = findViewById(R.id.btn_details);
        addFood = findViewById(R.id.btn_addFood);


        txtViewBudgetValue = findViewById(R.id.txtView_budget_value);
        txtViewIntakeValue = findViewById(R.id.txtView_intake_value);
        txtViewRemainingValue = findViewById(R.id.txtView_remaining_value);

        txtViewBreakfastValue = findViewById(R.id.txtView_breakfast_value);
        txtViewLunchValue = findViewById(R.id.txtView_lunch_value);
        txtViewDinnerValue = findViewById(R.id.txtView_dinner_value);
        txtViewOthersValue = findViewById(R.id.txtView_others_value);
        txtViewCarbsValue = findViewById(R.id.txtView_carbs_value);
        txtViewProteinValue = findViewById(R.id.txtView_protein_value);
        txtViewFatValue = findViewById(R.id.txtView_fat_value);

        txtViewWeightValue = findViewById(R.id.txtView_weight_value);
        txtViewHeightValue = findViewById(R.id.txtView_height_value);
        txtViewBMIValue = findViewById(R.id.txtView_bmi_value);

        txtViewTipsText = findViewById(R.id.txtView_tips_text);

        db = new FoodLogDatabaseHelper(this);
        db_personal = new PersonalDatabaseHelper(this);


        openTodayDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openTodayActivity(v);
            }
        });

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddFoodActivity(v);
            }
        });



        initDatePicker();
        btnMainDatePicker = findViewById(R.id.btn_today_datePicker);
        btnMainDatePicker.setText(Utility.getTodayDate());
        btnMainDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        txtViewTipsText.setText(Utility.getTips(Utility.getToday_Day()));

        //delete all database record
        /*
        foodLogList_all = db.getAllFoodLog();
        for (int i = 0; i < foodLogList.size(); i++) {
            db.deleteFoodLog(foodLogList.get(i));
        }
        foodLogList_all = db.getAllFoodLog();
        */
        //foodLogList_all = db.getAllFoodLog();

        addFood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                switch (motionEvent.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = v.getX() - motionEvent.getRawX();
                        dY = v.getY() - motionEvent.getRawY();
                        lastAction = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE:
                        v.setY(motionEvent.getRawY() + dY);
                        v.setX(motionEvent.getRawX() + dX);
                        lastAction = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP:
                        if (lastAction == MotionEvent.ACTION_DOWN)
                            //Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();
                            openAddFoodActivity(v);
                        break;

                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = Utility.makeDateString(day, month, year);
                btnMainDatePicker.setText(date);
                updateMainPage();
                txtViewTipsText.setText(Utility.getTips(day));
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

    @Override
    protected void onResume() {
        super.onResume();
        updateMainPage();
        //displayPersonalInfo();
    }



    //---------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_settings) {
            //Toast.makeText(this, "Setting", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public void openTodayActivity(View view) {
        Intent intent = new Intent(this, TodayActivity.class);
        Bundle b = new Bundle();
        b.putString("date", btnMainDatePicker.getText().toString());
        intent.putExtras(b);
        startActivity(intent);
    }

    public void openAddFoodActivity(View view) {
        Intent intent = new Intent(this, SearchFoodActivity.class);
        startActivity(intent);
    }

    public void updateMainPage() {
        foodLogList_whole_day = db.getFoodLogByDate(btnMainDatePicker.getText().toString(),0);
        FoodLog foodLog_calculation_whole_day = Utility.NutritionCalculation(foodLogList_whole_day);

        foodLogList_breakfast = db.getFoodLogByDate(btnMainDatePicker.getText().toString(),1);
        FoodLog foodLog_calculation_breakfast = Utility.NutritionCalculation(foodLogList_breakfast);

        foodLogList_lunch = db.getFoodLogByDate(btnMainDatePicker.getText().toString(),2);
        FoodLog foodLog_calculation_lunch = Utility.NutritionCalculation(foodLogList_lunch);

        foodLogList_dinner = db.getFoodLogByDate(btnMainDatePicker.getText().toString(),3);
        FoodLog foodLog_calculation_dinner = Utility.NutritionCalculation(foodLogList_dinner);

        foodLogList_others = db.getFoodLogByDate(btnMainDatePicker.getText().toString(),4);
        FoodLog foodLog_calculation_others = Utility.NutritionCalculation(foodLogList_others);

        txtViewIntakeValue.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getCalories())));
        txtViewBreakfastValue.setText(Long.toString(Math.round(foodLog_calculation_breakfast.getCalories())));
        txtViewLunchValue.setText(Long.toString(Math.round(foodLog_calculation_lunch.getCalories())));
        txtViewDinnerValue.setText(Long.toString(Math.round(foodLog_calculation_dinner.getCalories())));
        txtViewOthersValue.setText(Long.toString(Math.round(foodLog_calculation_others.getCalories())));

        txtViewCarbsValue.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getTotal_carbohydrates())) + " g");
        txtViewProteinValue.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getProtein())) + " g");
        txtViewFatValue.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getTotal_fat())) + " g");
        displayPersonalInfo();
    }

    public void displayPersonalInfo() {
        personal = db_personal.getPersonal("test");
        if (personal != null) {
            double bmi = Utility.calculate_BMI(personal.getWeight(), personal.getHeight());

            txtViewWeightValue.setText(Long.toString(Math.round(personal.getWeight())) + " kg");
            txtViewHeightValue.setText(Long.toString(Math.round(personal.getHeight())) + " cm");
            txtViewBMIValue.setText(Double.toString(bmi));

            double budget = personal.getCal_budget();
            double intake = Double.parseDouble(txtViewIntakeValue.getText().toString());
            double remaining = budget - intake;
            //Toast.makeText(MainActivity.this, Double.toString(remaining), Toast.LENGTH_LONG).show();

            txtViewBudgetValue.setText(Long.toString(Math.round(personal.getCal_budget())));
            txtViewRemainingValue.setText(Long.toString(Math.round(remaining)));
        } else {
            txtViewWeightValue.setText("0" + " kg");
            txtViewHeightValue.setText("0" + " cm");
            txtViewBMIValue.setText("0");
            txtViewBudgetValue.setText("0");
            txtViewRemainingValue.setText("0");
            Toast.makeText(this, "Please input personal information first", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, Setting.class);
            startActivity(intent);
        }

    }

}