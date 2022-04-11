package com.example.studit.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class FragHomeStudyAdapter extends RecyclerView.Adapter<FragHomeStudyAdapter.FragHomeStudyViewHolder> {

    private final ArrayList<FragHomeStudyModel> StudyModelArrayList;
    private Context context;
    String getContentsNum;
    int pos;

    public FragHomeStudyAdapter(ArrayList<FragHomeStudyModel> StudyModelArrayList, Context context) {
        this.StudyModelArrayList = StudyModelArrayList;
        this.context = context;
    }

    public FragHomeStudyAdapter(ArrayList<FragHomeStudyModel> studyModelArrayList) {
        this.StudyModelArrayList = studyModelArrayList;
    }

    public class FragHomeStudyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, day, info;

        public FragHomeStudyViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.home_card_title);
            this.day = view.findViewById(R.id.home_card_day);
            this.info = view.findViewById(R.id.home_card_info);

            view.setClickable(true);
            view.setOnClickListener(v -> {
                pos = getAdapterPosition();

                if (pos != RecyclerView.NO_POSITION) {

                    Log.d("pos", pos+" 클릭됨");
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
    public FragHomeStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_main_home_study, parent, false);
        FragHomeStudyViewHolder holder = new FragHomeStudyViewHolder(cardView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FragHomeStudyViewHolder holder, int position) {

        FragHomeStudyModel dataModelPosition = StudyModelArrayList.get(position);
        holder.title.setText(dataModelPosition.getTitle());
        holder.day.setText(dataModelPosition.getDay());
        holder.info.setText(dataModelPosition.getInfo());

        context = holder.itemView.getContext();

        /* 리사이클러뷰의 버튼을 클릭할 때 실행될 것들을 적어준다. */
        holder.day.setOnClickListener(v -> Toast.makeText(context, "리사이클러뷰의 날짜가 클릭되었습니다.", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return (StudyModelArrayList != null ? StudyModelArrayList.size() : 0);
    }
}
