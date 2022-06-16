package com.example.studit.study.noticeboard;

import static com.example.studit.login.LoginActivity.sID;
import static java.security.AccessController.getContext;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class NoticeBoardActivity extends AppCompatActivity {

    // 사용할 컴포넌트 선언
    ImageButton add_study;
    String userid = "";
    RecyclerView mRecyclerView;
    NoticeBoardAdapter mAdapter;
    ArrayList<NoticeBoardModel> mArrayList;

    // 로그에 사용할 TAG 변수
    final private String TAG = getClass().getSimpleName();

    ArrayList<String>titleList = new ArrayList<>();
    ArrayList<String>seqList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_notice_board);

        userid = getIntent().getStringExtra("editor");

        mRecyclerView = findViewById(R.id.recycler_notice);
        mArrayList = new ArrayList<>();

        mAdapter = new NoticeBoardAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mArrayList.add(new NoticeBoardModel("스터기 공지사항1", "김수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항2", "곽수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항3", "깅수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항4", "황수정"));
        mArrayList.add(new NoticeBoardModel("스터기 공지사항5", "최수정"));


        // 아마 뷰홀더로 옮겨야할듯 ..? setOnItemClickListener 오류 해결 필요
//        mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Toast.makeText(NoticeBoardActivity.this, adapterView.getItemIdAtPosition(i)+"클릭", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(NoticeBoardActivity.this, DetailNoticeActivity.class);
//                intent.putExtra("editor", seqList.get(i));
//                intent.putExtra("title", userid);
//                startActivity(intent);
//            }
//        });

        // 버튼 컴포넌트 초기화
        add_study = findViewById(R.id.addstudy);

        // 버튼 이벤트 추가
        add_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // userID를 가지고 AddNoticeBoardActivity 로 이동
                Intent intent = new Intent(NoticeBoardActivity.this, AddNoticeBorardActivity.class);
                intent.putExtra("editor", userid);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        GetNotice getNotice = new GetNotice();
        getNotice.execute();
    }

    class GetNotice extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute"+ result);

            titleList.clear();
            seqList.clear();

            try {

                //JSONArray 형태로 넘어온 결과물 파싱
                JSONArray jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String title = jsonObject.optString("title");
                    String seq = jsonObject.optString("seq");

                    //title, seq 값 배열에 추가
                    titleList.add(title);
                    seqList.add(seq);
                }

                // mAdapter의 데이터가 변경될시 새로고침
                mAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {

            //url 추후 추가 예정
            String server_url = "";

            URL url;
            String response = "";
            try {
                url = new URL(server_url);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("userid", "");
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = "";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;

        }
    }
}
