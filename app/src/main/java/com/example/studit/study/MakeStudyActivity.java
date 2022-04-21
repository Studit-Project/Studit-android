package com.example.studit.study;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.example.studit.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MakeStudyActivity extends AppCompatActivity {
    final private String TAG = getClass().getSimpleName();

    EditText title_regi, content_regi_et;
    Button regi_button;
    String userid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerstudy);

        //ListStudyActivity에서 넘긴 userID를 변수로 받음
        userid = getIntent().getStringExtra("userID");

        //컴포넌트 초기화
        title_regi = findViewById(R.id.title_regi);
        content_regi_et = findViewById(R.id.content_regi_et);
        regi_button = findViewById(R.id.regi_button);

        //버튼 이벤트
        regi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //스터디 모집글 등록 함수
                RegiBoard regiBoard = new RegiBoard();
                regiBoard.execute(userid, title_regi.getText().toString(), content_regi_et.getText().toString());
            }
        });
    }

    class RegiBoard extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            Log.d(TAG, "onPreExecute");
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(TAG, "onPostExecute, " + result);

            if(result.equals("success")) {
                //success인 경우 토스트 메세지를 띄우고 ListStudyActivity 로 이동하며,
                //onResume함수가 호출되며 새로고침됨
                Toast.makeText(MakeStudyActivity.this, "게시완료", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(MakeStudyActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String userid = params[0];
            String title = params[1];
            String content = params[2];

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
                        .appendQueryParameter("title", title)
                        .appendQueryParameter("content", content);
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
