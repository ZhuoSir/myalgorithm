package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 199. 二叉树的右视图
 *
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $199 {

    /**
     *
     *
     *
     * */
    public List<Integer> rightSideView(TreeNode root) {

        if (root == null)
            return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        res.add(root.val);

        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
        int lastSize = 0;
        while (!queue.isEmpty()) {
            if (lastSize > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                lastSize--;
                if (lastSize == 0) {
                    res.add(node.val);
                }
                continue;
            }
            lastSize = queue.size();
        }

        return res;
    }

    @Test
    public void test() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;

        node2.right = node5;
        node3.right = node4;

        System.out.println(rightSideView(node1));
    }
}
