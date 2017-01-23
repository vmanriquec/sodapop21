package com.food.sistemas.sodapopapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ContactDB {

    public static final String TABLE_NAME = "contact";

    public static final String CON_ID = "_id";
    public static final String CON_NAME = "name";
    public static final String CON_PHONE = "phone";
    public static final String CON_MAIL = "mail";
    public static final String CON_COD = "cod";
    public static final String CON_SELECTED = "selected";

    public static final String CREATE_TABLE = " create table if not exists " + TABLE_NAME + " ("
            + CON_ID + " integer primary key autoincrement, "
            + CON_NAME + " text not null, "
            + CON_PHONE + " text not null, "
            + CON_MAIL + " text not null, "
            + CON_COD + " text not null,"
            + CON_SELECTED + " text not null default '0' );";

    public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

    private DBHelper dbHelper;

    public ContactDB(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertContact(int _id, String name, String phone, String mail, String cod) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CON_ID, _id);
        contentValues.put(CON_NAME, name);
        contentValues.put(CON_PHONE, phone);
        contentValues.put(CON_MAIL, mail);
        contentValues.put(CON_COD, cod);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public Cursor getContacts() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void deleteRecords() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public Cursor selectContact(int _id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CON_ID + "=?", new String[]{String.valueOf(_id)});
    }

    public void deleteContact(int _id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + CON_ID + " = " + _id);
        db.close();
    }

    public void updateContact(int _id, String name, String phone, String mail, String cod) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CON_ID, _id);
        contentValues.put(CON_NAME, name);
        contentValues.put(CON_PHONE, phone);
        contentValues.put(CON_MAIL, mail);
        contentValues.put(CON_COD, cod);
        db.update(TABLE_NAME, contentValues, " _id = ? ", new String[]{String.valueOf(_id)});
        db.close();
    }

    public void updateContactSelected(int _id, String selected) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + CON_SELECTED + " = " + selected +  " WHERE "
                + CON_ID + " = " + _id);
        db.close();
    }

}
