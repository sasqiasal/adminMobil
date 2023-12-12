package com.example.adminmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.Adapter.AdapterRiwayatBokingAdmin;
import com.example.adminmobile.Model.RiwayatBokingAdminModel;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DaftarBokingAdmin extends AppCompatActivity {
ImageButton back;
Button tambahjadwal;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarbokingadmin);

        recyclerView = findViewById(R.id.viewdaftarpesanan);
        String firebaseUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseFirestore.getInstance().collection("Boking_Admin");

        FirestoreRecyclerOptions<RiwayatBokingAdminModel> option = new FirestoreRecyclerOptions.Builder<RiwayatBokingAdminModel>()
                .setQuery(query, RiwayatBokingAdminModel.class)
                .build();

        AdapterRiwayatBokingAdmin adapterRiwayat = new AdapterRiwayatBokingAdmin(option, this);
        recyclerView.setAdapter(adapterRiwayat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));;
        adapterRiwayat.startListening();

        back  = findViewById(R.id.back);
        tambahjadwal = findViewById(R.id.tambahjadwal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBokingAdmin.this, Home.class);
                startActivity(intent);
            }

        });
        tambahjadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarBokingAdmin.this, BokingAdmin.class);
                startActivity(intent);
            }
        });
    }
}