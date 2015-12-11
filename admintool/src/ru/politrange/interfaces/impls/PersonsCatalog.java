package ru.politrange.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Person;
import ru.politrange.utils.DialogManager;
import ru.politrange.utils.WebApiAdapter;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by msv on 24.11.2015.
 */
public class PersonsCatalog implements ICatalog<Person> {
    final private String COMMAND_PREFIX = "/api/persons/";
    final private WebApiAdapter apiAdapter = new WebApiAdapter(COMMAND_PREFIX);

    // т.к. статическое нельзя вынести в интерфейс
    private ObservableList<Person> catalogList = FXCollections.observableArrayList();

    @Override
    public void add(Person person) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("personId", person.getId());
        jsonObject.put("name", person.getName());
        try {
            String result = apiAdapter.insert(jsonObject);
            if (result != null) {
                jsonObject = (JSONObject) new JSONParser().parse(result);
                person.setId((int) (long) jsonObject.get("personId"));
                person.setName((String) jsonObject.get("name"));
                catalogList.add(person);
            } else {
                DialogManager.showErrorDialog("Ошибка","Неизвестная ошибка...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Person person) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Person person) {
        try {
            if (apiAdapter.delete(String.valueOf(person.getId()))) {
                catalogList.remove(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Person> getCatalogList() {
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
            catalogList.add(new Person((int) (long) o.get("personId"), (String) o.get("name")));
        }
    }
}
