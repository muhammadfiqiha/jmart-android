package com.fiqihJmartPK.jmart_android.request;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.*;
import java.util.*;

/**
 * kelas login request yang digunakan untuk meminta request http pada saat login session
 * @author Fiqih
 */
public class LoginRequest extends StringRequest {
    //instance variables
    private static final String URL = "http://10.0.2.2:8080/account/login";
    private final Map<String, String> params;

    /**
     * constructor loginrequest dengan param email dan password serta response json
     * @param email sebagai email akun
     * @param password sebagai password akun
     * @param listener sebagai response json
     * @param errorListener sebagai response json saat error
     */
    public LoginRequest(String email, String password, Response.Listener<String> listener,
                        Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    /**
     * method untuk mereturn params
     * @return params
     */
    public Map<String, String> getParams(){
        return params;
    }
}
