package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private TextField name;

    @FXML
    private ComboBox<?> mother;

    @FXML
    private ComboBox<?> father;

    @FXML
    private CheckBox isFemale;

    @FXML
    private AnchorPane familyTree;

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void isFemale(ActionEvent event) {

    }

}
