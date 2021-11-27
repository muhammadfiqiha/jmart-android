package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;

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
    }
}