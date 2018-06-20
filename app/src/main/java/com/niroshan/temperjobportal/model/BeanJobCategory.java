package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanJobCategory implements Parcelable{

    @SerializedName("description")
    private String description;

    @SerializedName("icon_path")
    private String icon_path;

    @SerializedName("slug")
    private String slug;

    protected BeanJobCategory(Parcel in) {
        description = in.readString();
        icon_path = in.readString();
        slug = in.readString();
    }

    public static final Creator<BeanJobCategory> CREATOR = new Creator<BeanJobCategory>() {
        @Override
        public BeanJobCategory createFromParcel(Parcel in) {
            return new BeanJobCategory(in);
        }

        @Override
        public BeanJobCategory[] newArray(int size) {
            return new BeanJobCategory[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(icon_path);
        parcel.writeString(slug);
    }
}
