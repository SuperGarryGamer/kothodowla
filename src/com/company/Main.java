package com.company;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static CatBST tree;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        DBController.getAllCats();
        for (Cat c: tree.traverseInOrder()) {
            System.out.println(c.toString());
        }
    }
}
