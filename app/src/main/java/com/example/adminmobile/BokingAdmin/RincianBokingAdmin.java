package com.example.adminmobile.BokingAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminmobile.MainActivity;
import com.example.adminmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RincianBokingAdmin extends AppCompatActivity {

    private EditText tujuan, tglpnjm, tglkmbli, mobil, nama, total, hari;
    Button simpan;
    FirebaseAuth Auth;
    Long jumlahHari;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincianbokingadmin);
        simpan = findViewById(R.id.btnsimpan);
        tujuan = findViewById(R.id.rTujuan);
        tglpnjm = findViewById(R.id.rtglpinjam);
        tglkmbli = findViewById(R.id.rTglKembali);
        mobil = findViewById(R.id.rJensiMobil);
        nama = findViewById(R.id.txtNamaPenyewa);
        total = findViewById(R.id.rTotal);




        Auth = FirebaseAuth.getInstance();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String bookingUid = getIntent().getStringExtra("DocumentID");

        DocumentReference reff = db.collection("Boking_Admin").document(bookingUid);

        reff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                jumlahHari = documentSnapshot.getLong("JumlahHari");
                db.collection("Data_Mobil").document(getIntent().getStringExtra("IDMobil")).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        mobil.setText( task.getResult().getString("Nama"));
                        Long harga = Long.valueOf(task.getResult().getString("Harga"));

                        if (jumlahHari == 1){
                            Long totalHarga = harga;
                            total.setText(totalHarga.toString());
                        }else {
                            Long totalHarga = harga * jumlahHari;
                            total.setText(totalHarga.toString());
                        }




                    }
                });


            }

        });



        simpan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DocumentReference bookingRef = FirebaseFirestore.getInstance().collection("Boking_Admin").document(bookingUid);
                String jmlh = total.getText().toString().trim();

                bookingRef
                        .update("Total", jmlh)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Tambahkan tindakan yang ingin Anda lakukan setelah berhasil menyimpan total
                                // Contoh: Menampilkan pesan sukses atau pindah ke halaman lain
                                Toast.makeText(RincianBokingAdmin.this, "Total berhasil disimpan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RincianBokingAdmin.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });

//                String jmlh = total.getText().toString().trim();

//                clickPay();
//                // Membuat referensi ke dokumen "Booking" menggunakan UID
//                assert bookingUid != null;
//                DocumentReference bookingRef = FirebaseFirestore.getInstance().collection("Boking_Admin").document(bookingUid);
//
//
//                // Mengupdate field "Total" pada dokumen "Booking" dengan nilai yang diambil dari EditText
//                bookingRef
//                        .update("Total", jmlh)
//                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                // Tambahkan tindakan yang ingin Anda lakukan setelah berhasil menyimpan total
//                                // Contoh: Menampilkan pesan sukses atau pindah ke halaman lain
//
//                                Toast.makeText(RincianBokingAdmin.this, "Total berhasil disimpan", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                // Tambahkan tindakan yang ingin Anda lakukan jika penyimpanan gagal
//                                Log.e("TAG", "Gagal menyimpan total", e);
//                                Toast.makeText(RincianBokingAdmin.this, "Gagal menyimpan total", Toast.LENGTH_SHORT).show();
//                            }
//                        });
            }
        });

        DocumentReference dbReff = db.collection("Boking_Admin").document(bookingUid );
        dbReff.get().addOnSuccessListener(documentSnapshot -> {

            String Tuju = documentSnapshot.getString("Tujuan");
            String TglP = documentSnapshot.get("TanggalPinjam").toString();
            String TglK = documentSnapshot.get("TanggalKembali").toString();
            String Mob = documentSnapshot.getString("NamaMobil");
            String Nma = documentSnapshot.getString("Namapemesan");

            Log.d("dataprofile", "Tujuan" + Tuju);
            Log.d("dataprofile", "tglpinjam" + TglP);
            Log.d("dataprofile", "tglk" + TglK);
            Log.d("dataprofile", "mobil" + Mob);
            Log.d("dataprofile", "Namapemesan" + Nma);

//                Log.d("dataprofile", "total" +ttl);

            tujuan.setText(Tuju);
            tglpnjm.setText(TglP);
//            tglkmbli.setText(TglK);
            mobil.setText(Mob);
            nama.setText(Nma);
//                total.setText(ttl);
            tujuan.setText(getIntent().getStringExtra("Tujuan"));
            tglpnjm.setText(getIntent().getStringExtra("TanggalPinjam"));
            tglkmbli.setText(getIntent().getStringExtra("TanggalKembali"));
            mobil.setText(getIntent().getStringExtra("NamaMobil"));
            nama.setText(getIntent().getStringExtra("Namapemesan"));
//                total.setText(getIntent().getStringExtra("JumlahHari"));

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
}
