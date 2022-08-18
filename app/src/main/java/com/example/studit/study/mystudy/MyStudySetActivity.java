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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.main.MainActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.study.ModelStudyResult;
import com.example.studit.search.SearchActivity;
import com.example.studit.study.studyhome.StudyHomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyStudySetActivity extends AppCompatActivity {

    private final ArrayList<MyStudySetListModel> MyStudySetModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    MyStudySetAdapter MyStudyAdapter;

    TextView study_name;
    EditText edit_add, edit_del;
    Button btn_add, btn_del;

    Map<String, Integer> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_setting_set);

        Intent intent = new Intent(this.getIntent());
        int studyId = intent.getIntExtra("studyId2", 0);

        SharedPreferences preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        Link link = new Link();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = findViewById(R.id.my_study_recycler);
        recyclerView.setHasFixedSize(true);

        study_name = findViewById(R.id.my_study_name);

        Call<ModelStudyResult> callStudyIdResponse = retrofitInterface.getStudyByStudyId(studyId, "Bearer " + token);
        callStudyIdResponse.enqueue(new Callback<ModelStudyResult>() {
            @Override
            public void onResponse(@NonNull Call<ModelStudyResult> call, @NonNull retrofit2.Response<ModelStudyResult> response) {
                ModelStudyResult StudyDetailResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");


                    assert StudyDetailResponse != null;
                    for (int i = 0; i < StudyDetailResponse.getResult().getFollowers().size(); i++) {
                        MyStudySetModelArrayList.add(new MyStudySetListModel(StudyDetailResponse.getResult().getFollowers().get(i).getId(), StudyDetailResponse.getResult().getFollowers().get(i).getNickname()));
                        map.put(StudyDetailResponse.getResult().getFollowers().get(i).getNickname(), StudyDetailResponse.getResult().getFollowers().get(i).getId());
                    }

                    MyStudyAdapter.notifyDataSetChanged();


                    study_name.setText(StudyDetailResponse.getResult().getName());
//                    if (userPhone.equals(StudyDetailResponse.getLeader().getPhone())) {
//                        exit.setVisibility(View.GONE);
//                        setting.setVisibility(View.VISIBLE);
//                        mandate.setVisibility(View.VISIBLE);
//                        delete.setVisibility(View.VISIBLE);
//                    } else {
//                        exit.setVisibility(View.VISIBLE);
//                        setting.setVisibility(View.GONE);
//                        mandate.setVisibility(View.GONE);
//                        delete.setVisibility(View.GONE);
//                    }


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

        MyStudyAdapter = new MyStudySetAdapter(MyStudySetModelArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(MyStudyAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        edit_add = findViewById(R.id.my_study_edit_add);
        btn_add = findViewById(R.id.my_study_btn_add);
        edit_del = findViewById(R.id.my_study_edit_del);
        btn_del = findViewById(R.id.my_study_btn_del);
//        edit_add.setVisibility(View.INVISIBLE);
//        btn_add.setVisibility(View.INVISIBLE);

        btn_add.setOnClickListener(view -> { // 스터디원 추가
            System.out.println("스터디원 추가 들어옴");
            Call<Void> callNewStudyMemberIdResponse = retrofitInterface.postNewStudyMemberByStudyId(Long.parseLong(String.valueOf(studyId)), new MyStudySetModel(edit_add.getText().toString()), "Bearer " + token);
            callNewStudyMemberIdResponse.enqueue(new Callback<Void>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                    System.out.println("안에 들어옴");
                    System.out.println(response.code());
                    if (response.code() == 200) {
                        System.out.println("성공");
                        Toast.makeText(getApplicationContext(), "스터디원을 등록했습니다", Toast.LENGTH_LONG).show();
                        MyStudySetModelArrayList.add(new MyStudySetListModel(1, edit_add.getText().toString()));
                        MyStudyAdapter.notifyDataSetChanged();

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

        btn_del.setOnClickListener(view -> { // 스터디원 삭제
            Call<Void> callNewStudyMemberIdResponse = retrofitInterface.deleteStudyMemberByStudyId(Long.parseLong(String.valueOf(studyId)), Long.parseLong(String.valueOf(map.get(edit_del.getText().toString()))), "Bearer " +token);
            callNewStudyMemberIdResponse.enqueue(new Callback<Void>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                    System.out.println("안에 들어옴");
                    System.out.println(response.code());
                    if (response.code() == 200) {
                        System.out.println("성공");
                        Toast.makeText(getApplicationContext(), "스터디원을 삭제했습니다", Toast.LENGTH_LONG).show();
                        MyStudySetModelArrayList.remove(new MyStudySetListModel(1, edit_del.getText().toString()));
                        MyStudyAdapter.notifyDataSetChanged();

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

        ImageView back = findViewById(R.id.my_study_ic_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyStudySetActivity.this, StudyHomeActivity.class);
                startActivity(intent);
            }
        });

//        Button plus = findViewById(R.id.my_study_btn_plus);
//        plus.setOnClickListener(view -> {
//            edit_add.setVisibility(View.VISIBLE);
//            btn_add.setVisibility(View.VISIBLE);
//        });

    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }
}
