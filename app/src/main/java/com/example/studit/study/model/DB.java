package com.example.studit.study.model;

import java.util.ArrayList;

public class DB {
    private static DB instance;
    private ArrayList<Study> personList = new ArrayList<>();
    private String winner;
    private DatabaseListener databaseListener;

    private DB() {
        //Logger.d("Model인 Database 생성");
        personList.add(new Study(0, "최OO"));
        personList.add(new Study(1, "김OO"));
        personList.add(new Study(2, "고OO"));
        personList.add(new Study(3, "문OO"));
        personList.add(new Study(4, "윤OO"));
    }

    public static DB getInstance() {
        //Logger.d("Model에 접근 할 수 있도록 DB 인스턴스 값 요청");
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public void getUser() {
        //Logger.d("당첨자 획득");
        winner = personList.get((int) (Math.random() * 5)).getName();
        notifyChange();
    }

    private void notifyChange() {
        if (databaseListener != null) {
            // Logger.d("Model | Data 변경 되어 notify 하라고 알림");
            databaseListener.onChanged();
        }
    }

    public void setOnDatabaseListener(DatabaseListener databaseListener) {
        //Logger.d("DatabaseListener 구현 객체 참조 변수 세팅 (arg1 : %s)",databaseListener.getClass().getSimpleName());
        this.databaseListener = databaseListener;
    }

    public String getWinner() {
        return winner;
    }

    public interface DatabaseListener {
        void onChanged();
    }
}