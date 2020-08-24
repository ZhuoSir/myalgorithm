package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 * <p>
 * 注意：1 ≤ k ≤ n ≤ 109。
 * <p>
 * 示例 :
 * <p>
 * 输入:
 * n: 13   k: 2
 * <p>
 * 输出:
 * 10
 * <p>
 * 解释:
 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $440 {


    /**
     * 解题：
     * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/solution/ben-ti-shi-shang-zui-wan-zheng-ju-ti-de-shou-mo-sh/
     *
     *
     * */
    public int findKthNumber(int n, int k) {

        int cur  = 1;
        int prex = 1;

        while (cur < k) {
            int tmp = count(prex, n);
            if (tmp + cur > k) {
                prex *= 10;
                cur++;
            } else {
                prex++;
                cur += tmp;
            }
        }

        return prex;
    }

    private int count(int prex, int n) {

        long cur  = prex;
        long next = cur + 1;

        int count = 0;
        while (cur <= n) {

            count += Math.min(n + 1, next) - cur;

            cur *= 10;
            next *= 10;
        }
        return count;
    }

    @Test
    public void test() {

        System.out.println(findKthNumber(681692778, 351251360));

    }
}
