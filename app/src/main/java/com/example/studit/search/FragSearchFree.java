package com.example.studit.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studit.R;

public class FragSearchFree  extends Fragment {
    private View view;

    public FragSearchFree () {}

    public static FragSearchFree newInstance() {
        FragSearchFree fragSearchFree = new FragSearchFree();
        Bundle bundle = new Bundle();
        return fragSearchFree;
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_free, container, false);

        return view;
    }
}