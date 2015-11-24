package com.example.nortti.politrange.Daily;

/**
 * Created by User on 24.11.2015.
 */
public class Day {
    private String dayDate;
    private String dayNum;

    public Day(String dayDate, String dayNum) {
        this.dayDate = dayDate;
        this.dayNum = dayNum;
    }

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getDayNum() {
        return dayNum;
    }

    public void setDayNum(String dayNum) {
        this.dayNum = dayNum;
    }
}
