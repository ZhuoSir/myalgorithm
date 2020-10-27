package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * <p>
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * <p>
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * <p>
 * 如果不含有 “山脉” 则返回 0。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * <p>
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-mountain-in-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $845 {

    public int longestMountain2(int[] A) {
        int max = 0;
        int i = 1;
        while (i < A.length) {
            int inc = 0, dec = 0;
            while (i < A.length && A[i - 1] < A[i]) {
                i++;
                inc++;
            }
            while (i < A.length && A[i - 1] > A[i]) {
                i++;
                dec++;
            }
            if (inc > 0 && dec > 0) {
                max = Math.max(max, dec + inc + 1);
            }
            while (i < A.length && A[i - 1] == A[i]) {
                i++;
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] a = new int[]{2, 1, 4, 7, 3, 2, 5, 9, 11, 14, 17, 20, 13, 11, 14};
//        int[] a = new int[]{2, 2, 2};
//        int[] a = new int[]{0, 1, 2, 3, 4, 5, 4, 3, 2, 1, 0};
//        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int r = longestMountain2(a);
        System.out.println(r);
    }
}
