package com.example.studit.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.profile.SettingActivity;
import com.example.studit.profile.FragPreference;

import java.util.ArrayList;

public class FragProfile extends Fragment {
    private View view;
    private final ArrayList<FragProfileViewModel> ViewModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragProfileViewAdapter FragProfileViewAdapter;

    ArrayList<FragProfileMyPostData> posts = new ArrayList<>();
    ListView listView;
    private static FragProfilePostAdapter postAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_profile, container, false);

        super.onCreate(savedInstanceState);

        //뱃지 recyclerview
        recyclerView = view.findViewById(R.id.profile_recycler_badge);
        recyclerView.setHasFixedSize(true);

        for(int i=0; i<5; i++){
            ViewModelArrayList.add(new FragProfileViewModel("iconName","badge"+i));
        }
        FragProfileViewAdapter = new FragProfileViewAdapter(ViewModelArrayList);
        recyclerView.setAdapter(FragProfileViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //타임라인 Listview
        posts.add(new FragProfileMyPostData("안녕하세요","2022.01.01"));
        posts.add(new FragProfileMyPostData("안녕하세요","2022.01.01"));
        posts.add(new FragProfileMyPostData("안녕하세요","2022.01.01"));

        listView = (ListView)view.findViewById(R.id.profile_list_myPost);
        postAdapter = new FragProfilePostAdapter(getContext(),posts);
        listView.setAdapter(postAdapter);

        //설정버튼
        ImageView btn_edit = view.findViewById(R.id.btn_profile_edit);
        btn_edit.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingActivity.class);
            startActivity(intent);
        });

        return view;

    }

}
