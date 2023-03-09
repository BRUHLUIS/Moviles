package com.example.registro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DBNAME = "DataBase.db";

    public DBHelper(Context context) {
        super(context, "DataBase.db", null, 1,null);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT, gender TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {

    }

    public Boolean inserData(String username, String password, String gender, String email){
        SQLiteDatabase MyBD = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password",password);
        contentValues.put("gender",gender);
        contentValues.put("email", email);


        long result = MyBD.insert("users", null, contentValues);
        if(result == -1) return false;
        else return true;
    }

    public Boolean checkusername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkusernamepassword(String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where password = ? " , new String[]{password} );
        if(cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

}
