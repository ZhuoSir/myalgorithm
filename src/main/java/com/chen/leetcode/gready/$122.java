package com.chen.leetcode.gready;

import org.junit.Test;

/**
 *
 * 122. 买卖股票的最佳时机 II
 *
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 *
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i] <= 10 ^ 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $122 {

    /**
     * 解题思路：
     * 股票买卖策略：
     *
     * 单独交易日： 设今天价格 p_1p
     * 1
     * ​
     *  、明天价格 p_2p
     * 2
     * ​
     *  ，则今天买入、明天卖出可赚取金额 p_2 - p_1p
     * 2
     * ​
     *  −p
     * 1
     * ​
     *   （负值代表亏损）。
     * 连续上涨交易日： 设此上涨交易日股票价格分别为 p_1, p_2, ... , p_np
     * 1
     * ​
     *  ,p
     * 2
     * ​
     *  ,...,p
     * n
     * ​
     *  ，则第一天买最后一天卖收益最大，即 p_n - p_1p
     * n
     * ​
     *  −p
     * 1
     * ​
     *  ；等价于每天都买卖，即 p_n - p_1=(p_2 - p_1)+(p_3 - p_2)+...+(p_n - p_{n-1})p
     * n
     * ​
     *  −p
     * 1
     * ​
     *  =(p
     * 2
     * ​
     *  −p
     * 1
     * ​
     *  )+(p
     * 3
     * ​
     *  −p
     * 2
     * ​
     *  )+...+(p
     * n
     * ​
     *  −p
     * n−1
     * ​
     *  )。
     * 连续下降交易日： 则不买卖收益最大，即不会亏钱。
     * 算法流程：
     *
     * 遍历整个股票交易日价格列表 price，策略是所有上涨交易日都买卖（赚到所有利润），所有下降交易日都不买卖（永不亏钱）。
     * 设 tmp 为第 i-1 日买入与第 i 日卖出赚取的利润，即 tmp = prices[i] - prices[i - 1] ；
     * 当该天利润为正 tmp > 0，则将利润加入总利润 profit；当利润为 00 或为负，则直接跳过；
     * 遍历完成后，返回总利润 profit。
     * 复杂度分析：
     *
     * 时间复杂度 O(N)O(N) ： 只需遍历一次price；
     * 空间复杂度 O(1)O(1) ： 变量使用常数额外空间。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public int maxProfit(int[] prices) {

        int profit = 0;
        int tmp;
        for (int i = 1; i <= prices.length - 1; i++) {
            tmp = prices[i] - prices[i-1];
            if (tmp > 0) {
                profit += tmp;
            }
        }

        return profit;
    }

    @Test
    public void test() {

        int[] prices = {7,6,4,3,1};
        System.out.println(maxProfit(prices));

    }
}