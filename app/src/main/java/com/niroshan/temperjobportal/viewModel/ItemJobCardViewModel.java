package com.niroshan.temperjobportal.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.config.AppConstants;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.view.activity.JobDetailActivity;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class ItemJobCardViewModel extends BaseObservable {

    private BeanJobList jobList;
    private Context context;
    private int rating;

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
        String clientName = "€" + Double.toString(jobList.getMax_possible_earnings_hour())+ "/u " +jobList.getClient().getName();
        return clientName;
    }

    public int getRating() {
        int rating = 0;

        if(jobList.getClient().getRating() != null){
            rating = (int) jobList.getClient().getRating().getAverage();
        }
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewCount(){
        String review = "";
        if(jobList.getClient().getRating() != null && jobList.getClient().getRating().getCount() != 0){
            review = "" + jobList.getClient().getRating().getCount() + " reviews";
        }
        return review;
    }

    public String getCell(){
        String cell = cell = Integer.toString(jobList.getOpen_positions()) + " open positie - " + jobList.getShifts().get(0).getStart_time()+ " ("+jobList.getShifts().get(0).getDuration() +"u)";
        return cell;
    }

    public void onItemClick(View v){

    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).placeholder(R.drawable.placeholder).into(imageView);
    }

    public void setJobList(BeanJobList jobList) {
        this.jobList = jobList;
        notifyChange();
    }
}
