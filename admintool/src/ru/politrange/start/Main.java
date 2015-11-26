package ru.politrange.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import ru.politrange.controllers.MainController;

public class Main extends Application {

    public static final int INDEX_FIRST_CSS = 0;
    public static final int MIN_HEIGHT_WINDOW = 400;
    public static final int MIN_WIDTH_WINDOW = 708;

    @Override
    // загружаем интерфейс главной формы и запускаем приложение
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../fxml/main.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainParams(primaryStage,this);
        primaryStage.setTitle("PolitRange: Admin tools...");
        primaryStage.setMinHeight(MIN_HEIGHT_WINDOW);
        primaryStage.setMinWidth(MIN_WIDTH_WINDOW);
        primaryStage.setResizable(false);
        Scene scene = new Scene(fxmlMain, MIN_WIDTH_WINDOW, MIN_HEIGHT_WINDOW);
        scene.getStylesheets().add(INDEX_FIRST_CSS, "ru/politrange/styles/main.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
