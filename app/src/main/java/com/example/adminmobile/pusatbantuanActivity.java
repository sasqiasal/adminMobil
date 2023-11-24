package com.example.adminmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class pusatbantuanActivity extends AppCompatActivity {
ImageButton btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusatbantuan);
        btnback = findViewById(R.id.back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pusatbantuanActivity.this, akunProfile.class);
                startActivity(intent);
            }
        });
    }
}