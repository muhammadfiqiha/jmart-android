package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

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
    }
}