package com.example.studit.study.studyhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.study.mystudy.MyStudyActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudyHomeAdapter extends RecyclerView.Adapter<StudyHomeAdapter.ViewHolder>{

    // 멤버를 출력하는 리사이클러뷰의 어뎁터와 연결
    StudyMemAdapter adapter;
    private List<StudyHomeActivity.Recycler_item> items;

    // adapter에 들어갈 list
    private ArrayList<String> listData;

    private Context context;

    // 연결하기
    public StudyHomeAdapter(ArrayList<String> data, List<StudyHomeActivity.Recycler_item> items) {
        this.listData = data;
        this.items = items;
    }

    // 뷰홀더 생성
    @NonNull
    @Override
    public StudyHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_study_home, parent, false);
        return new ViewHolder(v);
    }

    // 뷰홀더에 함수 넣기
    @Override
    public void onBindViewHolder(@NonNull StudyHomeAdapter.ViewHolder holder, final int position) {

        // 리사이클러뷰 넣기
        holder.recyclerView.setLayoutManager( new LinearLayoutManager(context));
        adapter = new StudyMemAdapter(items);
        holder.recyclerView.setAdapter(adapter);

        holder.onBind(position);
    }

    // 총 갯수
    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // 첫번째 아이템에서 사용했던 텍스트뷰와 리사이클러뷰
        private TextView textView1, textView2;
        private final RecyclerView recyclerView;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.list_study_title);
            textView2 = itemView.findViewById(R.id.list_study_state);
            recyclerView = itemView.findViewById(R.id.my_study_recycler);
        }

        void onBind(int position) {
            this.position = position;
        }
    }
}



