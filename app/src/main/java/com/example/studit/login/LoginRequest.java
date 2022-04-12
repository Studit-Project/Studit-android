package com.example.studit.login;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //서버 URL 설정(php 파일 연동) (추후 예정)
    final static private String URL = "";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPassword, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("ID", userID);
        map.put("Password", userPassword);
    }

    @Override
    protected Map<String, String> getMap() throws AuthFailureError {
        return map;
    }
}
