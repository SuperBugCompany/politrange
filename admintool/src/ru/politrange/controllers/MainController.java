package ru.politrange.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.politrange.interfaces.impls.PersonsCatalog;
import ru.politrange.interfaces.impls.SitesCatalog;
import ru.politrange.start.Main;
import ru.politrange.utils.DialogManager;

import java.io.InputStream;

public class MainController {
    public static final String TITLE_EDIT_TEXT = "Редактировать запись..."; // #good_code_3 пишем без ошибок
    public static final String TITLE_ADD_TEXT = "Новая запись...";
    public static final String ERROR_TEXT = "Ошибка";
    public static final String SELECT_RECORD_TEXT = "Выберите запись...";
    public static final String WARNING_TEXT = "Предупреждение";
    public static final String FILL_FIELDS_TEXT = "Заполните все поля...";
    public static final String CONFIRM_TEXT = "Вы действительно хотите удалить запись \"";
    public static final int DOUBLE_CLICK = 2; // #good_code_9 нет магическим символам

    public AnchorPane rightPanel;
    VBox rightPanelContent;
    private Main application;
    private Stage mainStage;

   public void setMainParams(Stage mainStage,Main application) {
        this.mainStage = mainStage;
        this.application = application;
    }
    @FXML
    private void initialize() {
        onLinkPersons(null);
    }
// загрузчик интерфейса в правую панель главной формы
    private Object replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        if (rightPanelContent != null) {
            rightPanel.getChildren().removeAll(rightPanelContent);
        }
        try {
            rightPanelContent = (VBox) loader.load(in);
        } finally {
            in.close();
        }
        rightPanel.getChildren().addAll(rightPanelContent);
        return  loader.getController();
    }
// при нажатии на линк Личности, загружаем интерфейс справочника "Личности"
    public void onLinkPersons(ActionEvent actionEvent) {
        try {
            PersonsController personsController = (PersonsController) replaceSceneContent("/ru/politrange/fxml/persons.fxml");
            personsController.setMainStage(mainStage);
            personsController.setDataSource(new PersonsCatalog());
        } catch (Exception e) {
            DialogManager.outOfService();
            e.printStackTrace();
        }
    }
// при нажатиии на линк Ключи, загружаем интерфейс справочника "Ключи"
    public void onLinkKeywords(ActionEvent actionEvent) {
        try {
            KeywordsController keywordsController = (KeywordsController) replaceSceneContent("/ru/politrange/fxml/keywords.fxml");
            keywordsController.setMainStage(mainStage);
            keywordsController.setDataSource(new PersonsCatalog());
        } catch (Exception e) {
            DialogManager.outOfService();
            e.printStackTrace();
        }

    }
// при нажатии на линк Сайты, загружаем интерфейс справочника "Сайты"
    public void onLinkSites(ActionEvent actionEvent) {
        try {
            SitesController sitesController = (SitesController) replaceSceneContent("/ru/politrange/fxml/sites.fxml");
            sitesController.setMainStage(mainStage);
            sitesController.setDataSource(new SitesCatalog());
        } catch (Exception e) {
            DialogManager.outOfService();
            e.printStackTrace();
        }
    }
}
