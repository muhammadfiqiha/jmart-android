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

/**
 * login activity sebagai activity untuk login session
 */
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

        getSupportActionBar().setTitle("Login");

        Button login = (Button) findViewById(R.id.loginButton);
        EditText edEmail = (EditText)findViewById(R.id.editTextTextEmailAddressLog);
        EditText edPassword = (EditText)findViewById(R.id.editTextTextPasswordLog);
        TextView dontHave = (TextView)findViewById(R.id.textViewLog1);
        TextView registerNow = (TextView)findViewById(R.id.textViewLog2);
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.linearLayoutLogin1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.linearLayoutLogin2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.linearLayoutLogin3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.linearLayoutLogin4);
        LinearLayout layout5 = (LinearLayout)findViewById(R.id.linearLayoutLogin5);
        LinearLayout layout6 = (LinearLayout)findViewById(R.id.linearLayoutLogin6);
        LinearLayout layout7 = (LinearLayout)findViewById(R.id.linearLayoutLogin7);
        LinearLayout layout8 = (LinearLayout)findViewById(R.id.linearLayoutLogin8);
        LinearLayout layout9 = (LinearLayout)findViewById(R.id.linearLayoutLogin9);
        LinearLayout layout10 = (LinearLayout)findViewById(R.id.linearLayoutLogin10);
        LinearLayout layout11 = (LinearLayout)findViewById(R.id.linearLayoutLogin11);
        LinearLayout layout12 = (LinearLayout)findViewById(R.id.linearLayoutLogin12);

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
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            loggedAccount = gson.fromJson(obj.toString(), Account.class);
                            Intent intentMain = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentMain);
                        } catch(JSONException e){
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(edEmail.getText().toString(), edPassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(loginRequest);
            }
        });

    }
}