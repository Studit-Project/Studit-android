package com.example.studit.chat;

import android.content.Intent;
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
        Intent intent = new Intent(this.getIntent());
        String s = intent.getStringExtra("getTitle");
        Long l = intent.getLongExtra("getId", 1);

        dataList.add(new DataItem("시용자1님 입장했음", null, Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem("사용자2님 입장했음", null, Code.ViewType.CENTER_CONTENT));
        dataList.add(new DataItem(s, "사용자1", Code.ViewType.LEFT_CONTENT));
        dataList.add(new DataItem(String.valueOf(l), "사용자2", Code.ViewType.RIGHT_CONTENT));

    }
}
