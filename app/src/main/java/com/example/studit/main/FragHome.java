package com.example.studit.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

public class FragHome extends Fragment {
    private View view;

    RecyclerView recyclerView;

    String name[] = {"스터디1", "스터디2", "챌린지1", "챌린지2"};


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_study);

//        MainStudyAdapter mainStudyAdapter = new MainStudyAdapter(getContext(), name);
//        recyclerView.setAdapter(mainStudyAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
