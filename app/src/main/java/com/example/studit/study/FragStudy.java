package com.example.studit.study;

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
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studit.R;
import com.example.studit.study.noticeboard.AddNoticeBoardActivity;
import com.example.studit.study.noticeboard.NoticeBoardActivity;
import com.example.studit.study.noticeboard.NoticeBoardAdapter;
import com.example.studit.study.noticeboard.NoticeBoardModel;
import com.example.studit.study.studyhome.StudyHomeActivity;

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

public class FragStudy extends Fragment {
    private View view;
    final private String TAG = getClass().getSimpleName();

    //사용할 컴포넌트 선언
    RecyclerView mRecyclerView;
    FragStudyAdapter mAdapter;
    ArrayList<FragStudyModel> mArrayList;
    ImageButton addstudy;
    String userid = "";

    //배열
    ArrayList<String> titleList = new ArrayList<>();
    ArrayList<String> seqList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_study_temp, container, false);

        //userID 넘겨받기
        Bundle extra = getArguments();
        if (extra != null) {
            userid = extra.getString("userID");
        }

        Button btn_study = view.findViewById(R.id.home_btn_study);
        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StudyHomeActivity.class);
                startActivity(intent);
            }
        });

//        mRecyclerView = mRecyclerView.findViewById(R.id.recycler_recruit);
//        mArrayList = new ArrayList<>();
//
//        mAdapter = new FragStudyAdapter(mArrayList);
//        mRecyclerView.setAdapter(mAdapter);
//        mArrayList.add(new FragStudyModel("스터디 모집해용1", "김수정"));
//        mArrayList.add(new FragStudyModel("스터디 모집해용2", "곽수정"));
//        mArrayList.add(new FragStudyModel("스터디 모집해용3", "강수정"));
//        mArrayList.add(new FragStudyModel("스터디 모집해용4", "황수정"));
//        mArrayList.add(new FragStudyModel("스터디 모집해용5", "최수정"));
//
//        mAdapter.notifyDataSetChanged();
//
//        // 아마 뷰홀더로 옮겨야할듯 ..? setOnItemClickListener 에서 오류남 수정 필요
////        mRecyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                Toast.makeText(getActivity(),"클릭", Toast.LENGTH_SHORT).show();
////
////                //게시물 번호와 userID를 갖고 FragDetailStudy로 이동
////                Bundle bundle = new Bundle();
////                Intent intent = new Intent(getActivity(),FragDetailStudy.class);
////                bundle.putString("board_seq", seqList.get(i));
////                bundle.putString("userID", userid);
////                startActivity(intent);
////            }
////        });
//
//        //버튼 컴포넌트 초기화
//        addstudy = getView().findViewById(R.id.regi_button);
//
//        // add 버튼 이벤트 추가
//        addstudy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                Intent intent = new Intent(getActivity(),FragDetailStudy.class);
//                bundle.putString("userID", userid);
//                startActivity(intent);
//            }
//        });
//        return view;
//    }
//
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        FragStudy.GetNotice getNotice = new FragStudy.GetNotice();
//        getNotice.execute();
//    }
//
//    class GetNotice extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            Log.d(TAG, "onPreExecute");
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.d(TAG, "onPostExecute"+ result);
//
//            titleList.clear();
//            seqList.clear();
//
//            try {
//
//                //JSONArray 형태로 넘어온 결과물 파싱
//                JSONArray jsonArray = new JSONArray(result);
//
//                for (int i = 0; i < jsonArray.length(); i++) {
//                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                    String title = jsonObject.optString("title");
//                    String seq = jsonObject.optString("seq");
//
//                    //title, seq 값 배열에 추가
//                    titleList.add(title);
//                    seqList.add(seq);
//                }
//
//                // mAdapter의 데이터가 변경될시 새로고침
//                mAdapter.notifyDataSetChanged();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            //url 추후 추가 예정
//            String server_url = "";
//
//            URL url;
//            String response = "";
//            try {
//                url = new URL(server_url);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000);
//                conn.setConnectTimeout(15000);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//                Uri.Builder builder = new Uri.Builder()
//                        .appendQueryParameter("userid", "");
//                String query = builder.build().getEncodedQuery();
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(query);
//                writer.flush();
//                writer.close();
//                os.close();
//
//                conn.connect();
//                int responseCode = conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//                    String line;
//                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    while ((line = br.readLine()) != null) {
//                        response += line;
//                    }
//                } else {
//                    response = "";
//
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return response;
//
//        }
//    }
        return view;
    }
}