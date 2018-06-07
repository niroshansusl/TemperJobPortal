package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanJobList implements Serializable {

    private String key;
    private long keyvlaue;

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

    @SerializedName("job_category")
    private BeanJobCategory job_category;

    @SerializedName("open_positions")
    private Integer open_positions;

    @SerializedName("new_matches_count")
    private Integer new_matches_count;

    @SerializedName("shifts")
    private ArrayList<BeanShifts> shifts;

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
}
