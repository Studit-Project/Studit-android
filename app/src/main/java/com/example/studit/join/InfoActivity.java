package com.example.studit.join;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.login.Login2Activity;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.join.ModelUserJoinInfo;
import com.example.studit.retrofit.join.Model_UserId;
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

    String BASE_URL = "http://52.79.239.41:8081/";

    private ArrayAdapter adapter;
    private Spinner sp_age_y;
    private Spinner sp_age_m;
    private Spinner sp_age_d;
    private String UserGender;
    private RadioGroup genderGroup;
    private Button bt_submit;
    private AlertDialog dialog;

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

        intent = getIntent();

        intent = getIntent();
        final long UserNumber;

        if(savedInstanceState == null){
            Bundle extras = intent.getExtras();
            if(extras == null){
                UserNumber = 0;
            } else {
                UserNumber = extras.getLong("number");
            }
        } else {
            UserNumber = (long) savedInstanceState.getSerializable("number");
        }

        Log.e(TAG,"userNumber 저장 됐나? -> " + UserNumber);


        //id값 부여
        final EditText nickname = findViewById(R.id.nickname);

        genderGroup = findViewById(R.id.gender);

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

            /*
            final long UserNumber;

            if(savedInstanceState == null){
                Bundle extras = intent.getExtras();
                if(extras == null){
                    UserNumber = 0;
                } else {
                    UserNumber = extras.getLong("number");
                }
            } else {
                UserNumber = (long) savedInstanceState.getSerializable("number");
            }

             */

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
            ModelUserJoinInfo userJoinInfo = new ModelUserJoinInfo(UserBirth,UserGender,UserNick);
            Call<Model_UserId> call = retrofitInterface.patchUserId(UserNumber,userJoinInfo);



            call.enqueue(new Callback<Model_UserId>() {
                @Override
                public void onResponse(@NonNull Call<Model_UserId> call, @NonNull Response<Model_UserId> response) {
                    Log.e(TAG,"가져온 userId : " + UserNumber);
                    if(response.isSuccessful() && response.body() != null){
                        Log.e(TAG, "가입 성공!");
                        AlertDialog.Builder builder = new AlertDialog.Builder((InfoActivity.this));
                        dialog = builder.setMessage("가입 성공! 로그인 하세요.").setNegativeButton("확인", null).create();
                        dialog.show();
                        intent = new Intent(InfoActivity.this, Login2Activity.class); //로그인페이지로 넘어감

                    } else {
                        try {
                            String body = response.errorBody().string();
                            Log.e(TAG, "가입 error!!! - body : " + body);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Model_UserId> call, Throwable t) {
                    Log.e(TAG, "가입 fail!!! " + t.getMessage());
                }
            });



        });

    }
}