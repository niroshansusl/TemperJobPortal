package com.niroshan.temperjobportal.viewModel;

import android.app.Activity;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.bumptech.glide.Glide;
import com.niroshan.temperjobportal.R;
import com.niroshan.temperjobportal.config.AppConstants;
import com.niroshan.temperjobportal.model.BeanJobList;
import com.niroshan.temperjobportal.utils.AppUtils;
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

    public String getIconImage(){
        String iconImage = AppConstants.IMAGE_BASE_URL + jobList.getJob_category().getIcon_path();
        return  iconImage;
    }

    public String getDistance(){

        Double currentLat = jobList.getCurrentLat();
        Double currentLong = jobList.getCurrentLong();
        Double placeLat = jobList.getLocation().getLat();
        Double placeLong = jobList.getLocation().getLng();

        //String distance = jobList.getDistance().toString() + AppConstants.DISTANCE_TYPE;
        String distance = "" + String.format("%.2f", AppUtils.getDistance(currentLat, currentLong, placeLat, placeLong)) + AppConstants.DISTANCE_TYPE;
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
        String cell = Integer.toString(jobList.getOpen_positions()) + " open positie - " + jobList.getShifts().get(0).getStart_time()+ " ("+jobList.getShifts().get(0).getDuration() +"u)";
        return cell;
    }

    public void onItemClick(View v){
        context.startActivity(JobDetailActivity.loadDetailView(v.getContext(), jobList));
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).placeholder(R.drawable.placeholder).into(imageView);
    }

    @BindingAdapter("iconUrl")
    public static void setIconImage(ImageView imageView, String url){
        SvgLoader.pluck()
                .with((Activity) imageView.getContext())
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(url, imageView);
    }


    public void setJobList(BeanJobList jobList) {
        this.jobList = jobList;
        notifyChange();
    }
}
