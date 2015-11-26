package ru.politrange.interfaces;

import ru.politrange.objects.Keyword;

/**
 * Created by msv on 24.11.2015.
 */
public interface KeywordsCatalog {
    // добавить запись
    void add(Keyword keyword);
    // внести измененные значения (подтвердить измененные данные)
    void update(Keyword keyword);
    // удалить запись
    void delete(Keyword keyword);
}
