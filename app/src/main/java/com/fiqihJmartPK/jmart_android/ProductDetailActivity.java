package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.request.AccountRequest;
import com.fiqihJmartPK.jmart_android.request.RequestFactory;
import com.google.gson.Gson;

import com.fiqihJmartPK.jmart_android.model.*;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * activity yang digunakan untuk tampilan detail produk setelah diklik pada main activity
 * @author Fiqih
 */
public class ProductDetailActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    public static Account prodOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        getSupportActionBar().setTitle("Detail Product");

        CardView cvProdDetail = (CardView) findViewById(R.id.cardViewProductDetail);
        ConstraintLayout clProdDetail = (ConstraintLayout) findViewById(R.id.constraintProdDetail);
        TextView prodDetail = (TextView) findViewById(R.id.textViewProductDetail);

        TextView name = (TextView) findViewById(R.id.prodDetailName);
        TextView nameProd = (TextView) findViewById(R.id.detailName);
        nameProd.setText(MainActivity.getProd().name);

        TextView weight = (TextView) findViewById(R.id.prodDetailWeight);
        TextView weightProd = (TextView) findViewById(R.id.detailWeight);
        weightProd.setText(String.valueOf(MainActivity.getProd().weight));

        TextView owner = (TextView) findViewById(R.id.prodDetailOwner);
        TextView ownerProd = (TextView) findViewById(R.id.detailOwner);

        TextView contact = (TextView) findViewById(R.id.prodDetailWeight);
        TextView contactProd = (TextView) findViewById(R.id.detailContact);

        TextView category = (TextView) findViewById(R.id.prodDetailCategory);
        TextView categoryProd = (TextView) findViewById(R.id.detailCategory);
        categoryProd.setText(String.valueOf(MainActivity.getProd().category));

        TextView condition = (TextView) findViewById(R.id.prodDetailCondition);
        TextView conditionProd = (TextView) findViewById(R.id.detailCondition);
        conditionProd.setText(convertCondition(MainActivity.getProd().conditionUsed));

        TextView price = (TextView) findViewById(R.id.prodDetailPrice);
        TextView priceProd = (TextView) findViewById(R.id.detailPrice);
        priceProd.setText(String.valueOf(MainActivity.getProd().price));

        TextView disc = (TextView) findViewById(R.id.prodDetailDisc);
        TextView discProd = (TextView) findViewById(R.id.detailDisc);
        discProd.setText(convertDiscount(MainActivity.getProd().discount));

        TextView shipment = (TextView) findViewById(R.id.prodDetailShipment);
        TextView shipmentProd = (TextView) findViewById(R.id.detailShipment);
        shipmentProd.setText(convertShipment(MainActivity.getProd().shipmentPlans));





        AppCompatButton buyButton = (AppCompatButton) findViewById(R.id.buyProductButton);
        AppCompatButton backMain = (AppCompatButton) findViewById(R.id.backMainButton);

        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intentMain);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPayment = new Intent(ProductDetailActivity.this, PaymentActivity.class);
                startActivity(intentPayment);
            }
        });

        Response.Listener <String> listenerAcc = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject obj = new JSONObject(response);
                    if(obj != null){
                        prodOwner = gson.fromJson(obj.toString(), Account.class);
                        contactProd.setText(String.valueOf(prodOwner.store.phoneNumber));
                        ownerProd.setText(prodOwner.store.name);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        AccountRequest accountRequest = new AccountRequest(MainActivity.getProd().accountId, listenerAcc, null);
        RequestQueue requestQueueAcc = Volley.newRequestQueue(ProductDetailActivity.this);
        requestQueueAcc.add(accountRequest);
    }

    public String convertShipment(byte shipment){
        switch(shipment){
            case (byte) (1 << 0):
                return "INSTANT";
            case (byte) (1 << 1):
                return "SAME DAY";
            case (byte) (1 << 2):
                return "NEXT DAY";
            case (byte) (1 << 3):
                return "REGULER";
            default:
                return "KARGO";
        }
    }

    public String convertDiscount(double discount){
        discount = discount * 100;
        return discount + "%";
    }

    public String convertCondition(boolean condition){
        if(condition){
            return "USED";
        }
        return "NEW";
    }
}