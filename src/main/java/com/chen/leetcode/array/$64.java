package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $64 {

    /**
     * 方法一：动态规划
     * 由于路径的方向只能是向下或向右，因此网格的第一行的每个元素只能从左上角元素开始向右移动到达，网格的第一列的每个元素只能从左上角元素开始向下移动到达，此时的路径是唯一的，因此每个元素对应的最小路径和即为对应的路径上的数字总和。
     * <p>
     * 对于不在第一行和第一列的元素，可以从其上方相邻元素向下移动一步到达，或者从其左方相邻元素向右移动一步到达，元素对应的最小路径和等于其上方相邻元素与其左方相邻元素两者对应的最小路径和中的最小值加上当前元素的值。由于每个元素对应的最小路径和与其相邻元素对应的最小路径和有关，因此可以使用动态规划求解。
     * <p>
     * 创建二维数组 \textit{dp}dp，与原始网格的大小相同，\textit{dp}[i][j]dp[i][j] 表示从左上角出发到 (i,j)(i,j) 位置的最小路径和。显然，\textit{dp}[0][0]=\textit{grid}[0][0]dp[0][0]=grid[0][0]。对于 \textit{dp}dp 中的其余元素，通过以下状态转移方程计算元素值。
     * <p>
     * 当 i>0i>0 且 j=0j=0 时，\textit{dp}[i][0]=\textit{dp}[i-1][0]+\textit{grid}[i][0]dp[i][0]=dp[i−1][0]+grid[i][0]。
     * <p>
     * 当 i=0i=0 且 j>0j>0 时，\textit{dp}[0][j]=\textit{dp}[0][j-1]+\textit{grid}[0][j]dp[0][j]=dp[0][j−1]+grid[0][j]。
     * <p>
     * 当 i>0i>0 且 j>0j>0 时，\textit{dp}[i][j]=\min(\textit{dp}[i-1][j],\textit{dp}[i][j-1])+\textit{grid}[i][j]dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
     * <p>
     * 最后得到 \textit{dp}[m-1][n-1]dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    @Test
    public void test() {
        int[][] grid = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println(minPathSum(grid));
    }
}
