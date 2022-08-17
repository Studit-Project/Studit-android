package com.example.studit.study.studyhome;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.study.mystudy.MyStudyActivity;
import com.example.studit.study.mystudy.MyStudyActivityAdapter;
import com.example.studit.study.mystudy.MyStudyActivityGridModel;

import java.util.ArrayList;

public class StudyHomeAdapter2 extends RecyclerView.Adapter<StudyHomeAdapter2.StudyHomeViewHolder> {

    private ArrayList<StudyHomeModel> StudyList;
    private Context context;
    int pos;

    public StudyHomeAdapter2(ArrayList<StudyHomeModel> data, Context context) {
        this.StudyList = data;
        this.context = context;
    }

    public class StudyHomeViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView activity;

        public StudyHomeViewHolder (View view) {
            super(view);
            this.title = view.findViewById(R.id.list_study_title);
            this.activity = view.findViewById(R.id.list_study_activity);

            view.setClickable(true);
            view.setOnClickListener(v -> {
                pos = getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {
                    Log.d("pos", pos + " 클릭됨");

                    StudyHomeModel item = StudyList.get(pos);

                    String getTitle = item.getName();
                    String getActivity = item.getActivity();

                    Intent intent = new Intent(context, MyStudyActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("getName", getTitle);
                    intent.putExtra("getActivity", getActivity);

                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public StudyHomeAdapter2.StudyHomeViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view = inflater.inflate(R.layout.list_study_home, parent, false);
//        StudyHomeAdapter2.StudyHomeViewHolder vh = new StudyHomeAdapter2.StudyHomeViewHolder(view);

        View gridView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_study_home, parent, false);
        StudyHomeAdapter2.StudyHomeViewHolder holder = new StudyHomeAdapter2.StudyHomeViewHolder(gridView);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull StudyHomeAdapter2.StudyHomeViewHolder holder, int position) {
        StudyHomeModel item = StudyList.get(position);

        holder.title.setText(item.getName());
        holder.activity.setText(item.getActivity());

        context = holder.itemView.getContext();
    }

    @Override
    public int getItemCount() {
        return  StudyList != null ? StudyList.size() : 0;
    }
}
