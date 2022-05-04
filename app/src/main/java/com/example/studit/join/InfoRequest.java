package com.example.studit.join;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InfoRequest extends StringRequest {
    //서버 URL 설정
    final static private String URL = "";
    private Map<String, String> map;

    public InfoRequest(String UserNick,String UserGender, String UserYear, String UserMonth, String UserDay, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("UserNick", UserNick);
        map.put("UserGender", UserGender);
        map.put("UserYear", UserYear);
        map.put("UserMonth", UserMonth);
        map.put("UserDay", UserDay);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

}