package com.milesilac.androidsqlitepracticer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String STRING_TABLE = "STRING_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_STRING = "ENTRYSTRING";


    public DBHelper(@Nullable Context context) {
        super(context, "textList.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + STRING_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_DATE + " TEXT, " + COLUMN_STRING + " TEXT)";

        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addEntry(StringEntry stringEntry) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, stringEntry.getDate());
        cv.put(COLUMN_STRING, stringEntry.getText());

        long insert = sqLiteDatabase.insert(STRING_TABLE, null, cv);

        sqLiteDatabase.close();
        return insert != -1;
    }

    public boolean deleteEntry(StringEntry stringEntry) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String deleteQuery = "DELETE FROM " + STRING_TABLE + " WHERE " + COLUMN_ID + " = " + stringEntry.getId();

        Cursor cursor = sqLiteDatabase.rawQuery(deleteQuery,null);

        if (cursor.moveToFirst()) {
            sqLiteDatabase.close();
            cursor.close();
            return true;
        }
        else {
            sqLiteDatabase.close();
            cursor.close();
            return false;
        }


    }

    public ArrayList<StringEntry> getAllTexts() {
        ArrayList<StringEntry> stringEntries = new ArrayList<>();

        String queryString = "SELECT * FROM " + STRING_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString,null);

        if (cursor.moveToFirst()) {
            do {
                int entryID = cursor.getInt(0);
                String entryDate = cursor.getString(1);
                String entryString = cursor.getString(2);

                StringEntry newEntry = new StringEntry(entryID,entryDate,entryString);
                stringEntries.add(newEntry);
            } while (cursor.moveToNext());
        }
        else {
            Log.e("ERROR","Error in looping");
        }

        cursor.close();
        sqLiteDatabase.close();
        return stringEntries;
    }
}
