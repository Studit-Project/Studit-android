package com.example.studit.search;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.studit.R;
import com.example.studit.databinding.ActivityMainBinding;
import com.example.studit.home.FragHome;
import com.example.studit.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<String> tabNames = new ArrayList<>();
    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       // binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        ImageView btn_search = findViewById(R.id.home_ic_back);  //검색 버튼, 엔터..기능도 넣어야할듯?
        btn_search.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });

        loadTabName();
        setTabLayout();
        setViewPager();

    }

    private void loadTabName() {
        tabNames.add("스터디");
        tabNames.add("챌린지");
        tabNames.add("자유");
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void setTabLayout() {

        //tabLayout = binding.search_tab;//== (
        tabLayout = findViewById(R.id.search_tab);
        //ArrayList에 저장된 순서대로 Tab 이름을 지정해줌
        tabNames.stream().forEach(name -> tabLayout.addTab(tabLayout.newTab().setText(name)));
    }

    private void setViewPager() {

        //어댑터 초기화
        FragSearchAdapter adapter = new FragSearchAdapter(getSupportFragmentManager(), PagerAdapter.POSITION_NONE);
        //viewPager = binding.;//== (
        viewPager = findViewById(R.id.search_view_pager);
        //어댑터 연결
        viewPager.setAdapter(adapter);
        //페이지 리스너 (viewPager와 TabLayout의 페이지를 맞춰줌)
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //탭 선택 리스너 (탭 행동 설정)
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //선택된 탭일 때
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //선택된 탭과 연결된 fragment를 가져옴
                viewPager.setCurrentItem(tab.getPosition());
                //아이콘 색상을 흰색으로 설정
                //tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }
            //선택되지 않은 탭일 때
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //아이콘 색상을 #0070C0 으로 설정
                //tab.getIcon().setColorFilter(Color.parseColor("#0070C0"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
