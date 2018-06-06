package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanShifts implements Serializable {

    @SerializedName("currency")
    private String currency;

    @SerializedName("duration")
    private Integer duration;

    @SerializedName("earnings_per_hour")
    private Double earnings_per_hour;

    @SerializedName("end_datetime")
    private String end_datetime;

    @SerializedName("end_time")
    private String end_time;

    @SerializedName("is_auto_accepted_when_applied_for")
    private Integer is_auto_accepted_when_applied_for;

    @SerializedName("start_date")
    private String start_date;

    @SerializedName("start_datetime")
    private String start_datetime;

    @SerializedName("start_time")
    private String start_time;

    @SerializedName("tempers_needed")
    private Integer tempers_needed;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getEarnings_per_hour() {
        return earnings_per_hour;
    }

    public void setEarnings_per_hour(Double earnings_per_hour) {
        this.earnings_per_hour = earnings_per_hour;
    }

    public String getEnd_datetime() {
        return end_datetime;
    }

    public void setEnd_datetime(String end_datetime) {
        this.end_datetime = end_datetime;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getIs_auto_accepted_when_applied_for() {
        return is_auto_accepted_when_applied_for;
    }

    public void setIs_auto_accepted_when_applied_for(Integer is_auto_accepted_when_applied_for) {
        this.is_auto_accepted_when_applied_for = is_auto_accepted_when_applied_for;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getStart_datetime() {
        return start_datetime;
    }

    public void setStart_datetime(String start_datetime) {
        this.start_datetime = start_datetime;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public Integer getTempers_needed() {
        return tempers_needed;
    }

    public void setTempers_needed(Integer tempers_needed) {
        this.tempers_needed = tempers_needed;
    }
}
