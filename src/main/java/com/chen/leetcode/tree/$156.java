package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

/**
 * 给定一个二叉树，其中所有的右节点要么是具有兄弟节点（拥有相同父节点的左节点）的叶节点，要么为空，将此二叉树上下翻转并将它变成一棵树， 原来的右节点将转换成左叶节点。返回新的根。
 * <p>
 * 例子:
 * <p>
 * 输入: [1,2,3,4,5]
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 输出: 返回二叉树的根 [4,5,2,#,#,3,1]
 * <p>
 * 4
 * / \
 * 5   2
 * / \
 * 3   1
 * 说明:
 * <p>
 * 对 [4,5,2,#,#,3,1] 感到困惑? 下面详细介绍请查看 二叉树是如何被序列化的。
 * <p>
 * 二叉树的序列化遵循层次遍历规则，当没有节点存在时，'#' 表示路径终止符。
 * <p>
 * 这里有一个例子:
 * <p>
 * 1
 * / \
 * 2   3
 * /
 * 4
 * \
 * 5
 * 上面的二叉树则被序列化为 [1,2,3,#,#,4,#,#,5].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-upside-down
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $156 {

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode r = leftNode(root);
        dfs(root);
        return r;
    }

    public TreeNode leftNode(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return root;
        return leftNode(root.left);
    }

    private void dfs(TreeNode node) {

        if (node == null) return;
        if (node.left == null && node.right == null) {
            return;
        }

        dfs(node.right);
        dfs(node.left);

        TreeNode left = node.left;
        TreeNode right = node.right;
        left.left = right;
        left.right = node;

        node.left = null;
        node.right = null;
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
        node2.left = node4;
        node2.right = node5;

        TreeNode node = upsideDownBinaryTree(node1);
        System.out.println(node);
    }
}
