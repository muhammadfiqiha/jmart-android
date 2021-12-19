package com.fiqihJmartPK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * kelas account request sebagai kelas untuk request http pada kelas akun khususnya untuk mencari id
 * yang sesuai dengan param
 * @author Fiqih
 */
public class AccountRequest extends StringRequest {

    //instance variables
    private final Map<String, String> params;

    /**
     * constructor accountrequest untuk meminta parameter id dan response dari json
     * @param id sebagai id akun
     * @param listener sebagai response json
     * @param errorListener sebagai response json saat error
     */
    public AccountRequest(int id, Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.GET, "http://10.0.2.2:8080/account/" + id, listener, errorListener);
        params = new HashMap<>();
        params.put("id", Integer.toString(id));
    }

    /**
     * constructor untuk top up balance dari suatu akun
     * @param id sebagai id akun
     * @param balance sebagai balance akun
     * @param listener response json
     * @param errorListener response jika error
     */
    public AccountRequest(int id, double balance,
                           Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, "http://10.0.2.2:8080/account/" + id + "/topUp" , listener, errorListener);
        params = new HashMap<>();
        params.put("balance", Double.toString(balance));
    }

    /**
     * method untuk mereturn params
     * @return params
     */
    public Map<String, String> getParams(){
        return params;
    }
}
