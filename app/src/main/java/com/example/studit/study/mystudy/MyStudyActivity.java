package com.example.studit.study.mystudy;

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
import com.example.studit.search.FragSearchStudyModel;
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


    TextView study_name;


    Button board, setting, delete, mandate, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_setting);

        study_name = findViewById(R.id.my_study_name);

//        Intent intent = getIntent();
//        int studyId = intent.getExtras().getInt("studyId");
//        Intent intent2 = getIntent();
//        String userPhone = intent2.getExtras().getString("userPhone");
        SharedPreferences preferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        String userPhone = preferences.getString("userPhone", "");
        int studyId = preferences.getInt("studyId", -1); //todo 스터디 홈에서 가져와야함

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
            Intent intent3 = new Intent(getApplication(), MyStudySetActivity.class);
            startActivity(intent3);
        });

        delete = findViewById(R.id.my_study_text_delete); // 스터디 없애기
        delete.setOnClickListener(view -> {
            Call<Void> callStudyIdResponse = retrofitInterface.deleteStudyByStudyId(studyId, "Bearer " + link.getToken());
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
        mandate.setOnClickListener(view -> {

        });

        exit = findViewById(R.id.my_study_text_exit); //스터디 나가기 - 타 스터디
        exit.setOnClickListener(view -> {

        });

        MyStudyModelArrayList.add(new MyStudyActivityGridModel("dddd", 0, "min", "min"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("dddd", 0, "min", "min"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("dddd", 0, "min", "min"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("dddd", 0, "min", "min"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("dddd", 0, "min", "min"));

        Call<ModelStudyDetail> callStudyIdResponse = retrofitInterface.getStudyByStudyId(studyId, "Bearer " + link.getToken());
        callStudyIdResponse.enqueue(new Callback<ModelStudyDetail>() {
            @Override
            public void onResponse(@NonNull Call<ModelStudyDetail> call, @NonNull retrofit2.Response<ModelStudyDetail> response) {
                ModelStudyDetail StudyDetailResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");

                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<Integer> arrayList2 = new ArrayList<>();

                    assert StudyDetailResponse != null;
                    for (int i = 0; i < StudyDetailResponse.getFollowers().size(); i++) {
                        MyStudyModelArrayList.add(new MyStudyActivityGridModel(StudyDetailResponse.getFollowers().get(i).getEmail(), StudyDetailResponse.getFollowers().get(i).getId(), StudyDetailResponse.getFollowers().get(i).getNickname(), StudyDetailResponse.getFollowers().get(i).getUsername()));
                        arrayList.add(StudyDetailResponse.getFollowers().get(i).getNickname());
                        arrayList2.add(StudyDetailResponse.getFollowers().get(i).getId());
                    }

                    MyStudyAdapter.notifyDataSetChanged();

                    arrayList.add("min");
                    arrayList.add("min2");
                    arrayList.add("min3");
                    arrayList.add("min4");
                    arrayList2.add(0);
                    arrayList2.add(2);
                    arrayList2.add(3);
                    arrayList2.add(4);
                    Intent intent2 = new Intent(getApplicationContext(), MyStudySetActivity.class);
                    intent2.putExtra("nickname", arrayList);
                    intent2.putExtra("followerId", arrayList2);

                    startActivity(intent2);

                    study_name.setText(StudyDetailResponse.getName());
                    if (userPhone.equals(StudyDetailResponse.getLeader().getPhone())) {
                        exit.setVisibility(View.GONE);
                        setting.setVisibility(View.VISIBLE);
                        mandate.setVisibility(View.VISIBLE);
                        delete.setVisibility(View.VISIBLE);
                    } else {
                        exit.setVisibility(View.VISIBLE);
                        setting.setVisibility(View.GONE);
                        mandate.setVisibility(View.GONE);
                        delete.setVisibility(View.GONE);
                    }


                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");
                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ModelStudyDetail> call, @NonNull Throwable t) {
                System.out.println("=============" + t.getMessage());
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
