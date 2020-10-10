package com.chen.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $300 {

    /**
     * 解法一：动态规划
     * 解题思路：
     * 状态定义：
     * <p>
     * dp[i]dp[i] 的值代表 nums 前 ii 个数字的最长子序列长度。
     * 转移方程： 设 j∈[0,i)j∈[0,i)，考虑每轮计算新 dp[i]dp[i] 时，遍历 [0,i)[0,i) 列表区间，做以下判断：
     * <p>
     * 当 nums[i] > nums[j]nums[i]>nums[j] 时： nums[i]nums[i] 可以接在 nums[j]nums[j] 之后（此题要求严格递增），此情况下最长上升子序列长度为 dp[j] + 1dp[j]+1 ；
     * 当 nums[i] <= nums[j]nums[i]<=nums[j] 时： nums[i]nums[i] 无法接在 nums[j]nums[j] 之后，此情况上升子序列不成立，跳过。
     * 上述所有 1. 情况 下计算出的 dp[j] + 1dp[j]+1 的最大值，为直到 ii 的最长上升子序列长度（即 dp[i]dp[i] ）。实现方式为遍历 jj 时，每轮执行 dp[i] = max(dp[i], dp[j] + 1)dp[i]=max(dp[i],dp[j]+1)。
     * 转移方程： dp[i] = max(dp[i], dp[j] + 1) for j in [0, i)。
     * 初始状态：
     * <p>
     * dp[i]dp[i] 所有元素置 11，含义是每个元素都至少可以单独成为子序列，此时长度都为 11。
     * 返回值：
     * <p>
     * 返回 dpdp 列表最大值，即可得到全局最长上升子序列长度。
     * 复杂度分析：
     * 时间复杂度 O(N^2)O(N
     * 2
     * ) ： 遍历计算 dpdp 列表需 O(N)O(N)，计算每个 dp[i]dp[i] 需 O(N)O(N)。
     * 空间复杂度 O(N)O(N) ： dpdp 列表占用线性大小额外空间。
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6};
//        int[] nums = new int[]{-2, -1};
//        int[] nums = new int[]{4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS(nums));
    }
}
