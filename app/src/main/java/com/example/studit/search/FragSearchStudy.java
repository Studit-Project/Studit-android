package com.example.studit.search;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.home.FragHomeStudyAdapter;
import com.example.studit.home.FragHomeStudyModel;
import com.example.studit.retrofit.Model_PostAllList;
import com.example.studit.retrofit.RetrofitClient;
import com.example.studit.retrofit.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragSearchStudy extends Fragment {
    private View view;

    String BASE_URL = "http://54.180.97.161:8081/";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1MzkwODM5LCJpYXQiOjE2NTUzODkwMzl9.h23Fx81mQuTTjQ1LuqL96C8DNkeGB_MmvhyGjxPLHwU";

    public FragSearchStudy() {
    }

    public static FragSearchStudy newInstance() {
        FragSearchStudy fragSearchStudy = new FragSearchStudy();
        Bundle bundle = new Bundle();
        return fragSearchStudy;
    }

    private final ArrayList<FragSearchStudyModel> StudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragSearchStudyAdapter studyAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_study, container, false);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")))
                .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context)
                        -> LocalDate.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .registerTypeAdapter(LocalTime.class, (JsonDeserializer<LocalTime>) (json, typeOfT, context)
                        -> LocalTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("HH:mm:ss")))
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<Model_PostAllList> callPostAllResponse = retrofitInterface.getPostListByAll("Bearer " + token);
        callPostAllResponse.enqueue(new Callback<Model_PostAllList>() {
            @Override
            public void onResponse(@NonNull Call<Model_PostAllList> call, @NonNull retrofit2.Response<Model_PostAllList> response) {
                Model_PostAllList PostALLResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");
                    assert PostALLResponse != null;
                    String s = "";
                    for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                        if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                            s = "모집중";
                        StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), PostALLResponse.getPosts().get(i).getLocalDateTime(), s));
                    }

                    recyclerView = view.findViewById(R.id.search_study_list);
                    recyclerView.setHasFixedSize(true);

                    studyAdapter = new FragSearchStudyAdapter(StudyModelArrayList, getContext());
                    recyclerView.setAdapter(studyAdapter);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");

                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<Model_PostAllList> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return view;
    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }
}
