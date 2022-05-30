package com.example.studit.study.mystudy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;


public class MyStudySetActivity extends AppCompatActivity {

    private final ArrayList<MyStudySetListModel> MyStudySetModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    MyStudySetAdapter MyStudyAdapter;

    EditText edit_add;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_study_setting_set);


        edit_add = findViewById(R.id.my_study_edit_add);
        btn_add = findViewById(R.id.my_study_btn_add);

        edit_add.setVisibility(View.INVISIBLE);
        btn_add.setVisibility(View.INVISIBLE);

        edit_add.setOnClickListener(view -> {

        });


        btn_add.setOnClickListener(view -> {

        });

        ImageView back = findViewById(R.id.my_study_ic_back);
        back.setOnClickListener(view -> finish());

        Button plus = findViewById(R.id.my_study_btn_plus);
        plus.setOnClickListener(view -> {
            edit_add.setVisibility(View.VISIBLE);
            btn_add.setVisibility(View.VISIBLE);
        });

        MyStudySetModelArrayList.add(new MyStudySetListModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));
        MyStudySetModelArrayList.add(new MyStudySetListModel("http://static.hubzum.zumst.com/hubzum/2018/02/06/09/962ec338ca3b4153b037168ec92756ac.jpg", "김민영"));

        recyclerView = findViewById(R.id.my_study_recycler);
        recyclerView.setHasFixedSize(true);

        MyStudyAdapter = new MyStudySetAdapter(MyStudySetModelArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(MyStudyAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
