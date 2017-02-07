package com.example.android.datafromzappos;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.android.datafromzappos.databinding.ActivityMain2Binding;
import com.example.android.datafromzappos.models.ZapposItem;


import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main2);
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
}
