package com.niroshan.temperjobportal.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Niroshan Rathnayake on 6/7/2018.
 */

public abstract class RecyclerItemAdapter extends RecyclerView.Adapter{

    private ArrayList<String> headerNames = new ArrayList<>();

    RecyclerItemAdapter(){
        setHasStableIds(true);
    }

    public void add(String name) {
        headerNames.add(name);
        notifyDataSetChanged();
    }

    public void add(int index, String name) {
        headerNames.add(index, name);
        notifyDataSetChanged();
    }

    public void addAll(Collection collection) {
        if (collection != null) {
            headerNames.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(String... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        headerNames.clear();
        notifyDataSetChanged();
    }

    public void remove(String object) {
        headerNames.remove(object);
        notifyDataSetChanged();
    }

}
