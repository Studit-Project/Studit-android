package com.example.studit.search;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.studit.R;
import com.example.studit.login.LoginActivity;
import com.example.studit.retrofit.Model_PostAll;
import com.example.studit.retrofit.Model_PostAllList;
import com.example.studit.retrofit.RetrofitClient;
import com.example.studit.retrofit.RetrofitInterface;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1Mzg2OTU1LCJpYXQiOjE2NTUzODUxNTV9.83vHzTrfygd45lmyt5Qb__NJclidb5myZkteQ0XMt2I";

    private TabLayout tabLayout;
    private ViewPager viewPager;
    TextView text_title;
    EditText edit_search;
    Button btn_apply;

    Drawable drawable, drawable2;
    ArrayList<String> checkedCB, checkedRB, checkedRB2, checkedTB;

    boolean filter_b = true;

    CheckBox cb1, cb2, cb3, cb4;
    RadioButton rb1, rb2, rb3, rb_on, rb_off, rb_in;
    TextView tb_seoul, tb_busan, tb_daegu, tb_incheon, tb_gwangju, tb_daejeon, tb_ulsan, tb_sejong,
            tb_gyeonggi, tb_gangwon, tb_chungbuk, tb_chungnam, tb_jeollabuk, tb_jeollanam, tb_gyeongbuk,
            tb_gyeongnam, tb_jeju;
    //todo 필터 값 보낼 각각 배열 들 추가하기

    private final ArrayList<String> tabNames = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        edit_search = findViewById(R.id.search_edit_search);

        cb1 = findViewById(R.id.search_rg_high);
        cb2 = findViewById(R.id.search_rg_college);
        cb3 = findViewById(R.id.search_rg_seeker);
        cb4 = findViewById(R.id.search_rg_worker);

        rb1 = findViewById(R.id.search_rg_w);
        rb2 = findViewById(R.id.search_rg_m);
        rb3 = findViewById(R.id.search_rg_n);

        rb_on = findViewById(R.id.search_rg_on);
        rb_off = findViewById(R.id.search_rg_off);
        rb_in = findViewById(R.id.search_rg_integration);

        drawable = getDrawable(R.color.bg);
        drawable2 = getDrawable(R.color.white);

        tb_seoul = findViewById(R.id.search_tb_seoul);
        tb_seoul.setOnClickListener(view -> {
            if (tb_seoul.getBackground() == drawable) tb_seoul.setBackground(drawable2);
            else tb_seoul.setBackground(drawable);
        });
        tb_busan = findViewById(R.id.search_tb_busan);
        tb_busan.setOnClickListener(view -> {
            if (tb_busan.getBackground() == drawable) tb_busan.setBackground(drawable2);
            else tb_busan.setBackground(drawable);
        });
        tb_daegu = findViewById(R.id.search_tb_daegu);
        tb_daegu.setOnClickListener(view -> {
            if (tb_daegu.getBackground() == drawable) tb_daegu.setBackground(drawable2);
            else tb_daegu.setBackground(drawable);
        });
        tb_incheon = findViewById(R.id.search_tb_incheon);
        tb_incheon.setOnClickListener(view -> {
            if (tb_incheon.getBackground() == drawable) tb_incheon.setBackground(drawable2);
            else tb_incheon.setBackground(drawable);
        });
        tb_gwangju = findViewById(R.id.search_tb_gwangju);
        tb_gwangju.setOnClickListener(view -> {
            if (tb_gwangju.getBackground() == drawable) tb_gwangju.setBackground(drawable2);
            else tb_gwangju.setBackground(drawable);
        });
        tb_daejeon = findViewById(R.id.search_tb_daejeon);
        tb_daejeon.setOnClickListener(view -> {
            if (tb_daejeon.getBackground() == drawable) tb_daejeon.setBackground(drawable2);
            else tb_daejeon.setBackground(drawable);
        });
        tb_ulsan = findViewById(R.id.search_tb_ulsan);
        tb_ulsan.setOnClickListener(view -> {
            if (tb_ulsan.getBackground() == drawable) tb_ulsan.setBackground(drawable2);
            else tb_ulsan.setBackground(drawable);
        });
        tb_sejong = findViewById(R.id.search_tb_sejong);
        tb_sejong.setOnClickListener(view -> {
            if (tb_sejong.getBackground() == drawable) tb_sejong.setBackground(drawable2);
            else tb_sejong.setBackground(drawable);
        });
        tb_gyeonggi = findViewById(R.id.search_tb_gyeonggi);
        tb_gyeonggi.setOnClickListener(view -> {
            if (tb_gyeonggi.getBackground() == drawable) tb_gyeonggi.setBackground(drawable2);
            else tb_gyeonggi.setBackground(drawable);
        });
        tb_gangwon = findViewById(R.id.search_tb_gangwon);
        tb_gangwon.setOnClickListener(view -> {
            if (tb_gangwon.getBackground() == drawable) tb_gangwon.setBackground(drawable2);
            else tb_gangwon.setBackground(drawable);
        });
        tb_chungbuk = findViewById(R.id.search_tb_chungbuk);
        tb_chungbuk.setOnClickListener(view -> {
            if (tb_chungbuk.getBackground() == drawable) tb_chungbuk.setBackground(drawable2);
            else tb_chungbuk.setBackground(drawable);
        });
        tb_chungnam = findViewById(R.id.search_tb_chungnam);
        tb_chungnam.setOnClickListener(view -> {
            if (tb_chungnam.getBackground() == drawable) tb_chungnam.setBackground(drawable2);
            else tb_chungnam.setBackground(drawable);
        });
        tb_jeollabuk = findViewById(R.id.search_tb_jeollabuk);
        tb_jeollabuk.setOnClickListener(view -> {
            if (tb_jeollabuk.getBackground() == drawable) tb_jeollabuk.setBackground(drawable2);
            else tb_jeollabuk.setBackground(drawable);
        });
        tb_jeollanam = findViewById(R.id.search_tb_jeollanam);
        tb_jeollanam.setOnClickListener(view -> {
            if (tb_jeollanam.getBackground() == drawable) tb_jeollanam.setBackground(drawable2);
            else tb_jeollanam.setBackground(drawable);
        });
        tb_gyeongbuk = findViewById(R.id.search_tb_gyeongbuk);
        tb_gyeongbuk.setOnClickListener(view -> {
            if (tb_gyeongbuk.getBackground() == drawable) tb_gyeongbuk.setBackground(drawable2);
            else tb_gyeongbuk.setBackground(drawable);
        });
        tb_gyeongnam = findViewById(R.id.search_tb_gyeongnam);
        tb_gyeongnam.setOnClickListener(view -> {
            if (tb_gyeongnam.getBackground() == drawable) tb_gyeongnam.setBackground(drawable2);
            else tb_gyeongnam.setBackground(drawable);
        });
        tb_jeju = findViewById(R.id.search_tb_jeju);
        tb_jeju.setOnClickListener(view -> {
            if (tb_jeju.getBackground() == drawable) tb_jeju.setBackground(drawable2);
            else tb_jeju.setBackground(drawable);
        });

        btn_apply = findViewById(R.id.search_apply_btn);
        btn_apply.setOnClickListener(view -> {
            sendFilter();

//            Retrofit retrofit  = new Retrofit.Builder() //todo 수정 필요
//                    .baseUrl("http://3.34.99.17:8081/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            RetrofitInterface client = retrofit.create(RetrofitInterface.class);
//            Call<List<Model_PostAll>> callPostAllResponse = client.getPostListByAll("Bearer "+token);
//            callPostAllResponse.enqueue(new Callback<List<Model_PostAll>>() {
//                @Override
//                public void onResponse(Call<List<Model_PostAll>> call, retrofit2.Response<List<Model_PostAll>> response) {
//                    List<Model_PostAll> PostALLResponse = response.body();
//
//                }
//                @Override
//                public void onFailure(Call<List<Model_PostAll>> call, Throwable t) {
//                    //Toast.makeText(this, "Failed ", Toast.LENGTH_SHORT).show();
//                }
//            });

            layout_filter.setVisibility(View.INVISIBLE);
            viewPager_list.setVisibility(View.VISIBLE);
            filter_b = true;
        });


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
        btn_search.setOnClickListener(view -> finish());

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

    private void sendFilter() {
        checkedCB = new ArrayList<>();
        checkedRB = new ArrayList<>();
        checkedRB2 = new ArrayList<>();
        checkedTB = new ArrayList<>();

        if (rb_on.isChecked()) checkedRB2.add(rb_on.getText().toString());
        else if (rb_off.isChecked()) checkedRB2.add(rb_off.getText().toString());
        else if (rb_in.isChecked()) checkedRB2.add(rb_in.getText().toString());

        if (cb1.isChecked()) checkedCB.add(cb1.getText().toString());
        if (cb2.isChecked()) checkedCB.add(cb2.getText().toString());
        if (cb3.isChecked()) checkedCB.add(cb3.getText().toString());
        if (cb4.isChecked()) checkedCB.add(cb4.getText().toString());

        if (rb1.isChecked()) checkedRB.add(rb1.getText().toString());
        else if (rb2.isChecked()) checkedRB.add(rb2.getText().toString());
        else if (rb3.isChecked()) checkedRB.add(rb3.getText().toString());

        if (tb_seoul.getBackground() == drawable) checkedTB.add(tb_seoul.getText().toString());
        if (tb_busan.getBackground() == drawable) checkedTB.add(tb_busan.getText().toString());
        if (tb_incheon.getBackground() == drawable) checkedTB.add(tb_incheon.getText().toString());
        if (tb_gwangju.getBackground() == drawable) checkedTB.add(tb_gwangju.getText().toString());
        if (tb_daejeon.getBackground() == drawable) checkedTB.add(tb_daejeon.getText().toString());
        if (tb_sejong.getBackground() == drawable) checkedTB.add(tb_sejong.getText().toString());
        if (tb_gyeonggi.getBackground() == drawable)
            checkedTB.add(tb_gyeonggi.getText().toString());
        if (tb_gangwon.getBackground() == drawable) checkedTB.add(tb_gangwon.getText().toString());
        if (tb_chungbuk.getBackground() == drawable)
            checkedTB.add(tb_chungbuk.getText().toString());
        if (tb_chungnam.getBackground() == drawable)
            checkedTB.add(tb_chungnam.getText().toString());
        if (tb_jeollabuk.getBackground() == drawable)
            checkedTB.add(tb_jeollabuk.getText().toString());
        if (tb_jeollanam.getBackground() == drawable)
            checkedTB.add(tb_jeollanam.getText().toString());
        if (tb_gyeongbuk.getBackground() == drawable)
            checkedTB.add(tb_gyeongbuk.getText().toString());
        if (tb_gyeongnam.getBackground() == drawable)
            checkedTB.add(tb_gyeongnam.getText().toString());
        if (tb_jeju.getBackground() == drawable) checkedTB.add(tb_jeju.getText().toString());
        if (tb_daegu.getBackground() == drawable) checkedTB.add(tb_daegu.getText().toString());
        if (tb_ulsan.getBackground() == drawable) checkedTB.add(tb_ulsan.getText().toString());
    }
}
