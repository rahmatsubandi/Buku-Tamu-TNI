package com.Mabes.datatamu.activities;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Mabes.datatamu.R;
import com.Mabes.datatamu.helper.DataHelper;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class BiodataAnggotaActivity extends AppCompatActivity {

    String[] daftar;
    ListView lvData;
    protected Cursor cursor;
    DataHelper dataHelper;
    public static com.Mabes.datatamu.activities.BiodataAnggotaActivity biodataAnggotaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata_anggota);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Data Anggota Piket/Jaga Mabes TNI");
        setSupportActionBar(toolbar);

        // SEARCH IN HERE
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        SearchView searchView = (SearchView) findViewById(R.id.action_search);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return false;
            }
        });


        ExtendedFloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(com.Mabes.datatamu.activities.BiodataAnggotaActivity.this, TambahAnggotaActivity.class);
                startActivity(intent);
            }
        });


        biodataAnggotaActivity = this;
        dataHelper = new DataHelper(this);
        RefreshList();

    }

    // FOR SEARCH
    private void getData(String newText) {
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata_anggota WHERE nama LIKE '%" + newText + "%'", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        ListView lvData = findViewById(R.id.lvData);
        lvData.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, daftar));
        lvData.setSelected(true);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"SS Bukti Anggota", "Update Anggota", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(com.Mabes.datatamu.activities.BiodataAnggotaActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), lihatanggota.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), Update_AnggotaActivity.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
                                sqLiteDatabase.execSQL("delete from biodata_anggota where nama = '" + selection + "'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    public void RefreshList() {
        SQLiteDatabase sqLiteDatabase = dataHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM biodata_anggota", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1);
        }

        lvData = findViewById(R.id.lvData);
        lvData.setAdapter(new ArrayAdapter(this, android.R.layout.simple_selectable_list_item, daftar));
        lvData.setSelected(true);
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2]; //.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"SS Bukti Anggota", "Update Anggota", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(com.Mabes.datatamu.activities.BiodataAnggotaActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), lihatanggota.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in = new Intent(getApplicationContext(), Update_AnggotaActivity.class);
                                in.putExtra("nama", selection);
                                startActivity(in);
                                break;
                            case 2:
                                SQLiteDatabase sqLiteDatabase = dataHelper.getWritableDatabase();
                                sqLiteDatabase.execSQL("delete from biodata_anggota where nama = '" + selection + "'");
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
