package com.chen.leetcode.math;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $46 {

    /**
     * 回溯算法：
     *
     * https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
     * */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null)
            return null;
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>(), new int[nums.length], 0);
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, ArrayList<Integer> temp, int[] visited, int depth) {

        if (depth == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i] == 1) continue;
            temp.add(nums[i]);
            visited[i] = 1;
            backtrack(nums, res, temp, visited, depth + 1);
            visited[i] = 0;
            temp.remove(temp.size() - 1);
        }
    }

    List<List<Integer>> ans = new ArrayList();

    public List<List<Integer>> permute2(int[] nums) {
        dfs(0, nums);
        return ans;
    }

    public void dfs(int start, int nums[]) {
        if (start == nums.length) {
            List<Integer> cur = new ArrayList();
            for (int i : nums)
                cur.add(i);
            ans.add(cur);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;

            dfs(start + 1, nums);

            temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;
        }
    }

    @Test
    public void test() {
        System.out.println(permute2(new int[]{1, 2, 3}));
    }
}
