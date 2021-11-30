package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layoutReg1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layoutReg2);
        LinearLayout layout3 = (LinearLayout)findViewById(R.id.layoutReg3);
        LinearLayout layout4 = (LinearLayout)findViewById(R.id.layoutReg4);
        Button regButton = (Button)findViewById(R.id.registerButton);
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
                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT);
                                Intent intentLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intentLogin);
                            }
                        } catch(JSONException e){
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT);
                            e.printStackTrace();
                        }
                    }
                };
                Response.ErrorListener errorListener = new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", error.toString());
                        Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT);
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(edName.getText().toString(), edEmail.getText().toString(), edPassword.getText().toString(), listener, errorListener);
                RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                requestQueue.add(registerRequest);
            }
        });
    }


}