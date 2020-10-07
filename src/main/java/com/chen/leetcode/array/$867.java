package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定一个矩阵 A， 返回 A 的转置矩阵。
 * <p>
 * 矩阵的转置是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 * 示例 2：
 * <p>
 * 输入：[[1,2,3],[4,5,6]]
 * 输出：[[1,4],[2,5],[3,6]]
 *  
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/transpose-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $867 {

    public int[][] transpose(int[][] matrix) {

        if (matrix == null) return null;

        int w = matrix.length;
        int d = matrix[0].length;

        int[][] res = new int[d][w];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < d; j++) {
                res[j][i] = matrix[i][j];
            }
        }

        return res;
    }


    @Test
    public void test() {
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6}
//                {7, 8, 9},
        };

        int[][] res = transpose(matrix);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println("");
        }
    }
}


