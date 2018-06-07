package com.niroshan.temperjobportal.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Niroshan Rathnayake on 6/6/2018.
 */

public class BeanJobListResponse {

    @SerializedName("data")
    private Map<String, ArrayList<BeanJobList>> jobList;

    public Map<String, ArrayList<BeanJobList>> getJobList() {
        return jobList;
    }

    public void setJobList(Map<String, ArrayList<BeanJobList>> jobList) {
        this.jobList = jobList;
    }

    /* @SerializedName("2017-05-20")
    private BeanJobList[] listOne;

    @SerializedName("2017-05-21")
    private BeanJobList[] listTwo;

    @SerializedName("2018-05-24")
    private BeanJobList[] listThree;

    public BeanJobList[] getListOne() {
        return listOne;
    }

    public void setListOne(BeanJobList[] listOne) {
        this.listOne = listOne;
    }

    public BeanJobList[] getListTwo() {
        return listTwo;
    }

    public void setListTwo(BeanJobList[] listTwo) {
        this.listTwo = listTwo;
    }

    public BeanJobList[] getListThree() {
        return listThree;
    }

    public void setListThree(BeanJobList[] listThree) {
        this.listThree = listThree;
    }*/
}
