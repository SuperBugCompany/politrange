package ru.politrange.interfaces;

import ru.politrange.objects.Site;

/**
 * Created by msv on 24.11.2015.
 */
public interface SitesCatalog {
    // добавить запись
    void add(Site site);
    // внести измененные значения (подтвердить измененные данные)
    void update(Site site);
    // удалить запись
    void delete(Site site);
}
