package com.example.adminmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class daftar extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    TextView  login;
    Button daftar;
    EditText nama, alamat, hp, email, password;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        daftar = findViewById(R.id.btndaftar);
        email = findViewById(R.id.isiemail);
        hp = findViewById(R.id.isinomor);
        password= findViewById(R.id.isipw);
        nama = findViewById(R.id.isinama);
        alamat = findViewById(R.id.isialamat);
        login = findViewById(R.id.tulisanlogin);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent=new Intent(daftar.this, masuk.class);
                startActivity(Intent);
                finish();
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Hp = hp.getText().toString().trim();
                String Pw = password.getText().toString().trim();
                String Nama = nama.getText().toString().trim();
                String Alamat = alamat.getText().toString().trim();

                if (Email.isEmpty()) {
                    email.setError("email tidak boleh kosong");
                } else if (Pw.isEmpty()) {
                    password.setError("pasword tidak boleh kosong");
                } else {

                    mAuth.createUserWithEmailAndPassword(Email, Pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Map<String, Object> user = new HashMap<>();
                                user.put("Email", Email);
                                user.put("Nohp", Hp);
                                user.put("Nama", Nama);
                                user.put("Alamat", Alamat);

                                DocumentReference dbReff = db.collection("Adminn").document(mAuth.getUid());

                                dbReff.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                                Toast.makeText(daftar.this, "registrasi sukses", Toast.LENGTH_SHORT).show();
                                email.setText("");
                                hp.setText("");
                                password.setText("");
                                nama.setText("");
                                alamat.setText("");
                                if (task.isSuccessful()) {
                                    Intent Intent = new Intent(daftar.this, masuk.class);
                                    startActivity(Intent);

                                }


                            } else {
                                Toast.makeText(daftar.this, "registergagal" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                            }
                        }
                    });


                }
            }



        });



    }
}