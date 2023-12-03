package com.example.adminmobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class masuk extends AppCompatActivity {
    private EditText EditEmail, EditPassword;
    private Button btnLogin;
    private TextView btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);
        mAuth = FirebaseAuth.getInstance();
        EditEmail = findViewById(R.id.editemail);
        EditPassword = findViewById(R.id.editpassword);
        btnLogin = findViewById(R.id.Login);
        btnRegister = findViewById(R.id.btnregister);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent=new Intent(masuk.this, daftar.class);
                startActivity(Intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EditEmail.getText().toString().trim();
                String password = EditPassword.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!password.isEmpty()) {
                        // Check if the email exists in the "Adminn" collection
                        db.collection("Adminn")
                                .whereEqualTo("Email", email)
                                .get()
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        if (!task.getResult().isEmpty()) {
                                            // Email exists in the "Adminn" collection, proceed with login
                                            mAuth.signInWithEmailAndPassword(email, password)
                                                    .addOnSuccessListener(authResult -> {
                                                        Toast.makeText(masuk.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(masuk.this, MainActivity.class));
                                                        finish();
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        Toast.makeText(masuk.this, "Password salah", Toast.LENGTH_SHORT).show();
                                                    });
                                        } else {
                                            // Email does not exist in the "Adminn" collection
                                            Toast.makeText(masuk.this, "Login Dengan Akun Admin", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Handle the exception if the task is not successful
                                        Toast.makeText(masuk.this, "Gagal memeriksa email", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        EditPassword.setError("Password tidak tersedia");
                    }
                } else if (email.isEmpty()) {
                    EditEmail.setError("Email tidak tersedia");
                } else {
                    EditEmail.setError("Isi email dengan benar");
                }
            }
        });




//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = EditEmail.getText().toString().trim();
//                String password = EditPassword.getText().toString().trim();
//
//
//
//
//                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email) .matches()) {
//                    if(!password.isEmpty()){
//                        mAuth.signInWithEmailAndPassword(email, password)
//                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                                    @Override
//                                    public void onSuccess(AuthResult authResult) {
//                                        Toast.makeText(masuk.this, "login berhasil", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(masuk.this, MainActivity.class));
//                                        finish();
//                                    }
//                                }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(masuk.this, "pasword salah", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                });
//                    }else{
//                        EditPassword.setError("pasword tidak tersedia");
//                    }
//                } else if (email.isEmpty()) {
//                    EditEmail.setError("email tidak tersedia");
//                }else{
//                    EditEmail.setError("isi email dengan benar");
//                }
//
//            }
//        });
    }
}
