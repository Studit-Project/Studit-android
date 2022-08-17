package com.example.studit.search.postdetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import java.util.ArrayList;

public class PostDetailCommentAdapter extends RecyclerView.Adapter<PostDetailCommentAdapter.PostDetailCommentViewHolder> {

    private final ArrayList<PostDetailCommentModel> CommentModelArrayList;
    private Context context;
    String getContentsNum;
    int pos;

    public PostDetailCommentAdapter(ArrayList<PostDetailCommentModel> CommentModelArrayList) {
        this.CommentModelArrayList = CommentModelArrayList;
        this.context = context;
    }



    public class PostDetailCommentViewHolder extends RecyclerView.ViewHolder {
        public TextView userNick,userLevel,commentDate,commentContent;

        public PostDetailCommentViewHolder(View view) {
            super(view);
            this.userNick = view.findViewById(R.id.comment_userNick);
            //this.userLevel = view.findViewById(R.id.comment_userLevel);
            this.commentDate = view.findViewById(R.id.comment_date);
            this.commentContent = view.findViewById(R.id.comment_content);
        };
    }


    @NonNull
    @Override
    public PostDetailCommentAdapter.PostDetailCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comment, parent, false);
        PostDetailCommentAdapter.PostDetailCommentViewHolder holder = new PostDetailCommentAdapter.PostDetailCommentViewHolder(cardView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostDetailCommentAdapter.PostDetailCommentViewHolder holder, int position) {

        PostDetailCommentModel dataModelPosition = CommentModelArrayList.get(position);
        holder.userNick.setText(dataModelPosition.getUserNick());
        holder.commentContent.setText(dataModelPosition.getContent());
        holder.commentDate.setText(dataModelPosition.getDate());
        //holder.userNick.setText(dataModelPosition.getUserLevel());
        context = holder.itemView.getContext();

        /* 리사이클러뷰의 버튼을 클릭할 때 실행될 것들을 적어준다. */
        //holder.title.setOnClickListener(v -> Toast.makeText(context, "리사이클러뷰의 제목이 클릭되었습니다.", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return (CommentModelArrayList != null ? CommentModelArrayList.size() : 0);
    }
}
