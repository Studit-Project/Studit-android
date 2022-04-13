package com.example.studit.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.studit.main.MainActivity;
import com.example.studit.R;
import com.example.studit.main.MainActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    EditText Id, Password;
    Button Login_Button, Signup_Button, find_pwd ;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_login);

        //id 지정
        Id = (EditText) findViewById(R.id.iD);
        Password = (EditText) findViewById(R.id.password);

        Login_Button = (Button) findViewById(R.id.login_button);
        Signup_Button = (Button) findViewById(R.id.signup_button);

        //pwd 찾기
        find_pwd = (Button) findViewById(R.id.find_pwd);

        //회원가입 버튼 클릭(추후연동)
        //Signup_Button.setOnClickListener(v -> {
        //    Intent intent = new Intent(getApplicationContext(),JoinActivity.class);

        //});

        //비밀번호 찾기 클릭

        //로그인 버튼 클릭
        Login_Button.setOnClickListener(view -> {
            String userID = Id.getText().toString();
            String userPassword = Password.getText().toString();

            Response.Listener<String> responseListener = response -> {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Toast.makeText(getApplicationContext(), "환영합니다.", Toast.LENGTH_SHORT).show();

                        String userID1 = jsonResponse.getString("userID");
                        String userPassword1 = jsonResponse.getString("userPassword");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        // 로그인시 사용자 정보 넘기기
                        intent.putExtra("userID", userID1);
                        intent.putExtra("userPassword", userPassword1);
                        startActivity(intent);

                    } else {
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
