package com.example.studit.search;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.studit.R;
import com.example.studit.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

//    AlertDialog.Builder dialog_filter;
//    List<String> mAgeItems;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView text_title;
    EditText edit_search;

    boolean filter_b = true;

    private RadioButton r_btn1, r_btn2;
    private RadioGroup radioGroup;


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

//        //라디오 버튼 설정
//        r_btn1 = (RadioButton) findViewById(R.id.r_btn1);
//        r_btn2 = (RadioButton) findViewById(R.id.r_btn2);
//        r_btn1.setOnClickListener(radioButtonClickListener);
//        r_btn2.setOnClickListener(radioButtonClickListener);
//
//        //라디오 그룹 설정
//        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener(radioGroupButtonChangeListener);


        //dialog_filter.setContentView(R.layout.dialog_search_filter);


        LinearLayout layout_filter = findViewById(R.id.search_layout_filter);
        layout_filter.setVisibility(View.INVISIBLE);

        ViewPager viewPager_list = findViewById(R.id.search_view_pager);

        ImageView ic_filter = findViewById(R.id.search_ic_filter);
        ic_filter.setOnClickListener(view -> {
            if (filter_b) {
                layout_filter.setVisibility(View.VISIBLE);
                viewPager_list.setVisibility(View.INVISIBLE);
                filter_b = false;
            } else {
                layout_filter.setVisibility(View.INVISIBLE);
                viewPager_list.setVisibility(View.VISIBLE);
                filter_b = true;
            }
        });
//        ic_filter.setOnClickListener(view -> showDialog());

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

//    //라디오 버튼 클릭 리스너
//    RadioButton.OnClickListener radioButtonClickListener = new RadioButton.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Toast.makeText(MainActivity.this, "r_btn1 : " + r_btn1.isChecked() + "r_btn2 : " + r_btn2.isChecked(), Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    //라디오 그룹 클릭 리스너
//    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
//            if (i == R.id.rg_btn1) {
//                Toast.makeText(MainActivity.this, "라디오 그룹 버튼1 눌렸습니다.", Toast.LENGTH_SHORT).show();
//            } else if (i == R.id.rg_btn2) {
//                Toast.makeText(MainActivity.this, "라디오 그룹 버튼2 눌렸습니다.", Toast.LENGTH_SHORT).show();
//            }
//        }
//    };


//    public void showDialog(){
//        dialog_filter = new AlertDialog.Builder(SearchActivity.this);
//        mAgeItems = new ArrayList<>();
//        dialog_filter.setTitle("검색 필터");
//
//        dialog_filter.setMultiChoiceItems(R.array.search_age, null, (dialogInterface, i, b) -> {
//            String[] items = getResources().getStringArray(R.array.search_age);
//
//            if(b){
//                mAgeItems.add(items[i]);
//            }else mAgeItems.remove(items[i]);
//        });
//
//        dialog_filter.setPositiveButton("Ok", (dialogInterface, i) -> {
//            Log.d("check", String.valueOf(mAgeItems));
//        });
//
//
//
//        dialog_filter.setNegativeButton("Canel", (dialogInterface, i) -> dialogInterface.cancel());
//
//        dialog_filter.show();
//    }


    private void loadTabName() {
        tabNames.add("스터디");
        tabNames.add("챌린지");
        tabNames.add("자유");
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void setTabLayout() {
        tabLayout = findViewById(R.id.search_tab);
        tabNames.forEach(name -> tabLayout.addTab(tabLayout.newTab().setText(name)));
    }

    private void setViewPager() {

        text_title = findViewById(R.id.search_text_title);

        FragSearchAdapter adapter = new FragSearchAdapter(getSupportFragmentManager(), PagerAdapter.POSITION_NONE);

        viewPager = findViewById(R.id.search_view_pager);
        viewPager.setAdapter(adapter);

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
