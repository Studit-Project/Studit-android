package com.example.studit.study;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.studit.R;


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


        return view;
    }
}