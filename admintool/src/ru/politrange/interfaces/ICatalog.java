package ru.politrange.interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.politrange.objects.Person;

/**
 * Created by msv on 24.11.2015.
 */
public interface ICatalog<T> {

    // добавить запись
    void add(T value);
    // внести измененные значения (подтвердить измененные данные)
    void update(T value);
    // удалить запись
    void delete(T value);
    ObservableList <T> getCatalogList();
    void fillTestData();
}