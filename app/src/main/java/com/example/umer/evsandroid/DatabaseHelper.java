package com.example.umer.evsandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contacts_db";


    public static final String TABLE_NAME = "contacts";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_NAME = "name";

    // Create table SQL query
  /*  public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_NAME + " TEXT"
                    + ")";*/

    public static final String CREATE_TABLE="CREATE TABLE contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,phone TEXT,imageurl TEXT)";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create contacts table
       /* db.execSQL("CREATE TABLE mycontacts " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, phone TEXT, name TEXT)" +
                "");*/
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS Contacts"); //+ TABLE_NAME);

        // Create tables again
        onCreate(db);
    }


    public void insertContact(String phone,String Name) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_NAME, Name);


        // insert row
        long id = db.insert(TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        //return id;
    }

    public Contact getContact(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_PHONE},
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare contact object
        Contact contact = new Contact();

        contact.setPhoneNo(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
        contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));


        // close the db connection
        cursor.close();

        return contact;
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        String [] arr={"umer"};
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();

                contact.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                contact.setPhoneNo(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));

                contacts.add(contact);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return contact list
        return contacts;
    }


}
