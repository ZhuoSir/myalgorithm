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
     * 算法解析：
     * 初始化： 左边界 i = 0i=0 ，右边界 j = len(nums) - 1j=len(nums)−1 ；代表闭区间 [i, j][i,j] 。
     * 循环二分： 当 i \leq ji≤j 时循环 （即当闭区间 [i, j][i,j] 为空时跳出） ；
     * 计算中点 m = (i + j) // 2m=(i+j)//2 ，其中 "////" 为向下取整除法；
     * 若 nums[m] = mnums[m]=m ，则 “右子数组的首位元素” 一定在闭区间 [m + 1, j][m+1,j] 中，因此执行 i = m + 1i=m+1；
     * 若 nums[m] \ne mnums[m]
     * 
     * ​
     *  =m ，则 “左子数组的末位元素” 一定在闭区间 [i, m - 1][i,m−1] 中，因此执行 j = m - 1j=m−1；
     * 返回值： 跳出时，变量 ii 和 jj 分别指向 “右子数组的首位元素” 和 “左子数组的末位元素” 。因此返回 ii 即可。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/solution/mian-shi-ti-53-ii-0n-1zhong-que-shi-de-shu-zi-er-f/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 时间复杂度 O(log N)O(logN)： 二分法为对数级别复杂度。
     * 空间复杂度 O(1)O(1)： 几个变量使用常数大小的额外空间。
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
