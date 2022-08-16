package com.example.studit.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
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
import com.example.studit.retrofit.home.ModelHomeResult;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.search.FragSearchStudyModel;
import com.example.studit.search.SearchActivity;
import com.example.studit.study.studyhome.StudyHomeActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
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
    private SharedPreferences preferences;

    ImageView ic_search, ic_alarm;

    Link link = new Link();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_home, container, false);

        TextView nickname = view.findViewById(R.id.profile_nickname);

        ic_alarm = view.findViewById(R.id.home_ic_alarm);

        ic_search = view.findViewById(R.id.home_ic_search);
        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

//        Button btn_search = view.findViewById(R.id.home_btn_search);
//        btn_search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), SearchActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Button btn_study = view.findViewById(R.id.home_btn_study);
//        btn_study.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), StudyHomeActivity.class);
//                startActivity(intent);
//            }
//        });

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

        preferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");


        Call<ModelHomeResult> callGetHomeResponse = retrofitInterface.getHomeList("Bearer " + token);
        callGetHomeResponse.enqueue(new Callback<ModelHomeResult>() {
            @Override
            public void onResponse(@NonNull Call<ModelHomeResult> call, @NonNull retrofit2.Response<ModelHomeResult> response) {
                ModelHomeResult homeResult = response.body();
                if (response.code() == 200) {
                    Log.d("home", "성공");

                    assert homeResult != null;
                    if (homeResult.getResult().getStudies() != null) {
                        for (int i = 0; i < homeResult.getResult().getStudies().size(); i++) {
                            HomeModelArrayList.add(new FragHomeStudyModel(homeResult.getResult().getStudies().get(i).getName(), homeResult.getResult().getStudies().get(i).getIntro()));
                        }
                    }
                    nickname.setText(homeResult.getResult().getNickname() + "님 환영합니다!");

                    HomeStudyAdapter.notifyDataSetChanged();

                } else if (response.code() == 401) {
                    Log.d("home", "Unauthorized");
                } else if (response.code() == 403) {
                    Log.d("home", "Forbidden");

                } else if (response.code() == 404) {
                    Log.d("home", "Not Found");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelHomeResult> call, @NonNull Throwable t) {
                Log.d("home", t.getMessage());
            }
        });

        preferences = this.getActivity().getSharedPreferences("fcm", Context.MODE_PRIVATE);
        String fcmToken = preferences.getString("fcmToken", "");
        Call<Void> callMainResponse = retrofitInterface.patchFcmToken("Bearer " + token, fcmToken);
        callMainResponse.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull retrofit2.Response<Void> response) {
                if (response.code() == 200) {
                    Log.d("fcmToken", "성공");

                } else if (response.code() == 401) {
                    Log.d("fcmToken", "Unauthorized");
                } else if (response.code() == 403) {
                    Log.d("fcmToken", "Forbidden");

                } else if (response.code() == 404) {
                    Log.d("fcmToken", "Not Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.d("main", t.getMessage());
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
