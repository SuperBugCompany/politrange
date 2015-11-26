package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.politrange.interfaces.SitesCatalog;
import ru.politrange.objects.Site;

/**
 * Created by developermsv on 26.11.2015.
 */
public class CollectionSitesCatalog implements SitesCatalog {
    private ObservableList<Site> siteList = FXCollections.observableArrayList();
    @Override
    public void add(Site site) {
        siteList.add(site);
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Site site) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Site site) {
        siteList.remove(site);
    }

    public ObservableList<Site> getSiteList() {
        return siteList;
    }
    public void fillTestData(){
        siteList.add(new Site(1, "RBC"));
        siteList.add(new Site(2, "LENTA.RU"));
        siteList.add(new Site(3, "MAIL.RU политика"));
    }

}
