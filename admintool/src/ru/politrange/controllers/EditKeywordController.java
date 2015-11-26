package ru.politrange.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.politrange.objects.Keyword;
import ru.politrange.utils.DialogManager;

/**
 * Created by developermsv on 25.11.2015.
 */
public class EditKeywordController {
    @FXML
    public Button btnSave;
    public Button btnCancel;
    public TextField txtName;
    private Keyword keyword;

    public void setKeyword(Keyword keyword) {
        if (keyword != null) {
            this.keyword = keyword;
            txtName.setText(keyword.getName());
        }
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (checkValues()) {
            keyword.setName(txtName.getText());
            actionClose(actionEvent);
        }
    }

    private boolean checkValues() {

        if (txtName.getText().trim().length() == 0) {
            DialogManager.showInfoDialog(MainController.TEXT_ERROR, MainController.TEXT_FILL_FIELDS);
            return false;
        }
        return true;
    }
}
