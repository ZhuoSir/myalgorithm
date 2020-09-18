package com.chen.leetcode.common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    public void print() {
        ListNode node = this;
        while (node != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }
    }
}
