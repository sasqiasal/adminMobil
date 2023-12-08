package com.example.adminmobile;

import android.app.ProgressDialog;
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

import com.example.adminmobile.Adapter.AdapterMobil;
import com.example.adminmobile.Model.Mobil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class DataMobil extends AppCompatActivity {
    ImageButton back;
    Button tmbh;
    RecyclerView recyclerView;
    ArrayList<Mobil> recyclelist;
    FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Mobil> list = new ArrayList<>();
    private ProgressDialog progressDialog;

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


        Query query = FirebaseFirestore.getInstance().collection("Data_Mobil");

        FirestoreRecyclerOptions<Mobil> option = new FirestoreRecyclerOptions.Builder<Mobil>()
                .setQuery(query, Mobil.class)
                .build();


        AdapterMobil recyclerAdapter = new AdapterMobil(option, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.startListening();

        firebaseDatabase.getReference().child("Data_Mobil").addListenerForSingleValueEvent(new ValueEventListener() {
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
                Toast.makeText(DataMobil.this, "gagal" + error, Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataMobil.this, Home.class);
                startActivity(intent);
            }
        });

        tmbh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DataMobil.this, TambahMobil.class);
                startActivity(intent);
            }
        });

        }
    }
