package com.chen;

import org.junit.Test;

public class MyLinkedList {

    Node head = null;

    int size = 0;

    public void add(int v) {
        Node node = new Node(v);
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    public void addLink(Node head2) {
        if (head == null)
            head = head2;

        if (head.next == null)
            head.next = head2;

        Node tailNode = lastNode();
        tailNode.next = head2;
    }

    public int get(int index) {

        if (index == 0) {
            return head.data;
        }

        int i = 0;
        Node node = head;
        while (node != null) {
            if (i == index) {
                return node.data;
            }

            node = node.next;
            i++;
        }

        throw new IndexOutOfBoundsException();
    }

    public void delete(int index) {

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        Node preNode = head;
        Node curNode = preNode.next;

        int i = 0;
        while (curNode != null) {
            if (i == index) {
                preNode.next = curNode.next;
                return;
            }

            preNode = preNode.next;
            curNode = curNode.next;
            i++;
        }

        size--;
    }


    public int length() {

        if (head == null)
            return 0;

        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    public int length(Node head) {

        if (head == null)
            return 0;

        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        return length;
    }

    public void reserveLink() {

        Node curNode = head;
        Node preNode = null;

        while (curNode != null) {
            Node nextNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nextNode;
        }

        head = preNode;
    }

    public boolean isRing() {
        if (head == null)
            return false;

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public int findReverNode(int k) {
        Node first = head;
        Node second = head;

        for (int i = 0; i < k - 1; i++) {
            first = first.next;
        }

        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        return second.data;
    }

    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public Node lastNode() {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        return temp;
    }


    public Node lastNode(Node head) {

        Node temp = head;

        if (temp == null) {
            return null;
        }

        if (temp.next == null) {
            return temp;
        }

        while (temp.next != null) {
            temp = temp.next;
        }

        return temp;
    }

    public boolean isCross(Node head1, Node head2) {

        Node temp1 = lastNode(head1);
        Node temp2 = lastNode(head2);

        if (temp1 == temp2) {
            return true;
        }

        return false;
    }


    public int crossedFirstNode(Node head1, Node head2) {

        Node temp1 = head1;
        Node temp2 = head2;

        if (isCross(head1, head2)) {

            int length1 = length(head1);
            int length2 = length(head2);
            int len = 0;

            len = length1 - length2;
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    temp1 = temp1.next;
                }
            } else {
                len = -len;
                for (int i = 0; i < len; i++) {
                    temp2 = temp2.next;
                }
            }


            while (temp1 != temp2) {
                temp1 = temp1.next;
                temp2 = temp2.next;
            }

            return temp1.data;
        }

        return -1;
    }

    class Node {

        Node next = null;

        private int data;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "" + data;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node node = head;
        stringBuilder.append("[");
        while (node != null) {
            stringBuilder.append(node.data).append(",");
            node = node.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Test
    public void test1() {

        MyLinkedList myLinkedList = new MyLinkedList();
        for (int i = 0; i < 5; i++) {
            myLinkedList.add(i);
        }



//        MyLinkedList myLinkedList1 = new MyLinkedList();
//        for (int i = 11; i < 14; i++) {
//            myLinkedList.add(i);
//        }




        System.out.println(myLinkedList.toString());

//        myLinkedList.delete(3);
//        System.out.println(myLinkedList.toString());
//
//        myLinkedList.delete(3);
//        System.out.println(myLinkedList.toString());

        myLinkedList.reserveLink();
        System.out.println(myLinkedList);

        System.out.println(myLinkedList.isRing());
        System.out.println(myLinkedList.findMiddleNode());
//        System.out.println(myLinkedList.findReverNode(7));

        System.out.println(myLinkedList.lastNode());
        System.out.println(myLinkedList.length());
    }
}
