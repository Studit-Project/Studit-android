package com.example.studit.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class FragHome extends Fragment {
    private View view;

    private ArrayList<FragHomeStudyModel> StudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragHomeStudyAdapter HomeStudyAdapter;

    String title[] = {"스터디1", "스터디2", "스터디3", "스터디4", "스터디5"};
    String info[] = {"스터디1dasfsdf", "스터디2adfasdfsadf", "스터디3adfdsafsadf", "스터디4asdfasdfsdafasdf", "스터디5adsfsadfsdafasdfadsfa"};
    String day[] = {"+395일", "+195일", "+325일", "+205일", "+175일"};

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_home, container, false);

        StudyModelArrayList.add(new FragHomeStudyModel("스터디1", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ","+395일", "0"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디2", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ","+395일", "1"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디3", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ","+395일", "2"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디4", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ","+395일", "3"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디5", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ","+395일", "4"));
        StudyModelArrayList.add(new FragHomeStudyModel("스터디6", "ㅁ아리나어ㅣ렁ㄴ러ㅣㅁㄴ","+395일", "5"));

        recyclerView = view.findViewById(R.id.home_recycler_study);
        recyclerView.setHasFixedSize(true);

        HomeStudyAdapter = new FragHomeStudyAdapter(StudyModelArrayList, getContext());
        recyclerView.setAdapter(HomeStudyAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}
