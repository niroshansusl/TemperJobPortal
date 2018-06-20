package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanRating implements Parcelable{

    @SerializedName("average")
    private float average;

    @SerializedName("count")
    private Integer count;

    protected BeanRating(Parcel in) {
        average = in.readFloat();
        if (in.readByte() == 0) {
            count = null;
        } else {
            count = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(average);
        if (count == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(count);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeanRating> CREATOR = new Creator<BeanRating>() {
        @Override
        public BeanRating createFromParcel(Parcel in) {
            return new BeanRating(in);
        }

        @Override
        public BeanRating[] newArray(int size) {
            return new BeanRating[size];
        }
    };

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
