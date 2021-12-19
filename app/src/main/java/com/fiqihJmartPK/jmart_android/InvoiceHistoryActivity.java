package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.request.RequestFactory;
import com.google.gson.Gson;

import java.util.ArrayList;

import com.fiqihJmartPK.jmart_android.model.*;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * sebagai kelas invoice history yang akan menampilkan list history dari invoice
 * @author Fiqih
 */
public class InvoiceHistoryActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    int pageSize = 15;
    public static Integer page = 0;
    private static Payment payment = null;
    public static ArrayList<Payment> listPayment = new ArrayList<Payment>();

    public static Payment getPayment(){
        return payment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_history);

        getSupportActionBar().setTitle("Invoice History");

        Response.Listener <String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray arrObj = new JSONArray(response);
                    if(arrObj != null){
                        listPayment = gson.fromJson(arrObj.toString(), new TypeToken<ArrayList<Payment>>(){}.getType());
                        ArrayAdapter<Payment> adapter = new ArrayAdapter<Payment>(InvoiceHistoryActivity.this, R.layout.activity_listview_payment, listPayment);

                        ListView listViewPayment = (ListView) findViewById(R.id.listPayment);
                        listViewPayment.setAdapter(adapter);
                        listViewPayment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                payment = (Payment) listViewPayment.getItemAtPosition(position);
                                Toast.makeText(InvoiceHistoryActivity.this, "Seeing Invoice...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e){
                    Toast.makeText(InvoiceHistoryActivity.this, "See Product Failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }};
             RequestQueue requestQueue = Volley.newRequestQueue(InvoiceHistoryActivity.this);
             requestQueue.add(RequestFactory.getPage("product", page, pageSize, listener, null));
    }
}