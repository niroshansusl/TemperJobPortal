package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanDate implements Parcelable {

    @SerializedName("date")
    private String date;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("timezone_type")
    private Integer timezone_type;

    protected BeanDate(Parcel in) {
        date = in.readString();
        timezone = in.readString();
        if (in.readByte() == 0) {
            timezone_type = null;
        } else {
            timezone_type = in.readInt();
        }
    }

    public static final Creator<BeanDate> CREATOR = new Creator<BeanDate>() {
        @Override
        public BeanDate createFromParcel(Parcel in) {
            return new BeanDate(in);
        }

        @Override
        public BeanDate[] newArray(int size) {
            return new BeanDate[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezone_type() {
        return timezone_type;
    }

    public void setTimezone_type(int timezone_type) {
        this.timezone_type = timezone_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(timezone);
        if (timezone_type == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(timezone_type);
        }
    }
}
