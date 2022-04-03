package com.example.studit.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

public class FragStudyAdapter extends RecyclerView.Adapter<FragStudyAdapter.MainStudyViewHolder> {

    String data[];
    Context context;

    public FragStudyAdapter(Context ct, String[] name) {
        context = ct;
        data = name;
    }

    @NonNull
    @Override
    public MainStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_main_home_study, parent, false);
        return new MainStudyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainStudyViewHolder holder, int position) {
        holder.textView.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MainStudyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public MainStudyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }
}
