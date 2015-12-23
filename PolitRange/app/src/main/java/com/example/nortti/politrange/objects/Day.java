package com.example.nortti.politrange.objects;

import com.example.nortti.politrange.objects.Site;

/**
 * Created by User on 24.11.2015.
 */
public class Day {
    private int id;
    private String url;
    private int siteId;
    private String dayDateTime;
    private String dayLastScan;
    private int dayNum;
    private Site site;


    public Day(int id, String url, int siteId, String dayDateTime, int dayNum) {
        this.id = id;
        this.url = url;
        this.siteId = siteId;
        this.dayDateTime = dayDateTime;
        this.dayNum = dayNum;
    }

    public Day(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public String getDayDateTime() {
        return dayDateTime;
    }

    public void setDayDateTime(String dayDateTime) {
        this.dayDateTime = dayDateTime;
    }

    public int getDayNum() {
        return dayNum;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
