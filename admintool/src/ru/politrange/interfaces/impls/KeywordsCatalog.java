package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Keyword;
import ru.politrange.objects.Person;
import ru.politrange.utils.DialogManager;
import ru.politrange.utils.WebApiAdapter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by developermsv on 25.11.2015.
 */
public class KeywordsCatalog implements ICatalog <Keyword> {
    private final String ADDITIONAL_KEYWORD_PREFIX = "/keywords/";
    private String COMMAND_PREFIX = "/api/persons/";
    private WebApiAdapter apiAdapter;
    private Person person;

    private ObservableList<Keyword> catalogList = FXCollections.observableArrayList();
    public KeywordsCatalog(Person person) {
        this.person = person;
        COMMAND_PREFIX += String.valueOf(person.getId()) + ADDITIONAL_KEYWORD_PREFIX;
        apiAdapter = new WebApiAdapter(COMMAND_PREFIX);
    }


    public void add(Keyword keyword) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keywordId", keyword.getId());
        jsonObject.put("name", keyword.getName());
        try {
            String result = apiAdapter.insert(jsonObject);
            if (result != null) {
                jsonObject = (JSONObject) new JSONParser().parse(result);
                keyword.setId((int) (long) jsonObject.get("keywordId"));
                keyword.setName((String) jsonObject.get("name"));
                catalogList.add(keyword);
            } else {
                DialogManager.showErrorDialog("Ошибка","Неизвестная ошибка...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public boolean update(Keyword oldValue, Keyword newValue) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keywordId", newValue.getId());
        jsonObject.put("name", newValue.getName());
        try {
            if (apiAdapter.update(jsonObject, String.valueOf(newValue.getId()))) {
                oldValue.setId(newValue.getId());
                oldValue.setName(newValue.getName());
                return true;
            } else {
                DialogManager.showErrorDialog("Ошибка","Неизвестная ошибка обновления...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void delete(Keyword keyword) {
        try {
            if (apiAdapter.delete(String.valueOf(keyword.getId()))) {
                catalogList.remove(keyword);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Keyword> getCatalogList() {
        return catalogList;
    }

    public void populateData() {
        JSONArray jsonObject = null;
        try {
            jsonObject = (JSONArray) new JSONParser().parse(apiAdapter.select(null));
            catalogList.clear();
            Iterator<JSONObject> iterator = jsonObject.iterator();
            while (iterator.hasNext()) {
                JSONObject o = iterator.next();
                catalogList.add(new Keyword((int) (long) o.get("keywordId"), (String) o.get("name"),person));
            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
