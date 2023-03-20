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
    private ComboBox<Cat> mother;

    @FXML
    private ComboBox<Cat> father;

    @FXML
    private CheckBox isFemale;

    @FXML
    private AnchorPane familyTree;

    @FXML
    void add(ActionEvent event) {
        Cat cat = new Cat(name.getText(), Main.tree.traverseInOrder().size());
        cat.setDaddy(father.getSelectionModel().getSelectedItem());
        cat.setMommy(mother.getSelectionModel().getSelectedItem());
        mother.setValue(null);
        father.setValue(null);
        name.clear();

        System.out.println(cat);

    }

    @FXML
    void isFemale(ActionEvent event) {

    }

}
