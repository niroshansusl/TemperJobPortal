package com.niroshan.temperjobportal.view.adapter;

import android.support.v7.widget.RecyclerView;
import com.niroshan.temperjobportal.model.BeanJobList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public abstract class JobCardAdapter extends RecyclerView.Adapter {

    private List<BeanJobList> jobList;

    public void add(BeanJobList object) {
        jobList.add(object);
        notifyDataSetChanged();
    }

    public void add(int index, BeanJobList object) {
        jobList.add(index, object);
        notifyDataSetChanged();
    }

    public void addAll(Collection collection) {
        if (collection != null) {
            jobList.addAll(collection);
            notifyDataSetChanged();
        }
    }

    public void addAll(BeanJobList... items) {
        addAll(Arrays.asList(items));
    }

    public void clear() {
        jobList.clear();
        notifyDataSetChanged();
    }

    public void remove(String object) {
        jobList.remove(object);
        notifyDataSetChanged();
    }

}
