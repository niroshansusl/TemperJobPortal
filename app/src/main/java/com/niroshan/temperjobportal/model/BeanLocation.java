package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/12/2018.
 */

public class BeanLocation implements Parcelable {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    protected BeanLocation(Parcel in) {
        lat = in.readDouble();
        lng = in.readDouble();
    }

    public static final Creator<BeanLocation> CREATOR = new Creator<BeanLocation>() {
        @Override
        public BeanLocation createFromParcel(Parcel in) {
            return new BeanLocation(in);
        }

        @Override
        public BeanLocation[] newArray(int size) {
            return new BeanLocation[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(lat);
        parcel.writeDouble(lng);
    }
}
