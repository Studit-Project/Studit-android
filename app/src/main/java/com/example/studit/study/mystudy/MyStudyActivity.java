package com.example.studit.study.mystudy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.main.MainActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.retrofit.study.ModelStudyDetail;
import com.example.studit.retrofit.study.ModelStudyResult;
import com.example.studit.search.FragSearchStudyModel;
import com.example.studit.study.FragStudy;
import com.example.studit.study.studyhome.StudyHomeActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyStudyActivity extends AppCompatActivity {

    private final ArrayList<MyStudyActivityGridModel> MyStudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    MyStudyActivityAdapter MyStudyAdapter;
    private SharedPreferences preferences;

    TextView study_name;


    Button board, setting, delete, mandate, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_setting);

        study_name = findViewById(R.id.my_study_name);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

//        Intent intent = getIntent();
//        int studyId = intent.getExtras().getInt("studyId");
//        Intent intent2 = getIntent();
//        String userPhone = intent2.getExtras().getString("userPhone");

        Intent intent = new Intent(this.getIntent());
        int studyId = intent.getIntExtra("studyId", 1);

        Link link = new Link();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = findViewById(R.id.my_study_recycler);
        recyclerView.setHasFixedSize(true);

        MyStudyAdapter = new MyStudyActivityAdapter(MyStudyModelArrayList, this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(MyStudyAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        board = findViewById(R.id.my_study_text_board); //스터디 게시판 - 타 스터디
        board.setOnClickListener(view -> {

        });

        setting = findViewById(R.id.my_study_text_set); //스터디 관리
        setting.setOnClickListener(view -> {
            Intent intent2 = new Intent(getApplication(), MyStudySetActivity.class);
            intent2.putExtra("studyId2", studyId);
            startActivity(intent2);
        });

        delete = findViewById(R.id.my_study_text_delete); // 스터디 없애기
        delete.setOnClickListener(view -> {
            Call<Void> callStudyIdResponse = retrofitInterface.deleteStudyByStudyId(Long.parseLong(String.valueOf(studyId)), "Bearer " + token);
            callStudyIdResponse.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                    if (response.code() == 200) {
                        System.out.println("성공");
                        Toast.makeText(getApplicationContext(), "스터디를 삭제했습니다", Toast.LENGTH_LONG).show();
                        Intent intent3 = new Intent(getApplication(), StudyHomeActivity.class);
                        startActivity(intent3);

                    } else if (response.code() == 401) {
                        System.out.println("Unauthorized");
                    } else if (response.code() == 403) {
                        System.out.println("Forbidden");
                    } else if (response.code() == 404) {
                        System.out.println("Not Found");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    System.out.println("=============" + t.getMessage());
                }
            });
        });

        mandate = findViewById(R.id.my_study_text_mandate); //스터디 위임
        mandate.setVisibility(View.INVISIBLE);
        mandate.setOnClickListener(view -> {

        });

        exit = findViewById(R.id.my_study_text_exit); //스터디 나가기 - 타 스터디
        exit.setOnClickListener(view -> {
            Call<Void> callNewStudyMemberIdResponse = retrofitInterface.deleteStudyExitByStudyId(Long.parseLong(String.valueOf(studyId)), "Bearer " + token);
            callNewStudyMemberIdResponse.enqueue(new Callback<Void>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                    System.out.println("안에 들어옴");
                    System.out.println(response.code());
                    if (response.code() == 200) {
                        System.out.println("성공");
                        Toast.makeText(getApplicationContext(), "스터디를 나갔습니다", Toast.LENGTH_LONG).show();

                    } else if (response.code() == 401) {
                        System.out.println("Unauthorized");
                    } else if (response.code() == 403) {
                        System.out.println("Forbidden");
                    } else if (response.code() == 404) {
                        System.out.println("Not Found");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    System.out.println("=============" + t.getMessage());
                }
            });
        });

        Call<ModelStudyResult> callStudyIdResponse = retrofitInterface.getStudyByStudyId(Long.parseLong(String.valueOf(studyId)), "Bearer " + token);
        callStudyIdResponse.enqueue(new Callback<ModelStudyResult>() {
            @Override
            public void onResponse(@NonNull Call<ModelStudyResult> call, @NonNull retrofit2.Response<ModelStudyResult> response) {
                ModelStudyResult StudyDetailResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");


                    assert StudyDetailResponse != null;
                    for (int i = 0; i < StudyDetailResponse.getResult().getFollowers().size(); i++) {
                        MyStudyModelArrayList.add(new MyStudyActivityGridModel(StudyDetailResponse.getResult().getFollowers().get(i).getEmail(), StudyDetailResponse.getResult().getFollowers().get(i).getId(), StudyDetailResponse.getResult().getFollowers().get(i).getNickname(), StudyDetailResponse.getResult().getFollowers().get(i).getUsername(), studyId));

                    }

                    MyStudyAdapter.notifyDataSetChanged();
                    study_name.setText(StudyDetailResponse.getResult().getName());

                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");
                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ModelStudyResult> call, @NonNull Throwable t) {
                System.out.println("=============" + t.getMessage());
            }
        });

        ImageView back = findViewById(R.id.my_study_ic_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyStudyActivity.this, StudyHomeActivity.class);
                startActivity(intent);
            }
        });


    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }
}
