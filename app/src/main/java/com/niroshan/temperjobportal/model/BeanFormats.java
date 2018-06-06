package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanFormats implements Serializable {

    @SerializedName("cdn_url")
    private String cdn_url;

    @SerializedName("format")
    private String format;

    @SerializedName("created_at")
    private String created_at;

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
