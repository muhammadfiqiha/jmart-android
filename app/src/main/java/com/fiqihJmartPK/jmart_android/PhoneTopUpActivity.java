package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * aktivitas yag nantinya akan digunakan untuk top up no hp
 * @author Fiqih
 */
public class PhoneTopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_top_up);

        getSupportActionBar().setTitle("Phone Top Up");

        TextView phoneTopUp = (TextView) findViewById(R.id.viewPhoneTopUp);
    }
}