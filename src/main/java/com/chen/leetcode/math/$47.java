package com.chen.leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $47 {


    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums == null) return null;

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, res, new ArrayList<>(), new int[nums.length], 0);

        return res;
    }

    private void dfs(int[] nums, List<List<Integer>> res, List<Integer> temp, int[] visited, int depth) {

        if (depth == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1 || (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == 0)) continue;
            visited[i] = 1;
            temp.add(nums[i]);
            dfs(nums, res, temp, visited, depth + 1);
            visited[i] = 0;
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }
}
