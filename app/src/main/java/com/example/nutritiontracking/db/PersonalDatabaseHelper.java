package com.example.nutritiontracking.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nutritiontracking.db.entity.FoodLog;
import com.example.nutritiontracking.db.entity.Personal;

import java.util.ArrayList;
import java.util.List;

public class PersonalDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personal_db";

    Boolean isDataAvailable;

    public PersonalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Personal.CREATE_TABLE);
        Log.d("Database created", "during onCreate event");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Personal.TABLE_NAME);
        onCreate(db);
    }

    //Insert data into database:
    public long insertPersonal(String user_name, String gender, double weight, double height, double cal_budget){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Personal.COLUMN_USER_NAME, user_name);
        values.put(Personal.COLUMN_GENDER, gender);
        values.put(Personal.COLUMN_WEIGHT, weight);
        values.put(Personal.COLUMN_HEIGHT, height);
        values.put(Personal.COLUMN_CAL_BUDGET, cal_budget);
        
        long id = db.insert(Personal.TABLE_NAME, null, values);

        db.close();

        return id;      //if -1, failed
    }

    //Getting data from database by user name
    public Personal getPersonal(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Personal.TABLE_NAME,
                new String[] {
                        Personal.COLUMN_USER_ID,
                        Personal.COLUMN_USER_NAME,
                        Personal.COLUMN_GENDER,
                        Personal.COLUMN_WEIGHT,
                        Personal.COLUMN_HEIGHT,
                        Personal.COLUMN_CAL_BUDGET
                },
                Personal.COLUMN_USER_NAME + " = ? ",
                new String[] {
                        username
                },null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor != null && cursor.moveToFirst()) {
            //if there is any data
            Personal personal = new Personal(
                    cursor.getInt(cursor.getColumnIndexOrThrow(Personal.COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Personal.COLUMN_USER_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(Personal.COLUMN_GENDER)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Personal.COLUMN_WEIGHT)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Personal.COLUMN_HEIGHT)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(Personal.COLUMN_CAL_BUDGET))
            );
            cursor.close();
            isDataAvailable = true;
            return personal;
        } else {
            //if no data found
            cursor.close();
            isDataAvailable = false;
            return null;
        }
    }

    //--------------------------------------------------------------------------------------------
    //Getting all data
    public ArrayList<Personal> getAllPersonal() {
        ArrayList<Personal> personal_list = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Personal.TABLE_NAME + " ORDER BY " +
                Personal.COLUMN_USER_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Personal personal = new Personal(
                        cursor.getInt(cursor.getColumnIndexOrThrow(Personal.COLUMN_USER_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Personal.COLUMN_USER_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Personal.COLUMN_GENDER)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(Personal.COLUMN_WEIGHT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(Personal.COLUMN_HEIGHT)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(Personal.COLUMN_CAL_BUDGET))
                );
                personal_list.add(personal);
            } while (cursor.moveToNext());
        }
        db.close();
        Log.d("personal_list: ", personal_list.toString());
        return personal_list;
    }
    
    //--------------------------------------------------------------------------------------------
    //update data in Personal database by user name
    public int updatePersonal(Personal personal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Personal.COLUMN_USER_NAME, personal.getUser_name());
        values.put(Personal.COLUMN_GENDER, personal.getGender());
        values.put(Personal.COLUMN_WEIGHT, personal.getWeight());
        values.put(Personal.COLUMN_HEIGHT, personal.getHeight());
        values.put(Personal.COLUMN_CAL_BUDGET, personal.getCal_budget());

        return db.update(Personal.TABLE_NAME, values, Personal.COLUMN_USER_NAME + " = ? " ,
                new String[] {personal.getUser_name()});
    }

    public int deletePersonal(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id = db.delete(Personal.TABLE_NAME, Personal.COLUMN_USER_NAME + " = ?",
                new String[]{username});
        db.close();
        return id; //return > 0 if deletion was successful
    }

}
