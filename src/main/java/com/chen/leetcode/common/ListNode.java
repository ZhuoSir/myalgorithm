package com.chen.leetcode.common;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) { this.val = val; this.next = next;}

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
