package com.example.stevendeweille_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TodoDatabase extends SQLiteOpenHelper {
    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private static TodoDatabase instance;

    public static TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new TodoDatabase(context, "todos", null, 1);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed INTEGER)");
        ContentValues values = new ContentValues();
        values.put("title", "Do laundry");
        values.put("completed", 0);
        db.insert("todos", null, values);
        values.put("title", "Get started");
        values.put("completed", 0);
        db.insert("todos", null, values);
        values.put("title", "More items");
        values.put("completed", 0);
        db.insert("todos", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS todos");
        onCreate(db);
    }

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM todos",null);
    }

    public void insert(String title, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("completed", completed);
        db.insert("todos", null, values);
    }

    public void update(long id, int completed) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("completed", completed);
        db.update("todos", values, "_id = " + id, null);
    }

    public void delete(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos", "_id = " + id, null);
    }
}
