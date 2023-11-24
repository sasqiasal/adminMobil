package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;
public class ListMobil extends AppCompatActivity {

    TextView Merk, Detail;
    ImageView Mobil;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listmobil);
        Merk = findViewById(R.id.merek);
        Detail=findViewById(R.id.detail);

        Mobil = findViewById(R.id.mobil);

        Picasso.get().load(getIntent().getStringExtra("Mobil"))
                .placeholder(R.drawable.addimage)
                .into(Mobil);
        Merk.setText(getIntent().getStringExtra("Merk"));
        Detail.setText(getIntent().getStringExtra("Detail"));
    }
}