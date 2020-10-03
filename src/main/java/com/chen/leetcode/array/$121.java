package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $121 {

    /**
     * 1 / 1853
     * <p>
     * 121. 买卖股票的最佳时机
     * 力扣官方题解
     * 发布于 2020-03-07
     * 92.5k
     * 📺 视频题解
     * <p>
     * 📖 文字题解
     * 解决方案
     * 我们需要找出给定数组中两个数字之间的最大差值（即，最大利润）。此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）。
     * <p>
     * 形式上，对于每组 ii 和 jj（其中 j > ij>i）我们需要找出 \max(prices[j] - prices[i])max(prices[j]−prices[i])。
     * <p>
     * 方法一：暴力法
     * <p>
     * public class Solution {
     * public int maxProfit(int prices[]) {
     * int maxprofit = 0;
     * for (int i = 0; i < prices.length - 1; i++) {
     * for (int j = i + 1; j < prices.length; j++) {
     * int profit = prices[j] - prices[i];
     * if (profit > maxprofit)
     * maxprofit = profit;
     * }
     * }
     * return maxprofit;
     * }
     * }
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n^2)O(n
     * 2
     * )。循环运行 \dfrac{n (n-1)}{2}
     * 2
     * n(n−1)
     * ​
     * 次。
     * 空间复杂度：O(1)O(1)。只使用了常数个变量。
     * 方法二：一次遍历
     * 算法
     * <p>
     * 假设给定的数组为：[7, 1, 5, 3, 6, 4]
     * <p>
     * 如果我们在图表上绘制给定数组中的数字，我们将会得到：
     * <p>
     * Profit Graph
     * <p>
     * 我们来假设自己来购买股票。随着时间的推移，每天我们都可以选择出售股票与否。那么，假设在第 i 天，如果我们要在今天卖股票，那么我们能赚多少钱呢？
     * <p>
     * 显然，如果我们真的在买卖股票，我们肯定会想：如果我是在历史最低点买的股票就好了！太好了，在题目中，我们只要用一个变量记录一个历史最低价格 minprice，我们就可以假设自己的股票是在那天买的。那么我们在第 i 天卖出股票能得到的利润就是 prices[i] - minprice。
     * <p>
     * 因此，我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？当考虑完所有天数之时，我们就得到了最好的答案。
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)，只需要遍历一次。
     * 空间复杂度：O(1)O(1)，只使用了常数个变量。
     */
    public int maxProfit(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxProfit) {
                maxProfit = prices[i] - minprice;
            }
        }
        return maxProfit;
    }


    @Test
    public void test() {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }
}
