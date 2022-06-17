package com.example.studit.study;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import javax.net.ssl.HttpsURLConnection;

public class FragDetailStudy extends AppCompatActivity {
    final private String TAG = getClass().getSimpleName();

    TextView study_title_tv, content_regi_tv, region_tv, field_tv, mem_tv, age2_tv;
    LinearLayout comment_layout;
    EditText comment_et;
    Button comment_bt;

    String board_seq = "";
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_detail_study);

        //FragStudy 에서 넘긴 변수들을 받음
        board_seq = getIntent().getStringExtra("board_seq");
        userid = getIntent().getStringExtra("userid");

        study_title_tv = findViewById(R.id.study_title_tv);
        content_regi_tv = findViewById(R.id.content_regi_tv);
        region_tv = findViewById(R.id.region_tv);
        field_tv = findViewById(R.id.field_tv);
        mem_tv = findViewById(R.id.mem_tv);
        age2_tv = findViewById(R.id.age2_tv);

        comment_layout = findViewById(R.id.comment_layout);
        comment_et = findViewById(R.id.comment_et);
        comment_bt = findViewById(R.id.comment_bt);

        // 완료버튼 누를시, 댓글 등록 함수 호출
        comment_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegCmt regCmt = new RegCmt();
                regCmt.execute(userid, comment_et.getText().toString(), board_seq);
            }
        });

        //데이터 불러오기
        InitData();
    }

    private void InitData() {
        LoadBoard loadBoard = new LoadBoard();
        loadBoard.execute(board_seq);
    }

    class LoadBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute " + result);
            try {
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(result);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    // database의 데이터들을 변수로 저장한 후 해당 textview에 입력
                    String title = jsonObject.optString("title");
                    String content = jsonObject.optString("content");

                    study_title_tv.setText(title);
                    content_regi_tv.setText(content);
                }

                // 해당 게시물에 대한 댓글을 불러오는 함수 호출
                LoadCmt loadCmt = new LoadCmt();
                loadCmt.execute(board_seq);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String board_seq = params[0];

            // url 추후 추가 예정
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
                        .appendQueryParameter("board_seq", board_seq);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }

    // 댓글 읽어오는 함수
    class LoadCmt extends AsyncTask <String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute " + result);

            comment_layout.removeAllViews();

            try {

                // JSONArray, JSONObject 로 받은 데이터 파싱
                JSONArray jsonArray = null;
                jsonArray = new JSONArray(result);

                // custom_comment 를 불러오기 위한 객체 생성
                LayoutInflater layoutInflater = LayoutInflater.from(FragDetailStudy.this);

                for (int i = 0; i < jsonArray.length(); i++) {
                    // custom_comment 불러옴
                    View customView = layoutInflater.inflate(R.layout.custom_comment, null);
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String userid= jsonObject.optString("userid");
                    String content = jsonObject.optString("content");

                    ((TextView)customView.findViewById(R.id.cmt_userid_tv)).setText(userid);
                    ((TextView)customView.findViewById(R.id.cmt_content_tv)).setText(content);

                    // 댓글 레이아웃에 custom_comment 의 디자인에 데이터를 담아서 추가
                    comment_layout.addView(customView);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            }

            @Override
            protected String doInBackground(String... params) {

                String board_seq = params[0];
                // url 추후 추가 예정
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
                            .appendQueryParameter("board_seq", board_seq);
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

        // 댓글 등록 함수
    class RegCmt extends AsyncTask<String, Void, String> {

        @Override
            protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }

        @Override
            protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute " + result);

            if (result.equals("success")) {
                comment_et.setText("");

                // 키보드 숨김
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(comment_et.getWindowToken(), 0);

                // 댓글 등록 메세지 출력
                Toast.makeText(FragDetailStudy.this, "댓글이 등록되었습니다.", Toast.LENGTH_SHORT).show();

                // 댓글 불러오기
                LoadCmt loadCmt = new LoadCmt();
                loadCmt.execute(board_seq);
            }
            else {
                Toast.makeText(FragDetailStudy.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
            protected String doInBackground(String... params) {

            String userid = params[0];
            String content = params[1];
            String board_seq = params[2];

            // url 추후 추가 예정
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
                        .appendQueryParameter("userid", userid)
                        .appendQueryParameter("content", content)
                        .appendQueryParameter("board_seq", board_seq);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();
                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                }
                else {
                    response="";

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }
}
