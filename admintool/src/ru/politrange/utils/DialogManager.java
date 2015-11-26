package ru.politrange.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Created by developermsv on 25.11.2015.
 */
public class DialogManager {

    public static final String TEXT_BUTTON_DELETE = "Удалить";
    public static final String TEXT_BUTTON_CANCEL = "Отмена";

    public static void showInfoDialog(String title, String text){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.setHeaderText("");
            alert.showAndWait();
        }

        public static void showErrorDialog(String title, String text){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.setHeaderText("");
            alert.showAndWait();
        }
        public static boolean showConfirmDialog(String title, String text){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(title);
            alert.setContentText(text);
            alert.setHeaderText("");
            ButtonType yes = new ButtonType(TEXT_BUTTON_DELETE);
            ButtonType no = new ButtonType(TEXT_BUTTON_CANCEL);
            alert.getButtonTypes().setAll(yes, no);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yes) {
                return true;
            }
            return false;
        }
}
