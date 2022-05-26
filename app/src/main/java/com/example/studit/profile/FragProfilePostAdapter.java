package com.example.studit.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.studit.R;

import java.util.ArrayList;

public class FragProfilePostAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<FragProfileMyPostData> sample;

    public FragProfilePostAdapter(Context context, ArrayList<FragProfileMyPostData> data){
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public FragProfileMyPostData getItem(int position){
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent){
        View view = mLayoutInflater.inflate(R.layout.list_frag_profile_timeline,null);
        TextView postData = (TextView) view.findViewById(R.id.postData);
        TextView postDate = (TextView) view.findViewById(R.id.postDate);

        postData.setText(sample.get(position).getMyPostData());
        postDate.setText(sample.get(position).getMyPostDate());

        return view;
    }
}
