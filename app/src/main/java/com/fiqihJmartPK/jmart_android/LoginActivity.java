package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.*;

import android.content.Intent;
import android.os.Bundle;
import com.fiqihJmartPK.jmart_android.model.*;

import android.text.Layout;
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

        Button login = (Button)findViewById(R.id.loginButton);
        EditText edEmail = (EditText)findViewById(R.id.editTextTextEmailAddressLog);
        EditText edPassword = (EditText)findViewById(R.id.editTextTextPasswordLog);
        TextView dontHave = (TextView)findViewById(R.id.textViewLog1);
        TextView registerNow = (TextView)findViewById(R.id.textViewLog2);
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layoutLog1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layoutLog2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.layoutLog3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.layoutLog4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener <String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if (obj != null){
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                };
            }
        });
    }
}