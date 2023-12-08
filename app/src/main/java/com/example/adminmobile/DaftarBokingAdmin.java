package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarBokingAdmin extends AppCompatActivity {
ImageButton back;
Button tambahjadwal;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarbokingadmin);
        back  = findViewById(R.id.back);
        tambahjadwal = findViewById(R.id.tambahjadwal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBokingAdmin.this, Home.class);
                startActivity(intent);
            }

        });
        tambahjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarBokingAdmin.this, BokingAdmin.class);
                startActivity(intent);
            }
        });
    }
}