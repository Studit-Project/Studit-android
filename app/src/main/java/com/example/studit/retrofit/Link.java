package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://13.209.68.199:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJyb2xlIjoidXNlciIsIm15TmFtZSI6InN0cmluZyIsImV4cCI6MTY2MDI0MTcwNiwiaWF0IjoxNjYwMjIzNzA2fQ.JpaFlhFElcitGkIOs-myeE19rSLZlhj7nCqnLxGRhtE";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
