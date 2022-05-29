package com.example.studit.study.viewmodel;

import androidx.databinding.BaseObservable;

import com.example.studit.study.model.DB;
import com.example.studit.study.model.Study;

import java.util.ArrayList;
import java.util.List;


public class ViewModel extends BaseObservable {
    private DB database;
    private List<Study> items = new ArrayList<>();
    private String winner;

    public ViewModel(DB database) {
        //Logger.d("ViewModel 생성자 실행 | DB(Model) 참조");
        this.database = database;

        this.database.setOnDatabaseListener(new DB.DatabaseListener() {
            @Override
            public void onChanged() {
                //Logger.d("리스너 실행");
                winner = null;
                winner = database.getWinner();
                notifyChange();
            }
        });
    }

    public void getUser() {
        //Logger.d("db에게 user(winner)를 달라고 요청");
        database.getUser();
    }

    public String getWinner() {
        //Logger.d("Winner 반환 (%s)", winner);
        return winner;
    }


}

