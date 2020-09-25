package com.chen.leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 78. 子集
 * <p>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $78 {

    List<List<Integer>> res = new ArrayList<>();

    /**
     * https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
     *
     * 回溯法求值
     *
     * 时间复杂度：O(n \times 2 ^ n)O(n×2
     * n
     *  )。一共 2^n2
     * n
     *   个状态，每种状态需要 O(n)O(n) 的时间来构造子集。
     * 空间复杂度：O(n)O(n)。临时数组 tt 的空间代价是 O(n)O(n)，递归时栈空间的代价为 O(n)O(n)
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null)
            return null;
        dfs(0, nums, new ArrayList<>());
        return res;
    }

    private void dfs(int depth, int[] nums, List<Integer> temp) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[depth]);
        dfs(depth + 1, nums, temp);
        temp.remove(temp.size() - 1);
        dfs(depth + 1, nums, temp);
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int all = res.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(subsets2(new int[]{1, 2, 3}));
    }
}
