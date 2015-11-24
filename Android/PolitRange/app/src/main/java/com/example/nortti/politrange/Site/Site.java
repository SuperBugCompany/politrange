package com.example.nortti.politrange.Site;

public class Site {

    private String name;
    private int pic_id;
    private String site;

    public Site(String name, String site, int pic_id) {
        this.name = name;
        this.site = site;
        this.pic_id = pic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic_id() {
        return pic_id;
    }

    public void setPic_id(int pic_id) {
        this.pic_id = pic_id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
