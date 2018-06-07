package com.niroshan.temperjobportal.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.databinding.JobItemCardBinding;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.viewModel.ItemJobCardViewModel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public abstract class JobCardAdapter extends RecyclerView.Adapter {

    private List<BeanJobList> jobList;

   /* public JobCardAdapter(){
        this.jobList = Collections.emptyList();
        setHasStableIds(true);
    }*/

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

    /*public JobCardAdapter(){
        this.jobList = Collections.emptyList();
    }*/

   /* @Override
    public JobAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        JobItemCardBinding itemCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.job_item_card, parent, false);

        return new JobAdapterViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(JobAdapterViewHolder holder, int position) {
        holder.bindJob(jobList.get(position));
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void setJobList(List<BeanJobList> jobList){
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    public class JobAdapterViewHolder extends RecyclerView.ViewHolder {

        JobItemCardBinding mJobItemCardBinding;

        public JobAdapterViewHolder(JobItemCardBinding jobItemCardBinding) {
            super(jobItemCardBinding.getRoot());
            this.mJobItemCardBinding = jobItemCardBinding;
        }

        void bindJob(BeanJobList jobList){

            if(mJobItemCardBinding.getJobDetailViewModel() == null){
                mJobItemCardBinding.setJobDetailViewModel(new ItemJobCardViewModel(jobList, itemView.getContext()));
            } else {
                mJobItemCardBinding.getJobDetailViewModel().setJobList(jobList);
            }

        }
    }*/
}
