
package com.example.studit.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.join.JoinActivity;
import com.example.studit.main.MainActivity;
import com.example.studit.retrofit.AccessTokenServiceInterface;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.ModelAuth;
import com.example.studit.retrofit.Model_UserLogIn;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.TokenResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login2Activity extends AppCompatActivity {
    EditText Id, Password;
    Button Login_Button, find_pwd;
    TextView Signup_Button;
    CheckBox checkBox;
    SharedPreferences autoLogin;
    SharedPreferences.Editor editor;
    Intent intent;
    String Logout_Code;

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    RetrofitInterface retrofitInterface;

    //기본키
    public static String sID;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_login);

        //id 지정
        Id = (EditText) findViewById(R.id.iD);
        Password = (EditText) findViewById(R.id.password);

        Login_Button = (Button) findViewById(R.id.login_button);
        Signup_Button = (TextView) findViewById(R.id.signup_button);
//
//        checkBox = (CheckBox) findViewById(R.id.autoCheck);

//        autoLogin = getSharedPreferences("autoLogin", 0);
//        editor = autoLogin.edit();
//
//        intent = getIntent();
//        Logout_Code = intent.getStringExtra("Logout_Code");
//        Log.d("sujung", Logout_Code);

        // 자동로그인
//        if (autoLogin.getBoolean("chk_auto", false)) {
//            // 초기 싪행시 설정 값이 없으므로 false가 반환되어 if문 작동하지 않음
//
//            // Edittext에 setText로 적용
//            Id.setText(autoLogin.getString("ID", ""));
//            Password.setText(autoLogin.getString("Password", ""));
//
//            sID = autoLogin.getString("ID", "");
//            Log.d("sujung", sID);
//
//            // 자동로그인이 활성화 되어 checkbox 활성화 표시
//            checkBox.setChecked(true);
//
//            if (Logout_Code.equals("f")) {
//                intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
//                // 자동 로그인 후 로그인 액티비티를 종료하여 메인 엑티비티에서 뒤로가기 버튼을 누를 경우
//                // 앱 종료
//            }
//
//            // 메인 액티비티에서 로그아웃을 할 경우
//            else {
//                Id.setText(autoLogin.getString("", ""));
//                Password.setText(autoLogin.getString("", ""));
//
//                editor.clear();
//                editor.commit();
//                return;
//            }
//        }

//        //pwd 찾기(미해결)
//        find_pwd = (Button) findViewById(R.id.find_pwd);

        //회원가입 버튼 클릭
        Signup_Button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
            startActivity(intent);
        });

        Link link = new Link();

//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(link.getBASE_URL())
//                .build();
//
//        AccessTokenServiceInterface service = retrofit.create(AccessTokenServiceInterface.class);
//
////        Login_Button.setOnClickListener(view -> {
//            String userID = Id.getText().toString();
//            String userPassword = Password.getText().toString();

        //grant types = client_credentials
//        Call<TokenResponse> call = service.getToken(userPassword, userID);
//            AtomicReference<Response<TokenResponse>> response = null;
//            new Thread(() -> {
//                try {
//                    response.set(call.execute());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//            System.out.println("+==================");
//            System.out.println(Objects.requireNonNull(response.get().body()).getAccessToken());
//
//    });


//        Gson gson = new Gson();
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(link.getBASE_URL())
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//
//        Login_Button.setOnClickListener(view -> {
//            String userID = Id.getText().toString();
//            String userPassword = Password.getText().toString();
//            Model_UserLogIn logIn = new Model_UserLogIn(userPassword, userID);
//            Call<ModelAuth> call = jsonPlaceHolderApi.createPost(logIn);
//
//            call.enqueue(new Callback<ModelAuth>() {
//                @Override
//                public void onResponse(@NonNull Call<ModelAuth> call, @NonNull Response<ModelAuth> response) {
//
//                    if (!response.isSuccessful()) {
//                        return;
//                    }
//
//                    ModelAuth postResponse = response.body();
//
//                    System.out.println(response.code());
//                    System.out.println(response.raw());
//                    System.out.println(postResponse.getAuth());
//                    SharedPreferences preferences = getSharedPreferences("userLogin", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        editor.putString("userPhone", userID);
//                        editor.putString("auth", postResponse.getAuth());
//                        editor.apply();
//
//                        Intent intent = new Intent(Login2Activity.this, MainActivity.class);
//                        startActivity(intent);
//                }
//
//                @Override
//                public void onFailure(@NonNull Call<ModelAuth> call, Throwable t) {
//                    System.out.println("=================" + t.getMessage());
//                }
//            });
//        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);



        Login_Button.setOnClickListener(view -> {
            String userID = Id.getText().toString();
            String userPassword = Password.getText().toString();
            Model_UserLogIn login = new Model_UserLogIn(userPassword, userID);

            Call<ModelAuth> callPostFilterResponse = retrofitInterface.postUserLogin(login);
            callPostFilterResponse.enqueue(new Callback<ModelAuth>() {
                @Override
                public void onResponse(@NonNull Call<ModelAuth> call, @NonNull retrofit2.Response<ModelAuth> response) {

                    if (response.code() == 200) {
                        System.out.println("성공");

                        assert response.body() != null;
                        System.out.println(response.body().getAuth());

//                        Intent intent2 = new Intent(getApplicationContext(), MyStudyActivity.class);
//                        intent2.putExtra("userPhone", userID);
//                        startActivity(intent2);

                        SharedPreferences preferences = getSharedPreferences("userLogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("userPhone", userID);
                        editor.putString("auth", response.body().getAuth());
                        editor.apply();

                        Intent intent = new Intent(Login2Activity.this, MainActivity.class);
                        startActivity(intent);
                    } else if (response.code() == 201) {
                        System.out.println("Unauthorized");
                    } else if (response.code() == 401) {
                        System.out.println("Unauthorized");
                    } else if (response.code() == 403) {
                        System.out.println("Forbidden");
                    } else if (response.code() == 404) {
                        System.out.println("Not Found");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ModelAuth> call, @NonNull Throwable t) {
                    System.out.println("=================" + t.getMessage());
                }
            });

//            Response.Listener<String> responseListener = response -> {
//                try {
//                    JSONObject jsonResponse = new JSONObject(response);
//                    boolean success = jsonResponse.getBoolean("success");
//                    if (success) {
//                        // 자동 로그인 체크시
//                        if(checkBox.isChecked()) {
//                            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();
//
//                            String userID1 = jsonResponse.getString("userID");
//                            String userPassword1 = jsonResponse.getString("userPassword");
//
//                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//
//                            editor.putString("ID", userID1);
//                            editor.putString("Password", userPassword1);
//                            editor.putBoolean("chk_auto", true);
//                            editor.commit();
//
//                            // 로그인시 사용자 정보 넘기기
//                            intent.putExtra("userID", userID1);
//                            intent.putExtra("userPassword", userPassword1);
//                            startActivity(intent);
//                        }
//                        // 자동 로그인 체크 안한 경우
//                        else {
//                            editor.clear();
//                            editor.commit();
//
//                            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//
//                    //아이디 혹은 비밀번호가 입력 안된 경우
//                    if(userID.length() == 0 || userPassword.length() == 0){
//                        Toast.makeText(this, "아이디 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            };
//            LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
//            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//            queue.add(loginRequest);


        });
//        HttpPost();

    }


//
//    private void HttpPost() {
//
//        new AsyncTask<Void, Void, JSONObject>() {
//
//            @Override
//            protected JSONObject doInBackground(Void... voids) {
//
//                JSONObject result = null;
//                try {
//                    URL url = new URL("http://34.64.52.84:8081/user/login");
//                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//                    connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
//                    connection.setRequestMethod("POST");
//                    connection.setDoInput(true);
//                    connection.setDoOutput(true);
//                    connection.setUseCaches(false);
//                    connection.setConnectTimeout(15000);
//
//                    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("password", "min");
//                    map.put("phone", "01051328544");
//
//                    StringBuffer sbParams = new StringBuffer();
//                    boolean isAnd = false;
//                    for (String key : map.keySet()) {
//                        if (isAnd) sbParams.append("&");
//                        sbParams.append(key).append("=").append(map.get(key));
//                        if (!isAnd) if (map.size() >= 2) isAnd = true;
//                    }
//                    wr.write(sbParams.toString());
//                    wr.flush();
//                    wr.close();
//                    int responseCode = connection.getResponseCode();
//                    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                        String inputLine;
//                        StringBuffer response = new StringBuffer();
//                        while ((inputLine = in.readLine()) != null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//
//                        System.out.println("================"+response);
//                    } else {
//                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                        String inputLine;
//                        StringBuffer response = new StringBuffer();
//                        while ((inputLine = in.readLine()) != null) {
//                            response.append(inputLine);
//                        }
//                        in.close();
//                        result = new JSONObject(response.toString());
//                    }
//
//                } catch (ConnectException e) {
//                    Log.e("tag", "ConnectException");
//                    e.printStackTrace();
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(JSONObject jsonObject) {
//                super.onPostExecute(jsonObject);
//            }
//
//        }.execute();
//    }


    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }
}
