package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://3.34.52.62:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJyb2xlIjoidXNlciIsIm15TmFtZSI6InN0cmluZyIsImV4cCI6MTY2MDA3NjA4MywiaWF0IjoxNjYwMDU4MDgzfQ.eXjVg6JARJgQeIDG2fm0BFTbF3SkJS1SGUozLxEgvfU";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
