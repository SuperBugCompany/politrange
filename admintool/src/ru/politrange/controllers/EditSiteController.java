package ru.politrange.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.politrange.objects.Site;
import ru.politrange.utils.DialogManager;

/**
 * Created by developermsv on 26.11.2015.
 */
public class EditSiteController {

    @FXML
    public Button btnSave;
    public Button btnCancel;
    public TextField txtName;
    private Site Site;

    public void setSite(Site Site) {
        if (Site != null){
            this.Site = Site;
            txtName.setText(Site.getName());
        }
    }

    public Site getSite() {
        return Site;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (checkValues()){
            Site.setName(txtName.getText());
            actionClose(actionEvent);
        }
    }
    private boolean checkValues() {

        if (txtName.getText().trim().length()==0 ){
            DialogManager.showInfoDialog(MainController.TEXT_ERROR, MainController.TEXT_FILL_FIELDS);
            return false;
        }
        return true;
    }

}
