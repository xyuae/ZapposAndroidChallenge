package com.example.android.datafromzappos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.android.datafromzappos.databinding.ActivityMain2Binding;
import com.example.android.datafromzappos.models.ZapposItem;


import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        JSONObject itemJSONObject = null;
        // Get the JSONObject of queried item from Zappos API
        try {
            itemJSONObject = new JSONObject(intent.getStringExtra("item"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ZapposItem zapposItem = new ZapposItem(itemJSONObject);
        ActivityMain2Binding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main2);
        binding.setZapposItem(zapposItem);
    }

    // Override onCreateOptionsMenu to create custom menus for shopping cart, favorite
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_favorite) {
            item.setIcon(R.drawable.ic_favorite_black_24dp);
            return true;
        }
        if (itemThatWasClickedId == R.id.action_shopping_cart) {
            item.setIcon(R.drawable.ic_shopping_cart_black_24dp);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}