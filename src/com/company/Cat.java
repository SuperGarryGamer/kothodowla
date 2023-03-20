package com.company;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedList;

public class Cat {
    private String name;
    private int id;
    private boolean female;
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
        this.children = new LinkedList<Cat>();
        this.stackPane = new StackPane();
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", father=" + father +
                ", mother=" + mother +
                '}';
    }

    public LinkedList<Cat> getNotBreedableCats() {
        return getNotBreedableCats(4, this, null);
    }

    private LinkedList<Cat> getNotBreedableCats(int minDistance, Cat currCat, Cat prevCat) {
        LinkedList<Cat> cats = new LinkedList<Cat>();
        if (minDistance == 0) {
            return cats;
        }

        cats.add(this);

        for (Cat cat: children) {
            if (!cats.contains(cat)) cats.addAll(cat.getNotBreedableCats(minDistance - 1, cat, this));
        }

        if (!cats.contains(mother) && mother != null) cats.addAll(mother.getNotBreedableCats(minDistance - 1, mother, this));
        if (!cats.contains(father) && father != null) cats.addAll(father.getNotBreedableCats(minDistance - 1, father, this));

        return cats;
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
            for (Cat cat : this.getNotBreedableCats()){
                ((Rectangle) cat.stackPane.getChildren().get(0)).setFill(Color.RED);
            }
        });
    }
}





// dont mind this v
// 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
