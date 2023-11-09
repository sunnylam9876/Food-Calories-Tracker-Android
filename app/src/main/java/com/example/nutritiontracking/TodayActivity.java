package com.example.nutritiontracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutritiontracking.Recycler.DayDetailRecyclerAdapter;
import com.example.nutritiontracking.Recycler.DayDetailRecyclerTouchHelper;
import com.example.nutritiontracking.Utility.Utility;
import com.example.nutritiontracking.db.FoodLogDatabaseHelper;
import com.example.nutritiontracking.db.entity.FoodLog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TodayActivity extends AppCompatActivity {
    private Button btnTodayDatePicker;
    private TextView txtViewTodayCalories, txtViewTodayProteinAmount, txtViewTodayCarbohydrateAmount, txtViewTodayFatAmount;
    private DatePickerDialog datePickerDialog;
    private List<FoodLog> foodLogList_whole_day = new ArrayList<>();
    private FoodLogDatabaseHelper db;
    private FoodLog foodLog;
    Boolean recyclerCreated;

    RecyclerView recyclerTodayFoods;
    RecyclerView.LayoutManager todayLayoutManager;
    //RecyclerView.Adapter todayAdapter;
    DayDetailRecyclerAdapter todayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        recyclerCreated = false;
        btnTodayDatePicker = findViewById(R.id.btn_today_datePicker);
        txtViewTodayCalories = findViewById(R.id.txtView_today_calories);
        txtViewTodayProteinAmount = findViewById(R.id.txtView_today_protein_amount);
        txtViewTodayCarbohydrateAmount = findViewById(R.id.txtView_today_carbohydrate_amount);
        txtViewTodayFatAmount = findViewById(R.id.txtView_today_fat_amount);
        recyclerTodayFoods = findViewById(R.id.recycler_today_foods);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String date = b.getString("date");
        btnTodayDatePicker.setText(date);

        initDatePicker();

        btnTodayDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

        db = new FoodLogDatabaseHelper(this);

        updateTodayPage();

    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;
                String date = Utility.makeDateString(day, month, year);
                btnTodayDatePicker.setText(date);
                updateTodayPage();
            }
        };
        Calendar cal= Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        //int style = android.R.style.Theme_Material_Light_Dialog;


        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTodayPage();
    }

    private void updateTodayPage() {

        foodLogList_whole_day.clear();
        foodLogList_whole_day.addAll(db.getFoodLogByDate(btnTodayDatePicker.getText().toString(), 0));

        //foodLogList_whole_day = db.getFoodLogByDate(btnTodayDatePicker.getText().toString(),0);
        foodLogList_whole_day.sort((f1, f2) -> f1.getMeal().compareTo(f2.getMeal()));
        FoodLog foodLog_calculation_whole_day = Utility.NutritionCalculation(foodLogList_whole_day);

        txtViewTodayCalories.setText("Total Calories: " + Long.toString(Math.round(foodLog_calculation_whole_day.getCalories())));
        txtViewTodayCarbohydrateAmount.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getTotal_carbohydrates())) + " g");
        txtViewTodayFatAmount.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getTotal_fat())) + " g");
        txtViewTodayProteinAmount.setText(Long.toString(Math.round(foodLog_calculation_whole_day.getProtein())) + " g");

        //update recycler
        //recyclerTodayFoods = findViewById(R.id.recycler_today_foods);
        //todayLayoutManager = new LinearLayoutManager(this);
        //recyclerTodayFoods.setLayoutManager(todayLayoutManager);
        //todayAdapter = new DayDetailRecyclerAdapter(foodLogList_whole_day, TodayActivity.this, db);
        //recyclerTodayFoods.setAdapter(todayAdapter);
        //todayAdapter.notifyDataSetChanged();



        if (recyclerCreated == false) {
            todayAdapter = new DayDetailRecyclerAdapter(foodLogList_whole_day, TodayActivity.this, db);
            //recyclerTodayFoods.setHasFixedSize(true);
            todayLayoutManager = new LinearLayoutManager(this);
            recyclerTodayFoods.setLayoutManager(todayLayoutManager);
            recyclerTodayFoods.setAdapter(todayAdapter);
            recyclerCreated = true;

            DayDetailRecyclerTouchHelper touchHelper = new DayDetailRecyclerTouchHelper(todayAdapter);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchHelper);
            itemTouchHelper.attachToRecyclerView(recyclerTodayFoods);

        } else {
            long list_size = foodLogList_whole_day.size();
            //todayAdapter = new DayDetailRecyclerAdapter(foodLogList_whole_day, TodayActivity.this, db);
            todayAdapter.notifyDataSetChanged();
            //int i = todayAdapter.getItemCount();
            //String msg = "List size: " + Long.toString(list_size) + "; i: " + Long.toString(i);
            //Toast.makeText(TodayActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    private void openDatePicker() {
        datePickerDialog.show();
    }

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
}