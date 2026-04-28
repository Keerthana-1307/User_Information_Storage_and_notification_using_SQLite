package com.example.dbconnectivity;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

public class DB extends SQLiteOpenHelper {
    SQLiteDatabase db;
    public DB(Context c) {
        super(c, "student_data.db", null, 1);
        db = getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Table updated to store Name and Department
        db.execSQL("CREATE TABLE students(Name TEXT, Dept TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int o, int n) {}

    public void sav(String n, String d) {
        ContentValues v = new ContentValues();
        v.put("Name", n);
        v.put("Dept", d);
        db.insert("students", null, v);
    }

    public String retrieve(String n) {
        Cursor c = db.query("students", null, "Name = ?", new String[]{n}, null, null, null);
        if (c.moveToFirst()) {
            return "Department: " + c.getString(1); // Returns the Department
        } else {
            return "Record not found";
        }
    }
}

