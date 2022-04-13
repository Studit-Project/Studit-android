package com.example.studit.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.search.SearchActivity;

import java.util.ArrayList;

public class FragHome extends Fragment {
    private View view;

    private final ArrayList<FragHomeStudyModel> StudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragHomeStudyAdapter HomeStudyAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_home, container, false);

        StudyModelArrayList.add(new FragHomeStudyModel("스터디1", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ", "+395일", "0"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디2", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ", "+395일", "1"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디3", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ", "+395일", "2"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디4", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ", "+395일", "3"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디5", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ", "+395일", "4"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디6", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ", "+395일", "5"));

        recyclerView = view.findViewById(R.id.home_recycler_study);
        recyclerView.setHasFixedSize(true);

        HomeStudyAdapter = new FragHomeStudyAdapter(StudyModelArrayList, getContext());
        recyclerView.setAdapter(HomeStudyAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ImageView btn_search = view.findViewById(R.id.home_ic_search);  //검색 버튼, 엔터..기능도 넣어야할듯?
        btn_search.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });


        return view;
    }
}
