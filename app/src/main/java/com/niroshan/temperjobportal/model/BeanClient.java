package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanClient implements Serializable {

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("photos")
    private ArrayList<BeanPhotoObject> photos;

    @SerializedName("rating")
    private BeanRating rating;

    @SerializedName("avg_response_time_in_hours")
    private Integer avg_response_time_in_hours;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeanRating getRating() {
        return rating;
    }

    public void setRating(BeanRating rating) {
        this.rating = rating;
    }

    public int getAvg_response_time_in_hours() {
        return avg_response_time_in_hours;
    }

    public void setAvg_response_time_in_hours(int avg_response_time_in_hours) {
        this.avg_response_time_in_hours = avg_response_time_in_hours;
    }

    public ArrayList<BeanPhotoObject> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<BeanPhotoObject> photos) {
        this.photos = photos;
    }

    public void setAvg_response_time_in_hours(Integer avg_response_time_in_hours) {
        this.avg_response_time_in_hours = avg_response_time_in_hours;
    }
}
