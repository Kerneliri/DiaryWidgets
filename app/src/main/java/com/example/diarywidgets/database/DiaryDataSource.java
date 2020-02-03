package com.example.diarywidgets.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.diarywidgets.model.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryDataSource {

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    private DbHelper dbHelper;

    public DiaryDataSource(Context context) {
        dbHelper = new DbHelper(context);
    }

    // Adding new diary
    public void addDiary(Diary diary) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(DbHelper.diaries_ID, diary.getDiaryid()); // diary
        values.put(DbHelper.diaries_TITLE, diary.getTitle()); // diary
        values.put(DbHelper.diaries_DESCRIPTION, diary.getDescription());
        // Inserting Row
        db.insert(DbHelper.TABLE_DIARIES, null, values);
        db.close(); // Closing database connection
    }

    // Getting single diary
    public Diary getDiary(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(DbHelper.TABLE_DIARIES, new String[]{
                        DbHelper.diaries_ID, DbHelper.diaries_TITLE,DbHelper.diaries_DESCRIPTION}, DbHelper.diaries_ID
                        + "=?", new String[]{String.valueOf(id)}, null, null, null,
                null);
        if (cursor != null)
            cursor.moveToFirst();

        Diary diary = new Diary(Integer.parseInt(cursor.getString(0)),
                String.valueOf(cursor.getString(1)),String.valueOf(cursor.getString(2)));
        cursor.close();// Closing cursor connection
        db.close(); // Closing database connection
        return diary;
    }

    // Getting All Diaries
    public ArrayList<Diary> getAllDiaries() {
        ArrayList<Diary> diaryList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT * FROM " + DbHelper.TABLE_DIARIES;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Diary diary = new Diary(
                        Integer.parseInt(cursor.getString(0)),
                        String.valueOf(cursor.getString(1)),String.valueOf(cursor.getString(2)));
                // Adding diary to list
                diaryList.add(diary);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        // return diary list
        return diaryList;
    }

    // Delete one diary row
    public int deleteDiarywithID(int ID) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(DbHelper.TABLE_DIARIES, DbHelper.diaries_ID
                + " = ?", new String[]{String.valueOf(ID)});
        db.close();
        return result;

    }

    // Delete all diaries
    public int deletediaryAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(DbHelper.TABLE_DIARIES, null, null);
        db.close();
        return result;
    }

    // Select one diary row
    public int getLastPlusOnediaryID() {
        String query = "SELECT MAX(" + DbHelper.diaries_ID + ") AS max_id FROM "
                + DbHelper.TABLE_DIARIES;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int id = 1;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(0) + 1;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return id;
    }

    // get count diary rows
    public int getCountdiary() {
        String query = "SELECT COUNT(*) AS count_ FROM "
                + DbHelper.TABLE_DIARIES;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count_ = 0;
        //count_ = cursor.getCount();
        if (cursor.moveToFirst()) {
            do {
                count_ = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return count_;
    }

    // update one diary row
    public int updateDiary(Diary diary) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbHelper.diaries_ID, diary.getDiaryid());
        values.put(DbHelper.diaries_TITLE, diary.getTitle());
        values.put(DbHelper.diaries_DESCRIPTION, diary.getDescription());

        // updating row
        int result = db.update(DbHelper.TABLE_DIARIES, values,
                DbHelper.diaries_ID + " = ?",
                new String[]{String.valueOf(diary.getDiaryid())});
        db.close();
        return result;
    }


}
