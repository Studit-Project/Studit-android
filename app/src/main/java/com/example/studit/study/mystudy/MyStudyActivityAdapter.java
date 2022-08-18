package com.example.studit.study.mystudy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.studit.R;
import com.example.studit.chat.ChatActivity;
import com.example.studit.search.FragSearchStudyModel;
import com.example.studit.search.MyStudyActivityModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyStudyActivityAdapter extends RecyclerView.Adapter<MyStudyActivityAdapter.MyStudyActivityViewHolder> {

    private final ArrayList<MyStudyActivityGridModel> myStudyModelArrayList;
    private Context context;
    String getContentsNum;
    int pos;

    public MyStudyActivityAdapter(ArrayList<MyStudyActivityGridModel> freeModelArrayList, Context context) {
        this.myStudyModelArrayList = freeModelArrayList;
        this.context = context;
    }

    public MyStudyActivityAdapter(ArrayList<MyStudyActivityGridModel> freeModelArrayList) {
        this.myStudyModelArrayList = freeModelArrayList;
    }

    public class MyStudyActivityViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView url;

        public MyStudyActivityViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.my_study_grid_name);
            this.url = view.findViewById(R.id.my_study_gird_profile);

            view.setClickable(true);
            view.setOnClickListener(v -> {
                pos = getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {

                    Log.d("pos", pos + " 클릭됨");

                    //todo 프로필 클릭 시 프로필 상세보기로 가기!
//                    MyStudyActivityGridModel myStudyActivityGrid = myStudyModelArrayList.get(pos);
//
//                    int study_id = myStudyActivityGrid.getStudy_id();
//
//                    Intent intent2 = new Intent(context, MyStudySetActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent2.putExtra("studyId2", study_id);
//
//                    context.startActivity(intent2);
                }
            });
        }
    }

    @NonNull
    @Override
    public MyStudyActivityAdapter.MyStudyActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View gridView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity_my_strudy_grid, parent, false);
        MyStudyActivityAdapter.MyStudyActivityViewHolder holder = new MyStudyActivityAdapter.MyStudyActivityViewHolder(gridView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyStudyActivityAdapter.MyStudyActivityViewHolder holder, int position) {

        MyStudyActivityGridModel dataModelPosition = myStudyModelArrayList.get(position);
        holder.name.setText(dataModelPosition.getNickname());

//        Glide.with(holder.itemView.getContext())
//                .load(dataModelPosition.getImage())
//                .into(holder.url);

        context = holder.itemView.getContext();

        /* 리사이클러뷰의 버튼을 클릭할 때 실행될 것들을 적어준다. */
        //holder.day.setOnClickListener(v -> Toast.makeText(context, "리사이클러뷰의 날짜가 클릭되었습니다.", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return (myStudyModelArrayList != null ? myStudyModelArrayList.size() : 0);
    }
}
