package com.fiqihJmartPK.jmart_android;

import com.fiqihJmartPK.jmart_android.model.*;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    String[] listProduct = {"Chitato Chocolatos", "Rexus Ubuntu", "Philips GTX", "Windows Premium",
            "Sharp sharp", "Redmi Rexus"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem search = (MenuItem) menu.findItem(R.id.searchItem);
        MenuItem addList = (MenuItem) menu.findItem(R.id.addListItem);
        MenuItem profile = (MenuItem) menu.findItem(R.id.profileItem);

        if(LoginActivity.getLoggedAccount().store != null){
            addList.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.addListItem:
                Intent intentCreateProduct = new Intent(MainActivity.this, CreateProductActivity.class);
                startActivity(intentCreateProduct);
                return true;
            case R.id.profileItem:
                Intent intentAboutMe = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intentAboutMe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, listProduct);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        TabItem tabItemProduct = (TabItem) findViewById(R.id.productTab);
        TabItem tabItemFilter = (TabItem) findViewById(R.id.filterTab);

        CardView cardViewProduct = (CardView) findViewById(R.id.cardViewProduct1);
        ConstraintLayout constraintLayoutProduct = (ConstraintLayout) findViewById(R.id.constraintProduct);
        AppCompatButton previousPage = (AppCompatButton) findViewById(R.id.prevButton);
        AppCompatButton nextPage = (AppCompatButton) findViewById(R.id.nextButton);
        AppCompatButton goPage = (AppCompatButton) findViewById(R.id.goButton);
        EditText numberPage = (EditText) findViewById(R.id.editNumberPage);
        ListView listView = (ListView) findViewById(R.id.listProduct);
        listView.setAdapter(adapter);

        CardView cardViewFilter = (CardView) findViewById(R.id.cardViewFilter);
        ConstraintLayout constraintLayoutFilter = (ConstraintLayout) findViewById(R.id.constraintFilter);
        TextView textProductName = (TextView) findViewById(R.id.productName);
        EditText editProductName = (EditText) findViewById(R.id.editProductName);
        TextView prodLowestPrice = (TextView) findViewById(R.id.productLowestPrice);
        EditText editProdLowPrice = (EditText) findViewById(R.id.editLowestPrice);
        TextView prodHighestPrice = (TextView) findViewById(R.id.productHighestPrice);
        EditText editProdHighPrice = (EditText) findViewById(R.id.editHighestPrice);
        TextView toPrice = (TextView) findViewById(R.id.toPrice);
        TextView prodCondition = (TextView) findViewById(R.id.productCondition);
        CheckBox usedBox = (CheckBox) findViewById(R.id.usedCheckBox);
        CheckBox newBox = (CheckBox) findViewById(R.id.newCheckBox);
        TextView prodCategory = (TextView) findViewById(R.id.productCategory);
        Spinner spinnerProdCategory = (Spinner) findViewById(R.id.spinnerProductCategory);

        tabItemProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewFilter.setVisibility(View.INVISIBLE);
            }
        });

        tabItemFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewProduct.setVisibility(View.INVISIBLE);
            }
        });
    }


}