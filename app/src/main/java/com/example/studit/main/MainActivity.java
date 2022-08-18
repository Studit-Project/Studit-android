package com.example.studit.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studit.R;
import com.example.studit.chat.FragChat;

import com.example.studit.home.FragHome;
import com.example.studit.home.FragHomeStudyModel;
import com.example.studit.profile.FragProfile;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.ModelHomeList;
import com.example.studit.study.FragStudy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences;

    BottomNavigationView bottomNavigationView;

    Fragment frag_home, frag_study, frag_chat, frag_profile;

    Link link = new Link();

    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag_home = new FragHome();
        frag_study = new FragStudy();
        frag_chat = new FragChat();
        frag_profile = new FragProfile();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM Log", "getInstanceId faild", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                        Log.d("FCM Log", "FCM 토근 : " + token);

                        preferences = getSharedPreferences("fcm", MODE_PRIVATE);

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("fcmToken", token);
                        editor.apply();

                    }
                });


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
//                case R.id.chat:
//                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_chat).commit();
//                    return true;
                case R.id.profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_profile).commit();
                    return true;
            }
            return true;
        });

    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }
}