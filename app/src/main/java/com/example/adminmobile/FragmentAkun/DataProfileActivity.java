package com.example.adminmobile.FragmentAkun;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminmobile.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataProfileActivity extends AppCompatActivity {

    private EditText  editalamat, editnomor;
    TextView editnama,editemail;
    ImageButton balek;

    FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dataprofile);


        editnama = findViewById(R.id.labelnama);
        editalamat = findViewById(R.id.edittAlamat);
        editemail = findViewById(R.id.editEmail);
        editnomor = findViewById(R.id.editNoHp);
        balek = findViewById(R.id.back);
        auth = FirebaseAuth.getInstance();




        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference dbReff = db.collection("Adminn").document(auth.getCurrentUser().getUid());
        dbReff.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                String nama = documentSnapshot.getString("Nama");
                String email = documentSnapshot.getString("Email");
                String alamat = documentSnapshot.getString("Alamat");
                String nomor = documentSnapshot.getString("Nohp");

                Log.d("dataprofileActivity", "Nama" +nama);
                Log.d("dataprofileActivity", "Email" +email);
                Log.d("dataprofileActivity", "Alamat" +alamat);
                Log.d("dataprofileActivity", "Nohp" +nomor);

                editnama.setText(nama);
                editemail.setText(email);
                editalamat.setText(alamat);
                editnomor.setText(nomor);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {


            }
        });

        balek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(DataProfileActivity.this, AkunProfile.class);
                finish();
            }
        });





    }

}

