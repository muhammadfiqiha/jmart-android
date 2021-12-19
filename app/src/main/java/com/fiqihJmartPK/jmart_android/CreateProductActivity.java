package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.request.ProductRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import com.fiqihJmartPK.jmart_android.model.*;

import java.util.ArrayList;

/**
 * aktivitas dari create product untuk membuat suatu produk dan hasilnya akan ditampilkan
 * di list product pada main
 */
public class CreateProductActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Product prod = null;

    public static Product getProd(){ return prod; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        getSupportActionBar().setTitle("Create Product");

        AppCompatButton create = (AppCompatButton) findViewById(R.id.createProductButton);
        AppCompatButton cancel = (AppCompatButton) findViewById(R.id.cancelCreateProductButton);

        CardView cvCreateProduct = (CardView) findViewById(R.id.cardViewCreateProduct);
        ConstraintLayout clCreateProduct = (ConstraintLayout) findViewById(R.id.constraintCreateProduct);
        TextView createProduct = (TextView) findViewById(R.id.createProduct);
        EditText initProductName = (EditText) findViewById(R.id.editInitProductName);
        EditText initProductWeight = (EditText) findViewById(R.id.editInitProductWeight);
        EditText initProductPrice = (EditText) findViewById(R.id.editInitProductPrice);
        EditText initProductDisc = (EditText) findViewById(R.id.editInitProductDisc);
        TextView condition = (TextView) findViewById(R.id.condition);
        RadioButton usedButton = (RadioButton) findViewById(R.id.usedRadioButton);
        RadioButton newButton = (RadioButton) findViewById(R.id.newRadioButton);
        TextView category = (TextView) findViewById(R.id.category);
        Spinner spinCategory = (Spinner) findViewById(R.id.spinnerCategory);
        TextView shipmentPlan = (TextView) findViewById(R.id.shipmentPlan);
        Spinner spinShipment = (Spinner) findViewById(R.id.spinnerShipmentPlan);

        usedButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    newButton.setChecked(false);
                }
            }
        });

        newButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    usedButton.setChecked(false);
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initProductName.setText("");
                initProductWeight.setText("");
                initProductPrice.setText("");
                initProductDisc.setText("");
                usedButton.setChecked(false);
                newButton.setChecked(false);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener <String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(CreateProductActivity.this, "Create Product Successful", Toast.LENGTH_SHORT).show();
                            prod = gson.fromJson(obj.toString(), Product.class);
                            finish();
                            startActivity(getIntent());
                        } catch (JSONException e){
                            Toast.makeText(CreateProductActivity.this, "Create Product Failed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };
                ProductRequest productRequest = new ProductRequest(LoginActivity.getLoggedAccount().id, initProductName.getText().toString(),
                        Integer.parseInt(initProductWeight.getText().toString()), usedButton.isChecked(), Double.parseDouble(initProductPrice.getText().toString()),
                        Double.parseDouble(initProductDisc.getText().toString()), convertCategory(spinCategory.getSelectedItem().toString()), convertShipment(spinShipment.getSelectedItem().toString()),
                        listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(CreateProductActivity.this);
                requestQueue.add(productRequest);
            }
        });
    }

    protected byte convertShipment(String shipment){
        if(shipment.equals("INSTANT")){
            return (byte) (1 << 0);
        }
        else if(shipment.equals("SAME DAY")){
            return (byte) (1 << 1);
        }
        else if(shipment.equals("NEXT DAY")){
            return (byte) (1 << 2);
        }
        else if(shipment.equals("REGULER")){
            return (byte) (1 << 3);
        }
        else{
            return (byte) (1 << 4);
        }
    }

    public ProductCategory convertCategory(String category){
        switch(category){
            case "ART CRAFT":
                return ProductCategory.ART_CRAFT;
            case "AUTOMOTIVE":
                return ProductCategory.AUTOMOTIVE;
            case "BOOK":
                return ProductCategory.BOOK;
            case "CARPENTRY":
                return ProductCategory.CARPENTRY;
            case "COSMETICS":
                return ProductCategory.COSMETICS;
            case "ELECTRONIC":
                return ProductCategory.ELECTRONIC;
            case "FASHION":
                return ProductCategory.FASHION;
            case "FNB":
                return ProductCategory.FNB;
            case "FURNITURE":
                return ProductCategory.FURNITURE;
            case "GADGET":
                return ProductCategory.GADGET;
            case "GAMING":
                return ProductCategory.GAMING;
            case "HEALTHCARE":
                return ProductCategory.HEALTHCARE;
            case "JEWELRY":
                return ProductCategory.JEWELRY;
            case "KITCHEN":
                return ProductCategory.KITCHEN;
            case "MISCELLANEOUS":
                return ProductCategory.MISCELLANEOUS;
            case "MOTHERCARE":
                return ProductCategory.MOTHERCARE;
            case "PETCARE":
                return ProductCategory.PETCARE;
            case "PROPERTY":
                return ProductCategory.PROPERTY;
            case "SPORTS":
                return ProductCategory.SPORTS;
            case "STATIONERY":
                return ProductCategory.STATIONERY;
            case "TOYS":
                return ProductCategory.TOYS;
            default:
                return ProductCategory.TRAVEL;
        }
    }
}