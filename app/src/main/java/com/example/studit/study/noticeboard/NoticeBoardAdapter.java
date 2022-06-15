package com.example.studit.study.noticeboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class NoticeBoardAdapter extends RecyclerView.Adapter<NoticeBoardAdapter.NoticeBoardViewHolder> {
    private ArrayList<NoticeBoardModel> mData = null;

    public NoticeBoardAdapter(ArrayList<NoticeBoardModel> data) {
        mData = data;
    }

    @NonNull
    @Override
    public NoticeBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_activity_study_notice_board_grid, parent, false);
        NoticeBoardAdapter.NoticeBoardViewHolder vh = new NoticeBoardAdapter.NoticeBoardViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeBoardViewHolder holder, int position) {
        NoticeBoardModel item = mData.get(position);

        holder.title.setText(item.getTitle());
        holder.editor.setText(item.getEditor());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NoticeBoardViewHolder extends RecyclerView.ViewHolder {
        TextView title, editor;

        public NoticeBoardViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.study_title_tv);
            editor = itemView.findViewById(R.id.editor);
        }

    }
}
