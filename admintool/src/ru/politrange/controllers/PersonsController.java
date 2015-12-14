package ru.politrange.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.politrange.interfaces.ICatalog;
import ru.politrange.objects.Person;
import ru.politrange.utils.DialogManager;

import java.io.IOException;

/**
 * Created by msv on 23.11.2015.
 * #good_code_1 код четко соблюдает конвенцию
 */
public class PersonsController {

    private ICatalog personsCatalogImpl;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private Parent fxmlEdit;
    private EditPersonController editPersonController;
    private Stage mainStage;
    private Stage editPersonStage;

    @FXML
    public TableColumn <Person, String> columnName;
    public TableView mainTable;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    private void initialize() {
        columnName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        initListeners();
        initLoader();
    }

    // #solid_o
    // personsCatalogImpl это DataSource
    // нициализировать возможно только через setter
    public void setDataSource (ICatalog personsCatalogImpl) {
        this.personsCatalogImpl = personsCatalogImpl;
        populateData();
    }


    // #good_code_4 методы не пергружены логикой
    // инициализация слушателей по таблице интерфейса
    private void initListeners() {
        mainTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == MainController.DOUBLE_CLICK) {
                    editPerson();
                }
            }
        });
    }

    // заполнение таблицы интерфейса
    private void populateData() {
        personsCatalogImpl.populateData();
        mainTable.setItems(personsCatalogImpl.getCatalogList());
    }

    // предзагрузка интерфейса редактирования, чтобы не загружать при каждом нажатии на кнопки
    private void initLoader() {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/edit_person.fxml"));
            fxmlEdit = fxmlLoader.load();
            editPersonController = fxmlLoader.getController();

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
        Person selectedPerson = (Person) mainTable.getSelectionModel().getSelectedItem();
        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                editPersonController.setPerson(new Person());
                if (showDialog(MainController.TEXT_TITLE_ADD) == ModalResult.MD_SAVE) {
                    personsCatalogImpl.add(editPersonController.getPerson());
                }
                break;

            case "btnEdit":
                if (personIsSelected(selectedPerson)) {
                    editPerson();
                }
                break;

            case "btnDelete":
                if (personIsSelected(selectedPerson)) {
                    if (DialogManager.showConfirmDialog(MainController.TEXT_WARNING, MainController.TEXT_CONFIRM +
                            selectedPerson.getName() + "\"?")) {
                        personsCatalogImpl.delete(selectedPerson);
                    }
                }
                break;
        }
    }
    private boolean personIsSelected(Person selectedPerson) {
        if(selectedPerson == null){
            DialogManager.showInfoDialog(MainController.TEXT_ERROR, MainController.TEXT_SELECT_RECORD);
            return false;
        }
        return true;
    }

    //#good_code_8 не повторять код
    // редактировать личность
    private void editPerson() {
        Person oldPerson = (Person) mainTable.getSelectionModel().getSelectedItem();
        Person newPerson = new Person(oldPerson.getId(), oldPerson.getName());
        editPersonController.setPerson(newPerson);
        if (showDialog(MainController.TEXT_TITLE_EDIT) == ModalResult.MD_SAVE) {
            personsCatalogImpl.update(oldPerson,newPerson);
        }

    }

    // отображение диалога редактирования
    private ModalResult showDialog(String title) {

        if (editPersonStage == null) {
            editPersonStage = new Stage();
            editPersonStage.setTitle(title);
            editPersonStage.setResizable(false);
            editPersonStage.setScene(new Scene(fxmlEdit));
            editPersonStage.initModality(Modality.WINDOW_MODAL);
            editPersonStage.initOwner(mainStage);
        }
        editPersonStage.showAndWait(); // для ожидания закрытия окна
        return editPersonController.getModalResult();
    }
}
