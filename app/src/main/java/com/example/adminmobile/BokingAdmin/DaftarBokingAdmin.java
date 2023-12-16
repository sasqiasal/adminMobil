package com.example.adminmobile.BokingAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminmobile.Adapter.AdapterRiwayatBokingAdmin;
import com.example.adminmobile.MainActivity;
import com.example.adminmobile.Model.RiwayatBokingAdminModel;
import com.example.adminmobile.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DaftarBokingAdmin extends AppCompatActivity {
ImageButton back;
Button tambahjadwal;
     RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarbokingadmin);

        recyclerView = findViewById(R.id.rvRiwayat);
        String firebaseUID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = FirebaseFirestore.getInstance().collection("Boking_Admin");


        FirestoreRecyclerOptions<RiwayatBokingAdminModel> option = new FirestoreRecyclerOptions.Builder<RiwayatBokingAdminModel>()
                .setQuery(query, RiwayatBokingAdminModel.class)
                .build();


        AdapterRiwayatBokingAdmin adapterRiwayat = new AdapterRiwayatBokingAdmin(option);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterRiwayat);
                adapterRiwayat.startListening();

        back  = findViewById(R.id.back);
        tambahjadwal = findViewById(R.id.tambahjadwal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaftarBokingAdmin.this, MainActivity.class);
                startActivity(intent);
                finish();
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