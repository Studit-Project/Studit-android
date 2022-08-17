package com.example.studit.profile.setting;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.login.LoginActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.profile.ModelProfileResult;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SettingActivity extends AppCompatActivity {

    Intent intent;

    private ImageView ic_back;

    private ViewGroup edit_nick, edit_status, logout;

    private long userId;

    private final String TAG = this.getClass().getSimpleName();
    private AlertDialog dialog;

    Link link = new Link();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Gson gson = new Gson();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ModelProfileResult> call = retrofitInterface.getUserProfile("Bearer " + link.getToken());
        call.enqueue(new Callback<ModelProfileResult>() {
            @Override
            public void onResponse(@NonNull Call<ModelProfileResult> call, @NonNull Response<ModelProfileResult> response) {
                ModelProfileResult profileResult = response.body();
                if (response.code() == 200) {
                    assert profileResult != null;
                    userId = profileResult.getResult().getUserId();
                    Log.e(TAG, "설정에서 result 받아오기 성공! userId : " + userId);
                } else if (response.code() == 401) {
                    Log.e(TAG, "401 : Unauthorized");
                } else if (response.code() == 403) {
                    Log.e(TAG, "403 : Forbidden");
                } else if (response.code() == 404) {
                    Log.e(TAG, "404 : Not Found");
                }

            }

            @Override
            public void onFailure(Call<ModelProfileResult> call, Throwable t) {
                Log.e(TAG, "Fail!!!!! : " + t.getMessage());
            }
        });

        //뒤로가기
//        ic_back = findViewById(R.id.setting_bt_back);
//        ic_back.setOnClickListener(view -> {
//            Intent intent = new Intent(SettingActivity.this, MainActivity.class);
//            getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, frag_profile).commit();
//            startActivity(intent);
//        });

        //닉네임 변경
        edit_nick = findViewById(R.id.set_nickname_edit);
        edit_nick.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this, EditNickActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        //상태메세지 변경
        edit_status = findViewById(R.id.set_status_edit);
        edit_status.setOnClickListener(view -> {
            Intent intent = new Intent(SettingActivity.this, EditStatusActivity.class);
            intent.putExtra("userId", userId);
            startActivity(intent);
        });

        //로그아웃
        logout = findViewById(R.id.set_logout);
        logout.setOnClickListener(view -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
            builder.setMessage("로그아웃 하시겠습니까?");
            builder.setPositiveButton("확인",
                    (dialog, which) -> {
                        Toast.makeText(SettingActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    });
            builder.setNegativeButton("취소", (DialogInterface.OnClickListener) dialog);
            builder.show();

        });


    }


}