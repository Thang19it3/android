package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frament_hihi,new HomeFragment()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId())
                    {
                        case R.id.home_hihi:
                            fragment = new HomeFragment();
                            break;
                        case R.id.san_hihi:
                            fragment = new ProfileFragment();
                            break;
                        case R.id.news_hihi:
                            fragment = new mapFragment();
                            break;
                        case R.id.profile_hihi:
                            fragment = new ProfileFra();
                    }
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frament_hihi,fragment).commit();
                    return true;
                }
            };

}