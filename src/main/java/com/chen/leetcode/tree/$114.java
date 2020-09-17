package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 * <p>
 * 给定一个二叉树，原地将它展开为一个单链表。
 * <p>
 *  
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $114 {

    /**
     * 前序遍历：递归实现
     * <p>
     * 将遍历结果每个节点左侧置为NULl, 右侧子节点为遍历结果的下一个节点；
     */
    public void flatten(TreeNode root) {

        List<TreeNode> treeNodes = new ArrayList<>();
        preorderTraversal(root, treeNodes);

        for (int i = 0; i < treeNodes.size(); i++) {
            TreeNode treeNode = treeNodes.get(i);
            treeNode.left = null;
            if (i + 1 < treeNodes.size())
                treeNode.right = treeNodes.get(i + 1);
        }
    }

    private void preorderTraversal(TreeNode node, List<TreeNode> treeNodes) {

        if (node == null) return;
        treeNodes.add(node);
        preorderTraversal(node.left, treeNodes);
        preorderTraversal(node.right, treeNodes);
    }

    /**
     * 前序遍历：迭代实现
     * <p>
     * 将遍历结果每个节点左侧置为NULl, 右侧子节点为遍历结果的下一个节点；
     */
    public void flatten2(TreeNode root) {

        List<TreeNode> treeNodes = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                treeNodes.add(node);
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            node = node.right;
        }

        for (int i = 0; i < treeNodes.size(); i++) {
            TreeNode treeNode = treeNodes.get(i);
            treeNode.left = null;
            if (i + 1 < treeNodes.size())
                treeNode.right = treeNodes.get(i + 1);
        }
    }

    /**
     * 方法二：前序遍历和展开同步进行
     * 使用方法一的前序遍历，由于将节点展开之后会破坏二叉树的结构而丢失子节点的信息，因此前序遍历和展开为单链表分成了两步。能不能在不丢失子节点的信息的情况下，将前序遍历和展开为单链表同时进行？
     * <p>
     * 之所以会在破坏二叉树的结构之后丢失子节点的信息，是因为在对左子树进行遍历时，没有存储右子节点的信息，在遍历完左子树之后才获得右子节点的信息。只要对前序遍历进行修改，在遍历左子树之前就获得左右子节点的信息，并存入栈内，子节点的信息就不会丢失，就可以将前序遍历和展开为单链表同时进行。
     * <p>
     * 该做法不适用于递归实现的前序遍历，只适用于迭代实现的前序遍历。修改后的前序遍历的具体做法是，每次从栈内弹出一个节点作为当前访问的节点，获得该节点的子节点，如果子节点不为空，则依次将右子节点和左子节点压入栈内（注意入栈顺序）。
     * <p>
     * 展开为单链表的做法是，维护上一个访问的节点 prev，每次访问一个节点时，令当前访问的节点为 curr，将 prev 的左子节点设为 null 以及将 prev 的右子节点设为 curr，然后将 curr 赋值给 prev，进入下一个节点的访问，直到遍历结束。需要注意的是，初始时 prev 为 null，只有在 prev 不为 null 时才能对 prev 的左右子节点进行更新。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/er-cha-shu-zhan-kai-wei-lian-biao-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public void flatten3(TreeNode root) {

        if (root == null) return;

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode prev = null;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (prev != null) {
                prev.right = curr;
                prev.left = null;
            }

            if (curr.right != null)
                stack.push(curr.right);

            if (curr.left != null)
                stack.push(curr.left);

            prev = curr;
        }
    }

    /**
     * 方法三：寻找前驱节点
     * 前两种方法都借助前序遍历，前序遍历过程中需要使用栈存储节点。有没有空间复杂度是 O(1)O(1) 的做法呢？
     * <p>
     * 注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的左子节点为空，则该节点不需要进行展开操作。如果一个节点的左子节点不为空，则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。因此，问题转化成寻找当前节点的前驱节点。
     * <p>
     * 具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/er-cha-shu-zhan-kai-wei-lian-biao-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)，其中 nn 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次，在寻找前驱节点的过程中，每个节点最多被额外访问一次。
     * <p>
     * 空间复杂度：O(1)O(1)。
     */
    public void flatten4(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode pred = next;
                while (pred.right != null) {
                    pred = pred.right;
                }

                pred.right = curr.right;
                curr.right = next;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    @Test
    public void test() {

        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node2;
        root.right = node5;

        node2.left = node3;
        node2.right = node4;

        node5.right = node6;

        flatten3(root);
    }
}
