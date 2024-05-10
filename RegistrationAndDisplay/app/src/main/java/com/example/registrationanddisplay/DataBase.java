package com.example.registrationanddisplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table if not exists userRegistration(username text NOT NULL UNIQUE,dateOfBirth text NOT NULL,email text NOT NULL UNIQUE)";
        sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void register (String username,String dateOfBirth,String email){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("dateOfBirth",dateOfBirth);
        cv.put("email",email);

        SQLiteDatabase db = getWritableDatabase();
        db.insert("userRegistration",null,cv);
        db.close();
    }

    public  int checkUsername(String username){
        int result = 0;
        String str[]=new String[1];
        str[0]=username;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select username from userRegistration where username=? ",str);
        if (c.moveToFirst()){
            result = 1;
        }
        return  result;
    }

    public  int checkEmail(String email){
        int result = 0;
        String str[]=new String[1];
        str[0]=email;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select email from userRegistration where email=? ",str);
        if (c.moveToFirst()){
            result = 1;
        }
        return  result;
    }


}
