package com.chen.leetcode.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $167 {

    /**
     * 哈希求值
     */
    public int[] twoSum2(int[] numbers, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int t = target - numbers[i];
            if (map.containsKey(t)) {
                res[0] = map.get(t);
                res[1] = i + 1;
                break;
            } else {
                map.put(numbers[i], i + 1);
            }
        }
        return res;
    }

    /**
     * 双指针求值o(n)
     * <p>
     * https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/yi-zhang-tu-gao-su-ni-on-de-shuang-zhi-zhen-jie-fa/
     */
    public int[] twoSum3(int[] numbers, int target) {
        int x = numbers.length - 1;
        int y = 0;
        int[] res = new int[2];
        while (y < x) {
            int sum = numbers[y] + numbers[x];
            if (sum > target) {
                x--;
            } else if (sum < target) {
                y++;
            } else {
                res[0] = y + 1;
                res[1] = x + 1;
                break;
            }
        }
        return res;
    }

    /**
     * 方法一：二分查找
     * 在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。利用数组的有序性质，可以通过二分查找的方法寻找第二个数。为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n \log n)O(nlogn)，其中 nn 是数组的长度。需要遍历数组一次确定第一个数，时间复杂度是 O(n)O(n)，寻找第二个数使用二分查找，时间复杂度是 O(\log n)O(logn)，因此总时间复杂度是 O(n \log n)O(nlogn)。
     * <p>
     * 空间复杂度：O(1)O(1)。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length; i++) {
            int high = numbers.length - 1;
            int low = i + 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 7, 11, 14};
        int target = 9;
        int[] res = twoSum(nums, target);
        System.out.println(res[0] + "-" + res[1]);
    }
}
