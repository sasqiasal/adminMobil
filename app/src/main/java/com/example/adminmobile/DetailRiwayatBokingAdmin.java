package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailRiwayatBokingAdmin extends AppCompatActivity {
    EditText  tvhp,tvpenjemputan, tvTujuan, tvNama, tvmobil, tvTanggalpinjam,tvTanggalkembali, tvHari, tvTotal;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.deatil_riwayatbokingadmin);
        tvmobil = findViewById(R.id.mobil);
        tvNama = findViewById(R.id.Namabose);
        tvhp = findViewById(R.id.hape);
        tvpenjemputan =findViewById(R.id.penjemputan);
        tvTujuan = findViewById(R.id.tujuan);
        tvTanggalpinjam = findViewById(R.id.tanggalpinjam);
        tvTanggalkembali = findViewById(R.id.tanggalkembali);
        tvHari =findViewById(R.id.Totalhari);
        tvTotal = findViewById(R.id.total);
        Bundle bundle = getIntent().getExtras();
//        Toast.makeText(this, bundle.getString("uid"), Toast.LENGTH_SHORT).show();
        if (bundle != null){
            
            tvhp.setText(bundle.getString("NoHp"));
            tvTujuan.setText(bundle.getString("tujuan"));
            tvTotal.setText(bundle.getString("total"));
            tvTanggalpinjam.setText(bundle.getString("tanggalpinjam"));
            tvHari .setText(Integer.toString(bundle.getInt("hari")));
            tvNama .setText(bundle.getString("namapenyewa"));
            tvTanggalkembali .setText(bundle.getString("tanggalkembali"));
            tvpenjemputan .setText(bundle.getString("penjemputan"));



            db.collection("Boking_Admin").document(bundle.getString("uid")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    tvNama.setText(documentSnapshot.get("Namapemesan").toString());
                    tvpenjemputan.setText(documentSnapshot.get("Penjemputan").toString());
                    tvHari.setText(documentSnapshot.get("JumlahHari").toString());
                    tvTanggalpinjam.setText(documentSnapshot.get("TanggalPinjam").toString());
                    tvTanggalkembali.setText(documentSnapshot.get("TanggalKembali").toString());
                    tvTujuan.setText(documentSnapshot.get("Tujuan").toString());
                    tvTotal.setText(documentSnapshot.get("Total").toString());

                    db.collection("Data_Mobil").document(documentSnapshot.get("IDMobil").toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            tvmobil.setText(documentSnapshot.get("Nama").toString());
                        }
                    });

                }
            });


        }
    }
}