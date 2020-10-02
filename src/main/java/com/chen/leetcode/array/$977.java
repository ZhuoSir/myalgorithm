package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $977 {

    /**
     * 双指针办法；
     */
    public int[] sortedSquares(int[] A) {

        if (A == null) return null;

        int left = 0;
        int right = A.length - 1;
        int[] res = new int[A.length];
        int tail = A.length - 1;
        while (left <= right) {
            int leftV = A[left] * A[left];
            int rightV = A[right] * A[right];
            if (leftV < rightV) {
                res[tail] = rightV;
                right--;
            } else if (leftV >= rightV) {
                res[tail] = leftV;
                left++;
            }

            if (tail > 0) {
                tail--;
            } else {
                break;
            }
        }
        return res;
    }

    /**
     * 双指针 2
     *
     * */
    public int[] sortedSquares2(int[] A) {

        int left = 0, right = A.length - 1;
        int[] res = new int[A.length];
        int tail = A.length - 1;
        while (tail >= 0) {
            if (A[left] + A[right] > 0) {
                res[tail] = A[right] * A[right];
                right--;
            } else {
                res[tail] = A[left] * A[left];
                left++;
            }

            if (tail == 0) break;
            else tail--;
        }

        return res;
    }

    @Test
    public void test() {
//        int[] nums = new int[]{-7, -3, 2, 3, 11};
//        int[] nums = new int[]{-4, -1, 0, 3, 10};
        int[] nums = new int[]{-8, -2, 0, 1, 3, 5};

        int[] res = sortedSquares2(nums);

        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + ",");
        }
    }
}
