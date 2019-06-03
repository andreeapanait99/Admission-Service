package library.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class MenuController {

    @FXML
    private TextField inputId;

    @FXML
    void searchById(ActionEvent event) {
        System.out.println(inputId.getText());
    }

}
