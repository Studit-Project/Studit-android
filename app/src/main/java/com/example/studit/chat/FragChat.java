package com.example.studit.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.home.FragHomeStudyAdapter;
import com.example.studit.home.FragHomeStudyModel;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.example.studit.search.FragSearchStudyAdapter;
import com.example.studit.search.FragSearchStudyModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragChat extends Fragment {
    private View view;

    private final ArrayList<FragSearchStudyModel> StudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragSearchStudyAdapter studyAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_chat, container, false);

        Link link = new Link();
        recyclerView = view.findViewById(R.id.search_study_list);
        studyAdapter = new FragSearchStudyAdapter(StudyModelArrayList, getContext());

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(studyAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ModelPostAllList> callPostAllResponse = retrofitInterface.getPostListByAll("Bearer " + link.getToken());
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
