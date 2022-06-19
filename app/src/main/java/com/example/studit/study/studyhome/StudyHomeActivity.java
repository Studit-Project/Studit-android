package com.example.studit.study.studyhome;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.Model_UserLogIn;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.study.ModelLeader;
import com.example.studit.retrofit.studyhome.ModelStudyLeader;
import com.example.studit.retrofit.studyhome.ModelStudyList;
import com.example.studit.retrofit.studyhome.ModelStudyListAll;
import com.example.studit.retrofit.studyhome.ModelStudyParticipatedMembers;
import com.example.studit.search.FragSearchStudyModel;
import com.example.studit.study.FragMakeStudy;
import com.example.studit.study.ListStudyActivity;
import com.example.studit.study.mystudy.MyStudyActivity;
import com.example.studit.study.mystudy.MyStudyActivityGridModel;
import com.example.studit.study.mystudy.MyStudySetActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.jar.Attributes;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudyHomeActivity extends AppCompatActivity {

    ImageButton addstudy;
    ImageView leader;
    StudyHomeAdapter adapterST, adapterMem;
    TextView study_name, state;
    ArrayList<Recycler_item> items = new ArrayList<>();
    public final ArrayList<StudyHomeModel> StudyHomeArrayList = new ArrayList<>();

    public static StudyHomeActivity newInstance() {
        StudyHomeActivity studyHomeActivity = new StudyHomeActivity();
        Bundle bundle = new Bundle();
        return studyHomeActivity;
    }

    Link link = new Link();

    String BASE_URL = "http://13.209.35.29:8081/";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1NTQ4NTkyLCJpYXQiOjE2NTU1NDY3OTJ9.wkdZIJOGKXzZdqbqeqgbng6-Bum8WAK6VuIu8uScHJ4";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_study_home);

        addstudy = (ImageButton) findViewById(R.id.addstudy);
        study_name = (TextView) findViewById(R.id.list_study_title);
        RecyclerView recyclerView = findViewById(R.id.recycler_study);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.sssss")))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")))
                .create();

        // addstudy 버튼 클릭시 스터디 작성할 수 있는 화면으로 이동
        addstudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudyHomeActivity.this, FragMakeStudy.class);
                startActivity(intent);
                StudyHomeActivity.this.finish();
            }
        });

        // 스터디 내부 접속


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

//        ArrayList<ModelStudyLeader> modelLeader = new ArrayList<>();
//        modelLeader.add(new ModelStudyLeader("", 0, "", "" ));
//
//        ArrayList<ModelStudyParticipatedMembers> participatedMembers = new ArrayList<>();
//        participatedMembers.add(new ModelStudyParticipatedMembers("", 0 ,"", ""));
//
//        ArrayList<ModelStudyList> studylist = new ArrayList<>();
//        studylist.add(new ModelStudyList("", 0, 0, modelLeader , "", "", participatedMembers));

        StudyHomeArrayList.add(new StudyHomeModel("dddd", 0, "ON", 0));
        StudyHomeArrayList.add(new StudyHomeModel("dddd", 0, "ON", 0));
        StudyHomeArrayList.add(new StudyHomeModel("dddd", 0, "ON", 0));
        StudyHomeArrayList.add(new StudyHomeModel("dddd", 0, "ON", 0));
        StudyHomeArrayList.add(new StudyHomeModel("dddd", 0, "ON", 0));

        Call<ModelStudyListAll> callStudyResponse = retrofitInterface.getStudyList(link.getToken());

        callStudyResponse.enqueue(new Callback<ModelStudyListAll>() {
            @Override
            public void onResponse(@NonNull Call<ModelStudyListAll> call, @NonNull retrofit2.Response<ModelStudyListAll> response) {
                ModelStudyListAll StudyResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");

                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<String> arrayList1 = new ArrayList<>();
//                    ArrayList<String> mem = new ArrayList<>();

                    assert StudyResponse != null;
                    for (int i = 0; i < StudyResponse.getStudyLists().size(); i++) {
//                        StudyHomeArrayList.add(new StudyHomeModel(StudyResponse.getCurrentNum().get(i).getName(), StudyResponse.getCurrentNum().get(i).getId(), StudyResponse.getCurrentNum().get(i).getState()));
//                        arrayList.add(StudyResponse.getCurrentNum().get(i).getName());
//                        arrayList1.add(StudyResponse.getCurrentNum().get(i).getState());

                    }

                    arrayList.add("min");
                    arrayList.add("min2");
                    arrayList.add("min3");
                    arrayList.add("min4");
                    arrayList1.add("ON");
                    arrayList1.add("OFF");
                    arrayList1.add("ON");
                    arrayList1.add("ON");

                    Intent intent2 = new Intent(getApplicationContext(), MyStudySetActivity.class);
                    intent2.putExtra("name", arrayList);
                    intent2.putExtra("state", arrayList1);
//                    for (int i = 0; i < StudyResponse.getLeader().size(); i++) {
//                        arrayList.add(StudyResponse.getName());
//                        arrayList1.add(StudyResponse.getActivity());
//
//                        for (int j = 0; j < StudyResponse.getParticipatedMembers().size(); j++) {
//                            items.add(new Recycler_item(StudyResponse.getParticipatedMembers().get(j).getEmail(), StudyResponse.getParticipatedMembers().get(j).getId(), StudyResponse.getParticipatedMembers().get(j).getNickname(), StudyResponse.getParticipatedMembers().get(j).getUserName()));
//
//                            mem.add(StudyResponse.getParticipatedMembers().get(i).getNickname());
//
//                            // 스터디장 표시
//                            if (mem.equals(StudyResponse.getLeader().get(i).getNickname())) {
//                                leader.setVisibility(View.VISIBLE);
//                            } else {
//                                leader.setVisibility(View.GONE);
//                            }
//                        }

//                    }
                    Intent intent1 = new Intent(getApplicationContext(), MyStudyActivity.class);
                    startActivity(intent1);

                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");
                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelStudyListAll> call, @NonNull Throwable t) {
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

    public static class Study_item {
        String name;
        String activity;
        ArrayList<String> mem = new ArrayList<>();

        Study_item(String name, String activity, ArrayList<String> mem) {
            this.name = name;
            this.activity = activity;
            this.mem = mem;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public ArrayList<String> getMem() {
            return mem;
        }

        public void setMem(ArrayList<String> mem) {
            this.mem = mem;
        }
    }

    public class Recycler_item {
        String email;
        int id;
        String nickname;
        String username;

        Recycler_item(String email, int id, String nickname, String username) {
            this.email = email;
            this.id = id;
            this.nickname = nickname;
            this.username = username;
        }
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

}
