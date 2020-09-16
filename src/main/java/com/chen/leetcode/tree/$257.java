package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 257. 二叉树的所有路径
 * <p>
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $257 {

    List<String> paths = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new ArrayList<>();
        buildTreePath(root, "");
        return paths;
    }

    public void buildTreePath(TreeNode node, String path) {

        if (node == null)
            return;

        StringBuilder str = new StringBuilder(path);
        str.append(node.val);
        if (node.left == null && node.right == null) {
            paths.add(str.toString());
            return;
        }
        str.append("->");
        buildTreePath(node.left, str.toString());
        buildTreePath(node.right, str.toString());
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);

        root.left = node2;
        root.right = node3;

        node2.left = node5;

        System.out.println(binaryTreePaths(root));
    }
}
