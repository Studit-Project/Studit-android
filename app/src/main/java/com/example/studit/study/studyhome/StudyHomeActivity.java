package com.example.studit.study.studyhome;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;
import java.util.List;

public class StudyHomeActivity extends AppCompatActivity {

    StudyHomeAdapter adapter;
    ArrayList<String> title = new ArrayList<>();
    private final List<Recycler_item> items = new ArrayList<>();

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_study_home);

        RecyclerView recyclerView = findViewById(R.id.recycler_study);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new StudyHomeAdapter(title, items);
        recyclerView.setAdapter(adapter);

        title.add("스터디1");
        title.add("스터디2");
        title.add("스터디3");
        title.add("스터디4");
        title.add("스터디5");

        items.add(new Recycler_item("", "김수정"));
        items.add(new Recycler_item("", "곽수정"));
        items.add(new Recycler_item("", "강수정"));
        items.add(new Recycler_item("", "황수정"));
        items.add(new Recycler_item("", "최수정"));

    }

    public class Recycler_item {
        String image;
        String name;

        String getImage() {
            return this.image;
        }

        String getName() {
            return this.name;
        }

        Recycler_item(String image, String name) {
            this.image = image;
            this.name = name;
        }
    }

}
