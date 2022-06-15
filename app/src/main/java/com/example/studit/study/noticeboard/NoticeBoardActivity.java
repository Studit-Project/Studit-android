package com.example.studit.study.noticeboard;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class NoticeBoardActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    NoticeBoardAdapter mAdapter;
    ArrayList<NoticeBoardModel> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_notice_board);

        mRecyclerView = findViewById(R.id.recycler_notice);
        mArrayList = new ArrayList<>();

        mAdapter = new NoticeBoardAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.add(new NoticeBoardModel("스터기 공지사항1", "김수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항2", "곽수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항3", "깅수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항4", "황수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항5", "최수정"));

        mAdapter.notifyDataSetChanged();
    }
}
