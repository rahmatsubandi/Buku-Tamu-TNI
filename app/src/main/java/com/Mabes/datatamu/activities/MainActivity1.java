package com.Mabes.datatamu.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Mabes.datatamu.R;
import com.Mabes.datatamu.helper.DataHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity1 extends AppCompatActivity {

    String[] daftar;
    ListView lvData;
    protected Cursor cursor;
    DataHelper dataHelper;
    public static MainActivity1 mainActivity1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Data Tamu Mabes TNI");
        setSupportActionBar(toolbar);

        ExtendedFloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity1.this, BuatBiodata1Activity.class);
                startActivity(intent);
            }
        });

        mainActivity1 = this;
        dataHelper = new DataHelper(this);
        RefreshList();

    }

    public void RefreshList() {
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM biodata_diri_khusus", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);
        }

        lvData = findViewById(R.id.lvData);
        lvData.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_checked, daftar));
        lvData.setSelected(true);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"SS Bukti Tamu", "Update Biodata", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity1.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), LihatBiodata1Activity.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), UpdateBiodata1Activity.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
                                sqLiteDatabase.execSQL("delete from biodata_diri_khusus where nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter) lvData.getAdapter()).notifyDataSetInvalidated();
    }

}
