package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Site;

/**
 * Created by developermsv on 26.11.2015.
 */
public class SitesCatalog implements ICatalog<Site> {
    private ObservableList<Site> catalogList = FXCollections.observableArrayList();
    @Override
    public void add(Site site) {
        catalogList.add(site);
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Site site) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Site site) {
        catalogList.remove(site);
    }

    public ObservableList<Site> getCatalogList() {
        return catalogList;
    }
    public void fillTestData(){
        catalogList.add(new Site(1, "rbc.ru"));
        catalogList.add(new Site(2, "lenta.ru"));
        catalogList.add(new Site(3, "mail.ru"));
    }

}
