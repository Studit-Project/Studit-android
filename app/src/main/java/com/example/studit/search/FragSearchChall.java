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

public class FragSearchChall  extends Fragment {
    private View view;

    public FragSearchChall () {}

    public static FragSearchChall newInstance() {
        FragSearchChall fragSearchChall = new FragSearchChall();
        Bundle bundle = new Bundle();
        return fragSearchChall;
    }

    private final ArrayList<FragSearchChallengeModel> challengeModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragSearchChallengeAdapter challengeAdapter;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search_chall, container, false);

        challengeModelArrayList.add(new FragSearchChallengeModel("도전 가능", "챌린지 같이하실 분~~!", "#아이스버킷 #챌린지", "35%"));
        challengeModelArrayList.add(new FragSearchChallengeModel("도전 가능", "챌린지 같이하실 분~~!", "#아이스버킷 #챌린지", "55%"));
        challengeModelArrayList.add(new FragSearchChallengeModel("도전 가능", "챌린지 같이하실 분~~!", "#아이스버킷 #챌린지", "66%"));

        recyclerView = view.findViewById(R.id.search_challenge_list);
        recyclerView.setHasFixedSize(true);

        challengeAdapter = new FragSearchChallengeAdapter(challengeModelArrayList, getContext());
        recyclerView.setAdapter(challengeAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }
}