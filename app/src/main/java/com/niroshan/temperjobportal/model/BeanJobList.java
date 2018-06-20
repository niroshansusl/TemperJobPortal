package com.niroshan.temperjobportal.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanJobList implements Parcelable {

    private String key;
    private long keyvlaue;
    private double currentLat;
    private double currentLong;

    @SerializedName("title")
    private String title;

    @SerializedName("id")
    private Integer id;

    @SerializedName("date")
    private BeanDate date;

    @SerializedName("distance")
    private Integer distance;

    @SerializedName("url")
    private String url;

    @SerializedName("max_possible_earnings_hour")
    private Double max_possible_earnings_hour;

    @SerializedName("max_possible_earnings_total")
    private Double max_possible_earnings_total;

    @SerializedName("client")
    private BeanClient client;

    @SerializedName("location")
    private BeanLocation location;

    @SerializedName("job_category")
    private BeanJobCategory job_category;

    @SerializedName("open_positions")
    private Integer open_positions;

    @SerializedName("new_matches_count")
    private Integer new_matches_count;

    @SerializedName("shifts")
    private ArrayList<BeanShifts> shifts;

    protected BeanJobList(Parcel in) {
        key = in.readString();
        keyvlaue = in.readLong();
        currentLat = in.readDouble();
        currentLong = in.readDouble();
        title = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        if (in.readByte() == 0) {
            distance = null;
        } else {
            distance = in.readInt();
        }
        url = in.readString();
        if (in.readByte() == 0) {
            max_possible_earnings_hour = null;
        } else {
            max_possible_earnings_hour = in.readDouble();
        }
        if (in.readByte() == 0) {
            max_possible_earnings_total = null;
        } else {
            max_possible_earnings_total = in.readDouble();
        }
        job_category = in.readParcelable(BeanJobCategory.class.getClassLoader());
        if (in.readByte() == 0) {
            open_positions = null;
        } else {
            open_positions = in.readInt();
        }
        if (in.readByte() == 0) {
            new_matches_count = null;
        } else {
            new_matches_count = in.readInt();
        }
    }

    public static final Creator<BeanJobList> CREATOR = new Creator<BeanJobList>() {
        @Override
        public BeanJobList createFromParcel(Parcel in) {
            return new BeanJobList(in);
        }

        @Override
        public BeanJobList[] newArray(int size) {
            return new BeanJobList[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BeanDate getDate() {
        return date;
    }

    public void setDate(BeanDate date) {
        this.date = date;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getMax_possible_earnings_hour() {
        return max_possible_earnings_hour;
    }

    public void setMax_possible_earnings_hour(Double max_possible_earnings_hour) {
        this.max_possible_earnings_hour = max_possible_earnings_hour;
    }

    public Double getMax_possible_earnings_total() {
        return max_possible_earnings_total;
    }

    public void setMax_possible_earnings_total(Double max_possible_earnings_total) {
        this.max_possible_earnings_total = max_possible_earnings_total;
    }

    public BeanClient getClient() {
        return client;
    }

    public void setClient(BeanClient client) {
        this.client = client;
    }

    public BeanJobCategory getJob_category() {
        return job_category;
    }

    public void setJob_category(BeanJobCategory job_category) {
        this.job_category = job_category;
    }

    public Integer getOpen_positions() {
        return open_positions;
    }

    public void setOpen_positions(Integer open_positions) {
        this.open_positions = open_positions;
    }

    public Integer getNew_matches_count() {
        return new_matches_count;
    }

    public void setNew_matches_count(Integer new_matches_count) {
        this.new_matches_count = new_matches_count;
    }

    public ArrayList<BeanShifts> getShifts() {
        return shifts;
    }

    public void setShifts(ArrayList<BeanShifts> shifts) {
        this.shifts = shifts;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getKeyvlaue() {
        return keyvlaue;
    }

    public void setKeyvlaue(long keyvlaue) {
        this.keyvlaue = keyvlaue;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(double currentLat) {
        this.currentLat = currentLat;
    }

    public double getCurrentLong() {
        return currentLong;
    }

    public void setCurrentLong(double currentLong) {
        this.currentLong = currentLong;
    }

    public BeanLocation getLocation() {
        return location;
    }

    public void setLocation(BeanLocation location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(key);
        parcel.writeLong(keyvlaue);
        parcel.writeDouble(currentLat);
        parcel.writeDouble(currentLong);
        parcel.writeString(title);
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        if (distance == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(distance);
        }
        parcel.writeString(url);
        if (max_possible_earnings_hour == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(max_possible_earnings_hour);
        }
        if (max_possible_earnings_total == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(max_possible_earnings_total);
        }
        parcel.writeParcelable(job_category, i);
        if (open_positions == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(open_positions);
        }
        if (new_matches_count == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(new_matches_count);
        }
    }
}
