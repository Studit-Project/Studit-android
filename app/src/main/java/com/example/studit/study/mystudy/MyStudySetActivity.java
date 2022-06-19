package com.example.studit.study.mystudy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.study.studyhome.StudyHomeActivity;

import java.util.ArrayList;
import java.util.HashMap;
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

    EditText edit_add, edit_del;
    Button btn_add, btn_del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_setting_set);

        SharedPreferences preferences = getSharedPreferences("userLogin", MODE_PRIVATE);
        String userPhone = preferences.getString("userPhone", "");
        int studyId = preferences.getInt("studyId", -1); //todo 스터디 홈에서 가져와야함

        Intent intent = getIntent();
        ArrayList<String> arrayList = new ArrayList<>(); //todo null값 나와서 막아둠
        //intent.getExtras().getStringArrayList("nickname");
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        // intent.getExtras().getIntegerArrayList("followerId");
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < arrayList.size(); i++) {
            hashMap.put(arrayList.get(i), arrayList2.get(i));
            MyStudySetModelArrayList.add(new MyStudySetListModel(arrayList.get(i)));
        }
        MyStudySetModelArrayList.add(new MyStudySetListModel("min"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("min"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("min"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("min"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("min"));

        Link link = new Link();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        recyclerView = findViewById(R.id.my_study_recycler);
        recyclerView.setHasFixedSize(true);

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
            Call<Void> callNewStudyMemberIdResponse = retrofitInterface.postNewStudyMemberByStudyId(studyId, edit_add.getText().toString(), "Bearer " + link.getToken());
            callNewStudyMemberIdResponse.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                    if (response.code() == 200) {
                        System.out.println("성공");
                        Toast.makeText(getApplicationContext(), "스터디원을 등록했습니다", Toast.LENGTH_LONG).show();
                        MyStudySetModelArrayList.add(new MyStudySetListModel(edit_add.getText().toString()));
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

        btn_add.setOnClickListener(view -> { // 스터디원 삭제 및 강퇴
            Call<Void> callNewStudyMemberIdResponse = retrofitInterface.deleteStudyMemberByStudyId(studyId, hashMap.get(edit_del.getText().toString()), "Bearer " + link.getToken());
            callNewStudyMemberIdResponse.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                    if (response.code() == 200) {
                        System.out.println("성공");
                        Toast.makeText(getApplicationContext(), "스터디원을 삭제했습니다", Toast.LENGTH_LONG).show();
                        MyStudySetModelArrayList.remove(new MyStudySetListModel(edit_del.getText().toString()));
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
        back.setOnClickListener(view -> finish());

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
