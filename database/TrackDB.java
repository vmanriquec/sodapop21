package com.food.sistemas.sodapopapp.database;

public class TrackDB {

    public static final String TABLE_NAME = "track";

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

}
