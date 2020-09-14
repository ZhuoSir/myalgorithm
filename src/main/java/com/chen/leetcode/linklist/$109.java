package com.chen.leetcode.linklist;

import com.chen.leetcode.common.ListNode;
import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $109 {

    /**
     * 将链表转换为数组；
     * 然后分治法构造成树；
     *
     * 方法1：将有序链表转成有序数组
     * 先将有序链表转成有序数组，我之前画过下图，想象成一条绳，提起中点作为根节点，分出左右两部分，再提起各自的中点作为根节点……分治下去，这根绳就成了BST的模样。
     *
     * 作者：xiao_ben_zhu
     * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/shou-hua-tu-jie-san-chong-jie-fa-jie-zhu-shu-zu-ku/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 时间复杂度：O(n)O(n)，用 O(1) 时间找到数组中间元素，总体复杂度相当于只遍历了一遍数组。
     * 空间复杂度：O(n)O(n)。
     *
     * */
    public TreeNode sortedListToBST(ListNode head) {

        if (head == null)
            return null;

        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        for (; node != null ;) {
            list.add(node.val);
            node = node.next;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }


        TreeNode res = buildBst(list, 0, list.size() - 1);
        return res;
    }

    private TreeNode buildBst(List<Integer> list, int start, int end) {

        if (start > end)
            return null;

        int mid = (start + end) / 2;
        if (mid >= list.size())
            return null;

        if (end - start == 1) {
            TreeNode node = new TreeNode(list.get(end));
            node.left = new TreeNode(list.get(start));
            return node;
        }

        TreeNode treeNode = new TreeNode(list.get(mid));
        treeNode.left = buildBst(list, start, mid - 1);
        treeNode.right = buildBst(list, mid + 1, end);
        return treeNode;
    }


    /**
     * 方法2：快慢指针
     * 寻找链表的中间点，有个小技巧：快、慢指针指向头结点，快指针一次走两步，慢指针一次走一步，当快指针走到尾节点时，慢指针正好走到链表的中间。然后断成两个链表，分而治之。
     *
     * 为了断开，我们需要一个指针记录慢指针的前一个节点，因为单向链表的节点没有前驱指针。
     *
     * 作者：xiao_ben_zhu
     * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/solution/shou-hua-tu-jie-san-chong-jie-fa-jie-zhu-shu-zu-ku/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 时间复杂度：O(nlogn)O(nlogn)。一共 logn 层递归，每次找中点 O(n/2)，即 O(n)（我这么解释好像不是很对）。可以参考这个解释：每次递归花 O(n) 时间找到中点，有 T(n) = O(n) + 2T(n/2), 根据主定理推导出 O(nlogn)。主定理（master theorem）见下图：
     * 空间复杂度：O(logn)O(logn)。递归栈的调用深度。
     *
     *
     * */
    public TreeNode sortedListToBST2(ListNode head) {

        if (head == null)
            return null;

        if (head.next == null)
            return new TreeNode(head.val);

        ListNode fast = head, slow = head, pre = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            pre = slow;
            slow = slow.next;
        }

        // 将链表从slow处断开；
        ListNode right = slow.next;
        pre.next = null;

        TreeNode treeNode = new TreeNode(slow.val);
        treeNode.right = sortedListToBST2(right);
        treeNode.left  = sortedListToBST2(head);

        return treeNode;
    }

    @Test
    public void test() {

        ListNode node0 = new ListNode(3);
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(8);
//        ListNode node3 = new ListNode(5);
//        ListNode node4 = new ListNode(9);

        node0.next = node1;
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;

        TreeNode treeNode = sortedListToBST2(node0);
        System.out.println(treeNode);
    }
}
