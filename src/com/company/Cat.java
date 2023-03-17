package com.company;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedList;

public class Cat {
    private String name;
    private int id;
    private StackPane stackPane;

    private int sizeX = 50;
    private int sizeY = 20;

    public StackPane getStackPane() {
        return stackPane;
    }

    public int getId() {
        return id;
    }

    private Cat father;
    private Cat mother;
    private LinkedList<Cat> children;

    public Cat(String name, int id) {
        this.name = name;
        this.id = id;
        this.stackPane = new StackPane();
    }

    public void addChild(Cat child) {
        this.children.add(child);
    }

    public void setMommy(Cat mother) {
        this.mother = mother;
    }

    public void setDaddy(Cat father) {
        this.father = father;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
        Rectangle base = new Rectangle(sizeX, sizeY);
        base.setFill(Color.GRAY);
        Text catName = new Text(name);
        Rectangle transparent = new Rectangle(sizeX, sizeY);
        transparent.setFill(Color.TRANSPARENT);

        stackPane.getChildren().add(base);
        stackPane.getChildren().add(catName);
        stackPane.getChildren().add(transparent);

        transparent.setOnMouseClicked(event -> {

        });
    }
}
