package com.example.nutritiontracking.DataModel;

public class FoodModel {
    private String foodName;
    private String serving_unit;

    private int serving_qty;
    private String imgUrl;
    private String brand;
    private String nix_item_id;

    public FoodModel(String foodName, String serving_unit, int serving_qty, String imgUrl, String brand, String nix_item_id) {
        this.foodName = foodName;
        this.serving_unit = serving_unit;
        this.serving_qty = serving_qty;
        this.imgUrl = imgUrl;
        this.brand = brand;
        this.nix_item_id = nix_item_id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getServing_unit() {
        return serving_unit;
    }

    public void setServing_unit(String serving_unit) {
        this.serving_unit = serving_unit;
    }

    public int getServing_qty() {
        return serving_qty;
    }

    public void setServing_qty(int serving_qty) {
        this.serving_qty = serving_qty;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getnix_item_id() {
        return nix_item_id;
    }

    public void setnix_item_id(String nix_item_id) {
        this.nix_item_id = nix_item_id;
    }
}

