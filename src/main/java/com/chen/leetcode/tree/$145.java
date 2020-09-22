package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * 145. 二叉树的后序遍历
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $145 {

    /**
     * 如何遍历一棵树
     * 有两种通用的遍历树的策略：
     *
     * 深度优先搜索（DFS）
     *
     * 在这个策略中，我们采用 深度 作为优先级，以便从跟开始一直到达某个确定的叶子，然后再返回到达另一个分支。
     *
     * 深度优先搜索策略又可以根据根节点、左孩子和右孩子的相对顺序被细分为先序遍历，中序遍历和后序遍历。
     *
     * 宽度优先搜索（BFS）
     *
     * 我们按照高度顺序一层一层的访问整棵树，高层次的节点将会比低层次的节点先被访问到。
     *
     * 下图中的顶点按照访问的顺序编号，按照 1-2-3-4-5 的顺序来比较不同的策略。
     *
     *
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/solution/er-cha-shu-de-hou-xu-bian-li-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 复杂度分析
     *
     * 时间复杂度：访问每个节点恰好一次，因此时间复杂度为 O(N)O(N)，其中 NN 是节点的个数，也就是树的大小。
     * 空间复杂度：取决于树的结构，最坏情况需要保存整棵树，因此空间复杂度为 O(N)O(N)。
     *
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                res.addFirst(root.val);
                stack.push(root.left);
                root = root.right;
            } else {
                root = stack.pop();
            }
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
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        System.out.println(postorderTraversal(node1));
    }
}
