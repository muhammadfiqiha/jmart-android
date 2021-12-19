package com.fiqihJmartPK.jmart_android.request;
import java.util.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;

/**
 * kelas request factory digunakan sebagai request http untuk beberapa hal umum seperti mencari id
 * dan membuat suatu page
 * @author Fiqih
 */
public class RequestFactory {

    //instance variables
    private static final String URL_FORMAT_ID = "http://10.0.2.2:8080/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:8080/%s/page?page=%s&pageSize=%s";

    /**
     * method getById yang digunakan untuk mencari id dari suatu objek
     * @param parentURI sebagai identier untuk objek
     * @param id sebagai id dari objek
     * @param listener sebagai response
     * @param errorListener sebagai error response
     * @return respon string dari url yang dimaksud
     */
    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

    /**
     * method untuk mendapatkan list page
     * @param parentURI sebagai identier untuk objek
     * @param page inisial page
     * @param pageSize size page
     * @param listener response json
     * @param errorListener response jika error
     * @return respon string dari url yang dimaksud
     */
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }

}
