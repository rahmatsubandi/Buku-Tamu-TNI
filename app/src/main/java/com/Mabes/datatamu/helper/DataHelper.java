package com.Mabes.datatamu.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "biodatadiri.db";
    public static final String TABLE__NAME = "biodata_anggota";
    public static final String COL_1 = "no";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "password";

    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table biodata(no integer primary key, nama text null, noktp text null, jk text null, tujuan text null);";
        String sql1 = "create table biodata_anggota(no integer primary key, nama text null, nrp text null, jk text null, bagian text null, password text null);";
        String sql2 = "create table biodata_diri_khusus(no integer primary key, nama text null, noktp text null, jk text null, tujuan text null);";
        Log.d("Data", "onCreate: " + sql);
        Log.d("Data", "onCreate: " + sql1);
        Log.d("Data", "onCreate: " + sql2);
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        sql = "INSERT INTO biodata (no, nama, noktp, jk, tujuan) VALUES ('001', 'Reza', '23283732', 'Laki-laki','Jonggol');";
        sql1 = "INSERT INTO biodata_anggota (no, nama, nrp, jk, bagian, password) VALUES ('001', 'Reza', '23283732', 'Laki-laki','Jonggol', 'pw123');";
        sql2 = "INSERT INTO biodata_diri_khusus (no, nama, noktp, jk, tujuan) VALUES ('001', 'Reza', '23283732', 'Laki-laki','Jonggol');";
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public boolean checkuser(String nama, String password) {
        String[] columns = { COL_1} ;
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { nama, password };
        Cursor cursor = db.query(TABLE__NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();


        if (count>0)
            return true;
        else
            return false;
    }

}
