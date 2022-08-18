package com.example.studit.search;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.retrofit.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.time.LocalDateTime;
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


    boolean bool_text_filter = true;

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
    RecyclerView.LayoutManager layoutManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_study, container, false);

        Link link = new Link();

        SharedPreferences preferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        recyclerView = view.findViewById(R.id.chat_list);
        studyAdapter = new FragSearchStudyAdapter(StudyModelArrayList, getContext());

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(studyAdapter);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.sssss")))
                .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, typeOfT, context)
                        -> LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")))
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ModelPostAllList> callPostAllResponse = retrofitInterface.getPostListByAll("Bearer " + token);
        callPostAllResponse.enqueue(new Callback<ModelPostAllList>() {
            @Override
            public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                ModelPostAllList PostALLResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");
                    assert PostALLResponse != null;
                    String s = "";
                    for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                        if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                            s = "모집중";
                        StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));
                    }
                    studyAdapter.notifyDataSetChanged();


                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");

                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                System.out.println("=============" + t.getMessage());
            }
        });

        String[] s1 = new String[0], s2 = new String[0], s3 = new String[0], s4 = new String[0];

        Bundle bundle = getArguments();
        if (bundle != null) {
            s1 = bundle.getStringArray("activities");
            s2 = bundle.getStringArray("targets");
            s3 = bundle.getStringArray("provinces");
            s4 = bundle.getStringArray("genders");
        }


        TextView text_filter_apply = view.findViewById(R.id.chat_text);
        String[] finalS = s1;
        String[] finalS1 = s2;
        String[] finalS2 = s3;
        String[] finalS3 = s4;
        text_filter_apply.setOnClickListener(view -> {
            if (bool_text_filter) {
                text_filter_apply.setText("검색결과 필터 적용하기 click!");

                Call<ModelPostAllList> callPostFilterResponse = retrofitInterface.getPostListByFilter(finalS, finalS1, finalS2, finalS3, "Bearer " + token);
                callPostFilterResponse.enqueue(new Callback<ModelPostAllList>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                        ModelPostAllList PostALLResponse = response.body();
                        if (response.code() == 200) {
                            System.out.println("성공");
                            assert PostALLResponse != null;
                            String s = "";

                            StudyModelArrayList.clear();
                            for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                                if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                                    s = "모집중";

                                StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));
                            }
                            studyAdapter.notifyDataSetChanged();

                        } else if (response.code() == 401) {
                            System.out.println("Unauthorized");
                        } else if (response.code() == 403) {
                            System.out.println("Forbidden");
                        } else if (response.code() == 404) {
                            System.out.println("Not Found");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                bool_text_filter = false;
            } else {
                text_filter_apply.setText("검색결과 필터 적용해제하기 click!");

                Call<ModelPostAllList> callPostAllResponse2 = retrofitInterface.getPostListByAll("Bearer " + token);
                callPostAllResponse2.enqueue(new Callback<ModelPostAllList>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                        ModelPostAllList PostALLResponse = response.body();
                        if (response.code() == 200) {
                            System.out.println("성공");
                            assert PostALLResponse != null;
                            String s = "";
                            StudyModelArrayList.clear();
                            for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                                if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                                    s = "모집중";

                                StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));

                                System.out.println(PostALLResponse.getPosts().get(i).getId() + " ===== " + PostALLResponse.getPosts().get(i).getTitle());
                            }
                            studyAdapter.notifyDataSetChanged();

                        } else if (response.code() == 401) {
                            System.out.println("Unauthorized");
                        } else if (response.code() == 403) {
                            System.out.println("Forbidden");

                        } else if (response.code() == 404) {
                            System.out.println("Not Found");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                bool_text_filter = true;
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
