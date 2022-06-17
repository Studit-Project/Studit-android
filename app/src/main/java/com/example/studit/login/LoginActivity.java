package com.example.studit.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.studit.R;
import com.example.studit.join.JoinActivity;
import com.example.studit.main.MainActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText Id, Password;
    Button Login_Button;
    // Button find_pwd
    TextView Signup_Button;
    CheckBox checkBox;
    SharedPreferences autoLogin;
    SharedPreferences.Editor editor;
    Intent intent;
    String Logout_Code;

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

        checkBox = (CheckBox) findViewById(R.id.autoCheck);

        autoLogin = getSharedPreferences("autoLogin", 0);
        editor = autoLogin.edit();

        intent = getIntent();
        Logout_Code = intent.getStringExtra("Logout_Code");
        Log.d("sujung", Logout_Code);

        // 자동로그인
        if(autoLogin.getBoolean("chk_auto", false)) {
            // 초기 실행시 설정 값이 없으므로 false가 반환되어 if문 작동하지 않음

            // EditText에 setText로 적용
            Id.setText(autoLogin.getString("ID", ""));
            Password.setText(autoLogin.getString("Password", ""));

            sID = autoLogin.getString("ID", "");
            Log.d("sujung", sID);

            // 자동로그인이 활성화 되어 checkbox 활성화 표시
            checkBox.setChecked(true);

            if(Logout_Code.equals("f")){
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                // 자동 로그인 후 로그인 액티비티를 종료하여 메인 엑티비티에서 뒤로가기 버튼을 누를 경우
                // 앱 종료
            }

            // 메인 액티비티에서 로그아웃을 할 경우
            else {
                Id.setText(autoLogin.getString("", ""));
                Password.setText(autoLogin.getString("", ""));

                editor.clear();
                editor.commit();
                return;
            }
        }

//        //pwd 찾기(추후 구현 예정)
//        find_pwd = (Button) findViewById(R.id.find_pwd);

        //회원가입 버튼 클릭
        Signup_Button.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
            startActivity(intent);
        });

        //로그인 버튼 클릭
        Login_Button.setOnClickListener(view -> {
            String userID = Id.getText().toString();
            String userPassword = Password.getText().toString();

            Response.Listener<String> responseListener = response -> {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        // 자동 로그인 체크시
                        if(checkBox.isChecked()) {
                            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();

                            String userID1 = jsonResponse.getString("userID");
                            String userPassword1 = jsonResponse.getString("userPassword");

                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                            editor.putString("ID", userID1);
                            editor.putString("Password", userPassword1);
                            editor.putBoolean("chk_auto", true);
                            editor.commit();

                            // 로그인시 사용자 정보 넘기기
                            intent.putExtra("userID", userID1);
                            intent.putExtra("userPassword", userPassword1);
                            startActivity(intent);
                        }
                        // 자동 로그인 체크 안한 경우
                        else {
                            editor.clear();
                            editor.commit();

                            Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    //아이디 혹은 비밀번호가 입력 안된 경우
                    if(userID.length() == 0 || userPassword.length() == 0){
                        Toast.makeText(this, "아이디 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "아이디와 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
            LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListener);
            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginRequest);
        });

    }
}