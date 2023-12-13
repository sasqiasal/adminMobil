package com.example.adminmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SyaratKetentuanActivity extends AppCompatActivity {
ImageButton btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syaratketentuan);
        btnback =findViewById(R.id.back);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(SyaratKetentuanActivity.this, AkunProfile.class);
                finish();
            }
        });
    }
}