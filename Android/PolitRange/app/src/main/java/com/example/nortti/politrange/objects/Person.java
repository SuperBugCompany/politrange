package com.example.nortti.politrange.objects;

public class Person {
    private int id;
    private String name;
    private int rank;
    private int siteId;
    private Site site;

    public Person(int id, String name, int rank, int siteId) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.siteId = siteId;
    }
    public Person(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }
}
