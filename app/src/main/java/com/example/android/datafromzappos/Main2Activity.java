package com.example.android.datafromzappos;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.datafromzappos.models.ZapposItem;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        JSONObject itemJSONObject = null;
        // Get the JSONObject of queried item from Zappos API
        try {
            itemJSONObject = new JSONObject(intent.getStringExtra("item"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ZapposItem zapposItem = new ZapposItem(itemJSONObject);
        /**
         * Set ImageView on product page by Picasso API
         */
        ImageView productImageView = (ImageView) findViewById(R.id.productImageView);
        Picasso.with(this)
                .load(zapposItem.getImageUrl())
                .into(productImageView);
        TextView priceTextView = (TextView) findViewById(R.id.textView_brand);
        TextView brandTextView = (TextView) findViewById(R.id.textView_price);
        priceTextView.setText(zapposItem.getPrice());
        brandTextView.setText(zapposItem.getBrandName());
    }
}
