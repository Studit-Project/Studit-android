package com.example.studit.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.studit.R;
import com.example.studit.chat.FragChat;
import com.example.studit.home.FragHome;
import com.example.studit.profile.FragProfile;
import com.example.studit.study.FragStudy;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Fragment frag_home, frag_study, frag_chat, frag_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag_home = new FragHome();
        frag_study = new FragStudy();
        frag_chat = new FragChat();
        frag_profile = new FragProfile();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_home).commitAllowingStateLoss();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_home).commit();
                    return true;
                case R.id.study:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_study).commit();
                    return true;
                case R.id.chat:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_chat).commit();
                    return true;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_profile).commit();
                    return true;
            }
            return true;
        });
    }
}