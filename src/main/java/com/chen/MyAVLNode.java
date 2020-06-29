package com.chen;

public class MyAVLNode {

    int height;
    int element;

    MyAVLNode leftNode;
    MyAVLNode rightNode;

    public MyAVLNode(int element) {
        this.element = element;
    }

    public MyAVLNode(int element, MyAVLNode leftNode, MyAVLNode rightNode) {
        this.element = element;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
}
