package com.company;

import javafx.scene.layout.StackPane;

import java.util.LinkedList;

public class Cat {
    private String name;
    private int id;
    private StackPane stackPane;

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
}