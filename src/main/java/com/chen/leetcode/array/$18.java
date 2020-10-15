package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $18 {

    /**
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n^3)O(n
     * 3
     * )，其中 nn 是数组的长度。排序的时间复杂度是 O(n \log n)O(nlogn)，枚举四元组的时间复杂度是 O(n^3)O(n
     * 3
     * )，因此总时间复杂度为 O(n^3+n\log n)=O(n^3)O(n
     * 3
     * +nlogn)=O(n
     * 3
     * )。
     * <p>
     * 空间复杂度：O(\log n)O(logn)，其中 nn 是数组的长度。空间复杂度主要取决于排序额外使用的空间。此外排序修改了输入数组 \textit{nums}nums，实际情况中不一定允许，因此也可以看成使用了一个额外的数组存储了数组 \textit{nums}nums 的副本并排序，空间复杂度为 O(n)O(n)
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/4sum/solution/si-shu-zhi-he-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null) return null;
        if (nums.length < 4) return new ArrayList();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            int num = nums[i];
            int t = target - num;
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) continue;
                int n = nums[j];
                int l = j + 1;
                int r = nums.length - 1;
                if (j == i + 1 || nums[j] != nums[j - 1]) {
                    while (l < r) {
                        int sum = n + nums[l] + nums[r];
                        if (sum == t) {
                            res.add(Arrays.asList(num, n, nums[l], nums[r]));
                            while (l + 1 < nums.length && nums[l] == nums[l + 1]) {
                                l++;
                            }
                            while (r - 1 > 0 && nums[r] == nums[r - 1]) {
                                r--;
                            }
                            l++;
                            r--;
                        } else if (sum < t) {
                            while (l + 1 < nums.length && nums[l] == nums[l + 1]) {
                                l++;
                            }
                            l++;
                        } else {
                            while (r - 1 > 0 && nums[r] == nums[r - 1]) {
                                r--;
                            }
                            r--;
                        }
                    }
                }
            }
        }
        return res;
    }


    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    @Test
    public void test() {
        int[] nums = new int[]{-1, -3, -2, 2, 3, -3, 0, -4};
        System.out.println(fourSum(nums, 4));
    }

}
