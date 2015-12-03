package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Person;

/**
 * Created by msv on 24.11.2015.
 */
public class PersonsCatalog implements ICatalog<Person> {
    // т.к. статическое нельзя вынести в интерфейс
    private ObservableList <Person> catalogList = FXCollections.observableArrayList();
    @Override
    public void add(Person person) {
        catalogList.add(person);
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Person person) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Person person) {
        catalogList.remove(person);
    }

    public ObservableList<Person> getCatalogList() {
        return catalogList;
    }

    public void fillTestData(){
        catalogList.clear();
        catalogList.add(new Person(1,"Путин"));
        catalogList.add(new Person(2,"Медведев"));
        catalogList.add(new Person(3,"Навальный"));
    }
}
