package com.example.studit.study.mystudy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class MyStudySetAdapter extends RecyclerView.Adapter<MyStudySetAdapter.MyStudySetViewHolder> {

    private final ArrayList<MyStudySetListModel> myStudySetModelArrayList;
    private Context context;
    String getContentsNum;
    int pos;

    public MyStudySetAdapter(ArrayList<MyStudySetListModel> setModelArrayList, Context context) {
        this.myStudySetModelArrayList = setModelArrayList;
        this.context = context;
    }

    public MyStudySetAdapter(ArrayList<MyStudySetListModel> setModelArrayList) {
        this.myStudySetModelArrayList = setModelArrayList;
    }

    public class MyStudySetViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView url;

        public MyStudySetViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.my_study_grid_name);
            this.url = view.findViewById(R.id.my_study_gird_profile);

            view.setClickable(true);
            view.setOnClickListener(v -> {
                pos = getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {

                    Log.d("pos", pos + " 클릭됨");
//                    FragHomeStudyModel item = StudyModelArrayList.get(pos);
//
//                    getContentsNum = item.getContentsNum();
//                    String getTitle = item.getTitle();
//                    String getDay = item.getDay();
//                    String getInfo = item.getInfo();
//
//                    Intent intent = new Intent(context, FragHome.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.putExtra("getContentsNum", getContentsNum);
//                    intent.putExtra("getTitle", getTitle);
//                    intent.putExtra("getDay", getDay);
//                    intent.putExtra("getInfo", getInfo);
//
//                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public MyStudySetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity_my_study_member, parent, false);
        MyStudySetViewHolder holder = new MyStudySetViewHolder(listView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyStudySetViewHolder holder, int position) {

        MyStudySetListModel dataModelPosition = myStudySetModelArrayList.get(position);
        holder.name.setText(dataModelPosition.getName());

//        Glide.with(holder.itemView.getContext())
//                .load(dataModelPosition.getImage())
//                .into(holder.url);

        context = holder.itemView.getContext();

        /* 리사이클러뷰의 버튼을 클릭할 때 실행될 것들을 적어준다. */
        //holder.day.setOnClickListener(v -> Toast.makeText(context, "리사이클러뷰의 날짜가 클릭되었습니다.", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return (myStudySetModelArrayList != null ? myStudySetModelArrayList.size() : 0);
    }
}