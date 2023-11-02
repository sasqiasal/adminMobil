package com.example.adminmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class dataMobil extends AppCompatActivity {
    Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mobil);
        FragmentManager fm = getSupportFragmentManager();
        btnCheck = findViewById(R.id.btnTambah);

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popuphapus.newInstance("","").show(fm,"Fragment");
            }
        });

    }
}