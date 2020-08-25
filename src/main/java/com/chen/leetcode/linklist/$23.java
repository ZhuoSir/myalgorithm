package com.chen.leetcode.linklist;

import org.junit.Test;

import java.util.Arrays;

/**
 * 23. 合并K个升序链表
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $23 {

    /**
     *
     * 解题思路
     * 只要你知道归并排序，那么这题一定没有问题~
     *
     * 这题就是归并排序中的归并操作，只不过此时需要你归并多个而已。对于这种问题，我们只需要通过分治的思想不断将问题划分至基例(也就是能够很方便解决的状态)。
     *
     * 思路理清
     * 基例为当前要归并的只有一个链表，和当前要归并的有两个链表。因为这两种情况都是可以很方便解决的。
     *
     * 通过两个变量lo和hi来分别要归并链表数组的上界和下界：
     *
     * 若当前归并的区间lo==hi，即一个链表，则直接返回lists[lo]。
     * 若当前归并的区间lo==hi-1，即归并两个链表，则返回merge(lists[lo], lists[hi])
     * 否则在左半区间归并，右半区间进行归并，结果获得的两个链表再归并。
     * 时间复杂度
     * T(n) = 2T(n/2) + O(m)T(n)=2T(n/2)+O(m), n为数组长度，m为平均链表长度。
     * 总体的复杂度大致为：O(mlgn)O(mlgn)
     *
     * */
    public ListNode mergeKLists(ListNode[] lists) {

        int len;
        if (lists == null || (len = lists.length) == 0)
            return null;
        return merge(lists, 0, len - 1);
    }


    public ListNode merge(ListNode[] lists, int from, int to) {

        int t = to - from;

        if (t == 0) {
            return lists[to];
        }

        if (t == 1) {
            return mergeTwoLists(lists[from], lists[to]);
        }

        if (t >= 2) {
            int mid = from + (to - from) / 2;
            ListNode leftNode  = merge(lists, from, mid);
            ListNode rightNode = merge(lists, mid + 1, to);

            return mergeTwoLists(leftNode, rightNode);
        }

        return null;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1 == null)
            return l2;

        if (l2 == null)
            return l1;

        if (l1.val < l2.val) {

            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {

            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void test() {

        ListNode l11 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);

        l11.next = l12;
        l12.next = l13;

        ListNode l21 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l23 = new ListNode(4);

        l21.next = l22;
        l22.next = l23;

        ListNode l31 = new ListNode(2);
        ListNode l32 = new ListNode(6);

        l31.next = l32;

        ListNode[] listNodes = {l11, l21, l31};

        ListNode iter = mergeKLists(listNodes);
        while (iter != null) {
            System.out.print(iter.val + ", ");
            iter = iter.next;
        }
    }
}
