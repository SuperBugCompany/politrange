package ru.politrange.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ru.politrange.objects.Person;
import ru.politrange.utils.DialogManager;

/**
 * Created by msv on 24.11.2015.
 */
public class EditPersonController {

    @FXML
    public Button btnSave;
    public Button btnCancel;
    public TextField txtName;
    private Person person;

    public void setPerson(Person person) {
        if (person != null){
            this.person = person;
            txtName.setText(person.getName());
        }
    }

    public Person getPerson() {
        return person;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (checkValues()){
            person.setName(txtName.getText());
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
