package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://3.34.52.62:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJyb2xlIjoidXNlciIsIm15TmFtZSI6InN0cmluZyIsImV4cCI6MTY1OTYzMzcwMCwiaWF0IjoxNjU5NjE1NzAwfQ.SYsq2u-6bTq3JVVkTS1PDC8Me5UloEk0DZn-L701Xbo";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
