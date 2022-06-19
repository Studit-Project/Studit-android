package com.example.studit.join;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.studit.login.Login2Activity;
import com.example.studit.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.studit.R;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.join.ModelUserJoinInfo;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class InfoActivity extends AppCompatActivity {

    String BASE_URL = "http://13.209.35.29:8081/";

    private ArrayAdapter adapter;
    private EditText UserNick;
    private Spinner sp_age_y;
    private Spinner sp_age_m;
    private Spinner sp_age_d;
    private String UserGender;
    private RadioGroup genderGroup;
    private RadioButton rb_male;
    private RadioButton rb_female;
    private String UserBirth;
    private Button bt_submit;
    private AlertDialog dialog;
    private boolean validate = false;

    private final String TAG = this.getClass().getSimpleName();

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Gson gson = new Gson();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //id값 부여
        final EditText nickname = findViewById(R.id.nickname);

        genderGroup = findViewById(R.id.gender);
        rb_female = findViewById(R.id.FEMALE);
        rb_male = findViewById(R.id.MALE);


        //spinner 객체 선언, id 가져오기
        sp_age_y = findViewById(R.id.sp_age_y);
        adapter = ArrayAdapter.createFromResource(this, R.array.age_year, android.R.layout.simple_dropdown_item_1line);
        sp_age_y.setAdapter(adapter);

        sp_age_m = findViewById(R.id.sp_age_m);
        adapter = ArrayAdapter.createFromResource(this, R.array.age_month, android.R.layout.simple_dropdown_item_1line);
        sp_age_m.setAdapter(adapter);

        sp_age_d = findViewById(R.id.sp_age_d);
        adapter = ArrayAdapter.createFromResource(this, R.array.age_day, android.R.layout.simple_dropdown_item_1line);
        sp_age_d.setAdapter(adapter);


        //radio 버튼 값 적용해주기
        genderGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            if(i==R.id.MALE){
                UserGender = "MALE";
            } else if(i==R.id.FEMALE){
                UserGender = "FEMALE";
            }
        });


        //시작하기 버튼이 눌렸을 때
        bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(view -> {
            final String UserNick = nickname.getText().toString();
            final String UserYear = sp_age_y.getSelectedItem().toString();
            final String UserMonth = sp_age_m.getSelectedItem().toString();
            final String UserDay = sp_age_d.getSelectedItem().toString();
            final String UserBirth = UserYear + "-" + UserMonth + "-" + UserDay;

            //빈칸이 있는 경우
            if(UserNick.equals("") || UserYear.equals("") || UserMonth.equals("") || UserDay.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                dialog = builder.setMessage(("모두 입력해주세요.")).setNegativeButton("확인",null).create();
                dialog.show();
                return;
            }

            //radio 선택되지 않았을 경우
            if(UserGender == null){
                AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                dialog = builder.setMessage(("모두 입력해주세요.")).setNegativeButton("확인",null).create();
                dialog.show();
            }

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            ModelUserJoinInfo userJoinInfo = new ModelUserJoinInfo(UserBirth, UserGender, UserNick);
            Call<ModelUserJoinInfo> call = retrofitInterface.getUserInfo(userJoinInfo);

            intent = getIntent();

            call.enqueue(new Callback<ModelUserJoinInfo>() {
                @Override
                public void onResponse(@NonNull Call<ModelUserJoinInfo> call, @NonNull Response<ModelUserJoinInfo> response) {
                    if(response.isSuccessful() && response.body() != null){ //가입성공
                        Log.e(TAG, "정보기입 성공!");
                        Toast.makeText(getApplicationContext(), "가입성공! 로그인하세요.", Toast.LENGTH_LONG).show();
                        intent = new Intent(InfoActivity.this, Login2Activity.class); //로그인페이지로 넘어감
                        startActivity(intent);
                    } else {
                        try {
                            String body = response.errorBody().string();
                            Log.e(TAG, "error!!! - body : " + body);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ModelUserJoinInfo> call, Throwable t) {
                    Log.e(TAG, "fail!!!!! " + t.getMessage());
                }
            });


        });

    }
}