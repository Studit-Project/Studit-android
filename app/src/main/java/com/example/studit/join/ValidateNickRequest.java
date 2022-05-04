package com.example.studit.join;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidateNickRequest extends StringRequest{
    //서버 url 설정(php 파일 연동)
    final static  private String URL="";
    private Map<String,String> map;

    public ValidateNickRequest(String userNick, Response.Listener<String>listener){
        super(Request.Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("nickname",userNick);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}
