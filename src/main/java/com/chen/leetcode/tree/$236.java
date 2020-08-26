package com.chen.leetcode.tree;

import org.junit.Test;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $236 {

    /**
     *
     * 解题思路：
     * 祖先的定义： 若节点 pp 在节点 rootroot 的左（右）子树中，或 p = rootp=root ，则称 rootroot 是 pp 的祖先。
     *
     *
     *
     * 最近公共祖先的定义： 设节点 rootroot 为节点 p, qp,q 的某公共祖先，若其左子节点 root.leftroot.left 和右子节点 root.rightroot.right 都不是 p,qp,q 的公共祖先，则称 rootroot 是 “最近的公共祖先” 。
     *
     * 根据以上定义，若 rootroot 是 p, qp,q 的 最近公共祖先 ，则只可能为以下情况之一：
     *
     * pp 和 qq 在 rootroot 的子树中，且分列 rootroot 的 异侧（即分别在左、右子树中）；
     * p = rootp=root ，且 qq 在 rootroot 的左或右子树中；
     * q = rootq=root ，且 pp 在 rootroot 的左或右子树中；
     *
     *
     * 考虑通过递归对二叉树进行后序遍历，当遇到节点 pp 或 qq 时返回。从底至顶回溯，当节点 p, qp,q 在节点 rootroot 的异侧时，节点 rootroot 即为最近公共祖先，则向上返回 rootroot 。
     *
     * 递归解析：
     * 终止条件：
     * 当越过叶节点，则直接返回 nullnull ；
     * 当 rootroot 等于 p, qp,q ，则直接返回 rootroot ；
     * 递推工作：
     * 开启递归左子节点，返回值记为 leftleft ；
     * 开启递归右子节点，返回值记为 rightright ；
     * 返回值： 根据 leftleft 和 rightright ，可展开为四种情况；
     * 当 leftleft 和 rightright 同时为空 ：说明 rootroot 的左 / 右子树中都不包含 p,qp,q ，返回 nullnull ；
     * 当 leftleft 和 rightright 同时不为空 ：说明 p, qp,q 分列在 rootroot 的 异侧 （分别在 左 / 右子树），因此 rootroot 为最近公共祖先，返回 rootroot ；
     * 当 leftleft 为空 ，rightright 不为空 ：p,qp,q 都不在 rootroot 的左子树中，直接返回 rightright 。具体可分为两种情况：
     * p,qp,q 其中一个在 rootroot 的 右子树 中，此时 rightright 指向 pp（假设为 pp ）；
     * p,qp,q 两节点都在 rootroot 的 右子树 中，此时的 rightright 指向 最近公共祖先节点 ；
     * 当 leftleft 不为空 ， rightright 为空 ：与情况 3. 同理；
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 复杂度分析：
     * 时间复杂度 O(N)O(N) ： 其中 NN 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N)O(N) ： 最差情况下，递归深度达到 NN ，系统使用 O(N)O(N) 大小的额外空间。
     *
     * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null && right == null) return null;
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }


    @Test
    public void test() {

        TreeNode root = new TreeNode(9);

        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        TreeNode node6 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);


        TreeNode node10 = new TreeNode(10);



        root.left = node3;

        node3.left = node5;
        node3.right = node1;

        node5.left = node6;
        node5.right = node2;

        node2.left = node7;
        node2.right = node4;

        node1.left = node0;
        node1.right = node8;

        System.out.println(lowestCommonAncestor(root, node6, node2));
    }

}
