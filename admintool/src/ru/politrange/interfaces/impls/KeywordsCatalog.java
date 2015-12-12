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
    private final String COMMAND_PREFIX = "/api/persons/";
    final private WebApiAdapter apiAdapter = new WebApiAdapter(COMMAND_PREFIX);

    private Person person;
    private ObservableList<Keyword> catalogList = FXCollections.observableArrayList();
    public KeywordsCatalog(Person person) {
        this.person = person;
    }

    @Override
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

        catalogList.add(keyword);
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Keyword person) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
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
        JSONArray jsonObject = null;
        try {
            jsonObject = (JSONArray) new JSONParser().parse(apiAdapter.select(null));
        } catch (IOException e) {
            e.printStackTrace();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        catalogList.clear();
        Iterator<JSONObject> iterator = jsonObject.iterator();
        while (iterator.hasNext()) {
            JSONObject o = iterator.next();
            catalogList.add(new Keyword((int) (long) o.get("keywordId"), (String) o.get("name"),person));
        }
    }

}
