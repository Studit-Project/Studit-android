//package com.example.studit.study.registerstudy;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import com.example.studit.R;
//
//import java.util.ArrayList;
//
//public class RegisterStudyAdapter extends BaseAdapter {
//    Context mContext = null;
//    LayoutInflater mLayoutInflater = null;
//    ArrayList<RegisterStudyModel> registerStudyModel;
//
//    public RegisterStudyAdapter(Context context, ArrayList<RegisterStudyModel> model) {
//        mContext = context;
//        registerStudyModel = model;
//        mLayoutInflater = LayoutInflater.from(mContext);
//    }
//
//    @Override
//    public int getCount() {
//        return registerStudyModel.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return registerStudyModel.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view = mLayoutInflater.inflate(R.layout.activity_register_study,null);
//        EditText title = (EditText) view.findViewById(R.id.title_regi);
//        Spinner activity = (Spinner) view.findViewById(R.id.activity_spinner);
//
//        title.setText(registerStudyModel.get(position).getName());
//        String activity1 = activity.getSelectedItem().toString();
//        activity.setAdapter(activity.getAdapter()); // 스피너값 스트링으로 받아오기
//
//        return view;
//    }
//}
