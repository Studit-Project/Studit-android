package com.example.studit.join;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ArrayAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.studit.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.studit.R;

public class InfoActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private EditText UserNick;
    private Spinner sp_age;
    private String UserGender;
    private String UserAge;
    private Button bt_submit;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //id값 부여
        final EditText nickname = findViewById(R.id.nickname);

        RadioGroup genderGroup = findViewById(R.id.gender);
        int genderID = genderGroup.getCheckedRadioButtonId();
        UserGender = ((RadioButton) findViewById(genderID)).getText().toString(); //초기화값 지정


        //spinner 객체 선언, id 가져오기
        sp_age = findViewById(R.id.sp_age);
        adapter = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_dropdown_item_1line);
        sp_age.setAdapter(adapter);


        //radio 버튼 값 적용해주기
        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                RadioButton genderButton = findViewById(i);
                UserGender = genderButton.getText().toString();
            }
        });

        //시작하기 버튼이 눌렸을 때
        bt_submit = findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserNick = nickname.getText().toString();
                String UserAge = sp_age.getSelectedItem().toString();

                //빈칸이 있는 경우
                if(UserNick.equals("") || UserAge.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
                    dialog = builder.setMessage(("모두 입력해주세요.")).setNegativeButton("확인",null).create();
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
                                Toast.makeText(getApplicationContext(), "StudIT에 오신것을 환영합니다!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InfoActivity.this, MainActivity.class); //메인 페이지로 넘어감
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
                }; //Response.Listener 끝

                //volley 통신
                com.example.studit.join.InfoRequest InfoRequest = new InfoRequest(UserNick, UserGender, UserAge, responseListener);
                RequestQueue queue = Volley.newRequestQueue(InfoActivity.this);
                queue.add(InfoRequest);
            }
        });

    }
}