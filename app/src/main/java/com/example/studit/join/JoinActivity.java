package com.example.studit.join;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.join.Model_UserJoin;
import com.example.studit.retrofit.join.Model_ValidatePhone;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

//import android.view.View;
//import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    String BASE_URL = "http://52.79.239.41:8081/";

    private EditText mID,mName, mPhone, inputCheckNum, mEmail, mPassword, inputCheckPw;
    private Button btn_numCheck;
    private AlertDialog dialog;

    private final String TAG = this.getClass().getSimpleName();

    String numStr;
    long userNumber;
    List sMessage;

    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //id값 부여
        mID = findViewById(R.id.ID);
        mName = findViewById(R.id.name);
        mPhone = findViewById(R.id.phone);
        inputCheckNum = findViewById(R.id.inputCheckNum);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.pw);
        inputCheckPw = findViewById(R.id.pw2);


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

        //전화번호 인증버튼 클릭시
        btn_numCheck = findViewById(R.id.bt_numcheck);
        btn_numCheck.setOnClickListener(view -> {

            final String Phone = mPhone.getText().toString();

            if (Objects.equals(Phone, "")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                dialog = builder.setMessage("전화번호를 입력하세요.").setPositiveButton("확인", null).create();
                dialog.show();
            } else {
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                Model_ValidatePhone userPhoneValidate = new Model_ValidatePhone(Phone);
                Call<Model_ValidatePhone> call = retrofitInterface.getValidatePhone(userPhoneValidate);

                call.enqueue(new Callback<Model_ValidatePhone>() {

                    @Override
                    public void onResponse(@NonNull Call<Model_ValidatePhone> call, @NonNull Response<Model_ValidatePhone> response) {

                        Model_ValidatePhone responseBody = response.body();
                        numStr = responseBody.getResult();

                        if (response.code() == 200 && response.body() != null) {
                            Log.e(TAG, "문자발송 성공! 인증번호 : " + numStr);
                            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                            dialog = builder.setMessage("문자전송 완료! 인증번호를 입력해주세요.").setPositiveButton("확인", null).create();
                            dialog.show();
                            mPhone.setEnabled(false); //전화번호값 고정
                            btn_numCheck.setBackgroundColor(getResources().getColor(R.color.gray));
                        } else if (response.code() == 401) {
                            System.out.println("Unauthorized");
                        } else if (response.code() == 403) {
                            System.out.println("Forbidden");
                        } else if (response.code() == 404) {
                            System.out.println("Not Found");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Model_ValidatePhone> call, @NonNull Throwable t) {
                        System.out.println("fail!!!!!!! " + t.getMessage());
                    }
                });
            }
        });


        //가입하기 버튼 클릭시
        Button bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(view -> {
            final String UserID = mID.getText().toString();
            final String UserName = mName.getText().toString();
            final String Phone = mPhone.getText().toString();
            final String UserCheckNum = inputCheckNum.getText().toString();
            final String Email = mEmail.getText().toString();
            final String Password = mPassword.getText().toString();
            final String PwCheck = inputCheckPw.getText().toString();

            //빈칸 있는지 확인
            if (UserID.equals(("")) || UserName.equals("") || Phone.equals("") || Email.equals("") || Password.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                dialog.show();
                return;
            }
            //비밀번호 일치여부 확인
            if (Password.compareTo(PwCheck) != 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                dialog = builder.setMessage("비밀번호가 일치하지 않습니다.").setNegativeButton("확인", null).create();
                dialog.show();
                return;
            }
            //인증번호 일치여부 확인
            if (numStr.compareTo(UserCheckNum) != 0){
                AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                dialog = builder.setMessage("인증번호가 일치하지 않습니다.").setNegativeButton("확인", null).create();
                dialog.show();
                return;
            }

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            Model_UserJoin userJoin = new Model_UserJoin(Email, UserID, Password, Phone, UserName);
            Call<Model_UserJoin> call = retrofitInterface.postUserJoin(userJoin);

            intent = getIntent();

            call.enqueue(new Callback<Model_UserJoin>() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(@NonNull Call<Model_UserJoin> call, @NonNull Response<Model_UserJoin> response) {

                    Model_UserJoin responseBody = response.body();
                    userNumber = responseBody.getResult();
                    long extraResult = userNumber;
                    sMessage = responseBody.getMessage();
                    String aMessage = String.join(",",sMessage);

                    if (response.isSuccessful() && response.body() != null) { //통신성공
                        if(responseBody.getIsSuccess() == false){
                            Log.e(TAG, "가입 실패 : " + aMessage); //서버에서 받은 message 보여줌
                            AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                            dialog = builder.setMessage(aMessage).setNegativeButton("확인", null).create();
                            dialog.show();
                        } else {
                            Log.e(TAG, "가입 성공 : " + aMessage);
                            System.out.println("넘겨주는 result 값 : " + extraResult);
                            intent = new Intent(JoinActivity.this, InfoActivity.class); //정보입력 페이지로 넘어감
                            intent.putExtra("number", extraResult);
                            startActivity(intent);
                        }
                    } else {
                        try {
                            String body = response.errorBody().string();
                            Log.e(TAG, "error - body : " + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Model_UserJoin> call, @NonNull Throwable t) {
                    Log.e(TAG, "fail!!!!! " + t.getMessage());
                }

            });

        });
    }
}