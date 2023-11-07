package com.example.adminmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class spesifikasiMobil extends AppCompatActivity {
    ImageButton back;
    Button btnedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spesifikasi_mobil);
        back  = findViewById(R.id.back);
        btnedit = findViewById(R.id.buttonedit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(spesifikasiMobil.this, home.class);
                startActivity(intent);
            }
        });

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(spesifikasiMobil.this, editDataMobil.class);
                startActivity(intent);
            }
        });
    }
}