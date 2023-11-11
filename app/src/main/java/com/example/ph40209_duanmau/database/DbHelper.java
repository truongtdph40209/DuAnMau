package com.example.ph40209_duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "ThuVienPhuongNam", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String dbThuThu = "CREATE TABLE THUTHU(matt TEXT PRIMARY KEY, hoten TEXT, matkhau TEXT)";
        db.execSQL(dbThuThu);

        String dbThanhVien = "CREATE TABLE THANHVIEN(matv INTEGER PRIMARY KEY AUTOINCREMENT, hoten TEXT, namsinh TEXT)";
        db.execSQL(dbThanhVien);

        String dbLoaiSach = "CREATE TABLE LOAISACH(maloai INTEGER PRIMARY KEY AUTOINCREMENT, tenloai TEXT)";
        db.execSQL(dbLoaiSach);

        String dbSach = "CREATE TABLE SACH(masach INTEGER PRIMARY KEY AUTOINCREMENT, tensach TEXT, giathue INTEGER, maloai INTERGER REFERENCES LOAISACH(maloai))";
        db.execSQL(dbSach);

        String dbPhieuMuon = "CREATE TABLE PHIEUMUON(mapm INTEGER PRIMARY KEY AUTOINCREMENT, matv INTEGER REFERENCES THANHVIEN(matv), matt TEXT REFERENCES THUTHU(matt), masach INTEGER REFERENCES SACH(masach), ngay TEXT, trasach INTEGER, tienthue INTEGER)";
        db.execSQL(dbPhieuMuon);

        db.execSQL("INSERT INTO LOAISACH VALUES (1, 'Tiểu thuyết'),(2,'Tình cảm'),(3, 'Giáo khoa')");
        db.execSQL("INSERT INTO SACH VALUES (1, 'Hãy đợi đấy', 2500, 1), (2, 'Thằng cuội', 1000, 1), (3, 'Lập trình Android', 2000, 3)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu1','Trịnh Đình Trường','123456'),('thuthu2','Trịnh Đình Sơn ','654321')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1,'Trịnh Đình Trường','2004'),(2,'Trịnh Đình Sơn','2001')");
        //trả sách: 1: đã trả - 0: chưa trả
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1,1,'thuthu1', 1, '21/09/2023', 1, 2500),(2,1,'thuthu1', 3, '29/09/2023', 0, 2000),(3,2,'thuthu2', 1, '13/10/2023', 1, 2000)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion) {
            db.execSQL("drop table if exists THUTHU");
            db.execSQL("drop table if exists THANHVIEN");
            db.execSQL("drop table if exists LOAISACH");
            db.execSQL("drop table if exists SACH");
            db.execSQL("drop table if exists PHIEUMUON");
            onCreate(db);
        }
    }
}
