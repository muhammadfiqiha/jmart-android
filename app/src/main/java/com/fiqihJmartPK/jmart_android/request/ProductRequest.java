package com.fiqihJmartPK.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.fiqihJmartPK.jmart_android.model.Product;
import com.fiqihJmartPK.jmart_android.model.ProductCategory;

import java.util.HashMap;
import java.util.Map;

/**
 * kelas product request sebagai request http untuk objek product
 *
 * @author Fiqih
 */
public class ProductRequest extends StringRequest {

    //instance variables
    private static final String URL = "http://10.0.2.2:8080/product/create";
    private static final String urlFilter = "http://10.0.2.2:8080/product/getFiltered";
    private final Map<String, String> params;

    /**
     * constructor productrequest khususnya create untuk meminta param dan response json
     * @param accountId sebagai id dari akun pemilik
     * @param name sebagai nama dari produk
     * @param weight sebagai berat produk
     * @param conditionUsed sebagai kondisi produk
     * @param price sebagai harga awal dari produk
     * @param discount sebagai diskon produk
     * @param category sebagai kategori produk
     * @param shipmentPlans sebagai jenis durasi shipment produk
     * @param listener sebagai response json
     * @param errorListener sebagai response json jika error
     */
    public ProductRequest(int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans,
                          Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("accountId", Integer.toString(accountId));
        params.put("name", name);
        params.put("weight", Integer.toString(weight));
        params.put("conditionUsed", Boolean.toString(conditionUsed));
        params.put("price", Double.toString(price));
        params.put("discount", Double.toString(discount));
        params.put("category", category.toString());
        params.put("shipmentPlans", Byte.toString(shipmentPlans));
    }

    /**
     * constructor productrequest khususnya untuk filter
     * @param page sebagai inisial halaman
     * @param pageSize sebagai ukuran halaman
     * @param accountId sebagai id pemilik produk
     * @param search sebagai nama dari produk yang akan dicari
     * @param minPrice sebagai harga minim produk
     * @param maxPrice sebagai harga max produk
     * @param category sebagai kategori produk
     * @param listener response json
     * @param errorListener response json jika error
     */
    public ProductRequest(int page, int pageSize, int accountId, String search, int minPrice, int maxPrice, ProductCategory category,
                          Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.GET, urlFilter, listener, errorListener);
        params = new HashMap<>();
        params.put("page", Integer.toString(page));
        params.put("pageSize", Integer.toString(pageSize));
        params.put("accountId", Integer.toString(accountId));
        params.put("search", search);
        params.put("minPrice", Integer.toString(minPrice));
        params.put("maxPrice", Integer.toString(maxPrice));
        params.put("category", category.toString());
    }

    /**
     * method untuk mereturn params
     * @return params
     */
    public Map<String, String> getParams(){
        return params;
    }
}
