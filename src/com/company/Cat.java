package com.company;

import java.util.LinkedList;

public class Cat {
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    private Cat father;
    private Cat mother;
    private LinkedList<Cat> children;

    public Cat(String name, int id) {
        this.name = name;
        this.id = id;
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
