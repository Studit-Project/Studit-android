package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://3.39.24.152:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoeWUiLCJyb2xlIjoidXNlciIsIm15TmFtZSI6Imh5ZSIsImV4cCI6MTY2MDc3MDE1MSwiaWF0IjoxNjYwNzUyMTUxfQ.c_6o5OWGASIkuAHFRoMwy87X1rmBMRRYU-NaEpCRRMA";

//    private SharedPreferences preferences;
//    preferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//    String token = preferences.getString("token", "");

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
