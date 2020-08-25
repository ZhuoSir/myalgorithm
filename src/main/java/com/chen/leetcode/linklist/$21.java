package com.chen.leetcode.linklist;

import org.junit.Test;

/**
 * 21. 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $21 {


    /**
     * 思路
     * 标签：链表、递归
     * 这道题可以使用递归实现，新链表也不需要构造新节点，我们下面列举递归三个要素
     * 终止条件：两条链表分别名为 l1 和 l2，当 l1 为空或 l2 为空时结束
     * 返回值：每一层调用都返回排序好的链表头
     * 本级递归内容：如果 l1 的 val 值更小，则将 l1.next 与排序好的链表头相接，l2 同理
     * O(m+n)O(m+n)，mm 为 l1的长度，nn 为 l2 的长度
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/hua-jie-suan-fa-21-he-bing-liang-ge-you-xu-lian-bi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     *
     * */
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

    /**
     *
     * 方法二：迭代
     * 思路
     *
     * 我们可以用迭代的方法来实现上述算法。当 l1 和 l2 都不是空链表时，判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     *
     * 算法
     *
     * 首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前的节点接在 prev 节点的后面同时将 l1 指针往后移一位。否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都需要把 prev 向后移一位。
     *
     * 在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n + m)O(n+m) ，其中 nn 和 mm 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)O(n+m)。
     *
     * 空间复杂度：O(1)O(1) 。我们只需要常数的空间存放若干变量。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        ListNode prex = new ListNode(-1);
        ListNode head = prex;

        while (l1 != null && l2 != null) {

            if (l1.val < l2.val) {
                prex.next = l1;
                l1 = l1.next;
            } else {
                prex.next = l2;
                l2 = l2.next;
            }

            prex = prex.next;
        }

        prex.next = l1 != null ? l1 : l2;

        return head.next;
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
}
