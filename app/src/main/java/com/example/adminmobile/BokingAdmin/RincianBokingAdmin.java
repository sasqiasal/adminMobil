package com.example.adminmobile.BokingAdmin;

import static com.example.adminmobile.BokingAdmin.DetailRiwayatBokingAdmin.formatFirestoreTimestamp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminmobile.MainActivity;
import com.example.adminmobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RincianBokingAdmin extends AppCompatActivity {

    private EditText tujuan, tglpnjm, tglkmbli, mobil, nama, total, hari;
    Button simpan;
    FirebaseAuth Auth;
    Long jumlahHari;


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

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Log.d("MainActivity", "handleOnBackPressed");
                deletketikagagal();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String bookingUid = getIntent().getStringExtra("DocumentID");

        DocumentReference reff = db.collection("Booking").document(bookingUid);

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
                DocumentReference bookingRef = FirebaseFirestore.getInstance().collection("Booking").document(bookingUid);
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
                                finish();
                            }
                        });

//
            }
        });

        DocumentReference dbReff = db.collection("Booking").document(bookingUid );
        dbReff.get().addOnSuccessListener(documentSnapshot -> {
            Timestamp TglP = documentSnapshot.getTimestamp("TanggalPinjam");
            Timestamp TglK = documentSnapshot.getTimestamp("TanggalKembali");
            String Tuju = documentSnapshot.getString("Tujuan");
            String Mob = documentSnapshot.getString("NamaMobil");
            String Nma = documentSnapshot.getString("Namapemesan");

            Log.d("dataprofile", "Tujuan" + Tuju);
            Log.d("dataprofile", "tglpinjam" + TglP);
            Log.d("dataprofile", "tglk" + TglK);
            Log.d("dataprofile", "mobil" + Mob);
            Log.d("dataprofile", "Namapemesan" + Nma);

//                Log.d("dataprofile", "total" +ttl);

            tujuan.setText(Tuju);
            tglpnjm.setText(formatFirestoreTimestamp(TglP));
            tglkmbli.setText(formatFirestoreTimestamp(TglK));
            mobil.setText(Mob);
            nama.setText(Nma);
//                total.setText(ttl);
            tujuan.setText(getIntent().getStringExtra("Tujuan"));
            tglpnjm.setText(getIntent().getStringExtra("TanggalPinjam"));
            tglkmbli.setText(getIntent().getStringExtra("TanggalKembali"));
            mobil.setText(getIntent().getStringExtra("NamaMobil"));
            nama.setText(getIntent().getStringExtra("Namapemesan"));
            total.setText(getIntent().getStringExtra("JumlahHari"));

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    private void deletketikagagal(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String bookingUid = getIntent().getStringExtra("DocumentID");

// Create a reference to the "Booking" document using the UID
        DocumentReference bookingRef = db.collection("Booking").document(bookingUid);

// Delete the document
        bookingRef.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Document successfully deleted
                        Toast.makeText(RincianBokingAdmin.this, "GAGAL TRANSAKSI", Toast.LENGTH_SHORT).show();
                        finish(); // Add any additional actions after successful deletion
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle any errors that occurred during the deletion
                        Log.e("TAG", "Error deleting document", e);
                        Toast.makeText(RincianBokingAdmin.this, "Error deleting booking", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
