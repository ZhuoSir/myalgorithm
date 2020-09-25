package com.chen.leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $90 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null)
            return null;
        Arrays.sort(nums);
        dfs(0, nums, new ArrayList<>());
        return res;

    }

    private void dfs(int depth, int[] nums, List<Integer> temp) {

        res.add(new ArrayList<>(temp));

        for (int i = depth; i < nums.length; i++) {
            if (i > depth && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            dfs(i + 1, nums, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(subsetsWithDup(new int[]{4, 4, 1, 4}));
    }
}
