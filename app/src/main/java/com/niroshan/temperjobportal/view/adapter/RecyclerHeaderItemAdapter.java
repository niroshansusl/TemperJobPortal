package com.niroshan.temperjobportal.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.databinding.JobItemCardBinding;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.utils.AppUtils;
import com.niroshan.temperjobportal.viewModel.ItemJobCardViewModel;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Niroshan Rathnayake on 6/7/2018.
 */

public class RecyclerHeaderItemAdapter extends JobCardAdapter implements StickyRecyclerHeadersAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private List<BeanJobList> jobList;

    public RecyclerHeaderItemAdapter(){
        this.jobList = Collections.emptyList();
    }

    public RecyclerHeaderItemAdapter(ArrayList<BeanJobList> mList, Context context) {
        this.jobList = mList;
        this.context = context;
    }

    @Override
    public long getHeaderId(int position) {
       /* if (position == 0) {
            return 0;
        } else {

        }*/
        return getItem(position).getKeyvlaue();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_header, parent, false);
        return new ItemHeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHeaderViewHolder) {
            if (getItem(position)!= null) {
                String header = String.valueOf(getItem(position).getKey());
                ((ItemHeaderViewHolder) holder).header.setText(AppUtils.convertDateWithTime(header));
            }
        }
    }

    public BeanJobList getItem(int position) {
        return jobList.get(position);
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        JobItemCardBinding itemCardBinding = DataBindingUtil.inflate(layoutInflater, R.layout.job_item_card, parent, false);

        return new JobAdapterViewHolder(itemCardBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       // holder.bindJob(jobList.get(position));
       // BeanJobList itemModel = mList.get(position);
        ((JobAdapterViewHolder)holder).bindJob(jobList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public void addItems(List<BeanJobList> jobList){
        this.jobList.addAll(jobList);
        notifyDataSetChanged();
    }

    public void setJobList(List<BeanJobList> jobList){
        this.jobList = jobList;
        notifyDataSetChanged();
    }

    public static class ItemHeaderViewHolder extends RecyclerView.ViewHolder {

        TextView header;

        public ItemHeaderViewHolder(View itemView) {
            super(itemView);

            header = itemView.findViewById(R.id.headerTxt);
            //ButterKnife.bind(this, itemView);
        }
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
    }

}
