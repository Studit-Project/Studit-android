package com.example.studit.search.postdetail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.posting.ModelPostComment;
import com.example.studit.retrofit.posting.ModelPostDetail;
import com.example.studit.search.SearchActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class PostDetailActivity extends AppCompatActivity {

    private TextView title, postDate, userNick, field, gender, target, province, activity, content;

    private EditText newComment;

    private ImageView ic_back;

    private Button commentOK;

    PostDetailCommentAdapter CommentAdapter;
    private final ArrayList<PostDetailCommentModel> CommentArrayList = new ArrayList<>();

    private final String TAG = this.getClass().getSimpleName();
    Link link = new Link();
    Intent intent;
    private AlertDialog dialog;
    RecyclerView recyclerView;

    private SharedPreferences preferences;

    private int postingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "");

        Log.e(TAG, "token : " + token);

        recyclerView = findViewById(R.id.post_detail_commentList);

        recyclerView.setHasFixedSize(true);
        CommentAdapter = new PostDetailCommentAdapter(CommentArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(new PostDetailActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(CommentAdapter);

        //postingId 가져오기
        intent = new Intent(this.getIntent());
        postingId = intent.getIntExtra("getId",1);
        Log.e(TAG,"postingId 저장 됐나? -> " + postingId);

        Gson gson = new Gson();

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link.getBASE_URL())
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        //id값 부여
        title = findViewById(R.id.post_detail_title);
        postDate = findViewById(R.id.post_detail_date);
        userNick = findViewById(R.id.post_detail_userNick);
        field = findViewById(R.id.post_detail_field);
        gender = findViewById(R.id.post_detail_gender);
        target = findViewById(R.id.post_detail_age);
        province = findViewById(R.id.post_detail_province);
        activity = findViewById(R.id.post_detail_activity);
        content = findViewById(R.id.post_detail_content);

        newComment = findViewById(R.id.post_detail_input_comment);

        //뒤로가기
        ic_back = findViewById(R.id.post_detail_back);
        ic_back.setOnClickListener(view -> {
            Intent intent = new Intent(PostDetailActivity.this, SearchActivity.class);
            startActivity(intent);
        });


        //댓글 입력
        commentOK = findViewById(R.id.bt_comment);
        commentOK.setOnClickListener(view -> {
            final String comment = newComment.getText().toString();

            if (Objects.equals(newComment, "")) { //댓글 입력칸 빈칸
                Toast.makeText(getApplicationContext(), "댓글을 입력해주세요.", Toast.LENGTH_LONG).show();
            } else {
                RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
                ModelPostComment postComment = new ModelPostComment(comment);
                Call<ModelPostComment> call2 = retrofitInterface.postPostComment("Bearer "+token,postingId,postComment);
                call2.enqueue(new Callback<ModelPostComment>() {
                    @Override
                    public void onResponse(@NonNull Call<ModelPostComment> call, @NonNull Response<ModelPostComment> response) {
                        ModelPostComment responseBody = response.body();
                        if (response.code() == 200 && response.body() != null) {
                            Log.e(TAG, "댓글등록 완료!");
                            //Toast.makeText(getApplicationContext(), "댓글 달기 성공!", Toast.LENGTH_LONG).show();
                            finish();   //액티비티 새로고침
                            overridePendingTransition(0, 0);
                            Intent intent = getIntent();
                            startActivity(intent);
                            overridePendingTransition(0, 0);
//                            CommentAdapter.notifyDataSetChanged();
                        } else if (response.code() == 401) { System.out.println("Unauthorized");
                        } else if (response.code() == 403) { System.out.println("Forbidden");
                        } else if (response.code() == 404) { System.out.println("Not Found");
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<ModelPostComment> call, @NonNull Throwable t) {
                        System.out.println("댓글등록 실패!!!! " + t.getMessage());
                    }
                });
            }
        });

        //게시글 내용 띄우기
        RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
        Call<ModelPostDetail> call = retrofitInterface.getPostDetail("Bearer "+token,postingId);
        call.enqueue(new Callback<ModelPostDetail>() {
            @Override
            public void onResponse(Call<ModelPostDetail> call, Response<ModelPostDetail> response) {
                ModelPostDetail postingDetail = response.body();
                if (response.code() == 200) {
                    Log.e(TAG, "포스팅 result 받아오기 성공! " + postingDetail.toString());
                    assert postingDetail != null;
                    title.setText(postingDetail.getResult().getTitle());    //게시글 기본 내용 띄우기
                    postDate.setText(postingDetail.getResult().getLocalDateTime());
                    province.setText(postingDetail.getResult().getProvince());
                    userNick.setText(postingDetail.getResult().getUserInfo().getNickname());
                    field.setText(postingDetail.getResult().getField());
                    gender.setText(postingDetail.getResult().getGender());
                    target.setText(postingDetail.getResult().getTarget());
                    content.setText(postingDetail.getResult().getContent());
                    activity.setText(postingDetail.getResult().getActivity());

                    if (postingDetail.getResult().getCommentList() != null){
                        Log.e(TAG, "댓글리스트 불러오기 성공");
                        for (int i = 0; i < postingDetail.getResult().getCommentList().size(); i++) {
                            CommentArrayList.add(new PostDetailCommentModel(
                                    postingDetail.getResult().getCommentList().get(i).getUserNick(),
                                    postingDetail.getResult().getCommentList().get(i).getCommentDate(),
                                    postingDetail.getResult().getCommentList().get(i).getContent()
                            ));
                        }
                    } else { Log.e(TAG, "댓글리스트 불러오기 실패"); }

                    CommentAdapter.notifyDataSetChanged();

                } else if (response.code() == 401) {
                    Log.e(TAG, "401 : Unauthorized");
                } else if (response.code() == 403) {
                    Log.e(TAG, "403 : Forbidden");
                } else if (response.code() == 404) {
                    Log.e(TAG, "404 : Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelPostDetail> call, Throwable t) {
                Log.e(TAG, "포스팅 result 받아오기 fail!!!!!! " + t.getMessage());
            }
        });



    }


}