package com.example.studit.search;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.study.mystudy.MyStudyActivity;

import java.util.ArrayList;

public class FragSearchStudyAdapter extends RecyclerView.Adapter<FragSearchStudyAdapter.FragSearchStudyViewHolder> {

    private final ArrayList<FragSearchStudyModel> StudyModelArrayList;
    private Context context;
    String getContentsNum;
    int pos;

    public FragSearchStudyAdapter(ArrayList<FragSearchStudyModel> StudyModelArrayList, Context context) {
        this.StudyModelArrayList = StudyModelArrayList;
        this.context = context;
    }

    public FragSearchStudyAdapter(ArrayList<FragSearchStudyModel> studyModelArrayList) {
        this.StudyModelArrayList = studyModelArrayList;
    }

    public class FragSearchStudyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView state, title;
        ItemClickListener itemClickListener;

        public FragSearchStudyViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.list_search_free_title);
            this.state = view.findViewById(R.id.list_search_free_state);

            itemView.setOnClickListener(this);
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

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClickListener(view, getLayoutPosition());
        }
    }

    @NonNull
    @Override
    public FragSearchStudyAdapter.FragSearchStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_frag_search_study, parent, false);
        FragSearchStudyAdapter.FragSearchStudyViewHolder holder = new com.example.studit.search.FragSearchStudyAdapter.FragSearchStudyViewHolder(cardView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.studit.search.FragSearchStudyAdapter.FragSearchStudyViewHolder holder, int position) {

        FragSearchStudyModel dataModelPosition = StudyModelArrayList.get(position);
        holder.title.setText(dataModelPosition.getTitle());
        holder.state.setText(dataModelPosition.getStudyStatus());

        context = holder.itemView.getContext();

        holder.itemClickListener = (v, position1) -> {
            int studyId = StudyModelArrayList.get(position1).getId();
            Intent intent = new Intent(v.getContext(), MyStudyActivity.class);
            intent.putExtra("studyId", studyId);
            v.getContext().startActivity(intent);
        };

    }

    @Override
    public int getItemCount() {
        return (StudyModelArrayList != null ? StudyModelArrayList.size() : 0);
    }
}
