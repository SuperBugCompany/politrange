package ru.politrange.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.politrange.start.Main;

import java.io.InputStream;

public class MainController {
    public static final String TEXT_TITLE_EDIT = "Редактировать запись..."; // #good_code_3 пишем без ошибок
    public static final String TEXT_TITLE_ADD = "Новая запись...";
    public static final String TEXT_ERROR = "Ошибка";
    public static final String TEXT_SELECT_RECORD = "Выберите запись...";
    public static final String TEXT_WARNING = "Предупреждение";
    public static final String TEXT_FILL_FIELDS = "Заполните все поля...";
    public static final String TEXT_CONFIRM = "Вы действительно хотите удалить запись \"";
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
            PersonsController personsController = (PersonsController) replaceSceneContent("../fxml/persons.fxml");
            personsController.setMainStage(mainStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// при нажатиии на линк Ключи, загружаем интерфейс справочника "Ключи"
    public void onLinkKeywords(ActionEvent actionEvent) {
        try {
            KeywordsController keywordsController = (KeywordsController) replaceSceneContent("../fxml/keywords.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
// при нажатии на линк Сайты, загружаем интерфейс справочника "Сайты"
    public void onLinkSites(ActionEvent actionEvent) {
        try {
            SitesController sitesController = (SitesController) replaceSceneContent("../fxml/sites.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
