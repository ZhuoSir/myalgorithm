package com.chen.leetcode.array;

import com.chen.leetcode.common.ListNode;
import org.junit.Test;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        return isSame(l1, l2);
    }

    private boolean isSame(ListNode l1, ListNode l2) {
        while (l1 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head) {
        if (head == null)
            return null;

        ListNode curr = head;
        ListNode prex = null;

        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prex;
            prex = curr;
            curr = tmp;
        }
        return prex;
    }

    private ListNode middleNode(ListNode head) {
        ListNode prev = null;
        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
            prev = slow;
            slow = slow.next;
        }
        return prev;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(3);
        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        System.out.println(isPalindrome(node1));
    }

    @Test
    public void test1() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        System.out.println(isPalindrome(node1));
    }
}
