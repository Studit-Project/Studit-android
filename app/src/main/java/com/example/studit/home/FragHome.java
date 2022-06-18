package com.example.studit.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.ModelHomeList;
import com.example.studit.search.FragSearchStudyModel;
import com.example.studit.search.SearchActivity;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragHome extends Fragment {
    private View view;

    private final ArrayList<FragHomeStudyModel> HomeModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragHomeStudyAdapter HomeStudyAdapter;

    String BASE_URL = "http://3.39.192.79:8081/";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1NTQ4NTkyLCJpYXQiOjE2NTU1NDY3OTJ9.wkdZIJOGKXzZdqbqeqgbng6-Bum8WAK6VuIu8uScHJ4";

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_home, container, false);

        TextView nickname = view.findViewById(R.id.profile_nickname);

        recyclerView = view.findViewById(R.id.home_recycler_study);
        recyclerView.setHasFixedSize(true);

        HomeStudyAdapter = new FragHomeStudyAdapter(HomeModelArrayList, getContext());
        recyclerView.setAdapter(HomeStudyAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(HomeStudyAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ModelHomeList> callHomeResponse = retrofitInterface.getHomeList("Bearer " + token);
        callHomeResponse.enqueue(new Callback<ModelHomeList>() {
            @Override
            public void onResponse(@NonNull Call<ModelHomeList> call, @NonNull retrofit2.Response<ModelHomeList> response) {
                ModelHomeList HomeResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");
                    assert HomeResponse != null;
                    if (HomeResponse.getStudies() != null) {
                        for (int i = 0; i < HomeResponse.getStudies().size(); i++) {
                            HomeModelArrayList.add(new FragHomeStudyModel(HomeResponse.getStudies().get(i).getName(), HomeResponse.getStudies().get(i).getIntro()));
                        }
                    }
                    System.out.println("===============================" + HomeResponse.getNickname());
                    nickname.setText(HomeResponse.getNickname() + "님 환영합니다!");

                    HomeStudyAdapter.notifyDataSetChanged();

                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");
                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelHomeList> call, @NonNull Throwable t) {
                System.out.println("=============" + t.getMessage());
            }
        });

        ImageView btn_search = view.findViewById(R.id.home_ic_search);  //검색 버튼, 엔터..기능도 넣어야할듯?
        btn_search.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
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
