package com.example.studit.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class FragProfileViewAdapter extends RecyclerView.Adapter<FragProfileViewAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView badge_img;
        TextView badge_txt;

        public ViewHolder(@NonNull View view) {
            super(view);

            this.badge_img = view.findViewById(R.id.badge_img);
            this.badge_txt = view.findViewById(R.id.badge_txt);

        }
    }

    private ArrayList<FragProfileViewModel> mList = null;

    public FragProfileViewAdapter(ArrayList<FragProfileViewModel> mList) {
        this.mList = mList;
    }

    //뷰홀더 객체 생성 후 리턴
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.frag_profile_badge, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //position에 해당하는 데이터를 viewholder의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FragProfileViewModel item = mList.get(position);

        holder.badge_img.setImageResource(R.drawable.ic_launcher_background);   // 기본 파일로 이미지 띄움. 나중에 뱃지사진 추가
        holder.badge_txt.setText(item.getBadgeTxt());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}