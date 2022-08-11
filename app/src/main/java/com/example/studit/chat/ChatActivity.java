package com.example.studit.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ArrayList<DataItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initData();

        RecyclerView recyvlerv = findViewById(R.id.recyvlerv);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyvlerv.setLayoutManager(manager);
        recyvlerv.setAdapter(new MyAdapter(dataList));

    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(new DataItem("시용자1님 입장했음", null, Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem("사용자2님 입장했음", null, Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem("안녕하세요11", "사용자1", Code.ViewType.LEFT_CONTENT));
        dataList.add(new DataItem("안녕하세요22", "사용자2", Code.ViewType.RIGHT_CONTENT));

    }
}
