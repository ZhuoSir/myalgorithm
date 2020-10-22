package com.chen.leetcode.linklist;

import com.chen.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) return null;

        ListNode node1 = head;
        ListNode node2 = head;
        for (int i = 0; i < n; i++) {
            node1 = node1.next;
        }

        ListNode prev = null;
        while (node1 != null) {
            node1 = node1.next;
            prev = node2;
            node2 = node2.next;
        }

        if (prev != null)
            prev.next = node2.next;

        if (node2 == head)
            head = node2.next;
        node2 = null;
        return head;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        removeNthFromEnd(node1, 2);

        node1.print();
    }

    @Test
    public void test2() {
        ListNode node1 = new ListNode(1);

        node1 = removeNthFromEnd(node1, 1);

        node1.print();
    }
}
