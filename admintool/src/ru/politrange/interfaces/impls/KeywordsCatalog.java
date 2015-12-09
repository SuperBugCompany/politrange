package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Keyword;
import ru.politrange.objects.Person;

import java.util.Iterator;

/**
 * Created by developermsv on 25.11.2015.
 */
public class KeywordsCatalog implements ICatalog <Keyword> {

    private Person person;
    private ObservableList<Keyword> catalogList = FXCollections.observableArrayList();
    public KeywordsCatalog(Person person) {
        this.person = person;
    }

    @Override
    public void add(Keyword keyword) {
        catalogList.add(keyword);
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Keyword person) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Keyword keyword) {
        catalogList.remove(keyword);
    }

    public ObservableList<Keyword> getCatalogList() {
        // исключаем ключи не принадлежащие личности
        Iterator<Keyword> keywordIterator = catalogList.iterator();
        while (keywordIterator.hasNext()) {
            Keyword keyword = keywordIterator.next();
            if (keyword.getPersonId() != person.getId()) {
                keywordIterator.remove();
            }
        }
        return catalogList;
    }

    public void populateData() {
        catalogList.clear();
        catalogList.add(new Keyword(1, "Путина", 1));
        catalogList.add(new Keyword(2, "Путину", 1));
        catalogList.add(new Keyword(3, "Медведева", 2));
        catalogList.add(new Keyword(4, "Медведеву", 2));
        catalogList.add(new Keyword(5, "Навальный", 3));
        catalogList.add(new Keyword(6, "Навальному", 3));
    }

}
