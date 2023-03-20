package com.company;

import java.util.LinkedList;

public class CatBST {
    static private class Node {
        Cat cat;
        Node parent; // parent only for data reasons
        Node left;
        Node right;
        public Node(){}
    }

    Node root;

    public void add(Cat cat) {
        add(cat, root);
    }

    private void add(Cat cat, Node currNode) {
        Node catNode = new Node();
        catNode.cat = cat;

        if (root == null) {
            root = catNode;
            return;
        }

        if (currNode.left == null && cat.getId() < currNode.cat.getId()) {
            catNode.parent = currNode;
            currNode.left = catNode;
            return;
        }
        else if (currNode.right == null && cat.getId() > currNode.cat.getId()) {
            catNode.parent = currNode;
            currNode.right = catNode;
            return;
        }

        if (cat.getId() < currNode.cat.getId()) {
            add(cat, currNode.left);
        }
        else {
            add(cat, currNode.right);
        }
    }

    public Cat getById(int id) {
        return getById(id, root);
    }

    private Cat getById(int id, Node currNode) {
        if (id == currNode.cat.getId()) {
            return currNode.cat;
        }

        if (currNode.left == null && currNode.right == null) {
            return null;
        }

        if (id < currNode.cat.getId() && currNode.left != null) {
            return getById(id, currNode.left);
        }
        else if (id > currNode.cat.getId() && currNode.right != null) {
            return getById(id, currNode.right);
        }
        else {
            return null;
        }
    }

    public LinkedList<Cat> traverseInOrder() {
        return traverseInOrder(root);
    }
    public LinkedList<Cat> traverseInOrder(Node currNode) {
        LinkedList<Cat> out = new LinkedList<Cat>();
        if (currNode == null) {return out;}

        out.addAll(traverseInOrder(currNode.left));
        out.add(currNode.cat);
        out.addAll(traverseInOrder(currNode.right));

        return out;
    }
}
