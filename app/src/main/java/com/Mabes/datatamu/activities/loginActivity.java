package com.Mabes.datatamu.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.Mabes.datatamu.R;
import com.Mabes.datatamu.helper.DataHelper;

public class loginActivity extends AppCompatActivity {
    //Declaration EditTexts
    EditText editTextUsername, editTextPassword;
    Button BtnLogin;
    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataHelper(this);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        BtnLogin = (Button) findViewById(R.id.BtnLogin);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                Boolean res = db.checkuser(nama, password);

                if (res == true)
                {
                    Intent homepage = new Intent(loginActivity.this, DashboardActivity.class);
                    startActivity(homepage);
                    Toast.makeText(loginActivity.this, "Log in Berhasil ", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    Toast.makeText(loginActivity.this, "Log in Gagal ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}