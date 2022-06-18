//package com.example.studit.login;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Build;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.studit.R;
//import com.example.studit.join.JoinActivity;
//import com.example.studit.main.MainActivity;
//import com.example.studit.retrofit.RetrofitClient;
//import com.example.studit.retrofit.RetrofitInterface;
//import com.example.studit.search.FragSearchStudy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonDeserializer;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.concurrent.TimeUnit;
//
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class LoginActivity extends AppCompatActivity {
//
//    EditText Id, Password;
//    Button Login_Button;
//    TextView Signup_Button;
//    CheckBox checkBox;
//    SharedPreferences autoLogin;
//    SharedPreferences.Editor editor;
//
//    String BASE_URL = "http://54.180.97.161:8081/";
//    private Object OkHttpClient;
//
//    public static LoginActivity newInstance() {
//        LoginActivity loginActivity = new LoginActivity();
//        Bundle bundle = new Bundle();
//        return loginActivity;
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    protected void onCreate(@NonNull Bundle savedInstancesState) {
//        super.onCreate(savedInstancesState);
//        setContentView(R.layout.activity_login);
//
//        //id 지정
//        Id = (EditText) findViewById(R.id.iD);
//        Password = (EditText) findViewById(R.id.password);
//
//        Login_Button = (Button) findViewById(R.id.login_button);
//        Signup_Button = (TextView) findViewById(R.id.signup_button);
//
//        checkBox = (CheckBox) findViewById(R.id.autoCheck);
//
//        // pwd 찾기(추후 구현 예정)
////        find_pwd = (Button) findViewById(R.id.find_pwd);
//
//        //회원가입 버튼 클릭
//        Signup_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
//                startActivity(intent);
//                LoginActivity.this.finish();
//            }
//        });
//
//        // 자동로그인
//        if( !getPreferencesString("autoLoginId").equals("") && !getPreferencesString("autoLoginPw").equals("")) {
//            checkBox.setChecked(true);
//            checkAutoLogin(getPreferencesString("autoLoginId"));
//        }
//
//        Login_Button.setOnClickListener(new View.OnClickListener() {
//
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) {
//                String phone = Id.getText().toString();
//                String password = Password.getText().toString();
//
//                //로그인 정보 미입력
//                if (phone.trim().length() == 0 || password.trim().length() == 0 || phone == null || password == null) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                    builder.setTitle("알림")
//                            .setMessage("로그인 정보를 입력 바랍니다.")
//                            .setPositiveButton("확인", null)
//                            .create()
//                            .show(); }
//                else {
//                    loginUser();
//                }
//            }
//        });
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void loginUser() {
//        final String phone = Id.getText().toString().trim();
//        final String password = Password.getText().toString().trim();
//
//        //loginrequest 에 사용자가 입력한 Id Password 저장
//        LoginRequest loginRequest = new LoginRequest(phone, password);
//
//        //retrofit 생성
//        RetrofitClient retrofitClient = RetrofitClient.getInstance();
//        RetrofitInterface.initMyApi initMyApi = RetrofitClient.getRetrofitInterface();
//
//        // loginrequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
//
//        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
//            @Override
//            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
//
//                Log.d("retrofit", "Data fetch success");
//
//                // 통신 성공
//                if (response.isSuccessful() && response.body() != null) {
//                    //response.body() 를 result 에 저장
//                    LoginResponse result = response.body();
//
//                    // 받은 코드 저장
//                    String resultCode = result.getResultCode();
//
//                    // 받은 토큰 저장
//                    String token = result.getToken();
//
//                    String success = "200"; // 로그인 성공
//
//                    if (resultCode.equals(success)) {
//                        String phone = Id.getText().toString();
//                        String password = Password.getText().toString();
//
//                        setPreference(token, token);
//
//                        // 자동 로그인 여부
//                        if (checkBox.isChecked()) {
//                            setPreference(phone, phone);
//                            setPreference(password, password);
//                        } else {
//                            setPreference(phone, "");
//                            setPreference(password, "");
//                        }
//
//                        Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
//                        intent.putExtra("phone", phone);
//                        startActivity(intent);
//                        LoginActivity.this.finish();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요..", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<LoginResponse> call, Throwable t) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                builder.setTitle("알림")
//                        .setMessage("로그인에 실패하였습니다.")
//                        .setPositiveButton("확인", null)
//                        .create()
//                        .show();
//            }
//        });
//    }
//
//    // 데이터를 내부 저장소에 저장하기
//    public void setPreference (String key, String value) {
//        SharedPreferences pref = getSharedPreferences("mina", MODE_PRIVATE);
//        //로그인 정보 sharedpreference에 저장.
//        //mina 이름 파일 과 기본모드를 설정
//        SharedPreferences.Editor editor= pref.edit(); //sharedPreferences를 제어할 editor를 선언
//        editor.putString("autoLoginId",Id.getText().toString()); // key,value 형식으로 저장
//        editor.putString("autoLoginPw",Password.getText().toString()); // key,value 형식으로 저장
//        editor.putBoolean("chk_auto", true);
//
//        editor.apply();
//    }
//
//    // 내부 저장소에 저장된 데이터 가져오기
//    public String getPreferencesString(String key) {
//        SharedPreferences pref = getSharedPreferences("mina", MODE_PRIVATE);
//
//        String autoLoginId = pref.getString("autoLoginId","");
//        String autoLoginPw = pref.getString("autoLoginPw","");
//
//        return pref.getString(key, "");
//    }
//
//    // 자동 로그인 유저
//    public void checkAutoLogin(String phone) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        finish();
//    }
//
//    private OkHttpClient provideOkHttpClient() {
//        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
//        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
//        return okhttpClientBuilder.build();
//    }
//
//}
//


