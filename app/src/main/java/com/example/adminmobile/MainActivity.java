package com.example.adminmobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.adminmobile.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);

        frameLayout = findViewById(R.id.konten);

        MainActivity mainActivity = new MainActivity();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.konten, new home());
        transaction.commit();

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_NavView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu1) {
                Log.d("menu1", "onNavigationItemSelected: yes");
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.konten, new home());
                transaction1.commit();
                return true;
            } else if (item.getItemId() == R.id.menu2) {
                MainActivity mainActivity1 = new MainActivity();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.konten, new Riwayat());
                transaction1.commit();
            } else if (item.getItemId() == R.id.menu3) {
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction transaction1 = fragmentManager1.beginTransaction();
                transaction1.replace(R.id.konten, new akunProfile());
                transaction1.commit();
            }
            return true;
        });
    }
}