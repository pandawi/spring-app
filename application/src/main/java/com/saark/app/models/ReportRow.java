package com.saark.app.models;

public class ReportRow {

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public Integer getSiteID() {
        return siteID;
    }

    public void setSiteID(Integer siteID) {
        this.siteID = siteID;
    }

    private String data;
    private Integer siteID;

    public ReportRow(Integer siteID, String data) {
        this.data = data;
        this.siteID = siteID;
    }
}
