package com.example.nortti.politrange.objects;

public class Site {
    private int id;
    private String name;
    private int pic_id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Site(int id, String name, int pic_id) {
        this.id = id;
        this.name = name;
        this.pic_id = pic_id;
    }

public Site(){};

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


}
