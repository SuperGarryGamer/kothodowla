package com.company;

public class CatBST {
    private class Node {
        Cat cat;
        Node parent;
        Node left;
        Node right;
    }

    Node root;

    public void add(Cat cat) {
        add(cat, root);
    }

    private void add(Cat cat, Node currNode) {
        return;
    }
}
