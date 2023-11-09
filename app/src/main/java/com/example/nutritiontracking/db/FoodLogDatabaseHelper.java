package com.example.nutritiontracking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nutritiontracking.db.entity.FoodLog;

import java.util.ArrayList;
import java.util.List;

public class FoodLogDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "food_log_db";

    public FoodLogDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(FoodLog.CREATE_TABLE);
        Log.d("Database created", "during onCreate event");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FoodLog.TABLE_NAME);
        onCreate(db);
    }

    //Insert data into database:
    public long insertFood_log(String user_name, String log_date, String meal, String food_name,
                               String brand_name, String unit, double quantity, double serving_in_grams,
                               double calories, double total_fat, double s_fat, double t_fat, double cholesterol,
                               double sodium, double total_carbohydrates, double dietary_fiber, double sugars,
                               double protein, double vitamin_a, double vitamin_c, double vitamin_d,
                               double calcium, double iron, double potassium){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FoodLog.COLUMN_USER_NAME, user_name);
        values.put(FoodLog.COLUMN_LOG_DATE, log_date);
        values.put(FoodLog.COLUMN_MEAL, meal);
        values.put(FoodLog.COLUMN_FOOD_NAME, food_name);
        values.put(FoodLog.COLUMN_BRAND_NAME, brand_name);
        values.put(FoodLog.COLUMN_UNIT, unit);
        values.put(FoodLog.COLUMN_QUANTITY, quantity);
        values.put(FoodLog.COLUMN_SERVING_IN_GRAMS, serving_in_grams);
        values.put(FoodLog.COLUMN_CALORIES, calories);
        values.put(FoodLog.COLUMN_TOTAL_FAT, total_fat);
        values.put(FoodLog.COLUMN_S_FAT, s_fat);
        values.put(FoodLog.COLUMN_T_FAT, t_fat);
        values.put(FoodLog.COLUMN_CHOLESTEROL, cholesterol);
        values.put(FoodLog.COLUMN_SODIUM, sodium);
        values.put(FoodLog.COLUMN_TOTAL_CARBOHYDRATES, total_carbohydrates);
        values.put(FoodLog.COLUMN_DIETARY_FIBER, dietary_fiber);
        values.put(FoodLog.COLUMN_SUGARS, sugars);
        values.put(FoodLog.COLUMN_PROTEIN, protein);
        values.put(FoodLog.COLUMN_VITAMIN_A, vitamin_a);
        values.put(FoodLog.COLUMN_VITAMIN_C, vitamin_c);
        values.put(FoodLog.COLUMN_VITAMIN_D, vitamin_d);
        values.put(FoodLog.COLUMN_CALCIUM, calcium);
        values.put(FoodLog.COLUMN_IRON, iron);
        values.put(FoodLog.COLUMN_POTASSIUM, potassium);

        long id = db.insert(FoodLog.TABLE_NAME, null, values);

        db.close();

        return id;      //if -1, failed
    }

    //Getting data from database by database id
    public FoodLog getFoodLog(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(FoodLog.TABLE_NAME,
                new String[] {
                        FoodLog.COLUMN_FOODLOG_ID,
                        FoodLog.COLUMN_USER_NAME,
                        FoodLog.COLUMN_LOG_DATE,
                        FoodLog.COLUMN_MEAL,
                        FoodLog.COLUMN_FOOD_NAME,
                        FoodLog.COLUMN_BRAND_NAME,
                        FoodLog.COLUMN_UNIT,
                        FoodLog.COLUMN_QUANTITY,
                        FoodLog.COLUMN_SERVING_IN_GRAMS,
                        FoodLog.COLUMN_CALORIES,
                        FoodLog.COLUMN_TOTAL_FAT,
                        FoodLog.COLUMN_S_FAT,
                        FoodLog.COLUMN_T_FAT,
                        FoodLog.COLUMN_CHOLESTEROL,
                        FoodLog.COLUMN_SODIUM,
                        FoodLog.COLUMN_TOTAL_CARBOHYDRATES,
                        FoodLog.COLUMN_DIETARY_FIBER,
                        FoodLog.COLUMN_SUGARS,
                        FoodLog.COLUMN_PROTEIN,
                        FoodLog.COLUMN_VITAMIN_A,
                        FoodLog.COLUMN_VITAMIN_C,
                        FoodLog.COLUMN_VITAMIN_D,
                        FoodLog.COLUMN_CALCIUM,
                        FoodLog.COLUMN_IRON,
                        FoodLog.COLUMN_POTASSIUM
                },
                FoodLog.COLUMN_FOODLOG_ID + " = ? ",
                new String[] {
                        String.valueOf(id)
                },null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        FoodLog foodlog = new FoodLog(
                cursor.getInt(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_FOODLOG_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_USER_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_LOG_DATE)),
                cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_MEAL)),
                cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_FOOD_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_BRAND_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_UNIT)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_QUANTITY)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SERVING_IN_GRAMS)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CALORIES)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_TOTAL_FAT)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_S_FAT)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_T_FAT)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CHOLESTEROL)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SODIUM)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_TOTAL_CARBOHYDRATES)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_DIETARY_FIBER)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SUGARS)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_PROTEIN)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_A)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_C)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_D)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CALCIUM)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_IRON)),
                cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_POTASSIUM))
        );

        cursor.close();
        return foodlog;
    }

    //--------------------------------------------------------------------------------------------
    //get Food log by date
    public List<FoodLog> getFoodLogByDate(String date, int meal_option) {
        /*
        meal option:
        0: no meal selection
        1: Breakfast
        2: Lunch
        3: Dinner
        4: Others
         */

        String selection = FoodLog.COLUMN_LOG_DATE + " = ? ";
        String[] selectionArgs = new String[] {String.valueOf(date)};

        List<FoodLog> foodLogList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();


        if (meal_option == 0) {
            selection = FoodLog.COLUMN_LOG_DATE + " = ? ";
            selectionArgs = new String[] {String.valueOf(date)};
        } else if (meal_option == 1) {
            selection = FoodLog.COLUMN_LOG_DATE + " = ? AND " + FoodLog.COLUMN_MEAL + " = ?";
            selectionArgs = new String[] {String.valueOf(date), String.valueOf("Breakfast")};
        } else if (meal_option == 2) {
            selection = FoodLog.COLUMN_LOG_DATE + " = ? AND " + FoodLog.COLUMN_MEAL + " = ?";
            selectionArgs = new String[] {String.valueOf(date), String.valueOf("Lunch")};
        } else if (meal_option == 3) {
            selection = FoodLog.COLUMN_LOG_DATE + " = ? AND " + FoodLog.COLUMN_MEAL + " = ?";
            selectionArgs = new String[] {String.valueOf(date), String.valueOf("Dinner")};
        } else if (meal_option == 4) {
            selection = FoodLog.COLUMN_LOG_DATE + " = ? AND " + FoodLog.COLUMN_MEAL + " = ?";
            selectionArgs = new String[] {String.valueOf(date), String.valueOf("Others")};
        }

        Cursor cursor = db.query(FoodLog.TABLE_NAME,
                        null,
                                selection, selectionArgs
                                ,null,null,null,null);

        /*
        new String[] {String.valueOf(date)}
         */

        if (cursor.moveToFirst()) {
            do {
                FoodLog foodlog = new FoodLog(
                        cursor.getInt(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_FOODLOG_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_USER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_LOG_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_MEAL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_FOOD_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_BRAND_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_UNIT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_QUANTITY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SERVING_IN_GRAMS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CALORIES)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_TOTAL_FAT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_S_FAT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_T_FAT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CHOLESTEROL)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SODIUM)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_TOTAL_CARBOHYDRATES)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_DIETARY_FIBER)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SUGARS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_PROTEIN)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_A)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_C)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_D)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CALCIUM)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_IRON)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_POTASSIUM))
                );
                foodLogList.add(foodlog);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return foodLogList;
    }
    //--------------------------------------------------------------------------------------------


    //Getting all data
    public ArrayList<FoodLog> getAllFoodLog() {
        ArrayList<FoodLog> foodLogs_list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + FoodLog.TABLE_NAME + " ORDER BY " +
                FoodLog.COLUMN_FOODLOG_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FoodLog foodLog = new FoodLog(
                        cursor.getInt(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_FOODLOG_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_USER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_LOG_DATE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_MEAL)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_FOOD_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_BRAND_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_UNIT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_QUANTITY)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SERVING_IN_GRAMS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CALORIES)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_TOTAL_FAT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_S_FAT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_T_FAT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CHOLESTEROL)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SODIUM)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_TOTAL_CARBOHYDRATES)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_DIETARY_FIBER)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_SUGARS)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_PROTEIN)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_A)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_C)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_VITAMIN_D)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_CALCIUM)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_IRON)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(FoodLog.COLUMN_POTASSIUM))
                );
                foodLogs_list.add(foodLog);
            } while (cursor.moveToNext());
        }
        db.close();
        Log.d("foodlogs_list: ", foodLogs_list.toString());
        return foodLogs_list;
    }

    public int updateFoodLog(FoodLog foodlog) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FoodLog.COLUMN_USER_NAME, foodlog.getUser_name());
        values.put(FoodLog.COLUMN_LOG_DATE, foodlog.getLog_date());
        values.put(FoodLog.COLUMN_MEAL, foodlog.getMeal());
        values.put(FoodLog.COLUMN_FOOD_NAME, foodlog.getFood_name());
        values.put(FoodLog.COLUMN_BRAND_NAME, foodlog.getBrand_name());
        values.put(FoodLog.COLUMN_UNIT, foodlog.getUnit());
        values.put(FoodLog.COLUMN_QUANTITY, foodlog.getQuantity());
        values.put(FoodLog.COLUMN_SERVING_IN_GRAMS, foodlog.getServing_in_grams());
        values.put(FoodLog.COLUMN_CALORIES, foodlog.getCalories());
        values.put(FoodLog.COLUMN_TOTAL_FAT, foodlog.getTotal_fat());
        values.put(FoodLog.COLUMN_S_FAT, foodlog.getS_fat());
        values.put(FoodLog.COLUMN_T_FAT, foodlog.getT_fat());
        values.put(FoodLog.COLUMN_CHOLESTEROL, foodlog.getCholesterol());
        values.put(FoodLog.COLUMN_SODIUM, foodlog.getSodium());
        values.put(FoodLog.COLUMN_TOTAL_CARBOHYDRATES, foodlog.getTotal_carbohydrates());
        values.put(FoodLog.COLUMN_DIETARY_FIBER, foodlog.getDietary_fiber());
        values.put(FoodLog.COLUMN_SUGARS, foodlog.getSugars());
        values.put(FoodLog.COLUMN_PROTEIN, foodlog.getProtein());
        values.put(FoodLog.COLUMN_VITAMIN_A, foodlog.getVitamin_a());
        values.put(FoodLog.COLUMN_VITAMIN_C, foodlog.getVitamin_c());
        values.put(FoodLog.COLUMN_VITAMIN_D, foodlog.getVitamin_d());
        values.put(FoodLog.COLUMN_CALCIUM, foodlog.getCalcium());
        values.put(FoodLog.COLUMN_IRON, foodlog.getIron());
        values.put(FoodLog.COLUMN_POTASSIUM, foodlog.getPotassium());

        return db.update(FoodLog.TABLE_NAME, values, FoodLog.COLUMN_FOODLOG_ID + " = ? " ,
                new String[] {String.valueOf(foodlog.getFoodLog_id())});
    }

    public void deleteFoodLog(FoodLog foodLog) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FoodLog.TABLE_NAME, FoodLog.COLUMN_FOODLOG_ID + " = ?",
                new String[]{String.valueOf(foodLog.getFoodLog_id())});

        db.close();
    }

}
