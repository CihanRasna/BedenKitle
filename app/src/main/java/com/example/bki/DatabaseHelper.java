package com.example.bki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bki";
    private static final String TABLO_KISILER = "kisiler";
    private static final String ROW_ID = "id";
    private static final String ROW_WEIGHT = "kilo";
    private static final String ROW_HEIGHT = "boy";
    private static final String ROW_INDEX = "indeks";
    private static final String ROW_SONUC = "sonuc";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //TABLO DÜZENİNİN OLUŞTURULMASI
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLO_KISILER + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_HEIGHT + " TEXT NOT NULL, "
                + ROW_WEIGHT + " TEXT NOT NULL, "
                + ROW_SONUC + " TEXT NOT NULL, "
                + ROW_INDEX + " TEXT NOT NULL)");

    }

    @Override
    //EĞER Kİ HALİ HAZIRDA TABLO VARSA YENİSİ İLE GÜNCELLENMESİ SAĞLANIR
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_KISILER);
        onCreate(db);
    }

    public void addData(String kilo, String boy, String index, String sonuc) {
        SQLiteDatabase db = this.getWritableDatabase();
        //GİRİLEN VERİLERİN DATABASE'E KAYDEDİLMESİ İÇİN GEREKLİ İŞLEMLER
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROW_WEIGHT, kilo);
            contentValues.put(ROW_HEIGHT, boy);
            contentValues.put(ROW_INDEX, index);
            contentValues.put(ROW_SONUC, sonuc);
            db.insert(TABLO_KISILER, null, contentValues);
        } catch (Exception e) {
        }
        db.close();
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLO_KISILER;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}