package org.techtown.studit;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class JoinRequest extends StringRequest {
    //서버 URL 설정
    final static private String URL = "";
    private Map<String, String> map;

    public JoinRequest(String UserName,String UserNumber, String UserEmail, String UserPw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("UserName", UserName);
        map.put("UserNumber", UserNumber);
        map.put("UserEmail", UserEmail);
        map.put("UserPw", UserPw);

    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }

}
