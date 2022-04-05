package com.example.tuan7_lab7_a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PeopleDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "peoplesManager";
    private static final String TABLE_PEOPLES = "peoples";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    // Constructor
    public PeopleDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // create table
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_PEOPLES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" + ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLES);

        // Create tables again
        onCreate(sqLiteDatabase);
    }

    // add
    void addPeople(People people) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, people.get_name());

        // Inserting Row
        db.insert(TABLE_PEOPLES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // load
    public List<People> getAllContacts() {
        List<People> peopleList = new ArrayList<People>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PEOPLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                People contact = new People();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));

                // Adding people to list
                peopleList.add(contact);
            } while (cursor.moveToNext());
        }

        return peopleList;
    }

    // find by id
    People getPeople(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_PEOPLES, new String[] {
           KEY_ID, KEY_NAME
        }, KEY_ID + " =?", new String[] {String.valueOf(id)}, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        People people = new People(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        return people;
    }

    // delete
    public void deletePeople(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PEOPLES, KEY_ID + " =?", new String[] {String.valueOf(id)});
        db.close();
    }

}
