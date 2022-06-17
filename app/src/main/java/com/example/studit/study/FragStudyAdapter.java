package com.example.studit.study;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class FragStudyAdapter extends RecyclerView.Adapter<FragStudyAdapter.FragStudyViewHolder> {
    private ArrayList<FragStudyModel> mData = null;

    public FragStudyAdapter(ArrayList<FragStudyModel> data) {
        mData = data;
    }

    @NonNull
    @Override
    public FragStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_frag_study_grid, parent, false);
        FragStudyAdapter.FragStudyViewHolder vh = new FragStudyAdapter.FragStudyViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull FragStudyViewHolder holder, int position) {
        FragStudyModel item = mData.get(position);

        holder.title.setText(item.getTitle());
        holder.editor.setText(item.getEditor());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class FragStudyViewHolder extends RecyclerView.ViewHolder {
        TextView title, editor;

        public FragStudyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.study_title_tv);
            editor = itemView.findViewById(R.id.editor);
        }

    }
}
