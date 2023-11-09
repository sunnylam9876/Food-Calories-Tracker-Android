package com.example.nutritiontracking.db.entity;

public class FoodLog {
    //constants for database
    public static final String TABLE_NAME = "foodLog_table";
    public static final String COLUMN_FOODLOG_ID = "foodLog_id";
    public static final String COLUMN_USER_NAME = "user_Name";
    public static final String COLUMN_LOG_DATE = "log_date";
    public static final String COLUMN_MEAL = "meal";
    public static final String COLUMN_FOOD_NAME = "food_name";
    public static final String COLUMN_BRAND_NAME = "brand_name";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_UNIT = "unit";
    public static final String COLUMN_SERVING_IN_GRAMS = "serving_in_grams";
    public static final String COLUMN_CALORIES = "calories";
    public static final String COLUMN_TOTAL_FAT = "total_fat";
    public static final String COLUMN_S_FAT = "s_fat";
    public static final String COLUMN_T_FAT = "t_fat";
    public static final String COLUMN_CHOLESTEROL = "cholesterol";
    public static final String COLUMN_SODIUM = "sodium";
    public static final String COLUMN_TOTAL_CARBOHYDRATES = "total_carbohydrates";
    public static final String COLUMN_DIETARY_FIBER = "dietary_fiber";
    public static final String COLUMN_SUGARS = "sugars";
    public static final String COLUMN_PROTEIN = "protein";
    public static final String COLUMN_VITAMIN_A = "vitamin_a";
    public static final String COLUMN_VITAMIN_C = "vitamin_C";
    public static final String COLUMN_VITAMIN_D = "vitamin_d";
    public static final String COLUMN_CALCIUM = "calcium";
    public static final String COLUMN_IRON = "iron";
    public static final String COLUMN_POTASSIUM = "potassium";

    //variables
    private int foodLog_id;
    private String user_name, log_date, meal, food_name, brand_name, unit;
    private double quantity, serving_in_grams, calories, total_fat, s_fat, t_fat, cholesterol, sodium;
    private double total_carbohydrates, dietary_fiber, sugars, protein, vitamin_a, vitamin_c, vitamin_d;
    private double calcium, iron, potassium;

    //constructors

    public FoodLog(int foodLog_id, String user_name, String log_date, String meal, String food_name,
                   String brand_name, String unit, double quantity, double serving_in_grams,
                   double calories, double total_fat, double s_fat, double t_fat, double cholesterol,
                   double sodium, double total_carbohydrates, double dietary_fiber, double sugars,
                   double protein, double vitamin_a, double vitamin_c, double vitamin_d, double calcium,
                   double iron, double potassium) {
        this.foodLog_id = foodLog_id;
        this.user_name = user_name;
        this.log_date = log_date;
        this.meal = meal;
        this.food_name = food_name;
        this.brand_name = brand_name;
        this.unit = unit;
        this.quantity = quantity;
        this.serving_in_grams = serving_in_grams;
        this.calories = calories;
        this.total_fat = total_fat;
        this.s_fat = s_fat;
        this.t_fat = t_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.total_carbohydrates = total_carbohydrates;
        this.dietary_fiber = dietary_fiber;
        this.sugars = sugars;
        this.protein = protein;
        this.vitamin_a = vitamin_a;
        this.vitamin_c = vitamin_c;
        this.vitamin_d = vitamin_d;
        this.calcium = calcium;
        this.iron = iron;
        this.potassium = potassium;
    }


    //getter and setter


    public int getFoodLog_id() {
        return foodLog_id;
    }

    public void setFoodLog_id(int foodLog_id) {
        this.foodLog_id = foodLog_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getServing_in_grams() {
        return serving_in_grams;
    }

    public void setServing_in_grams(double serving_in_grams) {
        this.serving_in_grams = serving_in_grams;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(double total_fat) {
        this.total_fat = total_fat;
    }

    public double getS_fat() {
        return s_fat;
    }

    public void setS_fat(double s_fat) {
        this.s_fat = s_fat;
    }

    public double getT_fat() {
        return t_fat;
    }

    public void setT_fat(double t_fat) {
        this.t_fat = t_fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getTotal_carbohydrates() {
        return total_carbohydrates;
    }

    public void setTotal_carbohydrates(double total_carbohydrates) {
        this.total_carbohydrates = total_carbohydrates;
    }

    public double getDietary_fiber() {
        return dietary_fiber;
    }

    public void setDietary_fiber(double dietary_fiber) {
        this.dietary_fiber = dietary_fiber;
    }

    public double getSugars() {
        return sugars;
    }

    public void setSugars(double sugars) {
        this.sugars = sugars;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getVitamin_a() {
        return vitamin_a;
    }

    public void setVitamin_a(double vitamin_a) {
        this.vitamin_a = vitamin_a;
    }

    public double getVitamin_c() {
        return vitamin_c;
    }

    public void setVitamin_c(double vitamin_c) {
        this.vitamin_c = vitamin_c;
    }

    public double getVitamin_d() {
        return vitamin_d;
    }

    public void setVitamin_d(double vitamin_d) {
        this.vitamin_d = vitamin_d;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getIron() {
        return iron;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    //SQL query: creating the table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_FOODLOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_LOG_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
            + COLUMN_MEAL + " TEXT,"
            + COLUMN_FOOD_NAME + " TEXT,"
            + COLUMN_BRAND_NAME + " TEXT,"
            + COLUMN_QUANTITY + " REAL,"
            + COLUMN_UNIT + " TEXT,"
            + COLUMN_SERVING_IN_GRAMS + " REAL,"
            + COLUMN_CALORIES + " REAL,"
            + COLUMN_TOTAL_FAT + " REAL,"
            + COLUMN_S_FAT + " REAL,"
            + COLUMN_T_FAT + " REAL,"
            + COLUMN_CHOLESTEROL + " REAL,"
            + COLUMN_SODIUM + " REAL,"
            + COLUMN_TOTAL_CARBOHYDRATES + " REAL,"
            + COLUMN_DIETARY_FIBER + " REAL,"
            + COLUMN_SUGARS + " REAL,"
            + COLUMN_PROTEIN + " REAL,"
            + COLUMN_VITAMIN_A + " REAL,"
            + COLUMN_VITAMIN_C + " REAL,"
            + COLUMN_VITAMIN_D + " REAL,"
            + COLUMN_CALCIUM + " REAL,"
            + COLUMN_IRON + " REAL, "
            + COLUMN_POTASSIUM + " REAL"
            + ")";
}
