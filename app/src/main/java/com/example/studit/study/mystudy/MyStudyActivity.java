package com.example.studit.study.mystudy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.main.MainActivity;

import java.util.ArrayList;

public class MyStudyActivity extends AppCompatActivity {

    private final ArrayList<MyStudyActivityGridModel> MyStudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    MyStudyActivityAdapter MyStudyAdapter;

    Button board, setting, delete, mandate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_setting);

        board = findViewById(R.id.my_study_text_board);
        board.setOnClickListener(view -> {

        });

        setting = findViewById(R.id.my_study_text_set);
        setting.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MyStudySetActivity.class);
            startActivity(intent);
        });

        delete = findViewById(R.id.my_study_text_delete);
        delete.setOnClickListener(view -> {

        });

        mandate = findViewById(R.id.my_study_text_mandate);
        mandate.setOnClickListener(view -> {

        });

        MyStudyModelArrayList.add(new MyStudyActivityGridModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudyModelArrayList.add(new MyStudyActivityGridModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));

        recyclerView = findViewById(R.id.my_study_recycler);
        recyclerView.setHasFixedSize(true);

        MyStudyAdapter = new MyStudyActivityAdapter(MyStudyModelArrayList, this);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(MyStudyAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
