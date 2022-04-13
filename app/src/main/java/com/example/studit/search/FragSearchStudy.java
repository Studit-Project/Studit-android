package com.example.studit.search;

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
import com.example.studit.home.FragHomeStudyAdapter;
import com.example.studit.home.FragHomeStudyModel;

import java.util.ArrayList;

public class FragSearchStudy extends Fragment {
    private View view;

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

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_study, container, false);

        StudyModelArrayList.add(new FragSearchStudyModel("모집중", "수정이들 모여라~!!", "#창업 #IT #성신여대"));
        StudyModelArrayList.add(new FragSearchStudyModel("모집중", "수정이들 모여라~!!", "#창업 #IT #성신여대"));
        StudyModelArrayList.add(new FragSearchStudyModel("모집중", "수정이들 모여라~!!", "#창업 #IT #성신여대"));
        StudyModelArrayList.add(new FragSearchStudyModel("모집중", "수정이들 모여라~!!", "#창업 #IT #성신여대"));
        StudyModelArrayList.add(new FragSearchStudyModel("모집중", "수정이들 모여라~!!", "#창업 #IT #성신여대"));
        StudyModelArrayList.add(new FragSearchStudyModel("모집중", "수정이들 모여라~!!", "#창업 #IT #성신여대"));

        recyclerView = view.findViewById(R.id.search_study_list);
        recyclerView.setHasFixedSize(true);

        studyAdapter = new FragSearchStudyAdapter(StudyModelArrayList, getContext());
        recyclerView.setAdapter(studyAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }
}
