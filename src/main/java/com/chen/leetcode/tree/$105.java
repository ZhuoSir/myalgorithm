package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $105 {

    Map<Integer, Integer> inorderMap = new HashMap<>();

    /**
     * 解法一、递归
     * 先序遍历的顺序是根节点，左子树，右子树。中序遍历的顺序是左子树，根节点，右子树。
     *
     * 所以我们只需要根据先序遍历得到根节点，然后在中序遍历中找到根节点的位置，它的左边就是左子树的节点，右边就是右子树的节点。
     *
     * 生成左子树和右子树就可以递归的进行了。
     *
     * 比如上图的例子，我们来分析一下。
     *
     * Java
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * 首先根据 preorder 找到根节点是 3
     *
     * 然后根据根节点将 inorder 分成左子树和右子树
     * 左子树
     * inorder [9]
     *
     * 右子树
     * inorder [15,20,7]
     *
     * 把相应的前序遍历的数组也加进来
     * 左子树
     * preorder[9]
     * inorder [9]
     *
     * 右子树
     * preorder[20 15 7]
     * inorder [15,20,7]
     *
     * 现在我们只需要构造左子树和右子树即可，成功把大问题化成了小问题
     * 然后重复上边的步骤继续划分，直到 preorder 和 inorder 都为空，返回 null 即可
     * 事实上，我们不需要真的把 preorder 和 inorder 切分了，只需要用分别用两个指针指向开头和结束位置即可。注意下边的两个指针指向的数组范围是包括左边界，不包括右边界。
     *
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd) {

        if (preStart == preEnd)
            return null;

        int head = preorder[preStart];
        TreeNode root = new TreeNode(head);

        Integer root_index = inorderMap.get(head);
        if (root_index == null) return null;
        int leftnum    = root_index - inStart;
        root.left = buildTree(preorder, inorder, preStart + 1, preStart + leftnum + 1, inStart, root_index);
        root.right = buildTree(preorder, inorder, preStart + leftnum + 1, preEnd, root_index + 1, inEnd);

        return root;
    }

    @Test
    public void test() {

        int[] pre = {3,9,20,15,7};
        int[] ino = {9,3,15,20,7};

        TreeNode treeNode = buildTree(pre, ino);
        System.out.println(treeNode);
    }


    // 1. prestart = 0, root = pre[prestart];
    // 2. 找到in 中root 的坐标为 root_index, 左侧范围： (instart, rootindex -1 )即为root的左子树；左子树长度为 leftnum = rootindex - instart
    // 右侧范围：(rootindex + 1, inend); 为右子树；

    // 3. 递归 寻找下一个root , root = pre[prestart];
}
