package com.chen;

import java.util.Random;

public class MyBinarySearchTree {

    private MyBinaryNode root;

    public MyBinarySearchTree() {
        root = null;
    }

    public Integer findMax() {
        MyBinaryNode max = findMax(root);
        return max != null ? max.element : null;
    }

    public Integer findMin() {
        MyBinaryNode min = findMin(root);
        return min != null ? min.element : null;
    }

    public int nodeCount() {
        return nodeCount(root);
    }

    public int nodeCount(MyBinaryNode myBinaryNode) {
        if (myBinaryNode == null) {
            return 0;
        } else {
            return nodeCount(myBinaryNode.leftNode) + nodeCount(myBinaryNode.rightNode) + 1;
        }
    }


    public int depth() {
        return depth(root);
    }

    public int depth(MyBinaryNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth  = depth(node.leftNode);
        int rightDepth = depth(node.rightNode);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public MyBinaryNode findMin(MyBinaryNode node) {

        if (node == null) {
            return null;
        }

        else if (node.leftNode == null) {
            return node;
        }

        return findMin(node.leftNode);
    }


    public MyBinaryNode findMax(MyBinaryNode node) {

        if (node == null) {
            return null;
        }

        else if (node.rightNode == null) {
            return node;
        }

        return findMax(node.rightNode);
    }

    public void insert(int element) {
        root = insert(element, root);
    }

    public MyBinaryNode insert(int element, MyBinaryNode node) {

        if (node == null) {
            return new MyBinaryNode(element, null, null);
        }

        if (element < node.element) {
            node.leftNode  = insert(element, node.leftNode);
        } else if (element > node.element){
            node.rightNode = insert(element, node.rightNode);
        }
        return node;
    }


    public MyBinaryNode remove(int element, MyBinaryNode node) {
        if (node == null) {
            return node;
        }

        int compare = element - node.element;
        if (compare < 0)
            node.leftNode = remove(element, node.leftNode);
        else if (compare > 0)
            node.rightNode = remove(element, node.rightNode);
        else if (node.leftNode != null && node.rightNode != null) {
            node.element = findMin(node.rightNode).element;
            node.rightNode = remove(node.element, node.rightNode);
        }
        else
            node = (node.leftNode != null) ? node.leftNode : node.rightNode;

        return node;
    }

    public boolean remove(int element) {
        return (remove(element, root) != null);
    }


    public static void main(String[] args) {

        MyBinarySearchTree myBinarySearchTree = new MyBinarySearchTree();

        Random random = new Random(1000);
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(1000);
            myBinarySearchTree.insert(r);
        }

        int max = myBinarySearchTree.findMax();
        int min = myBinarySearchTree.findMin();

        System.out.println(max);
        System.out.println(min);

        System.out.println(myBinarySearchTree.nodeCount());
        System.out.println(myBinarySearchTree.depth());


        System.out.println(myBinarySearchTree.remove(7));
        System.out.println(myBinarySearchTree.nodeCount());
    }

}
