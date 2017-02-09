/**package com.example.android.datafromzappos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.datafromzappos.models.ZapposItem;
import com.example.android.datafromzappos.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ArrayList<ZapposItem> objects = new ArrayList<>();

        // Get intent
        Intent intent = getIntent();
        JSONArray queryResultJSONArray = null;
        // Get the JSONArray of queried item from Zappos API
        try {
            queryResultJSONArray = new JSONArray(intent.getStringExtra("item"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < queryResultJSONArray.length(); i++) {
            ZapposItem eachItem = null;
            try {
                eachItem = new ZapposItem(queryResultJSONArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            objects.add(eachItem);
        }
        ZapposAdapter mAdapter = new ZapposAdapter(objects);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }
}
**/