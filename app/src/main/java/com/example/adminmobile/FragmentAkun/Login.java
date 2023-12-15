package com.example.adminmobile.FragmentAkun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminmobile.MainActivity;
import com.example.adminmobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    private EditText EditEmail, EditPassword;
    private Button btnLogin;
    private TextView btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        EditEmail = findViewById(R.id.editemail);
        EditPassword = findViewById(R.id.editpassword);
        btnLogin = findViewById(R.id.Login);
        btnRegister = findViewById(R.id.btnregister);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(Login.this, Register.class);
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
                                                        Toast.makeText(Login.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Login.this, MainActivity.class));
                                                        finish();
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        Toast.makeText(Login.this, "Password salah", Toast.LENGTH_SHORT).show();
                                                    });
                                        } else {
                                            // Email does not exist in the "Adminn" collection
                                            Toast.makeText(Login.this, "Login Dengan Akun Admin", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // Handle the exception if the task is not successful
                                        Toast.makeText(Login.this, "Gagal memeriksa email", Toast.LENGTH_SHORT).show();
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
    }
        protected void onStart () {
            super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }


        }
    }

