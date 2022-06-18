package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://3.39.192.79:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaW5hIiwicm9sZSI6InVzZXIiLCJteU5hbWUiOiJtaW5hIiwiZXhwIjoxNjU1NTc5NDE5LCJpYXQiOjE2NTU1Nzc2MTl9.L9djxB8LZ4qx7D20pqAT19jrSqtzdXagKio6ivl6gF8";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
