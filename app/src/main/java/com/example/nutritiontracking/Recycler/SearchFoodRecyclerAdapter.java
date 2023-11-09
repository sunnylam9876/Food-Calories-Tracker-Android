package com.example.nutritiontracking.Recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutritiontracking.FoodDetailActivity;
import com.example.nutritiontracking.DataModel.FoodModel;
import com.example.nutritiontracking.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchFoodRecyclerAdapter extends RecyclerView.Adapter<SearchFoodRecyclerAdapter.MyViewHolder> {
    //1- Data Source
    //private FoodModel[] listData;
    private List<FoodModel> listData;
    private Context context;

    public SearchFoodRecyclerAdapter(Context context, List<FoodModel> listData) {
        this.context = context;
        this.listData = listData;
    }

    //2- View Holder Class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName;
        //public TextView foodCalorie;
        //public TextView brand;
        public ImageView food_img;
        public Button addFoodDetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.foodName = itemView.findViewById(R.id.txtView_foodName);
            //this.foodCalorie = itemView.findViewById(R.id.txtView_foodCalorie);
            this.food_img = itemView.findViewById(R.id.imgView_food_img);
            //this.brand = itemView.findViewById(R.id.txtView_brand);
            this.addFoodDetail = itemView.findViewById(R.id.btn_add);

        }
    }

    //3- Implementing the methods
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View listItem = inflater.inflate(R.layout.food_recycler_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FoodModel myListData = listData.get(position);
        holder.foodName.setText(myListData.getFoodName());
        Picasso.get().load(myListData.getImgUrl()).into(holder.food_img);

        holder.addFoodDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), myListData.getFoodName() + ", " + myListData.getnix_item_id(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, FoodDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("food_name", myListData.getFoodName());
                bundle.putString("nix_item_id", myListData.getnix_item_id());
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }



}
