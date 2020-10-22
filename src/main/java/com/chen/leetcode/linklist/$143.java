package com.chen.leetcode.linklist;

import com.chen.leetcode.common.ListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $143 {

    public void reorderList(ListNode head) {
        if (head == null) return;
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            list.get(left).next = list.get(right);
            left++;
            list.get(right).next = list.get(left);
            right--;
        }
        list.get(left).next = null;
    }

    public void reorderList2(ListNode head) {
        if (head == null) return;
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1tmp = l1;
        ListNode l2tmp = l2;
        while (l1 != null && l2 != null) {
            l1tmp = l1.next;
            l2tmp = l2.next;
            l1.next = l2;
            l1 = l1tmp;

            l2.next = l1;
            l2 = l2tmp;
        }
    }

    public ListNode reverseList(ListNode head) {
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

    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                break;
            }
            slow = slow.next;
        }
        return slow;
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

        reorderList2(node1);
//        node1 = reverseList(node1);

        ListNode curr = node1;
        while (curr != null) {
            System.out.print(curr.val + "->");
            curr = curr.next;
        }
    }
}
