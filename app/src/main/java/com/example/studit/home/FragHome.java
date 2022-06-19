package com.example.studit.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.join.InfoActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.ModelHomeList;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.search.FragSearchStudyModel;
import com.example.studit.search.SearchActivity;
import com.example.studit.study.studyhome.StudyHomeActivity;

import java.io.IOException;
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

    ImageView ic_search, ic_alarm;

    Link link = new Link();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_home, container, false);

        TextView nickname = view.findViewById(R.id.profile_nickname);

        ic_alarm = view.findViewById(R.id.home_ic_alarm);
        ic_alarm.setVisibility(View.INVISIBLE);

        ic_search = view.findViewById(R.id.home_ic_search);
        ic_search.setVisibility(View.INVISIBLE);
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        Button btn_search = view.findViewById(R.id.home_btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        Button btn_study = view.findViewById(R.id.home_btn_study);
        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudyHomeActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.home_recycler_study);
        recyclerView.setHasFixedSize(true);

        HomeStudyAdapter = new FragHomeStudyAdapter(HomeModelArrayList, getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(HomeStudyAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ModelHomeList> callPostAllResponse2 = retrofitInterface.getHomeList("Bearer " + link.getToken());
        callPostAllResponse2.enqueue(new Callback<ModelHomeList>() {
            @Override
            public void onResponse(@NonNull Call<ModelHomeList> call, @NonNull retrofit2.Response<ModelHomeList> response) {
                ModelHomeList homeList = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");

                    assert homeList != null;
                    if (homeList.getStudies() != null) {
                        for (int i = 0; i < homeList.getStudies().size(); i++) {
                            HomeModelArrayList.add(new FragHomeStudyModel(homeList.getStudies().get(i).getName(), homeList.getStudies().get(i).getIntro()));
                        }
                    }
                    System.out.println("===============================" + homeList.getNickname());


                    nickname.setText(homeList.getNickname() + "님 환영합니다!");

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
