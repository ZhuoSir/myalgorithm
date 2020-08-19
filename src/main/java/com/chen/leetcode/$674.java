package com.chen.leetcode;

import org.junit.Test;

/**
 * 674. 最长连续递增序列
 *
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 *
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $674 {

    /**
     * 思路
     * 标签：遍历
     * 过程：
     * count 为当前元素峰值，ans为最大峰值
     * 初始化 count = 1
     * 从 0 位置开始遍历，遍历时根据前后元素状态判断是否递增，递增则 count++，递减则 count=1
     * 如果 count>ans，则更新 ans
     * 直到循环结束
     * 时间复杂度：O(N)O(N)
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/solution/hua-jie-suan-fa-674-zui-chang-lian-xu-di-zeng-xu-l/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public int findLengthOfLCIS(int[] nums) {

        if (nums.length <= 1)
            return nums.length;

        int ans = 1;
        int count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i+1] > nums[i]) {
                count++;
            } else {
                count = 1;
            }

            ans = count > ans ? count : ans;
        }

        return ans;
    }


    @Test
    public void test1() {

        int[] nums1 = new int[5];
        nums1[0] = 1;
        nums1[1] = 3;
        nums1[2] = 5;
        nums1[3] = 4;
        nums1[4] = 7;


        int[] nums2 = new int[5];
        nums2[0] = 2;
        nums2[1] = 2;
        nums2[2] = 2;
        nums2[3] = 2;
        nums2[4] = 2;

        System.out.println(findLengthOfLCIS(nums2));

    }

}
