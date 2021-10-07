package com.Mabes.datatamu.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.Mabes.datatamu.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class DashboardActivity extends AppCompatActivity {
    RelativeLayout button1;
    RelativeLayout button2;
    RelativeLayout button3;
    RelativeLayout button4;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, MainActivity1.class);
                startActivity(i);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, Tata_Tertib.class);
                startActivity(i);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this, NewsActivity.class);
                startActivity(i);
            }
        });
        ExtendedFloatingActionButton fabAdd1 = findViewById(R.id.fabadd1);
        fabAdd1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(DashboardActivity.this, BiodataAnggotaActivity.class);
                startActivity(intent);
            }
        });
    }
}