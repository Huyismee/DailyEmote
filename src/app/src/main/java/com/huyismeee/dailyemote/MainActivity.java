package com.huyismeee.dailyemote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huyismeee.dailyemote.databinding.ActivityMainBinding;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private int NAV_HOME = R.id.navigation_home;
    private int NAV_TIMER = R.id.navigation_timer;

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        binding.navView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.navigation_home){
                replaceFragment(new HomeFragment());
            } else if(item.getItemId() == R.id.navigation_timer){
                replaceFragment(new TimerFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        fragmentTransaction.commit();
    }
}

