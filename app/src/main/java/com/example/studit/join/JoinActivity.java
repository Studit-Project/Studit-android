package com.example.studit.join;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.studit.R;

public class JoinActivity extends AppCompatActivity {

    private ImageButton bt_back;
    private EditText name, number, cert_num, email, pw, pw2;
    private Button bt_numck, bt_submit;
    private AlertDialog dialog;
    private boolean validate = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        //뒤로가기 버튼
        bt_back = findViewById(R.id.bt_back);
        bt_back.setOnClickListener(view -> onBackPressed());

        //id값 부여
        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        cert_num = findViewById(R.id.cert_num);
        email = findViewById(R.id.email);
        pw = findViewById(R.id.pw);
        pw2 = findViewById(R.id.pw2);

        //전화번호 인증버튼 클릭시

        bt_numck = findViewById(R.id.bt_numcheck);
        bt_numck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //가입하기 버튼 클릭시
        bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String UserName = name.getText().toString();
                final String UserNumber = number.getText().toString();
                final String UserEmail = email.getText().toString();
                final String UserPw = pw.getText().toString();
                final String Pwcheck = pw2.getText().toString();

                //빈칸 있는지 확인
                if (UserName.equals("") || UserNumber.equals("") || UserEmail.equals("") || UserPw.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }
                //전화번호 인증 했는지 확인
                if (!validate){
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("전화번호 인증 후 가입하실 수 있습니다.").setNegativeButton("확인",null).create();
                    dialog.show();
                    return;
                }

                if (UserPw.equals(Pwcheck)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder((JoinActivity.this));
                    dialog = builder.setMessage("비밀번호가 일치하지 않습니다.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                //회원가입 가능 여부 판단
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
                                Toast.makeText(getApplicationContext(),"failed", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                };//Response.Listener 끝

                //volley 통신
                com.example.studit.join.JoinRequest joinRequest = new com.example.studit.join.JoinRequest(UserName, UserNumber, UserEmail, UserPw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(JoinActivity.this);
                queue.add(joinRequest);


                }
            });
        }
}
