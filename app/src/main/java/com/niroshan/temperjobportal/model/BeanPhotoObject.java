package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanPhotoObject implements Serializable {

    @SerializedName("filetype")
    private String filetype;

    @SerializedName("formats")
    private ArrayList<BeanFormats> formats;

    @SerializedName("created_at")
    private String created_at;

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
}
