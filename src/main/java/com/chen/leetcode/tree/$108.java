package com.chen.leetcode.tree;

import org.junit.Test;

/**
 * 108. 将有序数组转换为二叉搜索树
 *
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $108 {


    /**
     *
     * 中序遍历，总是选择中间位置左边的数字作为根节点
     * 选择中间位置左边的数字作为根节点，则根节点的下标为 \textit{mid}=(\textit{left}+\textit{right})/2mid=(left+right)/2，此处的除法为整数除法。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)O(n)，其中 nn 是数组的长度。每个数字只访问一次。
     *
     * 空间复杂度：O(\log n)O(logn)，其中 nn 是数组的长度。空间复杂度不考虑返回值，因此空间复杂度主要取决于递归栈的深度，递归栈的深度是 O(\log n)O(logn)。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/solution/jiang-you-xu-shu-zu-zhuan-huan-wei-er-cha-sou-s-33/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        return sort(nums, 0, nums.length - 1);
    }

    public TreeNode sort(int[] nums, int from, int to) {

        int t = to - from;
        if (t == 0) {
            return new TreeNode(nums[to]);
        }

        if (t == 1) {
            TreeNode node = new TreeNode(nums[to]);
            node.left = new TreeNode(nums[from]);
            return node;
        }

        int mid = (from + to) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = sort(nums, from, mid - 1);
        node.right = sort(nums, mid + 1, to);

        return node;
    }

    @Test
    public void test() {

        int[] nums = {0,1,2,3,4,5};
        TreeNode res = sortedArrayToBST(nums);
        System.out.println(res);
    }
}
