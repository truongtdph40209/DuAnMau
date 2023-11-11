package com.example.ph40209_duanmau.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph40209_duanmau.database.DbHelper;
import com.example.ph40209_duanmau.model.thanhvien;
import com.example.ph40209_duanmau.model.thuthu;

import java.util.ArrayList;

public class ThuThuDAO {
    DbHelper dbHelper;



    public ThuThuDAO(Context context) {
        dbHelper = new DbHelper(context);

    }

    public ArrayList<thuthu> selectALLThuThu(){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ArrayList<thuthu> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new thuthu(cursor.getString(0), cursor.getString(1), cursor.getString(2)));

            }while (cursor.moveToNext());
        }
        return list;
    }

    public  boolean addThuThu(String matt, String hoten, String matkhau ){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matt", matt);
        contentValues.put("hoten", hoten);
        contentValues.put("matkhau", matkhau);
        long check = sqLiteDatabase.insert("THUTHU", null, contentValues);
        if (check == -1){
            return false;
        }
        return true;


    }

    public boolean checkdn(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean capNhatMatKhau(String username, String mkCu, String mkMoi){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ? ", new String[]{username,mkCu});
        if (cursor.getCount() > 0 ){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", mkMoi);
            long check =  sqLiteDatabase.update("THUTHU",contentValues, "matt = ?", new String[]{username} );
            if (check == -1){
                return false;
            }return true;
        }
        return false;
    }


}
