package com.chen.leetcode.dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 *
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 *  
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 *
 *  
 *
 * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-area-of-island
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $695 {

    public int maxAreaOfIsland(int[][] grid) {

        List<int[]> coordinates = new ArrayList<>();
        int ylength = grid.length;
        int xlength = grid[0].length;
        for (int i = 0; i < ylength; i++) {
            for (int j = 0; j < xlength; j++) {
                if (grid[i][j] == 1) {
                    coordinates.add(new int[] {i, j});
                    grid[i][j] = -1;
                }
            }
        }

        if (coordinates.size() == 0) return 0;

        int[] xdirection = new int[] {-1, 0, 1, 0};
        int[] ydirection = new int[] {0, -1, 0, 1};

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
                        if (xTemp >= 0 && xTemp < xlength && yTemp >=0 && yTemp < ylength) {
                            if (grid[yTemp][xTemp] != 0) {
                                queue.offer(new int[] {yTemp, xTemp});
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
        int[][] a = new int[][] {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };

        int[][] b = new int[][] {
                {0,0,0,0,0,0,0,0}
        };

        System.out.println(maxAreaOfIsland(b));
    }
}
