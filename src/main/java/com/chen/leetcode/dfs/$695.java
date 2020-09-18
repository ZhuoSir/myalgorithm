package com.chen.leetcode.dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 * <p>
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 * <p>
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * <p>
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,1,1,0,1,0,0,0,0,0,0,0,0],
 * [0,1,0,0,1,1,0,0,1,0,1,0,0],
 * [0,1,0,0,1,1,0,0,1,1,1,0,0],
 * [0,0,0,0,0,0,0,0,0,0,1,0,0],
 * [0,0,0,0,0,0,0,1,1,1,0,0,0],
 * [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 * <p>
 * 示例 2:
 * <p>
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 * <p>
 *  
 * <p>
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $695 {

    /**
     * 深度优先，递归实现
     *
     * 算法
     *
     * 我们想知道网格中每个连通形状的面积，然后取最大值。
     *
     * 如果我们在一个土地上，以 4 个方向探索与之相连的每一个土地（以及与这些土地相连的土地），那么探索过的土地总数将是该连通形状的面积。
     *
     * 为了确保每个土地访问不超过一次，我们每次经过一块土地时，将这块土地的值置为 0。这样我们就不会多次访问同一土地。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/max-area-of-island/solution/dao-yu-de-zui-da-mian-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(R * C)O(R∗C)。其中 RR 是给定网格中的行数，CC 是列数。我们访问每个网格最多一次。
     *
     * 空间复杂度：O(R * C)O(R∗C)，递归的深度最大可能是整个网格的大小，因此最大可能使用 O(R * C)O(R∗C) 的栈空间。
     *
     * */
    public int maxAreaOfIsland2(int[][] gird) {

        int ans = 0;
        for (int i = 0; i < gird.length; i++) {
            for (int j = 0; j < gird[0].length; j++) {
                if (gird[i][j] == 1)
                    ans = Math.max(dfs(gird, i, j), ans);
            }
        }

        return ans;
    }

    private int dfs(int[][] grid, int y, int x) {

        if (y < 0 || y >= grid.length || x < 0 || x >= grid[0].length || grid[y][x] == 0) return 0;
        grid[y][x] = 0;

        int res = 1;
        res += dfs(grid, y + 1, x);
        res += dfs(grid, y - 1, x);
        res += dfs(grid, y, x + 1);
        res += dfs(grid, y, x - 1);

        return res;
    }


    /**
     * 广度优先搜索；
     */
    public int maxAreaOfIsland(int[][] grid) {

        List<int[]> coordinates = new ArrayList<>();
        int ylength = grid.length;
        int xlength = grid[0].length;
        for (int i = 0; i < ylength; i++) {
            for (int j = 0; j < xlength; j++) {
                if (grid[i][j] == 1) {
                    coordinates.add(new int[]{i, j});
                    grid[i][j] = -1;
                }
            }
        }

        if (coordinates.size() == 0) return 0;

        int[] xdirection = new int[]{-1, 0, 1, 0};
        int[] ydirection = new int[]{0, -1, 0, 1};

        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < coordinates.size(); i++) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(coordinates.get(i));
            int area = 0;
            while (!queue.isEmpty()) {
                int[] coor = queue.poll();
                int y = coor[0];
                int x = coor[1];
                if (grid[y][x] == -1) {
                    grid[y][x] = 1;
                    area++;
                    for (int j = 0; j < 4; j++) {
                        int xTemp = x + xdirection[j];
                        int yTemp = y + ydirection[j];
                        if (xTemp >= 0 && xTemp < xlength && yTemp >= 0 && yTemp < ylength) {
                            if (grid[yTemp][xTemp] != 0) {
                                queue.offer(new int[]{yTemp, xTemp});
                            }
                        }
                    }
                }
            }

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    @Test
    public void test() {
        int[][] a = new int[][]{
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        int[][] b = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        System.out.println(maxAreaOfIsland2(a));
    }
}
