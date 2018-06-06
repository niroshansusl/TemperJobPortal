package com.niroshan.temperjobportal.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.niroshan.temperjobportal.config.AppConstants;
import com.niroshan.temperjobportal.model.BeanJobList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class ItemJobCardViewModel extends BaseObservable {

    private BeanJobList jobList;
    private Context context;

    public ItemJobCardViewModel(BeanJobList jobList, Context context) {
        this.jobList = jobList;
        this.context = context;
    }

    public String getImage(){
        String image = jobList.getClient().getPhotos().get(0).getFormats().get(0).getCdn_url().toString();
        return  image;
    }

    public String getDistance(){
        String distance = jobList.getDistance().toString() + AppConstants.DISTANCE_TYPE;
        return distance;
    }

    public String getClientName(){
        String clientName = jobList.getClient().getName();
        return clientName;
    }

    public String getTitle(){
        String title = jobList.getTitle();
        return title;
    }

    public String getRating(){
        String rating = "⭐ 0.0";

        if(jobList.getClient().getRating() != null){
            rating = "⭐ " + jobList.getClient().getRating().getAverage();
        }
        return rating;
    }

    public String getCell(){
        String cell = cell = Integer.toString(jobList.getShifts().size()) + " open positie €" + Double.toString(jobList.getMax_possible_earnings_hour())+ "/u";
        return cell;
    }

    public void onItemClick(View v){

    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

   /* public void onItemClick(View v){
        context.startActivity(JobDetailActivity.loadDetailView(v.getContext(), jobList));
    }*/

    public void setJobList(BeanJobList jobList) {
        this.jobList = jobList;
        notifyChange();
    }
}
