package com.example.nutritiontracking.db.entity;

public class Personal {
    //constants for database
    public static final String TABLE_NAME = "personal_table";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_WEIGHT = "weight";
    public static final String COLUMN_HEIGHT = "height";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CAL_BUDGET = "cal_budget";

    //variables
    private int user_id;
    private String user_name, gender;
    private double weight, height, cal_budget;

    //constructors

    public Personal(int user_id, String user_name, String gender, double weight, double height, double cal_budget) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.cal_budget = cal_budget;
    }


    //getter and setter


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getCal_budget() {
        return cal_budget;
    }

    public void setCal_budget(double cal_budget) {
        this.cal_budget = cal_budget;
    }

    //SQL query: creating the table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USER_NAME + " TEXT,"
                    + COLUMN_WEIGHT + " REAL, "
                    + COLUMN_HEIGHT + " REAL, "
                    + COLUMN_GENDER + " TEXT, "
                    + COLUMN_CAL_BUDGET + " REAL"
                    + ")";
}
