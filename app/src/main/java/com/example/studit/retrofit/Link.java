package com.example.studit.retrofit;

public class Link {
    public static String BASE_URL = "http://34.64.52.84:8081/";
    public static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMTA1MTMyODU0MyIsInJvbGUiOiJ1c2VyIiwibXlOYW1lIjoiMDEwNTEzMjg1NDMiLCJleHAiOjE2NTU2Njg1NzEsImlhdCI6MTY1NTY2Njc3MX0.tLZcCbVWoxKmzaVtvU8sYD0SfjaY4AJqfCuTNaLyL84";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getToken() {
        return token;
    }
}
