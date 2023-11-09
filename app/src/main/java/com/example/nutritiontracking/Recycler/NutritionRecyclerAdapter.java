package com.example.nutritiontracking.Recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NutritionRecyclerAdapter  {
    //extends RecyclerView.Adapter<NutritionRecyclerAdapter.MyViewHolder>

    /*private List<NutritionModel> nutritionListData;
    private Context context;

    public NutritionRecyclerAdapter(Context context, List<NutritionModel> nutritionListData) {
        this.nutritionListData = nutritionListData;
        this.context = context;
    }


    //View holder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nutritionName;
        public TextView nutritionAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nutritionName = itemView.findViewById(R.id.txtView_nutritionName);
            this.nutritionAmount = itemView.findViewById(R.id.txtView_nutritionAmount);
        }
    }

    //Implementing the methods
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.nutrition_detail_recycler, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NutritionModel myNutritionListData = nutritionListData.get(position);
        holder.nutritionName.setText(myNutritionListData.getNutritionName());
        holder.nutritionAmount.setText(Double.toString(myNutritionListData.getNutritionAmount()));
    }

    //@Override
    public int getItemCount() {
        return nutritionListData.size();
    }*/

}
