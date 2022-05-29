package com.example.studit.study.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;


import com.example.studit.R;

public class MyStudySetActivity extends AppCompatActivity {

    ViewDataBinding binding; // 상속 ViewDataBinding
    ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Logger.d("Main_onCrete() 실행");
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_study_set); // Activity Content's View - Layout 연결 & 반환 : ViewDataBinding을 상속하는 제네릭 타입

//        viewModel = new ViewModel(Database.getInstance());
//        binding.setViewModel(viewModel);
//
//        binding.okBtnview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Logger.d("버튼 클릭");
//                viewModel.getUser();
//            }
//        });


    }
}