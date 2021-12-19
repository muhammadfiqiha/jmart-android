package com.fiqihJmartPK.jmart_android;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.model.*;
import com.fiqihJmartPK.jmart_android.request.ProductRequest;
import com.fiqihJmartPK.jmart_android.request.RequestFactory;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


/**
 * activity utama pada jmart dan yang muncul pertama untuk login session
 * menampilkan produk serta menu-menu lainnya
 * @author Fiqih
 */
public class MainActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    int pageSize = 15;
    public static Integer page = 0;
    private static Product prod = null;
    public static ArrayList<Product> listProduct = new ArrayList<>();

    public static Product getProd(){
        return prod;
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem addList = menu.findItem(R.id.addListItem);

        addList.setVisible(LoginActivity.getLoggedAccount().store != null);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addListItem:
                Intent intentCreateProduct = new Intent(MainActivity.this, CreateProductActivity.class);
                startActivity(intentCreateProduct);
                return true;
            case R.id.profileItem:
                Intent intentAboutMe = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intentAboutMe);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Jmart");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabItem tabItemProduct = (TabItem) findViewById(R.id.productTab);
        TabItem tabItemFilter = (TabItem) findViewById(R.id.filterTab);

        CardView cardViewProduct = (CardView) findViewById(R.id.cardViewProduct1);
        ConstraintLayout constraintLayoutProduct = (ConstraintLayout) findViewById(R.id.constraintProduct);
        AppCompatButton previousPage = (AppCompatButton) findViewById(R.id.prevButton);
        AppCompatButton nextPage = (AppCompatButton) findViewById(R.id.nextButton);
        AppCompatButton goPage = (AppCompatButton) findViewById(R.id.goButton);
        EditText pageNumber = (EditText) findViewById(R.id.editNumberPage);

        pageNumber.setText(String.valueOf(page + 1), TextView.BufferType.EDITABLE);

        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = page - 1;
                Toast.makeText(MainActivity.this, "Previous Page", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = page + 1;
                Toast.makeText(MainActivity.this, "Next Page", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });

        goPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = Integer.parseInt(pageNumber.getText().toString()) - 1;
                Toast.makeText(MainActivity.this, "Go to page " + page, Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
            }
        });

        CardView cardViewFilter = (CardView) findViewById(R.id.cardViewFilter);
        ConstraintLayout constraintLayoutFilter = (ConstraintLayout) findViewById(R.id.constraintFilter);
        TextView textProductName = (TextView) findViewById(R.id.productName);
        EditText editProductName = (EditText) findViewById(R.id.editProductName);
        TextView prodLowestPrice = (TextView) findViewById(R.id.productLowestPrice);
        EditText editProdLowPrice = (EditText) findViewById(R.id.editLowestPrice);
        TextView prodHighestPrice = (TextView) findViewById(R.id.productHighestPrice);
        EditText editProdHighPrice = (EditText) findViewById(R.id.editHighestPrice);
        TextView toPrice = (TextView) findViewById(R.id.toPrice);
        TextView prodCondition = (TextView) findViewById(R.id.productCondition);
        CheckBox usedBox = (CheckBox) findViewById(R.id.usedCheckBox);
        CheckBox newBox = (CheckBox) findViewById(R.id.newCheckBox);
        TextView prodCategory = (TextView) findViewById(R.id.productCategory);
        Spinner spinnerProdCategory = (Spinner) findViewById(R.id.spinnerProductCategory);
        EditText editAccountId = (EditText) findViewById(R.id.editAccountId);
        AppCompatButton applyButton = (AppCompatButton) findViewById(R.id.applyButton);
        AppCompatButton clearButton = (AppCompatButton) findViewById(R.id.clearButton);

        cardViewFilter.setVisibility(View.GONE);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProductName.setText("");
                editProdHighPrice.setText("");
                editProdLowPrice.setText("");
                usedBox.setChecked(false);
                newBox.setChecked(false);
                editAccountId.setText("");
            }
        });

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray arrObj = new JSONArray();
                            if(arrObj != null){
                                listProduct = gson.fromJson(arrObj.toString(), new TypeToken<ArrayList<Product>>(){}.getType());
                                Toast.makeText(MainActivity.this, "Applying Filter...", Toast.LENGTH_SHORT).show();
                                ArrayList<String> listProductName = new ArrayList<>();
                                for(Product product : listProduct){
                                    listProductName.add(product.name);
                                }
                                //ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(MainActivity.this, R.layout.activity_listview, listProduct);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.activity_listview, listProductName);

                                ListView listViewProduct = (ListView) findViewById(R.id.listProduct);
                                listViewProduct.setAdapter(adapter);
                                listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        //prod = (Product) listViewProduct.getItemAtPosition(position);
                                        String productName = (String) listViewProduct.getItemAtPosition(position);
                                        for(Product product : listProduct){
                                            if(product.name.equals(productName)){
                                                prod = product;
                                            }
                                        }
                                        Toast.makeText(MainActivity.this, "Seeing Product...", Toast.LENGTH_SHORT).show();
                                        Intent intentProductDetail = new Intent(MainActivity.this, ProductDetailActivity.class);
                                        startActivity(intentProductDetail);
                                    }
                                });
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                ProductRequest productRequest = new ProductRequest(page.intValue(), pageSize, Integer.parseInt(editAccountId.getText().toString()), editProductName.getText().toString(), Integer.parseInt(editProdLowPrice.getText().toString()),
                                                                    Integer.parseInt(editProdHighPrice.getText().toString()), convertCategory(spinnerProdCategory.getSelectedItem().toString()), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                requestQueue.add(productRequest);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab){

                if(tabLayout.getSelectedTabPosition() == 0){
                    cardViewProduct.setVisibility(View.VISIBLE);
                    cardViewFilter.setVisibility(View.GONE);
                }
                else if(tabLayout.getSelectedTabPosition() == 1) {
                    cardViewProduct.setVisibility(View.GONE);
                    cardViewFilter.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab){

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        Response.Listener <String> listenerProd = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray arrObj = new JSONArray(response);
                    if(arrObj != null){
                        listProduct = gson.fromJson(arrObj.toString(), new TypeToken<ArrayList<Product>>(){}.getType());
                        ArrayList<String> listProductName = new ArrayList<>();
                        for(Product product : listProduct){
                            listProductName.add(product.name);
                        }
                        //ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(MainActivity.this, R.layout.activity_listview, listProduct);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.activity_listview, listProductName);

                        ListView listViewProduct = (ListView) findViewById(R.id.listProduct);
                        listViewProduct.setAdapter(adapter);
                        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //prod = (Product) listViewProduct.getItemAtPosition(position);
                                String productName = (String) listViewProduct.getItemAtPosition(position);
                                for(Product product : listProduct){
                                    if(product.name.equals(productName)){
                                        prod = product;
                                    }
                                }
                                Toast.makeText(MainActivity.this, "Seeing Product...", Toast.LENGTH_SHORT).show();
                                Intent intentProductDetail = new Intent(MainActivity.this, ProductDetailActivity.class);
                                startActivity(intentProductDetail);
                            }
                        });
                    }
                } catch (JSONException e){
                    Toast.makeText(MainActivity.this, "See Product Failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        };
        RequestQueue requestQueueProd = Volley.newRequestQueue(MainActivity.this);
        requestQueueProd.add(RequestFactory.getPage("product", page, pageSize, listenerProd, null));
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