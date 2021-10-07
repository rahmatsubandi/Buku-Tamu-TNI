package com.Mabes.datatamu.activities;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Mabes.datatamu.R;
import com.Mabes.datatamu.helper.DataHelper;

public class LihatBiodata1Activity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dataHelper;
    TextView text1, text2, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata1);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lihat Biodata");
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataHelper = new DataHelper(this);
        text1 = findViewById(R.id.textView1);
        text2 = findViewById(R.id.textView2);
        text3 = findViewById(R.id.textView3);
        text4 = findViewById(R.id.textView4);
        text5 = findViewById(R.id.textView5);
        SQLiteDatabase db = dataHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata_diri_khusus WHERE nama" +
                " = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0));
            text2.setText(cursor.getString(1));
            text3.setText(cursor.getString(2));
            text4.setText(cursor.getString(3));
            text5.setText(cursor.getString(4));
        }
        new AlertDialog.Builder(this)
                .setMessage("Silahkan Screen Shoot Bukti Verfikasi Tamu")
                .setIcon(R.mipmap.ic_launcher)
                .setCancelable(false)
                .setPositiveButton("", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LihatBiodata1Activity.this.finish();
                    }
                })
                .setNegativeButton("OK!", null)
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
