package com.example.studit.study.registerstudy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.login.LoginActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.study.registerstudy.ModelRegisterStudy;
import com.example.studit.retrofit.studyhome.ModelStudyListAll;
import com.example.studit.study.studyhome.StudyHomeActivity;
import com.example.studit.study.studyhome.StudyHomeAdapter2;
import com.example.studit.study.studyhome.StudyHomeModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterStudyActivity extends Activity {
    final private String TAG = getClass().getSimpleName();

    Link link = new Link();

    EditText title_regi;
    Spinner activity_regi;
    Button regi_button;
    ImageView regi_close;
    String userid = "";

    Intent intent;

    String title1, activity1;

    private final ArrayList<RegisterStudyModel> RegisterList = new ArrayList<>();
    RegisterStudyAdapter adapter;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register_study);

        // userID를 변수로 받음 ( 수정필요할듯 토큰을 받아오는 형식으로 ..? 아니면 본인 유저아이디 받아오도록)
        userid = getIntent().getStringExtra("userID");

        //컴포넌트 초기화
        title_regi = findViewById(R.id.title_regi);
        Spinner activity_regi = (Spinner) findViewById(R.id.activity_spinner);
        final String[] activity1 = {activity_regi.getSelectedItem().toString()};
        regi_button = findViewById(R.id.regi_button);
        regi_close = findViewById(R.id.regi_close);

        Gson gson = new Gson();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 닫기 버튼 클릭시
        regi_close.setOnClickListener(view -> {
            this.finish();
        });

        // 등록 버튼 클릭시
        regi_button.setOnClickListener(view -> {
            String title = title_regi.getText().toString();
            String activity = activity_regi.getSelectedItem().toString();

            // 정보 미기입시
            if (title.equals("") || activity.equals("")) {
                Log.e(TAG, "내용 입력 필요");
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterStudyActivity.this);
                builder.setTitle("알림")
                        .setMessage("정보를 모두 기입해주세요.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            ModelRegisterStudy modelRegisterStudy = new ModelRegisterStudy(activity, title);
            Call<ModelRegisterStudy> call = retrofitInterface.postRegisterStudy(modelRegisterStudy);

            intent = getIntent();

            call.enqueue(new Callback<ModelRegisterStudy>() {
                @Override
                public void onResponse(Call<ModelRegisterStudy> call, Response<ModelRegisterStudy> response) {
                    ModelRegisterStudy responseBody = response.body();
//                    title1 = responseBody.getName();
//                    activity1[0] = responseBody.getActivity();

                    if (response.isSuccessful() && response.body() != null) {
                        Log.e(TAG, "스터디 등록 완료!");
                        Toast.makeText(RegisterStudyActivity.this, "스터디가 개설되었습니다.", Toast.LENGTH_SHORT).show();

                        // 스터디 개설 성공시 팝업 닫기
                        finish();

                    } else{
                        try {
                            String body = response.errorBody().string();
                            Log.e(TAG, "error - body" + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ModelRegisterStudy> call, Throwable t) {
                    Log.e(TAG, "faillllllllllllll" + t.getMessage());
                }
            });
        });
    }

        @Override
        public boolean onTouchEvent (MotionEvent event){
            // 바깥 레이어 클릭시 닫히지 않도록
            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                return false;
            }
            return true;
        }

        private OkHttpClient provideOkHttpClient () {
            OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
            okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
            okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
            okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
            return okhttpClientBuilder.build();
        }

}

