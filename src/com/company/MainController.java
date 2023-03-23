package com.company;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.LinkedList;

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
        Cat motherCat, fatherCat;

        motherCat = mother.getSelectionModel().getSelectedItem();
        fatherCat = father.getSelectionModel().getSelectedItem();

        if (motherCat != null && fatherCat != null && motherCat.getNotBreedableCats().contains(fatherCat)) {
            System.out.println("Non breedy !! :3");
            return;
        }

        Cat cat = new Cat(name.getText(), Main.tree.traverseInOrder().size());
        cat.setMommy(motherCat);
        cat.setDaddy(fatherCat);
        cat.setFemale(isFemale.isSelected());

        mother.setValue(null);
        father.setValue(null);

        Main.tree.add(cat);
        DBController.addCat(cat);
        name.clear();

        System.out.println(cat);

    }

    @FXML
    void isFemale(ActionEvent event) {

    }

    @FXML
    void initialize() {
        mother.getItems().add(null);
        father.getItems().add(null);
        for (Cat cat: Main.tree.traverseInOrder()) {
            if (cat.isFemale()) mother.getItems().add(cat);
            else father.getItems().add(cat);
        }
    }

}
