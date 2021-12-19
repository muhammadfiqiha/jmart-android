package com.fiqihJmartPK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * kelas payment request sebagai request http khususnya untuk objek payment
 *
 * @author Fiqih
 */
public class PaymentRequest extends StringRequest {

    //instance variables
    private static final String urlCreate = "http://10.0.2.2:8080/payment/create";
    private final Map<String, String> params;

    /**
     * constructor untuk payment request khususnya create untuk meminta parameter dan response json
     * @param buyerId sebagai id dari akun pembeli
     * @param productId sebagai id dari produk
     * @param productCount sebagai total jumlah produk
     * @param shipmentAddress sebagai alamat pengiriman
     * @param shipmentPlan sebagai jenis durasi pengiriman
     * @param listener sebagai response json
     * @param errorListener sebagai response json jika error
     */
    public PaymentRequest(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan,
                          Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, urlCreate, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId", Integer.toString(buyerId));
        params.put("productId", Integer.toString(productId));
        params.put("productCount", Integer.toString(productCount));
        params.put("shipmentAddress", shipmentAddress);
        params.put("shipmentPlan", Byte.toString(shipmentPlan));
    }

    /**
     * method untuk mereturn params
     * @return params
     */
    public Map<String, String> getParams(){
        return params;
    }
}
