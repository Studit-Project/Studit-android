package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://13.125.250.60:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjYwNDIyMTMxLCJpYXQiOjE2NjA0MDQxMzF9.P2VDeO20aU4w5BvjxRMcLTcreMBlC1MSQdVMBQ4cjjk";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
