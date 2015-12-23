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
    private ModalResult modalResult = ModalResult.MD_CANCEL;

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
        setModalResult(ModalResult.MD_CANCEL);
    }

    public void actionSave(ActionEvent actionEvent) {
        if (checkValues()){
            person.setName(txtName.getText());
            actionClose(actionEvent);
            setModalResult(ModalResult.MD_SAVE);
        }
    }
    private boolean checkValues() {

        if (txtName.getText().trim().length()==0 ){
            DialogManager.showInfoDialog(MainController.ERROR_TEXT, MainController.FILL_FIELDS_TEXT);
            return false;
        }
        return true;
    }

    public ModalResult getModalResult() {
        return modalResult;
    }

    public void setModalResult(ModalResult modalResult) {
        this.modalResult = modalResult;
    }
}
