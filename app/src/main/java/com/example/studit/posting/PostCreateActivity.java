package com.example.studit.posting;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studit.R;
import com.example.studit.login.LoginActivity;
import com.example.studit.main.MainActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.posting.ModelPostCreate;
import com.example.studit.retrofit.study.registerstudy.ModelRegisterStudy;
import com.example.studit.study.registerstudy.RegisterStudyActivity;
import com.example.studit.study.registerstudy.RegisterStudyAdapter;
import com.example.studit.study.registerstudy.RegisterStudyModel;
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

public class PostCreateActivity extends AppCompatActivity {
    final private String TAG = getClass().getSimpleName();

    Link link = new Link();

    EditText title, content;
    Spinner category, province, activity, target, gender;
    Button register;

    String userid = "";
    Intent intent;

    private final ArrayList<PostCreateModel> PostList = new ArrayList<>();
    PostCreateAdapter adapter;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posting_new);

        // userID를 변수로 받음 ( 수정필요할듯 토큰을 받아오는 형식으로 ..? 아니면 본인 유저아이디 받아오도록)
        userid = getIntent().getStringExtra("userID");

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

        intent = getIntent();

        // 컴포넌트 초기화
        title = findViewById(R.id.title_post);
        content = findViewById(R.id.content_post);

        Spinner category = findViewById(R.id.field_spinner);
//        category.setAdapter(adapter);

        Spinner province = findViewById(R.id.region_spinner);
//        province.setAdapter(adapter);

        Spinner activity = findViewById(R.id.activity_spinner);
//        activity.setAdapter(adapter);

        Spinner target = findViewById(R.id.age2_spinner);
//        target.setAdapter(adapter);

        Spinner gender = findViewById(R.id.sex_spinner);
//        gender.setAdapter(adapter);

        // 등록하기 버튼 누를 경우
        register = findViewById(R.id.post_button);
        register.setOnClickListener(view -> {
            String Title = title.getText().toString();
            String Content = content.getText().toString();
            String Category = category.getSelectedItem().toString();
            String Province = province.getSelectedItem().toString();
            String Activity = activity.getSelectedItem().toString();
            String Target = target.getSelectedItem().toString();
            String Gender = gender.getSelectedItem().toString();

            // 정보를 모두 기입하지 않은 경우
            if (Title.equals("") || Content.equals("") || Category.equals("") || Province.equals("") || Activity.equals("") ||
                    Target.equals("") || Gender.equals("") ) {
                Log.e(TAG, "내용 입력 필요");
                AlertDialog.Builder builder = new AlertDialog.Builder(PostCreateActivity.this);
                builder.setTitle("알림")
                        .setMessage("정보를 모두 기입해주세요.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            ModelPostCreate modelPostCreate = new ModelPostCreate(Activity, Content, Title, Target, Province, Category, Gender);
            Call<ModelPostCreate> call = retrofitInterface.postPostCreate(modelPostCreate);

            call.enqueue(new Callback<ModelPostCreate>() {
                @Override
                public void onResponse(Call<ModelPostCreate> call, Response<ModelPostCreate> response) {
                    ModelPostCreate responsebody = response.body();

                    if (response.isSuccessful() && response.body() != null) {
                        Log.e(TAG, "게시 성공!");
                        Toast.makeText(PostCreateActivity.this, "게시글이 등록되었습니다.", Toast.LENGTH_SHORT).show();

                        // 게시글 등록 성공시 리스트 화면으로 이동
                        Intent intent = new Intent(PostCreateActivity.this, MainActivity.class); // 여기 메인액티비티변경~
//                        intent.putExtra("userId", userID);
                        startActivity(intent);
                        PostCreateActivity.this.finish();

                    } else{
                        try {
                            String body = response.errorBody().string();
                            Log.e(TAG, "error in postcreateactivity" + body);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ModelPostCreate> call, Throwable t) {
                    Log.e(TAG, "faillllllllllllll" + t.getMessage());
                }
            });
        });
    }
    private OkHttpClient provideOkHttpClient () {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }
}