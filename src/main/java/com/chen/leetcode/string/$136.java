package com.chen.leetcode.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 136. 只出现一次的数字
 * <p>
 * <p>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 */
public class $136 {

    public int singleNumber(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (Integer i : nums) {
            Integer count = map.get(i);
            count = count == null ? 1 : ++count;
            map.put(i, count);
        }
        for (Integer i : map.keySet()) {
            Integer count = map.get(i);
            if (count == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 复杂度分析
     *
     * 时间复杂度：O(n)O(n)，其中 nn 是数组长度。只需要对数组遍历一次。
     *
     * 空间复杂度：O(1)O(1)。
     *
     * 异或运算
     *
     * */
    public int singleNumber2(int[] nums) {
        int res = 0;
        for (Integer i : nums) {
            res = res ^ i;
        }
        return res;
    }
}
