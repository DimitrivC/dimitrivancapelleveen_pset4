package com.example.dimitrivc.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DimitrivC on 20-11-2017.
 */

public class TodoDatabase extends SQLiteOpenHelper {

    private static TodoDatabase instance;

    // constructor: SQLiteOpenHelper
    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, completed INT);");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES('Trash', 0);");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES('Laundry', 0);");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES('Dishes', 0);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // hoezo int i en int il?
        String TABLE_NAME = "todos";
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public static TodoDatabase getInstance(Context context){
            if (instance == null){
                instance = new TodoDatabase(context, "table", null, 5 );
            }
            return instance;
    }

    public Cursor selectAll() {
        // use getWritableDatabase() to open connection
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM todos";
        Cursor cursor = db.rawQuery(query, new String[] {});
            // ipv null iets van: new String[] {String.valueOf(stdId)

        return cursor;
    }

    public void insert(String title, Integer completed) {

        // get connection to database: dit op te lossen door static weg te halen, maar dan doet insert
        // in main -> addItem het niet meer.
        SQLiteDatabase db = getWritableDatabase();

        // create new contentvalues object
        ContentValues contentValues = new ContentValues();

        // use put method to add values for title and completed

        // maar, ipv title moet het
        contentValues.put("title", title);
        //contentValues.put("completed", completed);

        // Then, call insert on the database connection, passing in the right parameters
        // weet niet of "table" klopt
        // en huh: insert in zichzelf oproepen? al heeft het andere parameters, dus miss zit het wel goed.
        db.insert("todos", null, contentValues);
    }



    public void update(Cursor cursor) {


        SQLiteDatabase db = getWritableDatabase();

        //cursor.moveToFirst();
        int test = cursor.getInt(2);



        if (test == 1){
            test = 0;
            //Log.d("test1", String.valueOf(test));
        }
        else if (test == 0) {
            test = 1;
        }

        //test = 0;

        Log.d("test3", String.valueOf(test));


        // Create a ContentValues object that contains the new value for completed.
        ContentValues contentValues = new ContentValues();
        //String key = id.toString();
        contentValues.put("completed", test);


        Long id2 = cursor.getLong(0);
        // id ...

        db.update("todos", contentValues, "_id = " + id2, null);

        // table: String / values: contentValues / whereClause: string / whereArgs: string
    }

    public void delete(Long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("todos", "_id = " + id, null);
        // mogelijke error: die id moet je miss gebruiken om aan te geven wat je wil deleten?
        // table: string / whereClause: string / whereArgs: string
    }
}
