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

import java.util.ArrayList;

public class FragSearchFree extends Fragment {
    private View view;

    public FragSearchFree() {
    }

    public static FragSearchFree newInstance() {
        FragSearchFree fragSearchFree = new FragSearchFree();
        Bundle bundle = new Bundle();
        return fragSearchFree;
    }

    private final ArrayList<MyStudyActivityModel> freeModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragSearchFreeAdapter freeAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_free, container, false);

        freeModelArrayList.add(new MyStudyActivityModel("자유", "만나서 전시회 다니실 분~", "#전시회 #공연", "0/3"));
        freeModelArrayList.add(new MyStudyActivityModel("자유", "만나서 전시회 다니실 분~", "#전시회 #공연", "0/3"));
        freeModelArrayList.add(new MyStudyActivityModel("자유", "만나서 전시회 다니실 분~", "#전시회 #공연", "0/3"));
        freeModelArrayList.add(new MyStudyActivityModel("자유", "만나서 전시회 다니실 분~", "#전시회 #공연", "0/3"));
        freeModelArrayList.add(new MyStudyActivityModel("자유", "만나서 전시회 다니실 분~", "#전시회 #공연", "0/3"));

        recyclerView = view.findViewById(R.id.search_free_list);
        recyclerView.setHasFixedSize(true);

        freeAdapter = new FragSearchFreeAdapter(freeModelArrayList, getContext());
        recyclerView.setAdapter(freeAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}