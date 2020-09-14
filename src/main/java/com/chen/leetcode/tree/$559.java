package com.chen.leetcode.tree;

import com.chen.leetcode.common.Node;
import javafx.util.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 559. N叉树的最大深度
 *
 * 给定一个 N 叉树，找到其最大深度。
 *
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 *
 * 例如，给定一个 3叉树 :
 *
 *  
 *
 *
 *
 *  
 *
 * 我们应返回其最大深度，3。
 *
 * 说明:
 *
 * 树的深度不会超过 1000。
 * 树的节点总不会超过 5000。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $559 {

    /**
     * 递归方法
     *
     * 复杂度分析
     *
     * 时间复杂度：每个节点遍历一次，所以时间复杂度是 O(N)O(N)，其中 NN 为节点数。
     *
     * 空间复杂度：最坏情况下, 树完全非平衡，
     * 例如 每个节点有且仅有一个孩子节点，递归调用会发生 NN 次（等于树的深度），所以存储调用栈需要 O(N)O(N)。
     * 但是在最好情况下（树完全平衡），树的高度为 \log(N)log(N)。
     * 所以在此情况下空间复杂度为 O(\log(N))O(log(N))。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/solution/ncha-shu-de-zui-da-shen-du-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public int maxDepth(Node root) {

        if (root == null)
            return 0;

        int max = 0;
        if (root.children != null) {
            for (Node child: root.children) {
                max = Math.max(max, maxDepth(child));
            }
        }

        return max + 1;
    }

    /**
     * 方法二: 迭代
     * 我们还可以在堆栈的帮助下将上面的递归转换为迭代。
     *
     * 思路是是使用深度优先搜索策略访问每个节点，同时更新每次访问时的最大深度。
     *
     * 所以可以从包含根节点的、对应深度为 11 的栈开始。
     * 然后继续迭代，从栈中弹出当前节点并将子节点压入栈中，每次都更新对应深度。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/solution/ncha-shu-de-zui-da-shen-du-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)O(N)。
     * 空间复杂度：O(N)O(N)。
     *
     *
     * */
    public int maxDepth2(Node root) {

        if (root == null)
            return 0;

        Stack<Pair<Node, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));

        int currentMax = 0;
        while (!stack.empty()) {
            Pair<Node, Integer> pair = stack.pop();
            currentMax = Math.max(currentMax, pair.getValue());
            if (pair.getKey().children != null) {
                for (Node child: pair.getKey().children) {
                    stack.push(new Pair<>(child, pair.getValue() + 1));
                }
            }
        }

        return currentMax;
    }


    @Test
    public void test() {

        Node root = new Node(1);

        Node node3 = new Node(3);
        Node node2 = new Node(2);
        Node node4 = new Node(4);

        List<Node> nodes = Arrays.asList(node3, node2, node4);

        root.children = nodes;

        Node node5 = new Node(5);
        Node node6 = new Node(6);

        List<Node> nodes1 = Arrays.asList(node5, node6);
        node3.children = nodes1;

        System.out.println(maxDepth2(root));
    }
}
