package com.fiqihJmartPK.jmart_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.fiqihJmartPK.jmart_android.request.PaymentRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * activity untuk aktivitas transaksi produk
 * @author Fiqih
 */
public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        getSupportActionBar().setTitle("Payment");

        TextView prodName = (TextView) findViewById(R.id.paymentProdName);
        prodName.setText(MainActivity.getProd().name);
        TextView prodId = (TextView) findViewById(R.id.paymentProdId);
        prodId.setText(String.valueOf(MainActivity.getProd().id));
        EditText prodCount = (EditText) findViewById(R.id.prodCount);
        EditText shipmentAddress = (EditText) findViewById(R.id.shipmentAddress);
        TextView shipmentPlan = (TextView) findViewById(R.id.shipmentPlan);
        shipmentPlan.setText(convertShipment(MainActivity.getProd().shipmentPlans));

        AppCompatButton payNow = (AppCompatButton) findViewById(R.id.payNowButton);
        AppCompatButton clear = (AppCompatButton) findViewById(R.id.clearPayButton);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prodCount.setText("");
                shipmentAddress.setText("");
            }
        });

        payNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(PaymentActivity.this, "Pay Success", Toast.LENGTH_SHORT).show();
                            Intent mainIntent = new Intent(PaymentActivity.this, MainActivity.class);
                            startActivity(mainIntent);

                        } catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(PaymentActivity.this, "Pay Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                PaymentRequest paymentRequest = new PaymentRequest(LoginActivity.getLoggedAccount().id, MainActivity.getProd().id, Integer.parseInt(prodCount.getText().toString()),
                                                shipmentAddress.getText().toString(), MainActivity.getProd().shipmentPlans, listener, null);
                RequestQueue requestQueue = Volley.newRequestQueue(PaymentActivity.this);
                requestQueue.add(paymentRequest);
            }
        });
    }

    public String convertShipment(byte shipment){
        switch(shipment){
            case (byte) (1 << 0):
                return "INSTANT";
            case (byte) (1 << 1):
                return "SAME DAY";
            case (byte) (1 << 2):
                return "NEXT DAY";
            case (byte) (1 << 3):
                return "REGULER";
            default:
                return "KARGO";
        }
    }
}