package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.politrange.interfaces.PersonsCatalog;
import ru.politrange.objects.Person;

import java.util.ArrayList;

/**
 * Created by msv on 24.11.2015.
 */
public class CollectionPersonsCatalog implements PersonsCatalog{

    private ObservableList<Person> personList = FXCollections.observableArrayList();
    @Override
    public void add(Person person) {
        personList.add(person);
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Person person) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }
    public ObservableList<String> getPersonNameList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Person person : personList) {
            list.add(person.getName());
        }
        return list;
    }
    public void fillTestData(){
        personList.add(new Person(1,"Путин"));
        personList.add(new Person(2,"Медведев"));
        personList.add(new Person(3,"Навальный"));
    }
}
