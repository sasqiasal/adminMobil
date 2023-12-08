package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.adminmobile.Popup.popupedit;

public class EditDataMobil extends AppCompatActivity {
    Button btnedit;
    ImageButton back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data_mobil);
        FragmentManager fm = getSupportFragmentManager();
        btnedit = findViewById(R.id.buttonedit);
        back  = findViewById(R.id.back);
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { popupedit.newInstance("","").show(fm,"Fragment");}
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditDataMobil.this, Home.class);
                startActivity(intent);
            }
        });
    }
}