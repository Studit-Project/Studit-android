package com.example.studit.search;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.studit.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView text_title;
    EditText edit_search;

    private final ArrayList<String> tabNames = new ArrayList<>();
    //private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        edit_search = findViewById(R.id.search_edit_search);

//        edit_search.setOnTouchListener((v, event) -> {
//            final int DRAWABLE_RIGHT = 2;
//
//            if (event.getAction() == MotionEvent.ACTION_UP) {
//                if (event.getRawX() >= (edit_search.getRight() - edit_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//
//                    Toast.makeText(getApplicationContext(), "검색 버튼 클림됨", Toast.LENGTH_SHORT).show();
//
//                    return true;
//                }
//            }
//            return false;
//        });


        ImageView btn_search = findViewById(R.id.home_ic_back);  //검색 버튼, 엔터..기능도 넣어야할듯?
        btn_search.setOnClickListener(view -> {
            //Intent intent = new Intent(getApplication(), MainActivity.class);
            finish();
            //startActivity(intent);
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
        tabNames.forEach(name -> tabLayout.addTab(tabLayout.newTab().setText(name)));
    }

    private void setViewPager() {

        text_title = findViewById(R.id.search_text_title);

        FragSearchAdapter adapter = new FragSearchAdapter(getSupportFragmentManager(), PagerAdapter.POSITION_NONE);
        //viewPager = binding.;//== (
        viewPager = findViewById(R.id.search_view_pager);
        viewPager.setAdapter(adapter);
        //페이지 리스너 (viewPager와 TabLayout의 페이지를 맞춰줌)
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0) {
                    text_title.setText(getString(R.string.studit_study));
                    edit_search.setHint(getString(R.string.search_study_hint));
                } else if (tab.getPosition() == 1) {
                    text_title.setText(getString(R.string.studit_chall));
                    edit_search.setHint(getString(R.string.search_chall_hint));
                } else {
                    text_title.setText(getString(R.string.studit_free));
                    edit_search.setHint(getString(R.string.search_free_hint));
                }
                //아이콘 색상을 흰색으로 설정
                //tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
            }

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
