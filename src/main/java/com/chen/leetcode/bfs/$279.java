package com.chen.leetcode.bfs;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 279. 完全平方数
 *
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $279 {

    private class Node {
        int val;
        int step;

        public Node(int val, int step) {
            this.val = val;
            this.step = step;
        }
    }

    /**
     * 解题思路：
     * 当每一次都可以判断出多种情况，有多次的时候就适合用BFS-广度优先遍历
     * 使用BFS应注意：
     * 队列：用来存储每一轮遍历得到的节点；
     * 标记：对于遍历过的节点，应该将它标记，防止重复遍历。
     *
     * 我们将它第一个平方数可能出现的情况做分析 只要 i * i < n 就行
     * 再在此基础上进行二次可能出现的平方数分析
     * 注意：为了节省遍历的时间，曾经（ n - 以前出现的平方数） 这个值出现过，则在此出现这样的数时直接忽略。
     *
     *
     *
     * 作者：EiletXie
     * 链接：https://leetcode-cn.com/problems/perfect-squares/solution/yan-du-you-xian-sou-suo-java-by-eiletxie/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public int numSquares(int n) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(n, 1));

        HashMap<Integer, Boolean> map = new HashMap<>();

        while (!queue.isEmpty()) {

            Node node = queue.poll();
            int val = node.val;
            int step = node.step;

            for (int i = 1 ;; i++) {
                int nextVal = val - i * i;
                if (nextVal < 0)
                    break;

                if (nextVal == 0)
                    return step;

                Boolean isExisted = map.get(nextVal);
                if (isExisted == null || !isExisted) {
                    queue.add(new Node(nextVal, step + 1));
                    map.put(nextVal, true);
                }
            }
        }

        return -1;
    }

    @Test
    public void test() {
        System.out.println(numSquares(13));
    }
}
