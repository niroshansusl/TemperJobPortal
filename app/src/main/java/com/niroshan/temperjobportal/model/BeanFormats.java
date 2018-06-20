package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanFormats implements Parcelable {

    @SerializedName("cdn_url")
    private String cdn_url;

    @SerializedName("format")
    private String format;

    @SerializedName("created_at")
    private String created_at;

    protected BeanFormats(Parcel in) {
        cdn_url = in.readString();
        format = in.readString();
        created_at = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cdn_url);
        dest.writeString(format);
        dest.writeString(created_at);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeanFormats> CREATOR = new Creator<BeanFormats>() {
        @Override
        public BeanFormats createFromParcel(Parcel in) {
            return new BeanFormats(in);
        }

        @Override
        public BeanFormats[] newArray(int size) {
            return new BeanFormats[size];
        }
    };

    public String getCdn_url() {
        return cdn_url;
    }

    public void setCdn_url(String cdn_url) {
        this.cdn_url = cdn_url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
