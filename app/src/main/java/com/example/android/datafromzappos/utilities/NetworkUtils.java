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
package com.example.android.datafromzappos.utilities;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */

/**
 * These utilities will be used to communicate with the zappos servers.
 */
public final class NetworkUtils {
    private final static String ZAPPOS_BASE_URL =
            "https://api.zappos.com/Search";
    final static String QUERY_PARAM = "term";
    final static String KEY = "b743e26728e16b81da139182bb2094357c31d331";
    final static String KEY_PARAM = "key";

    /**
     * Builds the URL used to talk to the weather server using a location. This location is based
     * on the query capabilities of the weather provider that we are using.
     *
     * @param zapposSearchQuery The item that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String zapposSearchQuery) {
        // COMPLETED (1) Fix this method to return the URL used to query Open Zappos's API
        Uri builtUri = Uri.parse(ZAPPOS_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, zapposSearchQuery)
                .appendQueryParameter(KEY_PARAM, KEY).build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static JSONObject parseJson(String responseFromHttpUrl) {
        if (responseFromHttpUrl == null) return null;
        try {
            JSONObject parentObject = new JSONObject(responseFromHttpUrl);
            // JSONArray parentArray = parentObject.getJSONArray("results");
            return parentObject.getJSONArray("results").getJSONObject(0);
            /**
             * This is the decelerator of all fields in JSON Object for future use
             String brandName = finalObject.getString("brandName");
             String imageUrl = finalObject.getString("thumbnailImageUrl");
             String productId = finalObject.getString("productId");
             String originalPrice = finalObject.getString("originalPrice");
             String sytleId = finalObject.getString("styleId");
             String price = finalObject.getString("price");
             String percentOff = finalObject.getString("percentOff");
             String productUrl = finalObject.getString("productUrl");
             String productName = finalObject.getString("productName");
             **/
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}