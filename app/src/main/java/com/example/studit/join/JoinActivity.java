package com.example.studit.join;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
//import android.view.View;
import android.widget.EditText;
import android.widget.Button;
//import android.widget.Toast;

import com.example.studit.R;
import com.example.studit.login.LoginActivity;
import com.example.studit.retrofit.Model_UserJoin;
import com.example.studit.retrofit.Model_UserJoin2;
import com.example.studit.retrofit.Model_ValidatePhone;
import com.example.studit.retrofit.RetrofitInterface;
import com.google.gson.Gson;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class JoinActivity extends AppCompatActivity {

    String BASE_URL = "http://3.39.192.79:8081/";

    private EditText mName, mPhone, inputCheckNum, mEmail, mPassword, inputCheckPw;
    private Button btn_numCheck;
    private AlertDialog dialog;
    //private boolean validate = false;

    private final String TAG = this.getClass().getSimpleName();

    Intent intent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //id값 부여
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

            if(Objects.equals(Phone, "")){
                AlertDialog.Builder builder2 = new AlertDialog.Builder(JoinActivity.this);
                dialog = builder2.setMessage("전화번호를 입력하세요.").setPositiveButton("확인", null).create();
                dialog.show();
            } else{
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                Call<Model_ValidatePhone> call = retrofitInterface.getValidatePhone(Phone);

                call.enqueue(new Callback<Model_ValidatePhone>() {

                    @Override
                    public void onResponse(@NonNull Call<Model_ValidatePhone> call, @NonNull Response<Model_ValidatePhone> response) {

                        Log.d("TAG",response.code()+"");

                        //전화번호값 고정
                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                        dialog = builder.setMessage("인증번호를 입력해주세요.").setPositiveButton("확인", null).create();
                        dialog.show();
                        mPhone.setEnabled(false); //아이디값 고정
                        btn_numCheck.setBackgroundColor(getResources().getColor(R.color.gray));

                        if(response.isSuccessful() && response.body() != null){ //통신 성공시 번호 전송
                            Model_ValidatePhone numStr = response.body();
                        } else if(response.code() == 401){
                            System.out.println("Unauthorized");
                        } else if(response.code() == 403){
                            System.out.println("Forbidden");
                        } else if(response.code() == 404){
                            System.out.println("Not Found");
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Model_ValidatePhone> call, @NonNull Throwable t) {
                        System.out.println("fail: " + t.getMessage());
                    }
                });
            }
        });


        //가입하기 버튼 클릭시
        Button bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(view -> {
            final String UserName = mName.getText().toString();
            final String Phone = mPhone.getText().toString();
            final String UserCheckNum = inputCheckNum.getText().toString();
            final String Email = mEmail.getText().toString();
            final String Password = mPassword.getText().toString();
            final String PwCheck = inputCheckPw.getText().toString();

            //빈칸 있는지 확인
            if (UserName.equals("") || Phone.equals("") || Email.equals("") || Password.equals("")) {
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
            /*인증번호 일치여부 확인
            if (UserCheckNum.compareTo(numStr)) {
                AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                dialog = builder.setMessage("인증번호가 일치하지 않습니다.").setNegativeButton("확인", null).create();
                dialog.show();
                return;
            }   */

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            Model_UserJoin2 userJoin2 = new Model_UserJoin2(Email,Password, Phone, UserName);
            Call<Model_UserJoin2> call = retrofitInterface.getUserJoin(userJoin2);

            intent = getIntent();

            call.enqueue(new Callback<Model_UserJoin2>(){

                             @Override
                             public void onResponse(@NonNull Call<Model_UserJoin2> call, @NonNull Response<Model_UserJoin2> response) {
                                 if(response.isSuccessful() && response.body() != null){ //가입성공
                                     Log.e(TAG, "가입 성공!");
                                     intent = new Intent(JoinActivity.this, LoginActivity.class); //정보입력 페이지로 넘어감
                                     startActivity(intent);
                                 } else {
                                     try {
                                         String body = response.errorBody().string();
                                         Log.e(TAG, "error - body : " + body);
                                     } catch (IOException e){
                                         e.printStackTrace();
                                     }
                                 }
                             }
                             @Override
                             public void onFailure(@NonNull Call<Model_UserJoin2> call, @NonNull Throwable t) {
                                 Log.e(TAG, "error! : " + t.getMessage());
                             }

                         });


        /*    //회원가입 가능 여부 판단
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");

                        //회원가입 성공
                        if(success){
                            Toast.makeText(getApplicationContext(), "가입 성공!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(JoinActivity.this,InfoActivity.class); //정보입력 페이지로 넘어감
                        }
                        //회원가입 실패
                        else{
                            Toast.makeText(getApplicationContext(),"failed: "+response, Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            };//Response.Listener 끝
*/
            /*volley 통신
            com.example.studit.join.JoinRequest joinRequest = new com.example.studit.join.JoinRequest(UserName, Phone, Email, Password, responseListener);
            RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
            queue.add(joinRequest);
             */

            });
        }

/*
    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();

        //interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpClientBuilder.addInterceptor(loggingInterceptor);

        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    } */
}
