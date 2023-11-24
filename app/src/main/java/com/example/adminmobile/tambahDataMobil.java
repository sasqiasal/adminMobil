package com.example.adminmobile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class tambahDataMobil extends AppCompatActivity {
    private Button simpan;
    private EditText MerkMobil,Harga, JmlKursi;
    private ImageView gambar;
    Uri uri;
    ProgressDialog dialog;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data_mobil);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        verifyStoragePermissions(this);
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Silahkan Menunggu");
        dialog.setCancelable(false);
        dialog.setTitle(" sedang diupload");
        dialog.setCanceledOnTouchOutside(false);
        simpan = findViewById(R.id.simpan);
        MerkMobil = findViewById(R.id.tMerkMobil);
        Harga = findViewById(R.id.tHargaSewa);
        JmlKursi = findViewById(R.id.tJumlahKursi);
        gambar = findViewById(R.id.addimage);


        simpan.setOnClickListener(view -> {
            dialog.show();
            final StorageReference reference = firebaseStorage.getReference().child("Data_Mobil")
                    .child(System.currentTimeMillis()+"");
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Mobil model = new Mobil();
                            model.setGambar(uri.toString());
                            model.setMerekmobil(MerkMobil.getText().toString());
                            model.setHarga(Harga.getText().toString());
                            model.setJmlkursi(JmlKursi.getText().toString());


                            firebaseDatabase.getReference().child("Mobil").push().setValue(model)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(tambahDataMobil.this, "berhasil upload", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(tambahDataMobil.this, "gagal upload", Toast.LENGTH_SHORT).show();
                                            dialog.dismiss();
                                        }
                                    });

                        }
                    });
                }
            });
        });
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uploadgambar();
            }
        });
    }

    private final int REQUEST_WRITE = 1;

    private final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public void verifyStoragePermissions(Activity activity) {
        int writePermission = ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );

        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_WRITE
            );
        }
    }

    private void  Uploadgambar() {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);
                    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            uri = data.getData();
            gambar.setImageURI(uri);
        }
    }
}