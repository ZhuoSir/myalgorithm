package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * <p>
 *  
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $120 {

    /**
     * f[i][j]=
     * ⎩
     * ⎪
     * ⎪
     * ⎨
     * ⎪
     * ⎪
     * ⎧
     * ​
     * <p>
     * f[i−1][0]+c[i][0],
     * f[i−1][i−1]+c[i][i],
     * min(f[i−1][j−1],f[i−1][j])+c[i][j],
     * ​
     * <p>
     * j=0
     * j=i
     * otherwise
     * ​
     * <p>
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n^2)O(n
     * 2
     * )，其中 nn 是三角形的行数。
     * <p>
     * 空间复杂度：O(n^2)O(n
     * 2
     * )。我们需要一个 n*nn∗n 的二维数组存放所有的状态。
     * ​
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] bp = new int[n][n];
        for (int i = 0; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) {
                    bp[i][j] = triangle.get(i).get(j);
                } else if (j == 0) {
                    bp[i][j] = triangle.get(i).get(j) + bp[i - 1][j];
                } else if (j == i) {
                    bp[i][j] = triangle.get(i).get(j) + bp[i - 1][j - 1];
                } else {
                    bp[i][j] = Math.min(bp[i - 1][j], bp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, bp[n - 1][i]);
        }
        return min;
    }

    /**
     * 上一个版本的优化版；
     * <p>
     * 空间复杂度为O(2n)；
     * 时间复杂度为O(n^2);
     */
    public int minimumTotal2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] bp1 = new int[n];
        int[] bp2 = new int[n];
        int[] last, curr = null;
        for (int i = 0; i < triangle.size(); i++) {
            if (i % 2 == 0) {
                curr = bp1;
                last = bp2;
            } else {
                curr = bp2;
                last = bp1;
            }
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (i == 0 && j == 0) {
                    curr[j] = triangle.get(i).get(j);
                } else if (j == 0) {
                    curr[j] = triangle.get(i).get(j) + last[j];
                } else if (j == i) {
                    curr[j] = triangle.get(i).get(j) + last[j - 1];
                } else {
                    curr[j] = Math.min(last[j], last[j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, curr[i]);
        }
        return min;
    }

    /**
     * 我们从 ii 到 00 递减地枚举 jj，这样我们只需要一个长度为 nn 的一维数组 ff，就可以完成状态转移。
     * <p>
     * 为什么只有在递减地枚举 jj 时，才能省去一个一维数组？当我们在计算位置 (i, j)(i,j) 时，f[j+1]f[j+1] 到 f[i]f[i] 已经是第 ii 行的值，而 f[0]f[0] 到 f[j]f[j] 仍然是第 i-1i−1 行的值。此时我们直接通过
     * <p>
     * f[j] = \min(f[j-1], f[j]) + c[i][j]
     * f[j]=min(f[j−1],f[j])+c[i][j]
     * <p>
     * 进行转移，恰好就是在 (i-1, j-1)(i−1,j−1) 和 (i-1, j)(i−1,j) 中进行选择。但如果我们递增地枚举 jj，那么在计算位置 (i, j)(i,j) 时，f[0]f[0] 到 f[j-1]f[j−1] 已经是第 ii 行的值。如果我们仍然使用上述状态转移方程，那么是在 (i, j-1)(i,j−1) 和 (i-1, j)(i−1,j) 中进行选择，就产生了错误。
     * <p>
     * 这样虽然空间复杂度仍然为 O(n)O(n)，但我们只使用了 nn 的空间存储状态，减少了一半的空间消耗。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/triangle/solution/san-jiao-xing-zui-xiao-lu-jing-he-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int minimumTotal3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] bp = new int[n];
        for (int i = 0; i < triangle.size(); i++) {
            for (int j = triangle.get(i).size() - 1; j >= 0; j--) {
                if (i == 0 && j == 0) {
                    bp[j] = triangle.get(i).get(j);
                } else if (i == j) {
                    bp[j] = triangle.get(i).get(j) + bp[j - 1];
                } else if (j == 0) {
                    bp[j] = triangle.get(i).get(j) + bp[j];
                } else {
                    bp[j] = Math.min(bp[j - 1], bp[j]) + triangle.get(i).get(j);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            min = Math.min(min, bp[i]);
        }

        return min;
    }

    /**
     * 终极版本：递归实现bp；
     * <p>
     * 空间复杂度为O(2n)；
     * 时间复杂度为O(n^2);
     */
    public int minimumTotal4(List<List<Integer>> triangle) {
        return dfs(0, triangle, new int[1]);
    }

    private int dfs(int level, List<List<Integer>> triangle, int[] bp) {

        if (level >= triangle.size()) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < bp.length; i++) {
                min = Math.min(min, bp[i]);
            }
            return min;
        }

        List<Integer> levelList = triangle.get(level);
        int size = levelList.size();
        int[] np = new int[size];
        np[0] = bp[0] + levelList.get(0);
        np[size - 1] = levelList.get(size - 1) + bp[bp.length - 1];
        for (int i = 1; i < size - 1; i++) {
            np[i] = levelList.get(i) + Math.min(bp[i], bp[i - 1]);
        }
        return dfs(level + 1, triangle, np);
    }


    @Test
    public void test() {

        List<Integer> l0 = Arrays.asList(2);
        List<Integer> l1 = Arrays.asList(3, 4);
        List<Integer> l2 = Arrays.asList(6, 5, 7);
        List<Integer> l3 = Arrays.asList(4, 1, 8, 3);

        List<List<Integer>> lists = Arrays.asList(l0, l1, l2, l3);
        System.out.println(minimumTotal4(lists));
    }
}
