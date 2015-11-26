package ru.politrange.objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by developermsv on 24.11.2015.
 */
public class Person {

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

    public Person() {
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
}
