package com.chen.leetcode.bp;

import org.junit.Test;

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

    /**
     * 本题需求出让组成的完全平方数的个数最少。一般求最值得问题，都可以朝着动态规划的方向去想。不出意外，本题使用动态规划的方法。首先需要确定dp数组含义，定义以为dp数组：
     *
     * dp[i]:表示第i个数最少需要dp[i]个完全平方数组成。
     *
     * 接下来是base case，由于n是正整数，所以n最小为1，当i=1的时候，就是由1组成的，dp[1] = 1.
     *
     * 假设已经知道dp[1] 到 dp[i-1],那如何求dp[i]呢？这里类似于找零钱的思路，对于本题来说，存在两种情况：
     *
     * 如果当前i是一个完全平方数，那么dp[i] = 1.
     *
     * 如果当前i不是一个完全平方数，如何求dp[i]。例如如何求dp[5],我们可以将5拆分为：1+4 ，2+ 3，（dp[1]+dp[4] , dp[2]+dp[3]），那dp[5]就取这两种情况的最小的即可。dp[5] = Math.min(dp[1]+dp[4],dp[2]+dp[3])
     *
     * 作者：Geralt_U
     * 链接：https://leetcode-cn.com/problems/perfect-squares/solution/279-wan-quan-ping-fang-shu-by-ming-zhi-shan-you-m9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public int numSquares(int n) {
        if(n == 1) return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for(int i = 2; i<=n; i++){
            if(Math.sqrt(i)%1 == 0){
                dp[i] = 1;
            }else{
                int min = Integer.MAX_VALUE;
                for(int j = 1; j*j<i; j++) {
                    min = Math.min(min,dp[i-j*j]+1);
                }
                dp[i] = min;
            }
        }
        return dp[n];

    }

    @Test
    public void test() {
        System.out.println(numSquares(13));
    }
}
