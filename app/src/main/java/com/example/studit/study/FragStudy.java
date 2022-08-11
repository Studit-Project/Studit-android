package com.example.studit.study;


import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studit.R;
import com.example.studit.search.SearchActivity;
import com.example.studit.study.studyhome.StudyHomeActivity;


public class FragStudy extends Fragment {
    private View view;

    String userid = "";


    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_main_study, container, false);

        //userID 넘겨받기
        Bundle extra = getArguments();
        if (extra != null) {
            userid = extra.getString("userID");
        }

        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });

        Button button2 = view.findViewById(R.id.button2);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), StudyHomeActivity.class);
            startActivity(intent);
        });


        return view;
    }
}