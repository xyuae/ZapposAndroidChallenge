/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.datafromzappos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.datafromzappos.models.ZapposItem;
import com.example.android.datafromzappos.utilities.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText mSearchBoxEditText;

    private TextView mUrlDisplayTextView;

    private TextView mSearchResultsTextView;

    // Create a variable to store a reference to the error message TextView
    private TextView mErrorMessageTextView;

    //  Create a ProgressBar variable to store a reference to the ProgressBar
    private ProgressBar mLoadingIndicatorProgressBar;

    private void showView(JSONObject queryJSONObject) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("item", queryJSONObject.toString());
        startActivity(intent);
    }
    // Create method to pass intent to recyclerView
    private void showRecyclerView(JSONArray queryJSONArray) {
        Intent intent = new Intent(this, RecyclerView.class);
        intent.putExtra("item", queryJSONArray.toString());
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearchBoxEditText = (EditText) findViewById(R.id.et_search_box);

        mUrlDisplayTextView = (TextView) findViewById(R.id.tv_url_display);
        mSearchResultsTextView = (TextView) findViewById(R.id.tv_search_results_json);

        // TODO (13) Get a reference to the error TextView using findViewById
        mErrorMessageTextView = (TextView) findViewById(R.id.tv_error_message_display);
        // TODO (25) Get a reference to the ProgressBar using findViewById
        mLoadingIndicatorProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
    }

    /**
     * This method retrieves the search text from the EditText, constructs the
     * URL (using {@link NetworkUtils}) for the Zappos item you'd like to find, displays
     * that URL in a TextView, and finally fires off an AsyncTask to perform the GET request using
     * our {@link QueryTask}
     */
    private void makeZapposSearchQuery() {
        String Query = mSearchBoxEditText.getText().toString();
        URL SearchUrl = NetworkUtils.buildUrl(Query);
        mUrlDisplayTextView.setText(SearchUrl.toString());
        new QueryTask().execute(SearchUrl);
    }

    // TODO (14) Create a method called showJsonDataView to show the data and hide the error
    private void showJsonDataView() {
        // Make sure the error is invisible
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
        // Make sure the JSON data is visible
        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage() {
        // Make sure the error is visible
        mErrorMessageTextView.setVisibility(View.VISIBLE);
        // The JSON data is invisible
        mSearchResultsTextView.setVisibility(View.INVISIBLE);
    }
    // TODO (15) Create a method called showErrorMessage to show the error and hide the data

    public class QueryTask extends AsyncTask<URL, Void, String> {

        // TODO (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicatorProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String SearchResults = null;
            try {
                SearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return SearchResults;
        }

        @Override
        protected void onPostExecute(String zapposSearchResults) {
            // TODO (27) As soon as the loading is complete, hide the loading indicator
            mLoadingIndicatorProgressBar.setVisibility(View.INVISIBLE);
            if (zapposSearchResults != null && !zapposSearchResults.equals("")) {
                // TODO (17) Call showJsonDataView if we have valid, non-null results
                showJsonDataView();
                // mSearchResultsTextView.setText(zapposSearchResults);
                showRecyclerView(NetworkUtils.parseJson(zapposSearchResults));
                // showView(NetworkUtils.parseJson(zapposSearchResults));
            } else {
                showErrorMessage();
            }
            // TODO (16) Call showErrorMessage if the result is null in onPostExecute
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            makeZapposSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
