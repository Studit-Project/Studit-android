package com.example.studit.join;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidatePhoneRequest extends StringRequest{
    //서버 url 설정
    final static  private String URL="http://54.180.97.161:8081/.php";
    private Map<String,String> map;

    public ValidatePhoneRequest(String Phone, Response.Listener<String>listener){
        super(Request.Method.POST,URL,listener,null);

        map=new HashMap<>();
        map.put("phone",Phone);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }

}

