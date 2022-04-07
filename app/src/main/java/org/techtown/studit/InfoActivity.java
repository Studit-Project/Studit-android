package org.techtown.studit;

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

import org.json.JSONException;
import org.json.JSONObject;

public class InfoActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private EditText nickname;
    private Spinner sp_age;
    private RadioGroup gender;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //spinner 객체 선언, id 가져오기
        sp_age = findViewById(R.id.sp_age);
        adapter = ArrayAdapter.createFromResource(this, R.array.age, android.R.layout.simple_dropdown_item_1line);
        sp_age.setAdapter(adapter);

        nickname = findViewById(R.id.nickname);

        gender =findViewById(R.id.gender);
        int genderID = gender.getCheckedRadioButtonId();
        gender = ((RadioGroup) findViewById(genderID)).getText().toString();

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                gender = findViewById(i);
                gender = gender.getText().toString();
            }
        });

    }
}