package com.project.nortti.politrange.objects;

import java.util.ArrayList;


public class DatePerson {
    private String name;
    private ArrayList<DayPerson> dayPersons = new ArrayList<DayPerson>();

    public DatePerson(String name, ArrayList<DayPerson> dayPersons) {
        this.name = name;
        this.dayPersons = dayPersons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<DayPerson> getDayPersons() {
        return dayPersons;
    }

    public void setDayPersons(ArrayList<DayPerson> dayPersons) {
        this.dayPersons = dayPersons;
    }
}
