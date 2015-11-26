package ru.politrange.interfaces;

import ru.politrange.objects.Person;

/**
 * Created by msv on 24.11.2015.
 */
public interface PersonsCatalog {
    // добавить запись
    void add(Person person);
    // внести измененные значения (подтвердить измененные данные)
    void update(Person person);
    // удалить запись
    void delete(Person person);

}
