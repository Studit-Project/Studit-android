package com.example.studit.profile.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.studit.R;
import com.example.studit.retrofit.Link;
import com.example.studit.retrofit.RetrofitInterface;
import com.example.studit.retrofit.home.profile.setting.Model_UserNick;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditNickActivity extends Activity {

    private AlertDialog dialog;

    private EditText mNick;
    private Button bt_edit;
    private Button bt_cancel;

    private long userId;

    List sMessage;

    Intent intent;

    Link link = new Link();

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting_nickname_edit);

        mNick = findViewById(R.id.editNick);

        intent = getIntent();

        //userNumber 가져오기
        if(savedInstanceState == null){
            Bundle extras = intent.getExtras();
            if(extras == null){
                userId = 0;
            } else {
                userId = extras.getLong("userId");
            }
        } else {
            userId = (long) savedInstanceState.getSerializable("userId");
        }

        Log.e(TAG,"userId 저장 됐나? -> " + userId);

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


        bt_cancel = findViewById(R.id.bt_edit_cancel);
        bt_cancel.setOnClickListener(view -> {
            finish();
        });

        bt_edit = findViewById(R.id.bt_edit_nick);
        bt_edit.setOnClickListener(view -> {
            final String NewNick = mNick.getText().toString();

            if(NewNick.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(EditNickActivity.this);
                dialog = builder.setMessage(("닉네임을 입력해주세요.")).setNegativeButton("확인",null).create();
                dialog.show();
            }

            RetrofitInterface retrofitInterface = retrofit.create(RetrofitInterface.class);
            Model_UserNick userNickEdit = new Model_UserNick(NewNick);
            Call<Model_UserNick> call = retrofitInterface.patchUserNick(userId,userNickEdit);

            call.enqueue(new Callback<Model_UserNick>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<Model_UserNick> call, Response<Model_UserNick> response) {
                    Model_UserNick responseBody = response.body();
                    sMessage = responseBody.getMessage();
                    String aMessage = String.join(",",sMessage);

                    if(response.isSuccessful() && response.body() != null){

                        if(responseBody.getIsSuccess() == false){
                            Log.e(TAG, "닉네임 변경 실패 : " + aMessage); //서버에서 받은 message 보여줌
                            AlertDialog.Builder builder = new AlertDialog.Builder((EditNickActivity.this));
                            dialog = builder.setMessage(aMessage).setNegativeButton("확인", null).create();
                            dialog.show();

                        } else {
                            Log.e(TAG, "닉네임 변경 성공!");
                            Toast.makeText(getApplicationContext(), "닉네임 변경 성공!", Toast.LENGTH_LONG).show();
                            finish();
                        }

                    } else {
                        try {
                            String body = response.errorBody().string();
                            Log.e(TAG, "닉네임 변경 error!!! - body : " + body);
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }


                @Override
                public void onFailure(Call<Model_UserNick> call, Throwable t) {
                    Log.e(TAG, "닉네임 변경 fail!!! " + t.getMessage());
                }
            });
        });

    }
}
