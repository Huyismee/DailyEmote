package com.huyismeee.dailyemote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.huyismeee.dailyemote.database.RecordDatabase;
import com.huyismeee.dailyemote.databinding.ActivityMainBinding;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private int NAV_HOME = R.id.navigation_home;
    private int NAV_TIMER = R.id.navigation_timer;
    FragmentContainerView fragmentContainerView;
    ActivityMainBinding binding;

    private void bindingView(){
        fragmentContainerView = findViewById(R.id.nav_host_fragment_activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

    }

    private void bindingAction(){
        binding.navView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.navigation_home){
                replaceFragment(new HomeFragment());
            } else if(item.getItemId() == R.id.navigation_timer){
                replaceFragment(new TimerFragment());
            }else if(item.getItemId() == R.id.navigation_note_list){
                replaceFragment(new fragment_record());
            }
            return true;
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bindingView();
        bindingAction();

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_activity_main, fragment);
        fragmentTransaction.commit();
    }
}

