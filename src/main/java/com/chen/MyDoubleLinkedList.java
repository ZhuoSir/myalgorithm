package com.chen;

/**
 * 双向链表
 *
 * */
public class MyDoubleLinkedList {

    private Node first;
    private Node last;

    public void addfirst(int v) {
        Node node = new Node(v);
        if (first == null) {
            first = node;
            last = node;
            return;
        }

        node.next = first;
        first.prev = node;

        first = node;
    }

    public void addLast(int v) {
        Node node = new Node(v);
        if (first == null) {
            first = node;
            last = node;
            return;
        }

        last.next = node;
        node.prev = last;

        last = node;
    }

    public int getFirst() {
        if (first == null) {
            throw new NullPointerException();
        }
        return first.data;
    }

    public int getLast() {
        if (last == null) {
            throw new NullPointerException();
        }
        return last.data;
    }

    public int removeFirst() {
        Node temp = first;
        if (temp == null) {
            throw new NullPointerException();
        }

        temp.next.prev = null;
        first = temp.next;

        return temp.data;
    }

    public int removeLast() {
        Node temp = last;
        if (temp == null) {
            throw new NullPointerException();
        }

        temp.prev.next = null;
        last = temp.prev;

        return temp.data;
    }

    public int remove(int index) {
        Node temp = first;
        if (temp == null) {
            throw new NullPointerException();
        }

        int i = 0;
        while (temp != null) {
            if (i == index) {
                unlink(temp);
                break;
            }
            temp = temp.next;
        }

        return temp.data;
    }

    public int get(int index) {
        Node temp = first;
        if (temp == null) {
            throw new NullPointerException();
        }

        int i = 0;
        while (temp != null) {
            if (i == index) {
                break;
            }
            temp = temp.next;
            i++;
        }

        if (temp != null) {
            return temp.data;
        } else {
            throw new NullPointerException();
        }
    }

    public int unlink(Node x) {

        Node prev = x.prev;
        Node next = x.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        return x.data;
    }

    @Override
    public String toString() {
        Node temp = first;
        StringBuilder stringBuilder = new StringBuilder();
        while (temp != null) {
            stringBuilder.append(temp.data).append(",");
            temp = temp.next;
        }

        return stringBuilder.toString();
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


    public static void main(String[] args) {

        MyDoubleLinkedList myDoubleLinkedList = new MyDoubleLinkedList();
        for (int i = 0; i < 10; i++) {
//            myDoubleLinkedList.addfirst(i);
            myDoubleLinkedList.addLast(i);
        }

        System.out.println(myDoubleLinkedList.toString());
        System.out.println(myDoubleLinkedList.getFirst());
        System.out.println(myDoubleLinkedList.getLast());

        int d = myDoubleLinkedList.removeLast();
        System.out.println(d);
        System.out.println(myDoubleLinkedList.getLast());

        System.out.println(myDoubleLinkedList.get(6));
    }
}
