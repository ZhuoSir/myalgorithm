package com.chen.leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1：
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * <p>
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $39 {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

//        for (int i = 0; i < candidates.length; i++) {
            dfs2(candidates, target, 0, new ArrayList<>());
//        }

        return res;
    }

    private void dfs(int[] candidates, int target, int index, List<Integer> temp) {

        if (index == candidates.length)
            return;

        temp.add(candidates[index]);
        int newtarget = target - candidates[index];
        if (newtarget == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        if (newtarget < candidates[0]) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            dfs(candidates, newtarget, i, temp);
            temp.remove(temp.size() - 1);
        }
    }

    private void dfs2(int[] candidates, int target, int index, List<Integer> temp) {

        if (target < 0) {
            return;
        }

        if (target == 0) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            temp.add(candidates[i]);
            dfs2(candidates, target - candidates[i], i, temp);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {

        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
}
