package com.example.kospolijejember;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.kospolijejember.fragment.AboutUsFragment;
import com.example.kospolijejember.fragment.CheckoutFragment;
import com.example.kospolijejember.fragment.HomeFragment;
import com.example.kospolijejember.fragment.ProfileFragment;
import com.example.kospolijejember.fragment.RiwayatFragment;
import com.example.kospolijejember.session.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BerandaActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        sessionManager = new SessionManager(this);
        sessionManager.checkLoginUser();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new HomeFragment()).commit();

    }

    private void moveToLogin() {
        Intent intent = new Intent(BerandaActivity.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){

                        case R.id.RiwayatFragment:
                            selectedFragment = new RiwayatFragment();
                            break;
                        case R.id.AboutUsFragment:
                            selectedFragment = new AboutUsFragment();
                            break;
                        case R.id.HomeFragment:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.ProfileFragment:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.CheckoutFragment:
                            selectedFragment = new CheckoutFragment();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment,selectedFragment).commit();
                    return true;
                }
            };
}