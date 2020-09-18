package com.chen.leetcode.system;

import java.util.HashMap;


/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *  
 *
 * 进阶:
 *
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * https://leetcode-cn.com/problems/lru-cache/
 **/
public class LRUCache {

    private HashMap<Integer, DLinkedNode> map;
    private int capacity;
    private int size;

    private DLinkedNode head;
    private DLinkedNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.map = new HashMap<>();
        this.head = new DLinkedNode();
        this.tail = new DLinkedNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {

        DLinkedNode node = map.get(key);
        if (node == null) return -1;

        moveToHead(node);
        return node.val;
    }

    private void moveToHead(DLinkedNode node) {

        if (node == head.next)
            return;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

    public void put(int key, int value) {
        DLinkedNode node = map.get(key);
        if (node == null) {

            DLinkedNode newNode = new DLinkedNode(key, value);
            head.next.prev = newNode;
            newNode.next = head.next;
            head.next = newNode;
            newNode.prev = head;

            size++;
            if (size > capacity) {
                removeTail();
            }

            map.put(key, newNode);
        } else {
            node.val = value;
            moveToHead(node);
            map.put(key, node);
        }
    }

    private void removeTail() {
        DLinkedNode node = tail.prev;
        tail.prev = node.prev;
        node.prev.next = tail;
        map.remove(node.key);
    }


    class DLinkedNode {

        int key;
        int val;
        DLinkedNode next;
        DLinkedNode prev;

        public DLinkedNode() {
        }

        public DLinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public void print() {
        DLinkedNode node = head.next;
        while (node != tail) {
            System.out.print(node.val + "->");
            node = node.next;
        }
        System.out.println();
    }
}
