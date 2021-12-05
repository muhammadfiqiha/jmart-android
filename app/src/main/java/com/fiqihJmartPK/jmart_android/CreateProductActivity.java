package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        AppCompatButton create = (AppCompatButton) findViewById(R.id.createProductButton);
        AppCompatButton cancel = (AppCompatButton) findViewById(R.id.cancelCreateProductButton);

        CardView cvCreateProduct = (CardView) findViewById(R.id.cardViewCreateProduct);
        ConstraintLayout clCreateProduct = (ConstraintLayout) findViewById(R.id.constraintCreateProduct);
        TextView createProduct = (TextView) findViewById(R.id.createProduct);
        EditText initProductName = (EditText) findViewById(R.id.editInitProductName);
        EditText initProductWeight = (EditText) findViewById(R.id.editInitProductWeight);
        EditText initProductPrice = (EditText) findViewById(R.id.editInitProductPrice);
        EditText initProductDisc = (EditText) findViewById(R.id.editInitProductDisc);
        TextView condition = (TextView) findViewById(R.id.condition);
        RadioButton usedButton = (RadioButton) findViewById(R.id.usedRadioButton);
        RadioButton newButton = (RadioButton) findViewById(R.id.newRadioButton);
        TextView category = (TextView) findViewById(R.id.category);
        Spinner spinCategory = (Spinner) findViewById(R.id.spinnerCategory);
        TextView shipmentPlan = (TextView) findViewById(R.id.shipmentPlan);
        Spinner spinShipment = (Spinner) findViewById(R.id.spinnerShipmentPlan);
    }
}