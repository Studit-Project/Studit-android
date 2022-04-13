package com.example.studit.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studit.R;

public class FragSearchChall  extends Fragment {
    private View view;

    public FragSearchChall () {}

    public static FragSearchChall newInstance() {
        FragSearchChall fragSearchChall = new FragSearchChall();
        Bundle bundle = new Bundle();
        return fragSearchChall;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_chall, container, false);

        return view;
    }
}