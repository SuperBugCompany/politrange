package ru.politrange.objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by developermsv on 24.11.2015.
 */
public class Keyword {
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty("");

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    private int personId;
    private Person person;

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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;

    }
    public SimpleStringProperty nameProperty() {
        return name;
    }
    public Keyword() {
    }

    // продумать связку по personId
    public Keyword(int id, String name, Person person) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.person = person;
    }

    public Keyword(int id, String name, int personId) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.personId = personId;
    }
}
