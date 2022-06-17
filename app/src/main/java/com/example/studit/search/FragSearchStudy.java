package com.example.studit.search;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragSearchStudy extends Fragment {
    private View view;

    String BASE_URL = "http://54.180.97.161:8081/";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1NDg1OTMxLCJpYXQiOjE2NTU0ODQxMzF9.cQQ1LOZFphoL5KORAjp-TyEGo4pbvQW5FxYh37P809I";

    boolean bool_text_filter = true;

    public FragSearchStudy() {
    }

    public static FragSearchStudy newInstance() {
        FragSearchStudy fragSearchStudy = new FragSearchStudy();
        Bundle bundle = new Bundle();
        return fragSearchStudy;
    }
    //private final ArrayList<FragSearchStudyModel> StudyModelArrayListFilter = new ArrayList<>();

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

//        String[] s1 = new String[0], s2 = new String[0], s3 = new String[0], s4 = new String[0];
//
//        Bundle bundle = getArguments();
//        if(bundle != null){
//            s1 = bundle.getStringArray("activities");
//            s2 = bundle.getStringArray("targets");
//            s3 = bundle.getStringArray("provinces");
//            s4 = bundle.getStringArray("genders");
//        }
//
//
//        TextView text_filter_apply = view.findViewById(R.id.search_text_filter_apply);
//        String[] finalS = s1;
//        String[] finalS1 = s2;
//        String[] finalS2 = s3;
//        String[] finalS3 = s4;
//        text_filter_apply.setOnClickListener(view -> {
//            if(bool_text_filter) {
//                Call<Model_PostAllList> callPostFilterResponse = retrofitInterface.getPostListByFilter(finalS, finalS1, finalS2, finalS3, "Bearer " + token);
//                callPostFilterResponse.enqueue(new Callback<Model_PostAllList>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Model_PostAllList> call, @NonNull retrofit2.Response<Model_PostAllList> response) {
//                        Model_PostAllList PostALLResponse = response.body();
//                        if (response.code() == 200) {
//                            System.out.println("성공");
//                            assert PostALLResponse != null;
//                            String s = "";
//
//                            for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
//                                if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
//                                    s = "모집중";
//                                StudyModelArrayListFilter.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));
//
//                                System.out.println(PostALLResponse.getPosts().get(i).getId() + " ===== " + PostALLResponse.getPosts().get(i).getTitle());
//                            }
//
//                            recyclerView = view.findViewById(R.id.search_study_list);
//                            recyclerView.setHasFixedSize(true);
//
//                            studyAdapter = new FragSearchStudyAdapter(StudyModelArrayListFilter, getContext());
//                            recyclerView.setAdapter(studyAdapter);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//                        } else if (response.code() == 401) {
//                            System.out.println("Unauthorized");
//                        } else if (response.code() == 403) {
//                            System.out.println("Forbidden");
//                        } else if (response.code() == 404) {
//                            System.out.println("Not Found");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Model_PostAllList> call, @NonNull Throwable t) {
//                        System.out.println(t.getMessage());
//                    }
//                });
//                bool_text_filter = false;
//            }
//            else{
//                Call<Model_PostAllList> callPostAllResponse2 = retrofitInterface.getPostListByAll("Bearer " + token);
//                callPostAllResponse2.enqueue(new Callback<Model_PostAllList>() {
//                    @Override
//                    public void onResponse(@NonNull Call<Model_PostAllList> call, @NonNull retrofit2.Response<Model_PostAllList> response) {
//                        Model_PostAllList PostALLResponse = response.body();
//                        if (response.code() == 200) {
//                            System.out.println("성공");
//                            assert PostALLResponse != null;
//                            String s = "";
//                            for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
//                                if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
//                                    s = "모집중";
//
//                                StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), PostALLResponse.getPosts().get(i).getLocalDateTime(), s));
//
//                            }
//
//                            @SuppressWarnings("unchecked")
//                            ArrayList<FragSearchStudyModel> StudyModelArrayList = (ArrayList<FragSearchStudyModel>) getActivity().getIntent().getSerializableExtra("filterResult");
//
//                            recyclerView = view.findViewById(R.id.search_study_list);
//                            recyclerView.setHasFixedSize(true);
//
//                            studyAdapter = new FragSearchStudyAdapter(StudyModelArrayList, getContext());
//                            recyclerView.setAdapter(studyAdapter);
//                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//                            recyclerView.setLayoutManager(layoutManager);
//                            recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//                        } else if (response.code() == 401) {
//                            System.out.println("Unauthorized");
//                        } else if (response.code() == 403) {
//                            System.out.println("Forbidden");
//
//                        } else if (response.code() == 404) {
//                            System.out.println("Not Found");
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(@NonNull Call<Model_PostAllList> call, @NonNull Throwable t) {
//                        System.out.println(t.getMessage());
//                    }
//                });
//                bool_text_filter = true;
//            }
//        });


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
