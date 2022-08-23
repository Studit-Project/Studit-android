package com.example.studit.search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.studit.R;
import com.example.studit.main.MainActivity;
import com.example.studit.posting.PostCreateActivity;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.search.ModelPost;
import com.example.studit.retrofit.search.ModelPostAllList;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private final ArrayList<FragSearchStudyModel> StudyModelArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    FragSearchStudyAdapter studyAdapter;
    RecyclerView.LayoutManager layoutManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    LinearLayout list;
    TextView text_title;
    EditText edit_search;
    Button btn_apply;
    ImageButton addstudy;

    private SharedPreferences preferences;

    Drawable drawable, drawable2;
    ArrayList<String> checkedCB, checkedRB, checkedRB2, checkedTB;

    boolean filter_b = true;
    boolean bool_text_filter = true;

    CheckBox cb1, cb2, cb3, cb4;
    RadioButton rb1, rb2, rb3, rb_on, rb_off, rb_in;
    TextView tb_seoul, tb_busan, tb_daegu, tb_incheon, tb_gwangju, tb_daejeon, tb_ulsan, tb_sejong,
            tb_gyeonggi, tb_gangwon, tb_chungbuk, tb_chungnam, tb_jeollabuk, tb_jeollanam, tb_gyeongbuk,
            tb_gyeongnam, tb_jeju;

    private final ArrayList<String> tabNames = new ArrayList<>();
    ModelPost modelPost;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ImageView ic_search = findViewById(R.id.search_ic_search);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        Link link = new Link();

        list = findViewById(R.id.search_list);
        LinearLayout layout_filter = findViewById(R.id.search_layout_filter);
        layout_filter.setVisibility(View.GONE);
        TextView text_filter_apply = findViewById(R.id.chat_text);
        addstudy = (ImageButton) findViewById(R.id.addstudy);

        // addstudy 버튼 클릭시 스터디 작성할 수 있는 화면으로 이동
        addstudy.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PostCreateActivity.class);
            startActivity(intent);
        });

        recyclerView = findViewById(R.id.chat_list);
        studyAdapter = new FragSearchStudyAdapter(StudyModelArrayList, getApplication());

        layoutManager = new LinearLayoutManager(getApplication());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(studyAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<ModelPostAllList> callPostAllResponse = retrofitInterface.getPostListByAll("Bearer " + token);
        callPostAllResponse.enqueue(new Callback<ModelPostAllList>() {
            @Override
            public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                ModelPostAllList PostALLResponse = response.body();
                if (response.code() == 200) {
                    System.out.println("성공");
                    assert PostALLResponse != null;
                    String s = "";
                    for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                        if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                            s = "모집중";
                        StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));
                    }
                    studyAdapter.notifyDataSetChanged();

                } else if (response.code() == 401) {
                    System.out.println("Unauthorized");
                } else if (response.code() == 403) {
                    System.out.println("Forbidden");
                } else if (response.code() == 404) {
                    System.out.println("Not Found");
                }

            }

            @Override
            public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                System.out.println("=============" + t.getMessage());
            }
        });


        //ViewPager viewPager_list = findViewById(R.id.search_view_pager);

        ImageView ic_filter = findViewById(R.id.search_ic_filter);
        ic_filter.setOnClickListener(view -> {
            if (filter_b) {
                layout_filter.setVisibility(View.VISIBLE);
                list.setVisibility(View.GONE);
                filter_b = false;
            } else {
                layout_filter.setVisibility(View.GONE);
                list.setVisibility(View.VISIBLE);
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

//            String[] array1 = checkedRB2.toArray(new String[checkedRB2.size()]);
//            String[] array2 = checkedRB.toArray(new String[checkedRB.size()]);
//            String[] array3 = checkedTB.toArray(new String[checkedTB.size()]);
//            String[] array4 = checkedCB.toArray(new String[checkedCB.size()]);

//            FragSearchStudy fragment = new FragSearchStudy();
//            Bundle bundle = new Bundle();
//            bundle.putStringArray("activities", array1);
//            bundle.putStringArray("targets", array2);
//            bundle.putStringArray("provinces", array3);
//            bundle.putStringArray("genders", array4);
//
//            fragment.setArguments(bundle);

            layout_filter.setVisibility(View.GONE);
            bool_text_filter = true;
            text_filter_apply.setText("검색결과 필터 적용하기 click!");

            list.setVisibility(View.VISIBLE);
            filter_b = true;
        });


        text_filter_apply.setOnClickListener(view -> {
            if (bool_text_filter) {
                text_filter_apply.setText("검색결과 필터 적용해제하기 click!");

                String[] array1 = checkedRB2.toArray(new String[checkedRB2.size()]);
                String[] array2 = checkedRB.toArray(new String[checkedRB.size()]);
                String[] array3 = checkedTB.toArray(new String[checkedTB.size()]);
                String[] array4 = checkedCB.toArray(new String[checkedCB.size()]);

                Call<ModelPostAllList> callPostFilterResponse = retrofitInterface.getPostListByFilter(array1, array2, array3, array4, "Bearer " + token);
                callPostFilterResponse.enqueue(new Callback<ModelPostAllList>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                        ModelPostAllList PostALLResponse = response.body();
                        if (response.code() == 200) {
                            System.out.println("성공");
                            assert PostALLResponse != null;
                            String s = "";

                            StudyModelArrayList.clear();
                            for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                                if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                                    s = "모집중";

                                StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));
                            }
                            studyAdapter.notifyDataSetChanged();

                        } else if (response.code() == 401) {
                            System.out.println("Unauthorized");
                        } else if (response.code() == 403) {
                            System.out.println("Forbidden");
                        } else if (response.code() == 404) {
                            System.out.println("Not Found");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                bool_text_filter = false;
            } else {
                text_filter_apply.setText("검색결과 필터 적용하기 click!");

                Call<ModelPostAllList> callPostAllResponse2 = retrofitInterface.getPostListByAll("Bearer " + token);
                callPostAllResponse2.enqueue(new Callback<ModelPostAllList>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                        ModelPostAllList PostALLResponse = response.body();
                        if (response.code() == 200) {
                            System.out.println("성공");
                            assert PostALLResponse != null;
                            String s = "";
                            StudyModelArrayList.clear();
                            for (int i = 0; i < PostALLResponse.getPosts().size(); i++) {
                                if (PostALLResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                                    s = "모집중";

                                StudyModelArrayList.add(new FragSearchStudyModel(PostALLResponse.getPosts().get(i).getId(), PostALLResponse.getPosts().get(i).getTitle(), PostALLResponse.getPosts().get(i).getUserId(), s));

                                System.out.println(PostALLResponse.getPosts().get(i).getId() + " ===== " + PostALLResponse.getPosts().get(i).getTitle());
                            }
                            studyAdapter.notifyDataSetChanged();

                        } else if (response.code() == 401) {
                            System.out.println("Unauthorized");
                        } else if (response.code() == 403) {
                            System.out.println("Forbidden");

                        } else if (response.code() == 404) {
                            System.out.println("Not Found");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
                bool_text_filter = true;
            }
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


        ic_search.setOnClickListener(view -> {

            if (!edit_search.getText().toString().equals("")) {
                String s = edit_search.getText().toString();

                Call<ModelPostAllList> callKeywordResponse = retrofitInterface.getPostListByFilterKeyword(s, "Bearer " + token);

                callKeywordResponse.enqueue(new Callback<ModelPostAllList>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelPostAllList> call, @NonNull retrofit2.Response<ModelPostAllList> response) {
                        ModelPostAllList keywordResponse = response.body();

                        System.out.println("===============" + response.code());

                        if (response.code() == 200) {
                            System.out.println("성공");

                            StudyModelArrayList.clear();
                            assert keywordResponse != null;
                            String s = "";
                            for (int i = 0; i < keywordResponse.getPosts().size(); i++) {
                                if (keywordResponse.getPosts().get(i).getStudyStatus().equals("RECRUITING"))
                                    s = "모집중";
                                StudyModelArrayList.add(new FragSearchStudyModel(keywordResponse.getPosts().get(i).getId(), keywordResponse.getPosts().get(i).getTitle(), keywordResponse.getPosts().get(i).getUserId(), s));
                            }
                            studyAdapter.notifyDataSetChanged();


                        } else if (response.code() == 401) {
                            System.out.println("Unauthorized");
                        } else if (response.code() == 403) {
                            System.out.println("Forbidden");
                        } else if (response.code() == 404) {
                            System.out.println("Not Found");
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ModelPostAllList> call, @NonNull Throwable t) {
                        System.out.println("=============" + t.getMessage());
                    }
                });
            }
        });


        ImageView btn_search = findViewById(R.id.home_ic_back);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        loadTabName();
//        setTabLayout();
//        setViewPager();

    }

//    private void loadTabName() {
//        tabNames.add("스터디");
//        tabNames.add("챌린지");
//        tabNames.add("자유");
//    }
//
//    @TargetApi(Build.VERSION_CODES.N)
//    private void setTabLayout() {
//        tabLayout = findViewById(R.id.search_tab);
//        tabNames.forEach(name -> tabLayout.addTab(tabLayout.newTab().setText(name)));
//    }

//    private void setViewPager() {
//
//        text_title = findViewById(R.id.search_text_title);
//
//        FragSearchAdapter adapter = new FragSearchAdapter(getSupportFragmentManager(), PagerAdapter.POSITION_NONE);
//
//        viewPager = findViewById(R.id.search_view_pager);
//        viewPager.setAdapter(adapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//
//                if (tab.getPosition() == 0) {
//                    text_title.setText(getString(R.string.studit_study));
//                    edit_search.setHint(getString(R.string.search_study_hint));
//                } else if (tab.getPosition() == 1) {
//                    text_title.setText(getString(R.string.studit_chall));
//                    edit_search.setHint(getString(R.string.search_chall_hint));
//                } else {
//                    text_title.setText(getString(R.string.studit_free));
//                    edit_search.setHint(getString(R.string.search_free_hint));
//                }
//                //아이콘 색상을 흰색으로 설정
//                //tab.getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                //아이콘 색상을 #0070C0 으로 설정
//                //tab.getIcon().setColorFilter(Color.parseColor("#0070C0"), PorterDuff.Mode.SRC_IN);
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//    }

    private OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okhttpClientBuilder = new OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS);
        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS);
        return okhttpClientBuilder.build();
    }

    private void sendFilter() {
        checkedCB = new ArrayList<>();
        checkedRB = new ArrayList<>();
        checkedRB2 = new ArrayList<>();
        checkedTB = new ArrayList<>();

        checkedCB.clear();
        checkedRB.clear();
        checkedRB2.clear();
        checkedTB.clear();

        if (rb_on.isChecked()) checkedRB2.add("ONLINE");
        else if (rb_off.isChecked()) checkedRB2.add("OFFLINE");
        else if (rb_in.isChecked()) checkedRB2.add("INTEGRATION");

        if (cb1.isChecked()) checkedCB.add("HIGH_SCHOOL");
        if (cb2.isChecked()) checkedCB.add("UNIVERSITY");
        if (cb3.isChecked()) checkedCB.add("JOB_SEEKER");
        if (cb4.isChecked()) checkedCB.add("OFFICE_WORKER");

        if (rb1.isChecked()) checkedRB.add("FEMALE");
        else if (rb2.isChecked()) checkedRB.add("MALE");
        else if (rb3.isChecked()) checkedRB.add("MIX");

        if (tb_seoul.getBackground() == drawable) checkedTB.add("SEOUL");
        if (tb_busan.getBackground() == drawable) checkedTB.add("BUSAN");
        if (tb_incheon.getBackground() == drawable) checkedTB.add("INCHEON");
        if (tb_gwangju.getBackground() == drawable) checkedTB.add("GWANGJU");
        if (tb_daejeon.getBackground() == drawable) checkedTB.add("DAEJEON");
        if (tb_sejong.getBackground() == drawable) checkedTB.add("SEJONG");
        if (tb_gyeonggi.getBackground() == drawable) checkedTB.add("GYENGGI");
        if (tb_gangwon.getBackground() == drawable) checkedTB.add("GANGWON");
        if (tb_chungbuk.getBackground() == drawable) checkedTB.add("CHUNGBUK");
        if (tb_chungnam.getBackground() == drawable) checkedTB.add("CHUNGNAM");
        if (tb_jeollabuk.getBackground() == drawable) checkedTB.add("JEONBUK");
        if (tb_jeollanam.getBackground() == drawable) checkedTB.add("JEONNAM");
        if (tb_gyeongbuk.getBackground() == drawable) checkedTB.add("GYEONGBUK");
        if (tb_gyeongnam.getBackground() == drawable) checkedTB.add("GYENGNAM");
        if (tb_jeju.getBackground() == drawable) checkedTB.add("JEJU");
        if (tb_daegu.getBackground() == drawable) checkedTB.add("DAEGU");
        if (tb_ulsan.getBackground() == drawable) checkedTB.add("ULSAN");
    }
}
