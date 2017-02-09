package com.example.android.datafromzappos.models;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.android.datafromzappos.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Xiaojun(Tony) on 2/5/2017.
 */

public class ZapposItem {
    private String brandName;
    private String imageUrl;
    private String productId;
    private String originalPrice;
    private String sytleId;
    private String price;
    private String percentOff;
    private String productUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getSytleId() {
        return sytleId;
    }

    public void setSytleId(String sytleId) {
        this.sytleId = sytleId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_search_black_24dp)
                .into(view);
    }

    private String productName;
    public ZapposItem(JSONObject finalObject) {
        try {
            this.brandName = finalObject.getString("brandName");
            this.imageUrl = finalObject.getString("thumbnailImageUrl");
            this.productId = finalObject.getString("productId");
            this.originalPrice = finalObject.getString("originalPrice");
            this.sytleId = finalObject.getString("styleId");
            this.price = finalObject.getString("price");
            this.percentOff = finalObject.getString("percentOff");
            this.productUrl = finalObject.getString("productUrl");
            this.productName = finalObject.getString("productName");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
