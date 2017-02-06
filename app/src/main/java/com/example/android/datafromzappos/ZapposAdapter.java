package com.example.android.datafromzappos;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.datafromzappos.databinding.AdapterItemBinding;
import com.example.android.datafromzappos.models.ZapposItem;

import java.util.ArrayList;

/**
 * Created by Xiaojun(Tony) on 2/6/2017.
 */

public class ZapposAdapter extends RecyclerView.Adapter<ZapposItemHolder> {
    private ArrayList<ZapposItem> items;

    public ZapposAdapter(ArrayList<ZapposItem> data) {
        super();
        items = data;
    }

    @Override
    public ZapposItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.adapter_item, parent, false);
        return new ZapposItemHolder(binding);
    }

    @Override
    public void onBindViewHolder(ZapposItemHolder holder, int position) {
        holder.bindConnection(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
