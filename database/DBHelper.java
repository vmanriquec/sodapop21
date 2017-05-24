package com.food.sistemas.sodapopapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "comida.sqlite";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "usuario";

    public static final String USU_ID = "idusuario";
    public static final String USU_NOMBRE = "nombreusuario";
    public static final String USU_CLAVE = "claveusuario";
    public static final String USU_ALMACEN = "almacenusuario";
    public static final String USU_ESTADO = "estadousuario";
    public static final String USU_IMAGEN = "imagen";
    public static final String USU_SELECTED = "selected";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Usuariosql.CREATE_TABLE);
        //db.execSQL(TrackDB.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Usuariosql.DROP_TABLE);
        //db.execSQL(TrackDB.DROP_TABLE);
        onCreate(db);
    }

    public Boolean insertarUsuario( String nombreusuario, String claveusuario, String almacenusuario, String estadousuario,String imagen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USU_NOMBRE, nombreusuario);
        contentValues.put(USU_CLAVE, claveusuario);
        contentValues.put(USU_ALMACEN, almacenusuario);
        contentValues.put(USU_ESTADO, estadousuario);
        contentValues.put(USU_IMAGEN, imagen);
      long result=  db.insert(TABLE_NAME, null, contentValues);
        db.close();
        if (result==-1){
            return false;
        }else
        {
            return true;
        }
    }

    public Cursor traerUsarios() {
        SQLiteDatabase db = this.getWritableDatabase();

       Cursor res =db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;

    }

    public void eliminarusuario(int idusuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + USU_ID + " = " + idusuario);
        db.close();
    }
}
