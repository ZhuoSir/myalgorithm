package com.chen;

/**
 * 双向链表
 *
 * */
public class MyLinkedList2 {

    private Node first;
    private Node last;

    public void addfirst() {

    }

    class Node {

        Node prev;
        Node next;

        int data;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }
}
