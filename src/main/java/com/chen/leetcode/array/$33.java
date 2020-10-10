package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $33 {

    /**
     * 方法一：二分搜索
     * 思路和算法
     * <p>
     * 题目要求算法时间复杂度必须是 O(\log n)O(logn) 的级别，这提示我们可以使用二分搜索的方法。
     * <p>
     * 但是数组本身不是有序的，进行旋转后只保证了数组的局部是有序的，这还能进行二分搜索吗？答案是可以的。
     * <p>
     * 可以发现的是，我们将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。拿示例来看，我们从 6 这个位置分开以后数组变成了 [4, 5, 6] 和 [7, 0, 1, 2] 两个部分，其中左边 [4, 5, 6] 这个部分的数组是有序的，其他也是如此。
     * <p>
     * 这启示我们可以在常规二分搜索的时候查看当前 mid 为分割位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分搜索的上下界，因为我们能够根据有序的那部分判断出 target 在不在这个部分：
     * <p>
     * 如果 [l, mid - 1] 是有序数组，且 target 的大小满足 [\textit{nums}[l],\textit{nums}[mid])[nums[l],nums[mid])，则我们应该将搜索范围缩小至 [l, mid - 1]，否则在 [mid + 1, r] 中寻找。
     * 如果 [mid, r] 是有序数组，且 target 的大小满足 (\textit{nums}[mid+1],\textit{nums}[r]](nums[mid+1],nums[r]]，则我们应该将搜索范围缩小至 [mid + 1, r]，否则在 [l, mid - 1] 中寻找。
     * <p>
     * <p>
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度： O(\log n)O(logn)，其中 nn 为 \textit{nums}[]nums[] 数组的大小。整个算法时间复杂度即为二分搜索的时间复杂度 O(\log n)O(logn)。
     * <p>
     * 空间复杂度： O(1)O(1) 。我们只需要常数级别的空间存放变量。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/solution/sou-suo-xuan-zhuan-pai-xu-shu-zu-by-leetcode-solut/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        if (len == 0) return -1;
        if (len == 1) return nums[0] == target ? 0 : -1;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > nums[right]) {
                if (target <= nums[mid] && target >= nums[left]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] < nums[right]) {
                if (target <= nums[right] && target >= nums[mid + 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                break;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println(search(nums, 4));
        System.out.println(search(nums, 5));
        System.out.println(search(nums, 6));
        System.out.println(search(nums, 7));
        System.out.println(search(nums, 0));
        System.out.println(search(nums, 1));
        System.out.println(search(nums, 2));
        System.out.println(search(nums, 8));
    }
}
