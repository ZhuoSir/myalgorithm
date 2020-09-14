package com.chen.leetcode.tree;

import com.chen.leetcode.common.TreeNode;
import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

/**
 * 993. 二叉树的堂兄弟节点
 *
 * 在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
 *
 * 如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。
 *
 * 我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。
 *
 * 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3,4], x = 4, y = 3
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $993 {

    /**
     * 广度优先遍历，每一层去判断是否存在x 和 y,如果存在
     * 判断父类是不是同一个，不是就是表亲关系；
     *
     *
     * */
    public boolean isCousins(TreeNode root, int x, int y) {

        if (root == null)
            return false;

        Queue<Pair<TreeNode, TreeNode>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root.left, root));
        queue.add(new Pair<>(root.right, root));

        while (!queue.isEmpty()) {
            int n = queue.size();
            List<Integer> values = new ArrayList<>();
            Map<Integer, Pair<TreeNode, TreeNode>> nodes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                Pair<TreeNode, TreeNode> treeNode = queue.poll();
                if (treeNode != null) {
                    TreeNode child  = treeNode.getKey();
                    if (child != null) {
                        values.add(child.val);
                        nodes.put(child.val, treeNode);
                    }
                }
            }
            if (values.contains(x) && values.contains(y)) {
                if (nodes.get(x).getValue() != nodes.get(y).getValue()) {
                    return true;
                }
            }

            for (int i = 0; i < values.size(); i++) {
                TreeNode father = nodes.get(values.get(i)).getKey();
                if (father.left != null) {
                    queue.add(new Pair<>(father.left, father));
                }
                if (father.right != null) {
                    queue.add(new Pair<>(father.right, father));
                }
            }
        }

        return false;
    }

    HashMap<Integer, Integer> depth = new HashMap<>();
    HashMap<Integer, TreeNode> father = new HashMap<>();


    /**
     *
     * 思路
     *
     * 当且仅当一对节点深度相同而父节点不相同时，它们是堂兄弟节点。一种非常直接的方法就是通过某种方法求出每一个节点的深度与父节点。
     *
     * 算法
     *
     * 我们用深度优先搜索标记每一个节点，对于每一个节点 node，它的父亲为 par，深度为 d，我们将其记录到 Hashmap 中：parent[node.val] = par 且 depth[node.val] = d。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/cousins-in-binary-tree/solution/er-cha-shu-de-tang-xiong-di-jie-dian-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public boolean isCousins2(TreeNode root, int x, int y) {

        if (root == null)
            return false;

        dfs(root, null);

        return (depth.get(x).equals(depth.get(y))) && (father.get(x) != father.get(y));
    }

    private void dfs(TreeNode node, TreeNode fath) {

        if (node != null) {
            int fatherDepth = fath != null && depth.get(fath.val) != null ? depth.get(fath.val) : 0;
            depth.put(node.val, fatherDepth + 1);
            father.put(node.val, fath);
            dfs(node.left, node);
            dfs(node.right, node);
        }
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

        node2.right = node4;
        node3.right = node5;

        System.out.println(isCousins2(node1, 4, 5));
    }
}
