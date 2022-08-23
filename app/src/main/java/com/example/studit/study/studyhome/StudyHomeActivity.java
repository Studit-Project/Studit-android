package com.example.studit.study.studyhome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.home.FragHome;
import com.example.studit.main.MainActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.studyhome.ModelStudyList;
import com.example.studit.retrofit.studyhome.ModelStudyListAll;
import com.example.studit.study.registerstudy.RegisterStudyActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class StudyHomeActivity extends AppCompatActivity {
    final private String TAG = getClass().getSimpleName();
    private View view;

    ImageButton addstudy, back;
    TextView title, activity ;

    Link link = new Link();

    ModelStudyListAll listAll;
    ModelStudyList result;

    private final ArrayList<StudyHomeModel> StudyList = new ArrayList<>();

    RecyclerView recyclerView;
    StudyHomeAdapter2 adapter;

    private SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studymgmt);

        addstudy = (ImageButton) findViewById(R.id.addstudy);
        back = (ImageButton) findViewById(R.id.back);
        title = (TextView) findViewById(R.id.list_study_title);
        activity = (TextView) findViewById(R.id.list_study_activity);

        SharedPreferences preferences = getSharedPreferences("userLogin", MODE_PRIVATE);

        // addstudy 버튼 클릭시 스터디 작성할 수 있는 화면으로 이동
        addstudy.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegisterStudyActivity.class);
            intent.putExtra("data", "Register Popup");
            startActivityForResult(intent, 1);
        });

        // back 클릭시 스터디 내부로 이동 *오류 수정 필요함
        back.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        // title 클릭시 스터디 내부로 이동 *오류 수정 필요함
//        title.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), MyStudyActivity.class);
//            startActivity(intent);
//        });

        recyclerView = findViewById(R.id.recycler_recruit);
        recyclerView.setHasFixedSize(true);

        adapter = new StudyHomeAdapter2(StudyList, this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        Gson gson = new Gson();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

//        StudyList.add(new StudyHomeModel("hihihi", "online", 0));
//        StudyList.add(new StudyHomeModel("hihihi1", "online", 1));
//        StudyList.add(new StudyHomeModel("hihihi2", "online", 2));
//        StudyList.add(new StudyHomeModel("hihihi3", "online", 3));
//        StudyList.add(new StudyHomeModel("hihihi4", "online", 4));

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//        ModelStudyListAll modelStudyList = new ModelStudyListAll(activity, title);
        Call<ModelStudyListAll> call = retrofitInterface.getData( "Bearer " + token);
        call.enqueue(new Callback<ModelStudyListAll>() {
            @Override
            public void onResponse(Call<ModelStudyListAll> call, @NonNull retrofit2.Response<ModelStudyListAll> response) {
                Log.e(TAG, "1");
                ModelStudyListAll listAll  = response.body();

                if (response.code() == 200) {
                    Log.d("studyhome", "success");

                    ArrayList<String> arrayList = new ArrayList<>();
                    ArrayList<String> arrayList2 = new ArrayList<>();
                    ArrayList<Integer> arrayList3 = new ArrayList<>();

                    assert listAll != null;
                    if (listAll.getResult() != null) {
                        for (int i = 0; i < listAll.getResult().size(); i++) {
                            StudyList.add(new StudyHomeModel(listAll.getResult().get(i).getName(), listAll.getResult().get(i).getActivity(), listAll.getResult().get(i).getId()));

                            arrayList.add(listAll.getResult().get(i).getName());
                            arrayList2.add(listAll.getResult().get(i).getActivity());
                            arrayList3.add(listAll.getResult().get(i).getId());

                            adapter.notifyDataSetChanged();
                        }

                        adapter.notifyDataSetChanged();

//                        arrayList.add("min");
//                        arrayList.add("min2");
//                        arrayList.add("min3");
//                        arrayList.add("min4");
//                        arrayList2.add("ONLINE");
//                        arrayList2.add("OFFLINE");
//                        arrayList2.add("ONLINE");
//                        arrayList2.add("ONLINE");
//                        arrayList3.add(0);
//                        arrayList3.add(2);
//                        arrayList3.add(3);
//                        arrayList3.add(4);

                    }

                } else if (response.code() == 401) {
                    Log.d("studyhome", "Unauthorized");
                } else if (response.code() == 403) {
                    Log.d("studyhome", "Forbidden");

                } else if (response.code() == 404) {
                    Log.d("studyhome", "Not Found");
                }

//                Log.d("StudyHomeActivity", listAll.toString());

            }

            @Override
            public void onFailure(Call<ModelStudyListAll> call, Throwable t) {
                Log.d("StudyHomeActivity", t.toString());
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


//    ImageButton addstudy;
//    ImageView leader;
//    StudyHomeAdapter adapter;
//    private final List<Recycler_item> items = new ArrayList<>();
//
//
//    public static StudyHomeActivity newInstance() {
//        StudyHomeActivity studyHomeActivity = new StudyHomeActivity();
//        Bundle bundle = new Bundle();
//        return studyHomeActivity;
//    }
//
//    private final ArrayList<String> title = new ArrayList<>();
//    RecyclerView recyclerView;
//    RecyclerView.LayoutManager layoutManager;
//
//    String BASE_URL = "http://13.209.35.29:8081/";
//    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1NTQ4NTkyLCJpYXQiOjE2NTU1NDY3OTJ9.wkdZIJOGKXzZdqbqeqgbng6-Bum8WAK6VuIu8uScHJ4";
//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Nullable
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.frag_study_home);
//
//        addstudy = (ImageButton) findViewById(R.id.addstudy);
//        RecyclerView recyclerView = findViewById(R.id.recycler_study);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new StudyHomeAdapter(title, items);
//        recyclerView.setAdapter(adapter);
//
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
//                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.sssss")))
//                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
//                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")))
//                .create();
//
//        // addstudy 버튼 클릭시 스터디 작성할 수 있는 화면으로 이동
//       addstudy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(StudyHomeActivity.this, FragMakeStudy.class);
//                startActivity(intent);
//                StudyHomeActivity.this.finish();
//            }
//        });
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(provideOkHttpClient())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        Call<ModelStudyList> callStudyResponse = retrofitInterface.getStudyList("Bearer " + token);
//        callStudyResponse.enqueue(new Callback<ModelStudyList>() {
//            @Override
//            public void onResponse(@NonNull Call<ModelStudyList> call, @NonNull retrofit2.Response<ModelStudyList> response) {
//                ModelStudyList StudyResponse = response.body();
//                if (response.code() == 200) {
//                    System.out.println("성공");
//
//                    ArrayList<String> state = new ArrayList<>();
//                    ArrayList<String> mem = new ArrayList<>();
//
//                    assert StudyResponse != null;
//
//                    title.add(StudyResponse.getName());
//                    state.add(StudyResponse.getActivity());
//
//                    for (int i = 0; i < StudyResponse.getParticipatedMembers().size(); i++) {
//                        items.add(new Recycler_item(StudyResponse.getParticipatedMembers().get(i).getEmail(), StudyResponse.getParticipatedMembers().get(i).getId(), StudyResponse.getParticipatedMembers().get(i).getNickname(), StudyResponse.getParticipatedMembers().get(i).getUserName()));
//
//                        mem.add(StudyResponse.getParticipatedMembers().get(i).getNickname());
//
//                        // 스터디장 설정하는 부분인데 어떻게 넣어야할지 ./..ㅜㅜ
//                        if (mem.equals(StudyResponse.getLeader().get(i).getNickname())) {
//                            leader.setVisibility(View.VISIBLE);
//                        } else {
//                            leader.setVisibility(View.GONE);
//                        }
//                    }
////                    StudyHomeAdapter.notifyDataSetChanged();
//
//                    title.add("study1");
//                    title.add("study2");
//                    title.add("study3");
//                    title.add("study4");
//                    state.add("ONLINE");
//                    state.add("OFFLINE");
//                    state.add("ON/OFF");
//                    state.add("ONLINE");
//
//                    Intent intent1 = new Intent(getApplicationContext(), MyStudyActivity.class);
//                    intent1.putExtra("title", title);
//
//                    startActivity(intent1);
//
//
//
//                } else if (response.code() == 401) {
//                    System.out.println("Unauthorized");
//                } else if (response.code() == 403) {
//                    System.out.println("Forbidden");
//                } else if (response.code() == 404) {
//                    System.out.println("Not Found");
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ModelStudyList> call, @NonNull Throwable t) {
//                System.out.println("=============" + t.getMessage());
//            }
//        });
//
////        title.add("스터디1");
////        title.add("스터디2");
////        title.add("스터디3");
////        title.add("스터디4");
////        title.add("스터디5");
////
////        items.add(new Recycler_item("", "김수정"));
////        items.add(new Recycler_item("", "곽수정"));
////        items.add(new Recycler_item("", "강수정"));
////        items.add(new Recycler_item("", "황수정"));
////        items.add(new Recycler_item("", "최수정"));
//
//    }
//
//    private OkHttpClient provideOkHttpClient() {
//        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
//        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
//        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
//        return okhttpClientBuilder.build();
//    }

//    public class Recycler_item {
//         String email;
//         int id;
//         String nickname;
//         String username;
//
//        Recycler_item(String email, int id, String nickname, String username) {
//            this.email = email;
//            this.id = id;
//            this.nickname = nickname;
//            this.username = username;
//        }
//        public String getEmail() {
//            return email;
//        }
//
//        public void setEmail(String email) {
//            this.email = email;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public void setId(int id) {
//            this.id = id;
//        }
//
//        public String getNickname() {
//            return nickname;
//        }
//
//        public void setNickname(String nickname) {
//            this.nickname = nickname;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//    }

}
