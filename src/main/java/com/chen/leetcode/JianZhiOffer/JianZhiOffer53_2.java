package com.chen.leetcode.JianZhiOffer;

import org.junit.Test;

/**
 *
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * */
public class JianZhiOffer53_2 {

    /**
     * 暴力解法：循环比较，从0开始，每个元素是否等于i；
     *
     * 时间复杂度O(n);
     *
     * */
    public int missingNumber(int[] nums) {

        int ret = 0;
        boolean finded = false;

        for (int i = 0; i < nums.length; i++) {

            if (i != nums[i]) {
                ret = i;
                finded = true;
                break;
            }
        }

        if (!finded)
            ret = nums[nums.length-1] + 1;

        return ret;
    }

    /**
     * 本题也属于简单的二分查找，题意明确了所有数是递增的，且所有数的取值范围均在[0,n-1]上并且是唯一的，因此可以发现这样一个规律：
     * (1)只要查询过程中nums[i] == i，那么缺失的值一定在i的右侧；
     * (2)如果查询过程中nums[i] > i，那么缺失的值一定在左侧
     * 所以最后只要返回left即为结果(具体流程见代码)。
     *
     *
     * 时间复杂度：O(logN)
     * 空间复杂度：O(1)
     *
     * 作者：yi-wen-statistics
     * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/solution/ha-xi-biao-er-fen-cha-zhao-by-yi-wen-statistics/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public int missingNumber2(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] == m) i = m + 1;
            else j = m - 1;
        }
        return i;
    }

    @Test
    public void test() {

        int[] nums = new int[9];
        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 2;
        nums[3] = 3;
        nums[4] = 4;
        nums[5] = 5;
        nums[6] = 6;
        nums[7] = 7;
        nums[8] = 9;


        int[] nums2 = new int[3];
        nums2[0] = 1;
        nums2[1] = 2;
//        nums2[2] = 3;



        int[] nums3 = new int[1];
        nums3[0] = 1;

        int ret = missingNumber2(nums);
        System.out.println(ret);

    }

}
