package com.example.diarywidgets.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

    // Database Name
    static final String DATABASE_NAME = "DiaryDB";
    // Database Version
    static final int DATABASE_VERSION = 1;

    // category table name
    public static final String TABLE_DIARIES = "diaries";


    // diaries Table Columns names
    public static final String diaries_ID = "id";
    public static final String diaries_TITLE = "title";
    public static final String diaries_DESCRIPTION = "description";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Table diaries
        String CREATE_DIARIES_TABLE = "CREATE TABLE " + TABLE_DIARIES
                + "(" + diaries_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + diaries_TITLE + " VARCHAR(50),"
                + diaries_DESCRIPTION + " VARCHAR(500)" + ");";
        db.execSQL(CREATE_DIARIES_TABLE);
        //-----------------------------------------------------------------------------
        String sql = "";
        sql = "INSERT INTO " + TABLE_DIARIES + " (" + diaries_TITLE
                + ") " + "VALUES ('" + "یاداشت 1" + "')";
        db.execSQL(sql);
        sql = "INSERT INTO " + TABLE_DIARIES + " (" + diaries_TITLE
                + ") " + "VALUES ('" + "یاداشت 2" + "')";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
