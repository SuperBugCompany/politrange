package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Person;
import ru.politrange.objects.Site;
import ru.politrange.utils.DialogManager;
import ru.politrange.utils.WebApiAdapter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by developermsv on 26.11.2015.
 */
public class SitesCatalog implements ICatalog<Site> {
    final private String COMMAND_PREFIX = "/api/sites/";
    final private WebApiAdapter apiAdapter = new WebApiAdapter(COMMAND_PREFIX);

    private ObservableList<Site> catalogList = FXCollections.observableArrayList();
    @Override
    public void add(Site site) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("siteId", site.getId());
        jsonObject.put("name", site.getName());
        try {
            String result = apiAdapter.insert(jsonObject);
            if (result != null) {
                jsonObject = (JSONObject) new JSONParser().parse(result);
                site.setId((int) (long) jsonObject.get("siteId"));
                site.setName((String) jsonObject.get("name"));
                catalogList.add(site);
            } else {
                DialogManager.outOfService();
            }
        } catch (IOException e) {
            DialogManager.outOfService();
            e.printStackTrace();
        } catch (ParseException e) {
            DialogManager.outOfService();
            e.printStackTrace();
        }
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public boolean update(Site oldValue,Site newValue) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("siteId", newValue.getId());
        jsonObject.put("name", newValue.getName());
        try {
            if (apiAdapter.update(jsonObject, String.valueOf(newValue.getId()))) {
                oldValue.setId(newValue.getId());
                oldValue.setName(newValue.getName());
                return true;
            } else {
                DialogManager.outOfService();
            }
        } catch (IOException e) {
            DialogManager.outOfService();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(Site site) {
        try {
            if (apiAdapter.delete(String.valueOf(site.getId()))) {
                catalogList.remove(site);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Site> getCatalogList() {
        return catalogList;
    }
    public void populateData(){
        JSONArray jsonObject = null;
        try {
            jsonObject = (JSONArray) new JSONParser().parse(apiAdapter.select(null));
            catalogList.clear();
            Iterator<JSONObject> iterator = jsonObject.iterator();
            while (iterator.hasNext()) {
                JSONObject o = iterator.next();
                catalogList.add(new Site((int) (long) o.get("siteId"), (String) o.get("name")));
            }
        } catch (IOException e) {
            DialogManager.outOfService();
            e.printStackTrace();

        } catch (ParseException e) {
            DialogManager.outOfService();
            e.printStackTrace();
        }
    }

}
