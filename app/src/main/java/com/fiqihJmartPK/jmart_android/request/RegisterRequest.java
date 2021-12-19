package com.fiqihJmartPK.jmart_android.request;

import com.android.volley.toolbox.StringRequest;
import java.util.*;
import com.android.volley.*;

/**
 * kelas registerrequest sebagai kelas untuk request http khususnya yang berhubungan dengan register
 */
public class RegisterRequest extends StringRequest {

    //instance variables
    private static final String URL = "http://10.0.2.2:8080/account/register";
    private final Map<String, String> params;

    /**
     * constructor register request khususnya untuk register account
     * @param name sebagai nama akun
     * @param email sebagai email akun
     * @param password sebagai password akun
     * @param listener sebagai response json
     * @param errorListener response json jika error
     */
    public RegisterRequest(String name, String email, String password,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
    }

    /**
     * constructor untuk register store dari sebuah akun
     * @param id sebagai id akun
     * @param name sebagai nama toko
     * @param address sebagai alamat toko
     * @param phoneNumber sebagai kontak toko
     * @param listener response json
     * @param errorListener response jika error
     */
    public RegisterRequest(int id, String name, String address, String phoneNumber,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, "http://10.0.2.2:8080/account/" + id + "/registerStore" , listener, errorListener);
        params = new HashMap<>();
        params.put("name", name);
        params.put("address", address);
        params.put("phoneNumber", phoneNumber);
    }

    /**
     * method untuk mereturn params
     * @return params
     */
    public Map<String, String> getParams(){
        return params;
    }
}
