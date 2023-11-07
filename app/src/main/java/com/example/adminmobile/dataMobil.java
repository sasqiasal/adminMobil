package com.example.adminmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class dataMobil extends AppCompatActivity {
    Button btnCheck;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mobil);
        FragmentManager fm = getSupportFragmentManager();
        btnCheck = findViewById(R.id.btnTambah);
        back = findViewById(R.id.back);
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popuptambah.newInstance("","").show(fm,"Fragment");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dataMobil.this, home.class);
                startActivity(intent);
            }
        });

    }
}