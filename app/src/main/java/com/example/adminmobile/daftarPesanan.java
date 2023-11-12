package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class daftarPesanan extends AppCompatActivity {
ImageButton back;
ImageButton tambahjadwal;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);
        back  = findViewById(R.id.back);
        tambahjadwal = findViewById(R.id.tambahjadwal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(daftarPesanan.this, home.class);
                startActivity(intent);
            }

        });
        tambahjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(daftarPesanan.this, tambahjadwal.class);
                startActivity(intent);
            }
        });
    }
}