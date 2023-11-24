package com.example.adminmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class dataMobil extends AppCompatActivity {
    ImageButton back;
    Button tmbh;
    RecyclerView recyclerView;
    ArrayList<Mobil> recyclelist;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mobil);
        FragmentManager fragmentManager = getSupportFragmentManager();
        back = findViewById(R.id.back);
        tmbh = findViewById(R.id.btnTambah);


            recyclerView = findViewById(R.id.list_item);
            recyclelist = new ArrayList<>();

            firebaseDatabase = FirebaseDatabase.getInstance();
            adapterMobil recyclerAdapter = new adapterMobil(recyclelist, getApplicationContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(recyclerAdapter);

            firebaseDatabase.getReference().child("Mobil").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Mobil model = dataSnapshot.getValue(Mobil.class);
                        recyclelist.add(model);
                    }
                    recyclerAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(dataMobil.this, "gagal" + error, Toast.LENGTH_SHORT).show();
                }
            });
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