package com.chen.leetcode.bfs;

import com.chen.leetcode.common.ListNode;
import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表
 * （比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 *  
 *
 * 示例：
 *
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $MianShi0403 {

    /**
     * 层序遍历算法
     *
     *
     * */
    public ListNode[] listOfDepth(TreeNode tree) {

        if (tree == null)
            return null;

        List<ListNode> ret = new ArrayList<>();
        ret.add(new ListNode(tree.val));

        Queue<TreeNode> queue = new LinkedList<>();
        if (tree.left != null)
            queue.add(tree.left);
        if (tree.right != null)
            queue.add(tree.right);

        int lastSize = queue.size();
        ListNode head  = null;
        ListNode level = null;
        while (!queue.isEmpty()) {
            if (lastSize != 0) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null)
                    queue.add(treeNode.left);
                if (treeNode.right != null)
                    queue.add(treeNode.right);

                if (level == null) {
                    level = new ListNode(treeNode.val);
                    head  = level;
                } else {
                    level.next = new ListNode(treeNode.val);
                    level = level.next;
                }

                lastSize--;
                if (lastSize == 0) {
                    ret.add(head);
                }
                continue;
            }


            lastSize = queue.size();
            level = null;
            head  = null;
        }

        ListNode[] res = new ListNode[ret.size()];
        res = ret.toArray(res);
        return res;
    }

    @Test
    public void test() {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.right = node7;

        node4.left = node8;

        ListNode[] res = listOfDepth(root);
        System.out.println(res);
    }
}
