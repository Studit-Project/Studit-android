package com.example.studit.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.join.JoinActivity;
import com.example.studit.main.MainActivity;
import com.example.studit.retrofit.RetrofitClient;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.ModelHomeResult;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Tag;

public class LoginActivity extends AppCompatActivity {

    String BASE_URL = "http://13.209.68.199:8081/";

    private final String TAG = "LoginActivity";

    private RetrofitClient retrofitClient;
    private RetrofitInterface.initMyApi initMyApi;

    EditText Id, Password;
    Button Login_Button;
    TextView Signup_Button;
    CheckBox checkBox;

    //기본키
    public static String sID;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //id 지정
        Id = (EditText) findViewById(R.id.iD);
        Password = (EditText) findViewById(R.id.password);

        Login_Button = (Button) findViewById(R.id.login_button);
        Signup_Button = (TextView) findViewById(R.id.signup_button);
        checkBox = (CheckBox) findViewById(R.id.autoCheck);

        String userID = Id.getText().toString();
        String userPassword = Password.getText().toString();

        //회원가입 버튼 클릭
        Signup_Button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
            startActivity(intent);
        });

        //자동로그인 선택
        if(!getPreferenceString(userID).equals("") && !getPreferenceString(userPassword).equals("")) {
            checkBox.setChecked(true);
            checkAutoLogin(getPreferenceString(userID));
        }

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

        Login_Button.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String id = Id.getText().toString();
                String pw = Password.getText().toString();

                // 로그인 정보 미입력시
                if (id.trim().length() == 0 || pw.trim().length() == 0 || id == null || pw == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("아이디와 비밀번호를 확인해주세요.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // 로그인 통신
                    LoginResponse();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void LoginResponse() {
        String userID = Id.getText().toString().trim();
        String userPassword = Password.getText().toString().trim();

        // loginrequest에 사용자가 입력한 아이디와 패스워드 저장
        LoginRequest loginRequest = new LoginRequest(userID, userPassword);

        // retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        // 받은 토큰 저장
        String token = LoginResponseList.getAccessToken();

//        Call<LoginResponse> callGetLoginResponse = initMyApi.getLoginResponseList();
        // loginRequest에 저장된 데이터와 함께 init 에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d(TAG,"Data fetch success");

                // 통신 성공
                if (response.code() == 200) {
                    Log.d(TAG,"response!!!!!");

                    //response.body() 를 result 에 저장
                    LoginResponse result = response.body();

                    String resultCode = result.getCode();

                    // 받은 토큰 저장
                    String token = LoginResponseList.getAccessToken();

                    // 통신을 위한 token 저장
//                    SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putString("token", token);
//                    editor.commit();
//                    Log.d(TAG, token);

                    String userID = Id.getText().toString();
                    String userPassword = Password.getText().toString();

                    // 자동로그인을 위해 토큰 저장
                    setPreference(token, token);

                    // 자동 로그인 여부
                    if (checkBox.isChecked()) {
                        setPreference(userID, userID);
                        setPreference(userPassword, userPassword);
                    }
                    else {
                        setPreference(userID, "");
                        setPreference(userPassword, "");
                    }

                    Toast.makeText(LoginActivity.this, userID + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userId", userID);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }
                else {

                    Log.d(TAG,"response fail!!!!!");
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("알림")
                            .setMessage("아이디나 비밀번호를 다시 확인해주세요.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                }
            }

            // 통신 실패
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "ffffail,,,,," + t.getMessage());
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.\n고객센터에 문의바랍니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }

        });
    }

    // 데이터를 내부 저장소에 저장하기
    public void setPreference(String key, String value) {
        SharedPreferences pref = getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    // 내부 저장소에 저장된 데이터 가져오기
    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences("token", Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }

    // 자동 로그인 유저
    public void checkAutoLogin(String id) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
