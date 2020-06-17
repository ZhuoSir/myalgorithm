package com.chen;

public class MyBinaryNode {

    int element;

    MyBinaryNode leftNode;
    MyBinaryNode rightNode;

    public MyBinaryNode(int element, MyBinaryNode leftNode, MyBinaryNode rightNode) {
        this.element = element;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "" + element;
    }
}
