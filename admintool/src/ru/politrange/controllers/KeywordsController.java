package ru.politrange.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.interfaces.impls.KeywordsCatalog;
import ru.politrange.objects.Keyword;
import ru.politrange.objects.Person;
import ru.politrange.utils.DialogManager;

import java.io.IOException;

/**
 * Created by msv on 23.11.2015.
 */
public class KeywordsController {
    private ICatalog personsCatalogImpl;
    private ICatalog keywordsCatalogImpl;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent fxmlEdit;
    private EditKeywordController editKeywordController;
    private Stage mainStage;
    private Stage editKeywordStage;

    @FXML
    public TableColumn<Keyword, String> columnName;
    public ComboBox comBoxPersons;
    public TableView mainTable;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<Keyword, String>("name"));
        initListeners();
        initLoader();
    }

    // #solid_o
    // keywordsCatalogImpl, personsCatalogImpl это DataSource
    // нициализировать возможно только через setter
    public void setDataSource(ICatalog personsCatalogImpl) {
        this.personsCatalogImpl = personsCatalogImpl;
        fillDataComBoxPersons();
    }

    // #good_code_4 методы не пергружены логикой
    // инициализация слушателей по таблице интерфейса
    private void initListeners() {
        mainTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == MainController.DOUBLE_CLICK) {
                    editKeyword();
                }
            }
        });
    }

    // возращает каталог список имен личностей
    public ObservableList<String> getPersonNameList(ObservableList<Person> catalogList) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Person person : catalogList) {
            list.add(person.getName());
        }
        return list;
    }

    // заполнение таблицы комбобокса личностей
    private void fillDataComBoxPersons() {
        personsCatalogImpl.populateData();
        comBoxPersons.setItems(getPersonNameList(personsCatalogImpl.getCatalogList()));
    }

    // предзагрузка интерфейса редактирования, чтобы не загружать при каждом нажатии на кнопки
    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("/ru/politrange/fxml/edit_keyword.fxml"));
            fxmlEdit = fxmlLoader.load();
            editKeywordController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // #good_code_5 функция выполняет одну задачу
    // обработка событий нажатия на кнопки
    public void actionButtonPressed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }
        Keyword selectedKeyword = (Keyword) mainTable.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                editKeywordController.setKeyword(new Keyword());
                if (showDialog(MainController.TITLE_ADD_TEXT) == ModalResult.MD_SAVE) {
                    keywordsCatalogImpl.add(editKeywordController.getKeyword());
                }
                break;

            case "btnEdit":
                if (keywordIsSelected(selectedKeyword)) {
                    editKeyword();
                }
                break;

            case "btnDelete":
                if (keywordIsSelected(selectedKeyword)) {
                    if (DialogManager.showConfirmDialog(MainController.WARNING_TEXT, MainController.CONFIRM_TEXT +
                            selectedKeyword.getName() + "\"?")) {
                        keywordsCatalogImpl.delete(selectedKeyword);
                    }
                }
                break;
        }
    }

    private boolean keywordIsSelected(Keyword selectedKeyword) {
        if (selectedKeyword == null) {
            DialogManager.showInfoDialog(MainController.ERROR_TEXT, MainController.SELECT_RECORD_TEXT);
            return false;
        }
        return true;
    }

    //#good_code_8 не повторять код
    // редактировать запись
    private void editKeyword() {
        Keyword oldValue = (Keyword) mainTable.getSelectionModel().getSelectedItem();
        Keyword newValue = new Keyword(oldValue.getId(), oldValue.getName(), oldValue.getPerson());
        editKeywordController.setKeyword(newValue);
        if (showDialog(MainController.TITLE_EDIT_TEXT) == ModalResult.MD_SAVE) {
            keywordsCatalogImpl.update(oldValue, newValue);
        }
    }

    // отображение диалога редактирования
    private ModalResult showDialog(String title) {

        if (editKeywordStage == null) {
            editKeywordStage = new Stage();
            editKeywordStage.setTitle(title);
            editKeywordStage.setResizable(false);
            editKeywordStage.setScene(new Scene(fxmlEdit));
            editKeywordStage.initModality(Modality.WINDOW_MODAL);
            editKeywordStage.initOwner(mainStage);
        }
        editKeywordStage.showAndWait(); // для ожидания закрытия окна
        return editKeywordController.getModalResult();
    }

    // заполнение таблицы ключевых слов
    private void populateData(Person person) {
        keywordsCatalogImpl = new KeywordsCatalog(person);
        keywordsCatalogImpl.populateData();
        mainTable.setItems(keywordsCatalogImpl.getCatalogList());
    }

    public void actionSelectItem(ActionEvent actionEvent) {
        ComboBox source = (ComboBox) actionEvent.getSource();
        int itemIndex = source.getSelectionModel().getSelectedIndex();
        if (itemIndex != -1) {
            populateData((Person) personsCatalogImpl.getCatalogList().get(itemIndex));
        }
    }
}
