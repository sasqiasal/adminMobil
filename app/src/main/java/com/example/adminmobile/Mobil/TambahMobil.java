package com.example.adminmobile.Mobil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.adminmobile.R;
import com.example.adminmobile.databinding.ActivityTambahDataMobilBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TambahMobil extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private EditText merkmbl, hrgsewa, jmlhkrsi;
    private ImageButton balek;
    private ProgressDialog progressDialog;
    private ImageView imageView;
    private ActivityTambahDataMobilBinding binding;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage;
    private FirebaseAuth auth;
    private  String id = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahDataMobilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        merkmbl = findViewById(R.id.tMerkMobil);
        hrgsewa= findViewById(R.id.tHargaSewa);
        jmlhkrsi = findViewById(R.id.tJumlahKursi);
        imageView = findViewById(R.id.addimage);
        Button btn = findViewById(R.id.button);
        balek = findViewById(R.id.back);
        FirebaseApp.getInstance();
        auth = FirebaseAuth.getInstance();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        progressDialog = new ProgressDialog(TambahMobil.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(TambahMobil.this, DataMobil.class);
                startActivity(inten);
            }
        });


        binding.simpan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(merkmbl.getText().length()>0 && hrgsewa.getText().length()>0 &&
                        jmlhkrsi.getText().length()>0){
                    saveDataToFirestore(merkmbl.getText().toString(), hrgsewa.getText().toString(),
                            jmlhkrsi.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Silahkan isi semua data", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            id = intent.getStringExtra("id");
            merkmbl.setText(intent.getStringExtra("namaMobil"));
            hrgsewa.setText(intent.getStringExtra("harga"));
            jmlhkrsi.setText(intent.getStringExtra("kursi"));
            Glide.with(getApplicationContext()).load(intent.getStringExtra("gambar")).into(imageView);
        }
    }
    private void saveData (String MerkMobil, String HargaSewa, String JumlahKursi, String image){
        Map<String, Object> user = new HashMap<>();
        user.put("Nama", MerkMobil);
        user.put("Harga", HargaSewa);
        user.put("Kursi", JumlahKursi);
        user.put("Gambar", image);

        progressDialog.show();
        if (id!=null){
            db.collection("Data_Mobil").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else {
            db.collection("Data_Mobil")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }


    private void selectImage() {
        final CharSequence[] items = {"Ambil Foto", "Pilih dari Galeri", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(TambahMobil.this);
        builder.setTitle (getString (R.string.app_name));
        builder.setIcon (R.mipmap.ic_launcher);
        builder.setItems (items, (dialog, item) -> {
            if (items [item].equals("Ambil Foto")) {
                Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);
            } else if (items [item].equals("Pilih dari Galeri")) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser (intent,"Select Image"),20);
            } else if (items [item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 20 && resultCode == RESULT_OK && data != null) {
            final Uri path = data.getData();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(path);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        runOnUiThread(() -> {
                            imageView.setImageBitmap(bitmap);
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        }

        if (requestCode == 10 && resultCode == RESULT_OK) {
            final Bundle extras = data.getExtras();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    runOnUiThread(() -> {
                        imageView.setImageBitmap(bitmap);
                    });
                }
            };
            thread.start();
        }
    }

    private void saveDataToFirestore(String MerekMobil, String HargaSewa, String JumlahKursi) {
        progressDialog.show();
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("Data_Mobil").child("IMG"+new Date().getTime()+".jpeg");

        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if(taskSnapshot.getMetadata()!=null){
                    if(taskSnapshot.getMetadata().getReference()!=null){
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.getResult()!=null) {
                                    saveData(MerekMobil, HargaSewa, JumlahKursi, task.getResult().toString());
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
