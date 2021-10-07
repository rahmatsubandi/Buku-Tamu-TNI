package com.Mabes.datatamu.activities;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Mabes.datatamu.R;
import com.Mabes.datatamu.helper.DataHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class BuatBiodata1Activity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    ExtendedFloatingActionButton fabCreate;
    EditText text1, text2, text3, text4, text5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_biodata1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Buat Biodata");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataHelper = new DataHelper(this);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);
        text4 = findViewById(R.id.editText4);
        text5 = findViewById(R.id.editText5);
        fabCreate = findViewById(R.id.fabCreate);

        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("insert into biodata_diri_khusus(no, nama, noktp, jk, tujuan) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "','" +
                        text4.getText().toString() + "','" +
                        text5.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity1.mainActivity1.RefreshList();
                finish();
            }

        });

        new AlertDialog.Builder(this)
                .setMessage("Apakah Tamu Sudah Memenuhi Syarat?")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("Belum", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        BuatBiodata1Activity.this.finish();
                    }
                })
                .setNegativeButton("Sudah", null)
                .show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
