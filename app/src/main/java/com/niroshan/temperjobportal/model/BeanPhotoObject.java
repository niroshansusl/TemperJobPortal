package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanPhotoObject implements Parcelable {

    @SerializedName("filetype")
    private String filetype;

    @SerializedName("formats")
    private ArrayList<BeanFormats> formats;

    @SerializedName("created_at")
    private String created_at;

    protected BeanPhotoObject(Parcel in) {
        filetype = in.readString();
        created_at = in.readString();
    }

    public static final Creator<BeanPhotoObject> CREATOR = new Creator<BeanPhotoObject>() {
        @Override
        public BeanPhotoObject createFromParcel(Parcel in) {
            return new BeanPhotoObject(in);
        }

        @Override
        public BeanPhotoObject[] newArray(int size) {
            return new BeanPhotoObject[size];
        }
    };

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public ArrayList<BeanFormats> getFormats() {
        return formats;
    }

    public void setFormats(ArrayList<BeanFormats> formats) {
        this.formats = formats;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(filetype);
        parcel.writeString(created_at);
    }
}
