package com.example.studit.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studit.R;

public class FragSearchStudy extends Fragment {
    private View view;

    public FragSearchStudy () {}

    public static FragSearchStudy newInstance() {
        FragSearchStudy fragSearchStudy = new FragSearchStudy();
        Bundle bundle = new Bundle();
        return fragSearchStudy;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_study, container, false);

        return view;
    }
}
