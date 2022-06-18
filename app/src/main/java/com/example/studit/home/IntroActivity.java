package com.example.studit.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.login.Login2Activity;

public class IntroActivity extends AppCompatActivity {

    Animation animFadeOut;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        imageView = (ImageView) findViewById(R.id.imageview);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);

        Handler handler = new Handler();

        // fade out
        imageView.setOnClickListener(v -> {
            imageView.setVisibility(View.VISIBLE);
            imageView.startAnimation(animFadeOut);

            handler.postDelayed(() -> {
                Intent intent = new Intent(IntroActivity.this, Login2Activity.class);
                startActivity(intent);
            }, 100);
        });

        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_out);
    }


}