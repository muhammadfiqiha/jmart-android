package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.request.LoginRequest;
import com.google.gson.*;

import android.content.Intent;
import android.os.Bundle;
import com.fiqihJmartPK.jmart_android.model.*;

import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.*;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private static final Gson gson = new Gson();
    private static Account loggedAccount = null;

    public static Account getLoggedAccount(){
        return loggedAccount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AppCompatButton login = (AppCompatButton) findViewById(R.id.loginButton);
        EditText edEmail = (EditText)findViewById(R.id.editTextTextEmailAddressLog);
        EditText edPassword = (EditText)findViewById(R.id.editTextTextPasswordLog);
        TextView dontHave = (TextView)findViewById(R.id.textViewLog1);
        TextView registerNow = (TextView)findViewById(R.id.textViewLog2);
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layoutLog1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layoutLog2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.layoutLog3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.layoutLog4);

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener <String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG);
                                loggedAccount = gson.fromJson(obj.toString(), Account.class);
                                Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intentMain);
                            }
                        } catch(JSONException e){
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG);
                            e.printStackTrace();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", error.toString());
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG);
                    }
                };
                LoginRequest loginRequest = new LoginRequest(edEmail.getText().toString(), edPassword.getText().toString(), listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });

    }
}