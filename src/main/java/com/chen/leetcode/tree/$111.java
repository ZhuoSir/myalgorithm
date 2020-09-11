package com.chen.leetcode.tree;

import org.junit.Test;

/**
 * 111
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $111 {

    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;

        if (root.right == null && root.left == null) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        if (root.left != null) {
            min = Math.min(min, minDepth(root.left));
        }

        if (root.right != null) {
            min = Math.min(min, minDepth(root.right));
        }

        return min + 1;
    }

    @Test
    public void test() {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);

        node1.left = node2;

        System.out.println(minDepth(node1));

    }
}
