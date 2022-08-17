//package com.example.studit.posting;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import com.example.studit.R;
//
//import java.util.ArrayList;
//
//public class PostCreateAdapter extends BaseAdapter {
//
//    ArrayList<PostCreateModel> postCreateModel;
//    Context context;
//    String getContentsNum;
//    LayoutInflater mLayoutInflater = null;
//    int pos;
//
//    public PostCreateAdapter(Context context, ArrayList<PostCreateModel> model) {
//        postCreateModel = model;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return postCreateModel.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return postCreateModel.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = mLayoutInflater.inflate(R.layout.activity_posting_new,null);
//        EditText title = (EditText) view.findViewById(R.id.title_post);
//        Spinner activity = (Spinner) view.findViewById(R.id.activity_spinner);
//        Spinner province = (Spinner) view.findViewById(R.id.region_spinner);
//        EditText content = (EditText) view.findViewById(R.id.content_post);
//        Spinner category = (Spinner) view.findViewById(R.id.field_spinner);
//        Spinner target = (Spinner) view.findViewById(R.id.age2_spinner);
//        Spinner gender = (Spinner) view.findViewById(R.id.sex_spinner);
//
//        title.setText(postCreateModel.get(position).getName());
//        content.setText(postCreateModel.get(position).getContent());
//        // 스피너 해당하는 것들 추가
//        activity.setAdapter(postCreateModel.get(position).getActivity());
//        province.setAdapter(postCreateModel.get(position).getProvince());
//        category.setAdapter(postCreateModel.get(position).getCategory());
//        target.setAdapter(postCreateModel.get(position).getTarget());
//        gender.setAdapter(postCreateModel.get(position).getGender());
//
//
////        activity.setAdapter(activity.getAdapter());
////        province.setAdapter(province.getAdapter());
////        category.setAdapter(category.getAdapter());
////        target.setAdapter(target.getAdapter());
////        gender.setAdapter(gender.getAdapter());
//
//        return view;
//    }
//}
