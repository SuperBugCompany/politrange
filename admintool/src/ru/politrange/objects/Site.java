package ru.politrange.objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by developermsv on 24.11.2015.
 */
public class Site {
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty("");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public Site(int id, String name) {
        this.id = id;
        this.name.set(name);
    }

    public Site() {
    }
}
