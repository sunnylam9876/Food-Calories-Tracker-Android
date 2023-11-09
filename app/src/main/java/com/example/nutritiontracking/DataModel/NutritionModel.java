package com.example.nutritiontracking.DataModel;

public class NutritionModel {
    private String nutritionName;
    private double nutritionAmount;

    public NutritionModel(String nutritionName, double nutritionAmount) {
        this.nutritionName = nutritionName;
        this.nutritionAmount = nutritionAmount;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public double getNutritionAmount() {
        return nutritionAmount;
    }

    public void setNutritionAmount(double nutritionAmount) {
        this.nutritionAmount = nutritionAmount;
    }
}
