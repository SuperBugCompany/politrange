package ru.politrange.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

/**
 * Created by developermsv on 25.11.2015.
 */
public class DialogManager {

    public static final String BUTTON_DELETE_TEXT = "Удалить";
    public static final String BUTTON_CANCEL_TEXT = "Отмена";
    public static final String TITLE_ERROR_TEXT = "Ошибка";
    public static final String OUT_OF_SERVICE_TEXT = "Сервис не найден, или временно не доступен!";

    public static void showInfoDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public static void showErrorDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }

    public static boolean showConfirmDialog(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        ButtonType yes = new ButtonType(BUTTON_DELETE_TEXT);
        ButtonType no = new ButtonType(BUTTON_CANCEL_TEXT);
        alert.getButtonTypes().setAll(yes, no);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yes) {
            return true;
        }
        return false;
    }

    public static String showInputDialog(String defaultVal,String titleTxt,String messageTxt){
        TextInputDialog dialog = new TextInputDialog(defaultVal);
        dialog.setTitle(titleTxt);
        dialog.setHeaderText(messageTxt);
        Optional<String> result = dialog.showAndWait();
        String entered = null;
        if (result.isPresent()) {
            entered = result.get();
        }
        return entered;
    }

    public static void outOfService() {
        DialogManager.showErrorDialog(TITLE_ERROR_TEXT, OUT_OF_SERVICE_TEXT);
    }
}
