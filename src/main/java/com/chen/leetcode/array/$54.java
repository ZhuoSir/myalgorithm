package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $54 {

    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0) return new ArrayList<>();


        List<Integer> res = new ArrayList<>();
        int[][] visited = new int[matrix.length][matrix[0].length];
        int m = matrix[0].length;
        int n = matrix.length;
        int allCount = 0;

        int[] xdirect = {1, 0, -1, 0};
        int[] ydirect = {0, 1, 0, -1};
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < 4; i++) {
            while ((startX >= 0 && startX < m
                    && startY >= 0 && startY < n)
                    && visited[startY][startX] == 0) {
                if (startX == m) break;
                if (startY == n) break;
                res.add(matrix[startY][startX]);
                allCount++;
                visited[startY][startX] = 1;
                startX += xdirect[i];
                startY += ydirect[i];
            }

            if (allCount == m * n) {
                break;
            }

            startX -= xdirect[i];
            startY -= ydirect[i];
            res.remove(res.size() - 1);
            visited[startY][startX] = 0;
            allCount--;

            if (i == 3) {
                i = -1;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 10, 11, 12},
                {13, 14, 15, 16, 17, 18},
                {19, 20, 21, 22, 23, 24},

        };
        System.out.println(spiralOrder(matrix));
    }
}
