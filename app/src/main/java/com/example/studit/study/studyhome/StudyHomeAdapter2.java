package com.example.studit.study.studyhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class StudyHomeAdapter2 extends RecyclerView.Adapter<StudyHomeAdapter2.StudyHomeViewHolder> {

    private ArrayList<StudyHomeModel> mData = null;

    public StudyHomeAdapter2(ArrayList<StudyHomeModel> data) {
        mData = data;
    }

    @NonNull
    @Override
    public StudyHomeViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_study_home, parent, false);
        StudyHomeAdapter2.StudyHomeViewHolder vh = new StudyHomeAdapter2.StudyHomeViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull StudyHomeViewHolder holder, int position) {
        StudyHomeModel item = mData.get(position);

        holder.title.setText(item.getTitle());
        holder.activity.setText(item.getActivity());
    }

    @Override
    public int getItemCount() {
        return  mData.size();
    }

    public class StudyHomeViewHolder extends RecyclerView.ViewHolder {
        TextView title, activity;

        public StudyHomeViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.list_study_title);
            activity = itemView.findViewById(R.id.list_study_activity);
        }
    }
}
