package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $59 {

    public int[][] generateMatrix(int n) {

        int[][] res = new int[n][n];

        int[][] visited = new int[res.length][res[0].length];
        int allCount = 1;

        int[] xdirect = {1, 0, -1, 0};
        int[] ydirect = {0, 1, 0, -1};
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < 4; i++) {
            while ((startX >= 0 && startX < n
                    && startY >= 0 && startY < n)
                    && visited[startY][startX] == 0) {
                if (startX == n) break;
                if (startY == n) break;
                res[startY][startX] = allCount;
                allCount++;
                visited[startY][startX] = 1;
                startX += xdirect[i];
                startY += ydirect[i];
            }

            if (allCount == n * n + 1) {
                break;
            }

            startX -= xdirect[i];
            startY -= ydirect[i];
            visited[startY][startX] = 0;
            allCount--;

            if (i == 3) {
                i = -1;
            }
        }
        return res;
    }


    public int[][] generateMatrix2(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }

    @Test
    public void test() {
        int[][] nums = generateMatrix2(5);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                System.out.print(nums[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
