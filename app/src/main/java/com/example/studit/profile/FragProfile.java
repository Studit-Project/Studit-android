package com.example.studit.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.studit.profile.setting.SettingActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.profile.ModelProfileResult;
import com.google.gson.Gson;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragProfile extends Fragment {
    private View view;

    private final String TAG = this.getClass().getSimpleName();

    private final ArrayList<FragProfilePostModel> PostArrayList = new ArrayList<>();
    private final ArrayList<FragProfileBadgeModel> BadgeArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragProfilePostAdapter ProfilePostAdapter;
    FragProfileBadgeAdapter ProfileBadgeAdapter;
    private SharedPreferences preferences;

    private boolean isSuccess;
    private TextView mNick,mLevel,mStatus;
    private ImageView ic_settings;

    Link link = new Link();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_profile, container, false);

        preferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        mNick = view.findViewById(R.id.profile_userNick);
        mLevel = view.findViewById(R.id.profile_userLevel);
        mStatus = view.findViewById(R.id.profile_status);

        recyclerView = view.findViewById(R.id.profile_list_myPost);

        recyclerView.setHasFixedSize(true);
        ProfilePostAdapter = new FragProfilePostAdapter(PostArrayList,getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(ProfilePostAdapter);

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

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ModelProfileResult> call = retrofitInterface.getUserProfile("Bearer " + token);
        call.enqueue(new Callback<ModelProfileResult>() {
            @Override
            public void onResponse(@NonNull Call<ModelProfileResult> call, @NonNull Response<ModelProfileResult> response) {
                ModelProfileResult profileResult = response.body();
                if (response.code() == 200) {
                    Log.e(TAG, "프로필 result 받아오기 성공! 받아온 내용 : " + profileResult.toString());
                    assert profileResult != null;
                    mNick.setText(profileResult.getResult().getNickname());
                    mStatus.setText(profileResult.getResult().getStatusMessage());
                    mLevel.setText(profileResult.getResult().getLevel());

                    if (profileResult.getResult().getModelProfilePostings() != null){
                        Log.e(TAG, "post 불러오기 성공");
                        for (int i = 0; i < profileResult.getResult().getModelProfilePostings().size(); i++) {
                            PostArrayList.add(new FragProfilePostModel(profileResult.getResult().getModelProfilePostings().get(i).getCategory(), profileResult.getResult().getModelProfilePostings().get(i).getContent()));
                        }
                    } else { Log.e(TAG, "post 불러오기 실패"); }

                    ProfilePostAdapter.notifyDataSetChanged();

                } else if (response.code() == 401) {
                    Log.e(TAG, "401 : Unauthorized");
                } else if (response.code() == 403) {
                    Log.e(TAG, "403 : Forbidden");
                } else if (response.code() == 404) {
                    Log.e(TAG, "404 : Not Found");
                }

            }

            @Override
            public void onFailure(Call<ModelProfileResult> call, Throwable t) {
                Log.e(TAG, "Fail!!!!! : " + t.getMessage());
            }
        });

        //설정버튼으로 SettingActivity 넘어가기
        ic_settings = view.findViewById(R.id.bt_settings);
        ic_settings.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        });

        super.onCreate(savedInstanceState);
        recyclerView = view.findViewById(R.id.profile_recycler_badge);
        recyclerView.setHasFixedSize(true);

        //Call<ModelProfileUserList> call2 = retrofitInterface.getUserProfile(auth);


        //badge recyclerView
//        for(int i=0; i<5; i++){
//            ProfilePostArrayList.add(new FragProfileViewModel("iconName","badge"+i)); //뱃지 이미지(id), 이름(name)으로 변경
//       }
//      ProfilePostAdapter = new FragProfileBadgeAdapter(ProfilePostArrayList);
//      recyclerView.setAdapter(ProfilePostAdapter);
//      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
//      recyclerView.setLayoutManager(layoutManager);
//      recyclerView.setItemAnimator(new DefaultItemAnimator());


        //타임라인 Listview
//        posts.add(new FragProfileMyPostData("안녕하세요","2022.01.01"));
//        posts.add(new FragProfileMyPostData("안녕하세요","2022.01.01"));
//        posts.add(new FragProfileMyPostData("안녕하세요","2022.01.01"));

//        listView = (ListView)view.findViewById(R.id.profile_list_myPost);
//        postAdapter = new FragProfilePostAdapter(getContext(),posts);
//        listView.setAdapter(postAdapter);


        return view;

    }

}
