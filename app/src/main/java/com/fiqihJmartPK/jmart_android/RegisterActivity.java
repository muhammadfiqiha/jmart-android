package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.model.Account;
import com.fiqihJmartPK.jmart_android.request.RegisterRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * activity yang akan digunakan untuk register account jmart
 *
 * @author Fiqih 
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Register");

        LinearLayout layout1 = (LinearLayout)findViewById(R.id.linearLayoutReg1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.linearLayoutReg2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.linearLayoutReg3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.linearLayoutReg4);
        LinearLayout layout5 = (LinearLayout)findViewById(R.id.linearLayoutReg5);
        LinearLayout layout6 = (LinearLayout)findViewById(R.id.linearLayoutReg6);
        LinearLayout layout7 = (LinearLayout)findViewById(R.id.linearLayoutReg7);
        LinearLayout layout8 = (LinearLayout)findViewById(R.id.linearLayoutReg8);
        LinearLayout layout9 = (LinearLayout)findViewById(R.id.linearLayoutReg9);
        LinearLayout layout10 = (LinearLayout)findViewById(R.id.linearLayoutReg10);
        LinearLayout layout11 = (LinearLayout)findViewById(R.id.linearLayoutReg11);
        LinearLayout layout12 = (LinearLayout)findViewById(R.id.linearLayoutReg12);
        Button regButton = (Button) findViewById(R.id.registerButton);
        EditText edName = (EditText)findViewById(R.id.editTextTextPersonNameReg);
        EditText edEmail = (EditText)findViewById(R.id.editTextTextEmailAddressReg);
        EditText edPassword = (EditText)findViewById(R.id.editTextTextPasswordReg);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener <String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj != null){
                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                            }
                        } catch(JSONException e){
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(edName.getText().toString(),
                        edEmail.getText().toString(), edPassword.getText().toString(), listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }


}