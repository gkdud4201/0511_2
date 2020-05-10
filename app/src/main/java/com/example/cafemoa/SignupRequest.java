package com.example.cafemoa;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

public class SignupRequest extends StringRequest {
    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://203.237.179.120:7003/Register2.php";
    private Map<String, String> map;
    //private Map<String, String> parameters;

    public SignupRequest(String userID, String userPassword, String email, String phoneNumber, int userSort, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("userID", userID);
        map.put("userPassword", userPassword);
        map.put("userName", email);
        map.put("phoneNumber", phoneNumber);
        map.put("userSort", userSort + "");
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {

        return map;
    }
}
