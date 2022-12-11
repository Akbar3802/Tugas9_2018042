package com.example.tugas6_alarmmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBBuku extends SQLiteOpenHelper {
    public static final String DBNAME="buku.db";
    public static final String TABLENAME="daftarbuku";
    public static final int VER=1;
    public DBBuku(@Nullable Context context) {
        super(context, DBNAME, null, VER);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+TABLENAME+"(id integer primary key, name TEXT, star TEXT, price TEXT, avatar blob)";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists "+TABLENAME+"";
        db.execSQL(query);
        onCreate(db);
    }
}