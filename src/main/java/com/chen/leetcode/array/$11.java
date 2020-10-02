package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * <p>
 *  
 * <p>
 * <p>
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $11 {

    /**
     * 双指针遍历求值；
     *
     * 左右两条指针，求一次面积；
     * 如果左边比右边的高；以左侧为基准，右指针左移；
     * 如果右边比左边的高；以右侧为基准，左指针右移；
     *
     * 移动过程中，如果下一条高度比这一条要低，那么跳过，因为宽度已经变小了，高度在变小，那么面积肯定变小；
     * 如果下一条高度变大，那么重新计算求最大面积；
     *
     * 时间复杂度 O(n); 因为双指针差不多要遍历全部元素；
     * 空间复杂度 O(1);
     * */
    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(area, maxArea);
            if (height[left] > height[right]) {
                int newRight = right - 1;
                if (newRight > 0) {
                    while (height[right] > height[newRight]) {
                        newRight--;
                    }
                    right = newRight;
                } else {
                    break;
                }
            } else {
                int newLeft = left + 1;
                if (newLeft < height.length - 1) {
                    while (height[left] > height[newLeft]) {
                        newLeft++;
                    }
                    left = newLeft;
                } else {
                    break;
                }
            }
        }

        return maxArea;
    }

    @Test
    public void test() {
//        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = new int[]{1, 1};
        System.out.println(maxArea(height));
    }
}
