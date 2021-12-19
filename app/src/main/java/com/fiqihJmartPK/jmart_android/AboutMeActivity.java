package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.model.*;
import com.fiqihJmartPK.jmart_android.request.AccountRequest;
import com.fiqihJmartPK.jmart_android.request.RegisterRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * activity untuk kelas about me
 * menampilkan profil, profil toko
 * menyediakan top up balance
 *
 * @author Fiqih
 */
public class AboutMeActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_aboutme, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.historyItem:
                Intent intentInvoice = new Intent(AboutMeActivity.this, InvoiceHistoryActivity.class);
                startActivity(intentInvoice);
                return true;
            case R.id.phoneItem:
                Intent intentAboutMe = new Intent(AboutMeActivity.this, PhoneTopUpActivity.class);
                startActivity(intentAboutMe);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        getSupportActionBar().setTitle("About Me");

        TextView logAccName = (TextView) findViewById(R.id.logAccName);
        logAccName.setText((CharSequence) LoginActivity.getLoggedAccount().name);
        TextView logAccEmail = (TextView) findViewById(R.id.logAccEmail);
        logAccEmail.setText((CharSequence) LoginActivity.getLoggedAccount().email);
        TextView logAccBalance = (TextView) findViewById(R.id.logAccBalance);
        String accountBalance = String.valueOf(LoginActivity.getLoggedAccount().balance);
        logAccBalance.setText(accountBalance);

        CardView cvAccDetails = (CardView) findViewById(R.id.cardViewAccDetails);
        ConstraintLayout clAccDetails = (ConstraintLayout) findViewById(R.id.constraintAccDetails);
        TextView accDetails = (TextView) findViewById(R.id.accDetails);
        TextView accName = (TextView) findViewById(R.id.accName);
        TextView accEmail = (TextView) findViewById(R.id.accEmail);
        TextView accBalance = (TextView) findViewById(R.id.accBalance);
        EditText edTopUp = (EditText) findViewById(R.id.editTopUp);
        AppCompatButton topUpButton = (AppCompatButton) findViewById(R.id.topUpButton);
        AppCompatButton regStoreButton = (AppCompatButton) findViewById(R.id.regStoreButton);

        CardView cvRegStore = (CardView) findViewById(R.id.cardViewRegStore);
        ConstraintLayout clRegStore = (ConstraintLayout) findViewById(R.id.constraintLayoutRegStore);
        TextView registerStore = (TextView) findViewById(R.id.registerStore);
        EditText edStoreName = (EditText) findViewById(R.id.editStoreName);
        EditText edStoreAddress = (EditText) findViewById(R.id.editStoreAddress);
        EditText edStorePhone = (EditText) findViewById(R.id.editStorePhone);
        AppCompatButton registerStoreButton = (AppCompatButton) findViewById(R.id.registerStoreButton);
        AppCompatButton cancelRegisterStore = (AppCompatButton) findViewById(R.id.cancelRegisterStoreButton);

        CardView cvStore = (CardView) findViewById(R.id.cardViewStore);
        ConstraintLayout clStore = (ConstraintLayout) findViewById(R.id.constraintStore);
        TextView store = (TextView) findViewById(R.id.store);
        TextView storeName = (TextView) findViewById(R.id.storeName);
        TextView storeAddress = (TextView) findViewById(R.id.storeAddress);
        TextView storePhone = (TextView) findViewById(R.id.storePhone);
        TextView logStoreName = (TextView) findViewById(R.id.logStoreName);
        TextView logStoreAddress = (TextView) findViewById(R.id.logStoreAddress);
        TextView logStorePhone = (TextView) findViewById(R.id.logStorePhone);


        if(LoginActivity.getLoggedAccount().store != null){
            cvStore.setVisibility(View.VISIBLE);
            cvRegStore.setVisibility(View.GONE);
            regStoreButton.setVisibility(View.GONE);
            logStorePhone.setText(LoginActivity.getLoggedAccount().store.phoneNumber);
            logStoreAddress.setText(LoginActivity.getLoggedAccount().store.address);
            logStoreName.setText(LoginActivity.getLoggedAccount().store.name);
        }
        else if(LoginActivity.getLoggedAccount().store == null){
            cvStore.setVisibility(View.GONE);
            cvRegStore.setVisibility(View.GONE);
        }

        topUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener <String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LoginActivity.getLoggedAccount().balance += Integer.parseInt(edTopUp.getText().toString());
                        Toast.makeText(AboutMeActivity.this, "Top Up Successful", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
                };
                AccountRequest accountRequest = new AccountRequest(LoginActivity.getLoggedAccount().id, Double.parseDouble(edTopUp.getText().toString()), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(accountRequest);
            }
        });

        regStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvRegStore.setVisibility(View.VISIBLE);
            }
        });

        cancelRegisterStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvRegStore.setVisibility(View.GONE);
            }
        });

        registerStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener <String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            LoginActivity.getLoggedAccount().store = gson.fromJson(obj.toString(), Store.class);
                            if(LoginActivity.getLoggedAccount().store != null){
                                Toast.makeText(AboutMeActivity.this, "Store Register Successful", Toast.LENGTH_SHORT).show();
                                cvStore.setVisibility(View.VISIBLE);
                                finish();
                                startActivity(getIntent());
                            }
                        } catch (JSONException e){
                            Toast.makeText(AboutMeActivity.this, "Store Register Failed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(LoginActivity.getLoggedAccount().id, edStoreName.getText().toString(),
                        edStoreAddress.getText().toString(), edStorePhone.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(AboutMeActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }
}