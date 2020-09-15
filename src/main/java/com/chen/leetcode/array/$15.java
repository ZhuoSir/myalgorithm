package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $15 {

    /**
     *
     * 解题思路：
     * 暴力法搜索为 O(N^3)O(N
     * 3
     *  ) 时间复杂度，可通过双指针动态消去无效解来优化效率。
     * 双指针法铺垫： 先将给定 nums 排序，复杂度为 O(NlogN)O(NlogN)。
     * 双指针法思路： 固定 33 个指针中最左（最小）数字的指针 k，双指针 i，j 分设在数组索引 (k, len(nums))(k,len(nums)) 两端，通过双指针交替向中间移动，记录对于每个固定指针 k 的所有满足 nums[k] + nums[i] + nums[j] == 0 的 i,j 组合：
     * 当 nums[k] > 0 时直接break跳出：因为 nums[j] >= nums[i] >= nums[k] > 0，即 33 个数字都大于 00 ，在此固定指针 k 之后不可能再找到结果了。
     * 当 k > 0且nums[k] == nums[k - 1]时即跳过此元素nums[k]：因为已经将 nums[k - 1] 的所有组合加入到结果中，本次双指针搜索只会得到重复组合。
     * i，j 分设在数组索引 (k, len(nums))(k,len(nums)) 两端，当i < j时循环计算s = nums[k] + nums[i] + nums[j]，并按照以下规则执行双指针移动：
     * 当s < 0时，i += 1并跳过所有重复的nums[i]；
     * 当s > 0时，j -= 1并跳过所有重复的nums[j]；
     * 当s == 0时，记录组合[k, i, j]至res，执行i += 1和j -= 1并跳过所有重复的nums[i]和nums[j]，防止记录到重复组合。
     * 复杂度分析：
     * 时间复杂度 O(N^2)O(N
     * 2
     *  )：其中固定指针k循环复杂度 O(N)O(N)，双指针 i，j 复杂度 O(N)O(N)。
     * 空间复杂度 O(1)O(1)：指针使用常数大小的额外空间。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/3sum/solution/3sumpai-xu-shuang-zhi-zhen-yi-dong-by-jyd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int target = 0 - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            if (nums[i] > 0) {
                break;
            }
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (l < r) {
                    if (nums[l] + nums[r] == target) {
                        List<Integer> list = Arrays.asList(nums[i], nums[l], nums[r]);
                        res.add(list);
                        while (l + 1 < nums.length && nums[l] == nums[l + 1]) {
                            l++;
                        }

                        while (r - 1 > 0 && nums[r] == nums[r - 1]) {
                            r--;
                        }

                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }


    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        // negSize：负数的数量
        // posSize：正数的数量
        // zeroSize：0的数量
        int negSize = 0, posSize = 0, zeroSize = 0;
        for (int v : nums) {
            if (v < minValue) {
                minValue = v;
            }
            if (v > maxValue) {
                maxValue = v;
            }
            if (v > 0) {
                posSize++;
            } else if (v < 0) {
                negSize++;
            } else {
                zeroSize++;
            }
        }
        if (zeroSize >= 3) { // 超过3个0
            res.add(Arrays.asList(0, 0, 0));
        }
        // 如果没有负数或没有正数
        if (negSize == 0 || posSize == 0) return res;

        // 排除掉边界不可能凑成0的数（过大或过小的数）
        if ((minValue << 1) + maxValue > 0) { // [-3, -2, 0, 2, 5, 6, 7, 8, 9]
            maxValue = -minValue << 1;
        } else if ((maxValue << 1) + minValue < 0) { // [-9, -8, -7, -6, -5, -2, 0, 2, 3]
            minValue = -maxValue << 1;
        }

        // 存放每个数字v出现的次数，v对应下标为v - minValue
        int[] sizes = new int[maxValue - minValue + 1];
        int[] poses = new int[posSize]; // 存放所有的正数（已去重）
        posSize = 0;
        int[] negs = new int[negSize]; // 存放所有的负数（已去重）
        negSize = 0;
        for (int v : nums) {
            // 排除掉边界不可能凑成0的数（过大或过小的数）
            if (v < minValue || v > maxValue) continue;
            if (sizes[v - minValue]++ != 0) continue;
            if (v > 0) {
                poses[posSize++] = v;
            } else if (v < 0) {
                negs[negSize++] = v;
            }
        }
        // 只排序非0部分
        Arrays.sort(poses, 0, posSize);
        // 只排序非0部分
        Arrays.sort(negs, 0, negSize);

        int posBegin = 0;
        int nv;
        // 遍历负数数组（从绝对值最小的开始遍历）
        for (int ni = negSize - 1; ni >= 0; ni--) {
            nv = negs[ni];
            // 负数绝对值的一半
            int minPv = -nv >> 1;
            // 跳过 < minPv的正数
            // 如果 < minPv的正数可以和nv凑成0，会在遍历到 > minPv的正数时，倒回去找 < minPv的正数
            while (posBegin < posSize && poses[posBegin] < minPv) {
                posBegin++;
            }
            for (int pi = posBegin; pi < posSize; pi++) {
                int pv = poses[pi];
                int remain = -nv - pv; // 0 - nv - pv
                if (remain == nv) { // 还差一个nv，就凑够0
                    if (sizes[nv - minValue] > 1) {
                        res.add(Arrays.asList(nv, nv, pv));
                    }
                } else if (remain == pv) { // 还差一个pv，就凑够0
                    if (sizes[pv - minValue] > 1) {
                        res.add(Arrays.asList(nv, pv, pv));
                    }
                } else if (remain > nv && remain < pv) {
                    if (sizes[remain - minValue] > 0) { // 还差一个remain，就凑够0
                        res.add(Arrays.asList(nv, remain, pv));
                    }
                } else if (remain < nv) break; // 寻找绝对值更大的负数
            }
        }
        return res;
    }

    @Test
    public void test() {
//        int[] nums = {-2, 0, 0, 2, 2};
        int[] nums = {0, 0, 0};
        System.out.println(threeSum(nums));
    }
}
