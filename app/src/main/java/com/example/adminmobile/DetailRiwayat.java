package com.example.adminmobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailRiwayat extends AppCompatActivity {
    EditText tvjam, tvTujuan, tvNama, tvTanggal, tvHari, tvTotal, tvPenyewa, tvTKembali, tvPenjemputan, tvhp;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_detail_riwayat);
        tvjam = findViewById(R.id.jamBudal);
        tvhp = findViewById(R.id.hape);
        tvNama = findViewById(R.id.mobil);
        tvTujuan = findViewById(R.id.tujuan);
        tvTanggal = findViewById(R.id.tanggalpinjam);
        tvHari =findViewById(R.id.Totalhari);
        tvTotal = findViewById(R.id.total);
        tvPenyewa = findViewById(R.id.Namabose);
        tvTKembali = findViewById(R.id.tanggalkembali);
        tvPenjemputan = findViewById(R.id.penjemputan);
        Bundle bundle = getIntent().getExtras();
//        Toast.makeText(this, bundle.getString("uid"), Toast.LENGTH_SHORT).show();
        if (bundle != null){
            tvjam.setText(bundle.getString("JamBerangkat"));
            tvNama.setText(bundle.getString("mobil"));
            tvhp.setText(bundle.getString("NoHp"));
            tvTujuan.setText(bundle.getString("tujuan"));
            tvTotal.setText(bundle.getString("total"));
            tvTanggal.setText(bundle.getString("tanggalpinjam"));
            tvHari .setText(Integer.toString(bundle.getInt("hari")));
            tvPenyewa .setText(bundle.getString("namapenyewa"));
            tvTKembali .setText(bundle.getString("tanggalkembali"));
            tvPenjemputan .setText(bundle.getString("penjemputan"));





        }
    }

    public static String formatFirestoreTimestamp(Timestamp firestoreTimestamp) {
        // Convert Firestore timestamp to Java Date object
        Date dateObject = firestoreTimestamp.toDate();

        // Format the date to "dd-MMMM-yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return dateFormat.format(dateObject);
    }
}