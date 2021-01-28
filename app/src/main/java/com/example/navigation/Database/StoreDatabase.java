package com.example.navigation.Database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TableLayout;

import com.example.navigation.R;

public class StoreDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "lessondatabase.db";
    public static final int DATABASE_VERSION = 6;

    public static final String TABLE_STUDENTS = "students";
    public static final String TABLE_GROUPS = "user_group";
    public static final String TABLE_CITY = "user_city";


    public static final String COLUMN_USER_NAME = "user_name";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_PASSWORD = "user_password";
    public static final String COLUMN_STUDENT_GROUP_ID = "group_id";


    public static final String COLUMN_GINFO= "group_name";
    public static final String COLUMN_SUM = "group_sum";
    public static final String COLUMN_CITY = "group_city";
    public static final String COLUMN_CITY_ID = "group_city_id";




    Context context;



    public StoreDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users" + TABLE_STUDENTS + "("+
                COLUMN_USER_NAME + "TEXT," +
                COLUMN_USER_EMAIL  + "TEXT," +
                COLUMN_USER_PASSWORD +"INTEGER" +
                COLUMN_STUDENT_GROUP_ID + "TEXT)");

        db.execSQL("CREATE TABLE GROUPS" + TABLE_GROUPS + "("+
                COLUMN_STUDENT_GROUP_ID + "TEXT," +
                COLUMN_GINFO  + "TEXT," +
                COLUMN_SUM+"TEXT)");
        db.execSQL("CREATE TABLE CITY" + TABLE_CITY + "("+
                COLUMN_CITY_ID + "TEXT," +
                COLUMN_STUDENT_GROUP_ID + "TEXT," +
                COLUMN_USER_NAME + "TEXT," +
                COLUMN_CITY+"TEXT)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CITY);

        onCreate(db);

    }
    public void initGroups(SQLiteDatabase db){
        ContentValues group1 = new ContentValues();
        group1.put(COLUMN_USER_NAME, "IT");
        group1.put(COLUMN_SUM, 25);
        group1.put(COLUMN_STUDENT_GROUP_ID, "3e");
        group1.put(COLUMN_CITY, "Almaty");
        group1.put(COLUMN_CITY_ID, "02");
        db.insert(TABLE_GROUPS,null,group1);

        ContentValues group2 = new ContentValues();
        group2.put(COLUMN_USER_NAME, "smm");
        group2.put(COLUMN_SUM, 24);
        group2.put(COLUMN_STUDENT_GROUP_ID, "3f");
        group2.put(COLUMN_CITY, "Taraz");
        group2.put(COLUMN_CITY_ID, "08");
        db.insert(TABLE_GROUPS,null,group2);

        ContentValues group3 = new ContentValues();
        group3.put(COLUMN_USER_NAME, "is");
        group3.put(COLUMN_SUM, 24);
        group3.put(COLUMN_STUDENT_GROUP_ID, "3a");
        group3.put(COLUMN_CITY, "Shymkent");
        group3.put(COLUMN_CITY_ID, "13");
        db.insert(TABLE_GROUPS,null,group3);

    }

}
