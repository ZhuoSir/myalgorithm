package com.chen.leetcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBlockingQueue {

    private int capacity = 0;
    //    private AtomicInteger size = new AtomicInteger(0);
    private int size = 0;

    private ReentrantLock lock;

    private Condition notFull;
    private Condition notEmpty;

    private DNode head;
    private DNode tail;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.lock = new ReentrantLock(true);
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();

        head = new DNode();
        tail = new DNode();

        head.next = tail;
        tail.prev = head;
    }

    public void enqueue(int element) throws InterruptedException {

        try {
            lock.lock();
            while (isFull()) {
                notFull.await();
            }

            DNode newNode = new DNode(element);
            head.next.prev = newNode;
            newNode.next = head.next;
            head.next = newNode;
            newNode.prev = head;

            size++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {

        int val = 0;
        try {
            lock.lock();
            while (isEmpty()) {
                notEmpty.await();
            }

            DNode theNode = tail.prev;
            theNode.prev.next = tail;
            tail.prev = theNode.prev;

            size--;
            val = theNode.value;
            theNode = null;
        } finally {
            lock.unlock();
        }

        return val;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == capacity;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        DNode node = head;
        while (node.next != tail) {
            builder.append(node.value);
            node = node.next;
            if (node != tail) {
                builder.append("->");
            }
        }
        return builder.toString();
    }

    class DNode {

        int value;

        private DNode prev;

        private DNode next;

        public DNode() {
        }

        public DNode(int value) {
            this.value = value;
        }

        public DNode(int value, DNode prev, DNode next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public DNode getPrev() {
            return prev;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getNext() {
            return next;
        }

        public void setNext(DNode next) {
            this.next = next;
        }
    }
}
