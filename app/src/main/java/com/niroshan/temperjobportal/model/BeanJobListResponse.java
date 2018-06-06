package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanJobListResponse {

    @SerializedName("data") private Map<String, ArrayList<BeanJobList>> jobList;

    public Map<String, ArrayList<BeanJobList>> getJobList() {
        return jobList;
    }

    public void setJobList(Map<String, ArrayList<BeanJobList>> jobList) {
        this.jobList = jobList;
    }
}
