package com.example.adminmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class dataMobil extends AppCompatActivity {
    ImageButton back;
    Button tmbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mobil);
        FragmentManager fm = getSupportFragmentManager();
        back = findViewById(R.id.back);
        tmbh = findViewById(R.id.btnTambah);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dataMobil.this, home.class);
                startActivity(intent);
            }
        });

        tmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dataMobil.this, tambahDataMobil.class);
                startActivity(intent);
            }
        });



    }
}