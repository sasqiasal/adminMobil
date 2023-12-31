package com.example.adminmobile.BokingAdmin;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adminmobile.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailRiwayatBokingAdmin extends AppCompatActivity {
    EditText  tvjam, tvhp,tvpenjemputan, tvTujuan, tvNama, tvmobil, tvTanggalpinjam,tvTanggalkembali, tvHari, tvTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        setContentView(R.layout.deatil_riwayatbokingadmin);
        tvjam = findViewById(R.id.jamBerangkat);
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
            tvjam.setText(bundle.getString("jam"));
            tvhp.setText(bundle.getString("No"));
            tvTujuan.setText(bundle.getString("tujuan"));
            tvTotal.setText(bundle.getString("total"));
            tvHari .setText(bundle.getString("hari"));
            tvNama .setText(bundle.getString("namapemesan"));
            tvpenjemputan .setText(bundle.getString("penjemputan"));



            db.collection("Booking").document(bundle.getString("uid")).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    tvjam.setText(documentSnapshot.get("JamBerangkat").toString());
                    tvhp.setText(documentSnapshot.get("NoHp").toString());
                    tvHari.setText(documentSnapshot.get("JumlahHari").toString());
                    tvTanggalpinjam.setText(formatFirestoreTimestamp(documentSnapshot.getTimestamp("TanggalPinjam")));
                    tvTujuan.setText(documentSnapshot.get("Tujuan").toString());
                    tvpenjemputan.setText(documentSnapshot.get("Penjemputan").toString());
                    tvTanggalkembali.setText(formatFirestoreTimestamp(documentSnapshot.getTimestamp("TanggalKembali")));
                    tvNama.setText(documentSnapshot.get("NamaPenyewa").toString());
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
    public static String formatFirestoreTimestamp(Timestamp firestoreTimestamp) {
        // Convert Firestore timestamp to Java Date object
        Date dateObject = firestoreTimestamp.toDate();

        // Format the date to "dd-MMMM-yyyy"
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
        return dateFormat.format(dateObject);
    }
}