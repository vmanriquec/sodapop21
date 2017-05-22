package com.food.sistemas.sodapopapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by usuario on 17/05/2017.
 */

public class Usuariosql {


    public static final String TABLE_NAME = "usuario";

    public static final String USU_ID = "idusuario";
    public static final String USU_NOMBRE = "nombreusuario";
    public static final String USU_CLAVE = "claveusuario";
    public static final String USU_ALMACEN = "almacenusuario";
    public static final String USU_ESTADO = "estadousuario";
    public static final String USU_IMAGEN = "imagenusuario";
    public static final String USU_SELECTED = "selected";

    public static final String CREATE_TABLE = " create table if not exists " + TABLE_NAME + " ("
            + USU_ID + " integer primary key autoincrement, "
            + USU_NOMBRE + " text not null, "
            + USU_CLAVE + " text not null, "
            + USU_ALMACEN + " text not null, "
            + USU_ESTADO + " text not null,"
            + USU_IMAGEN + " text not null,"
            + USU_SELECTED + " text not null default '0' );";

    public static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

    private DBHelper dbHelper;

    public Usuariosql(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insertarUsuario(int idusuario, String nombreusuario, String claveusuario, String almacenusuario, String estadousuario,String imagen) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USU_ID, idusuario);
        contentValues.put(USU_NOMBRE, nombreusuario);
        contentValues.put(USU_CLAVE, claveusuario);
        contentValues.put(USU_ALMACEN, almacenusuario);
        contentValues.put(USU_ESTADO, estadousuario);
        contentValues.put(USU_IMAGEN, imagen);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public Cursor traerUsarios() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public void eliminarregistros() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public Cursor seleccionarunusuario(int idusuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USU_ID + "=?", new String[]{String.valueOf(idusuario)});
    }

    public void eliminarusuario(int idusuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + USU_ID + " = " + idusuario);
        db.close();
    }

    public void actualizarusuario(int idusuario, String nombreusuario, String claveusuario, String almacenusuaro, String estadousuario) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USU_ID, idusuario);
        contentValues.put(USU_NOMBRE, nombreusuario);
        contentValues.put(USU_CLAVE, claveusuario);
        contentValues.put(USU_ALMACEN, almacenusuaro);
        contentValues.put(USU_ESTADO, estadousuario);
        db.update(TABLE_NAME, contentValues, " idusuario = ? ", new String[]{String.valueOf(idusuario)});
        db.close();
    }

    public void actualizarunusuario(int idusuario, String selected) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET " + USU_SELECTED + " = " + selected +  " WHERE "
                + USU_ID + " = " + idusuario);
        db.close();
    }
    
}
