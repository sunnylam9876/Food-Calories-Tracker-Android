package com.example.nutritiontracking.Recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracking.FoodLogUpdate;
import com.example.nutritiontracking.R;
import com.example.nutritiontracking.TodayActivity;
import com.example.nutritiontracking.db.FoodLogDatabaseHelper;
import com.example.nutritiontracking.db.entity.FoodLog;

import java.util.List;

public class DayDetailRecyclerAdapter extends RecyclerView.Adapter<DayDetailRecyclerAdapter.MyViewHolder> {
    //1- Data Source
    private List<FoodLog> foodLogList;
    private Context context;

    private TodayActivity activity;

    FoodLogDatabaseHelper db;

    public DayDetailRecyclerAdapter(List<FoodLog> foodLogList, Context context, FoodLogDatabaseHelper db) {
        this.foodLogList = foodLogList;
        this.context = context;
        this.db = db;
    }

    //2- View Holder Class
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView txtViewDayMeal, txtViewDayFoodName, txtViewDayFoodQuantity, txtViewDayCalories;
        public Button btnDayUpdate;
        public LinearLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            txtViewDayMeal = itemView.findViewById(R.id.txtView_day_meal);
            txtViewDayFoodName = itemView.findViewById(R.id.txtView_day_food_name);
            txtViewDayFoodQuantity = itemView.findViewById(R.id.txtView_day_food_quantity);
            txtViewDayCalories = itemView.findViewById(R.id.txtView_day_calories);
            //btnDayUpdate = itemView.findViewById(R.id.btn_day_update);

            /*btnDayUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });*/
        }
    }

    //3- Implementing the methods

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.day_detail_recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodLog myListData = foodLogList.get(position);
        String meal = myListData.getMeal();

        double display_quantity = myListData.getServing_in_grams() * myListData.getQuantity();
        holder.txtViewDayMeal.setText(myListData.getMeal());
        holder.txtViewDayFoodName.setText(myListData.getFood_name());
        holder.txtViewDayFoodQuantity.setText(Double.toString(display_quantity) + " g");
        holder.txtViewDayCalories.setText(Double.toString(myListData.getCalories()) + " calories");

        if (meal.equals("Breakfast")){
            holder.txtViewDayMeal.setBackgroundResource(R.color.color_breakfast);
            holder.layout.setBackgroundResource(R.color.color_breakfast);
        }

        else if (meal.equals("Lunch")) {
            holder.txtViewDayMeal.setBackgroundResource(R.color.color_lunch);
            holder.layout.setBackgroundResource(R.color.color_lunch);
        }

        else if (meal.equals("Dinner")) {
            holder.txtViewDayMeal.setBackgroundResource(R.color.color_dinner);
            holder.layout.setBackgroundResource(R.color.color_dinner);
        }
        else {
            holder.txtViewDayMeal.setBackgroundResource(R.color.color_others);
            holder.layout.setBackgroundResource(R.color.color_others);
        }

    }

    @Override
    public int getItemCount() {
        return foodLogList.size();
    }

    //delete item
    public void deleteItem(int position) {
        FoodLog item = foodLogList.get(position);
        db.deleteFoodLog(item);
        foodLogList.remove(position);
        notifyItemRemoved(position);
    }

    public void updateItem(int position){

        FoodLog item = foodLogList.get(position);
        Intent intent = new Intent(context, FoodLogUpdate.class);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getFoodLog_id());
        intent.putExtras(bundle);
        //Toast.makeText(context, "id: " + Long.toString(item.getFoodLog_id()), Toast.LENGTH_LONG).show();

        context.startActivity(intent);
    }

    public Context getContext() {
        return context;
    }

}
