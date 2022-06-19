package com.example.studit.study.studyhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.List;

public class StudyMemAdapter extends RecyclerView.Adapter <StudyMemAdapter.ViewHolder> {
    Context context;
    private List<StudyHomeActivity.Recycler_item> items; // 리사이클러뷰 안에 들어갈 값 저장

    public StudyMemAdapter(List<StudyHomeActivity.Recycler_item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity_study_member, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(items.get(position).id);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.my_study_gird_profile);
            textView = itemView.findViewById(R.id.my_study_grid_name);
        }
    }
}
