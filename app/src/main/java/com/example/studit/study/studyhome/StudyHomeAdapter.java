package com.example.studit.study.studyhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.retrofit.studyhome.ModelStudyList;
import com.example.studit.study.mystudy.MyStudyActivityAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

public class StudyHomeAdapter extends RecyclerView.Adapter<StudyHomeAdapter.ViewHolder> {

//    // 멤버 출력하는 리사이클러뷰와 연결
//    StudyMemAdapter adapter;
//    private List<StudyMemModel> items; // StudyMemModel를 StudyMemAdapter 에 보내기 위함

    private List<ModelStudyList> dataList;
    private Context context;
//    private int id;

    // StudyHomeActivity와 연결
    public StudyHomeAdapter(Context context, List<ModelStudyList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    // 뷰홀더 생성
    @NonNull
    @Override
    public StudyHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_study_home, parent, false);
        return new ViewHolder(v);
    }

    // 뷰홀더에 데이터 넣는 함수
    @Override
    public void onBindViewHolder(@NonNull StudyHomeAdapter.ViewHolder holder, int position) {

        holder.title.setText(dataList.get(position).getName());
        holder.state.setText(dataList.get(position).getActivity());
//        // 리사이클러뷰 넣기
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        adapter = new StudyMemAdapter(items);
//        holder.recyclerView.setAdapter(adapter);
    }

    // 총 갯수
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView state;
//        private RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.list_study_title);
            state = itemView.findViewById(R.id.list_study_activity);
        }
    }
}