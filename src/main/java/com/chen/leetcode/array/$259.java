package com.chen.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 259. 较小的三数之和
 * <p>
 * 给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target 成立的三元组  i, j, k 个数（0 <= i < j < k < n）。
 * <p>
 * 示例：
 * <p>
 * 输入: nums = [-2,0,1,3], target = 2
 * 输出: 2
 * 解释: 因为一共有两个三元组满足累加和小于 2:
 *      [-2,0,1]
 * [-2,0,3]
 * 进阶：是否能在 O(n2) 的时间复杂度内解决？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-smaller
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $259 {

    /**
     * 在方法二中，我们首先解决了一个简化版的问题，随后在外部套上一层循环，就可以解决当前的问题。如果我们能找出更好的解决简化版的问题的方法，就能在更低的时间复杂度内解决当前的问题。
     *
     * 我们首先仍然对数组进行排序，例如 \mathrm{nums} = [3,5,2,8,1]nums=[3,5,2,8,1] 排序后变成 [1,2,3,5,8][1,2,3,5,8]。\mathrm{target}target 的值为 7。
     *
     * [1, 2, 3, 5, 8]
     *  ↑           ↑
     * left       right
     * 我们用两个指针 \mathrm{left}left 和 \mathrm{right}right 分别指向数组中的第一个和最后一个元素，它们的和为 1+8=9>\mathrm{target}1+8=9>target，这说明 \mathrm{right}right 不能出现在二元组中（因为 \mathrm{left}left 只向左移动，如果此时二者的和已经大于 \mathrm{target}target，那么在 \mathrm{left}left 移动的过程中，二者的和就不可能小于 \mathrm{target}target 了），因此需要将 \mathrm{right}right 向左移动一位。
     *
     * [1, 2, 3, 5, 8]
     *  ↑        ↑
     * left    right
     * 现在二者的和为 1+5=6< \mathrm{target}1+5=6<target，那么对于当前的 \mathrm{left}left，应当有 \mathrm{right}-\mathrm{left}=3right−left=3 对二元组满足要求，它们分别为 (1,2), (1,3), (1,5)(1,2),(1,3),(1,5)。在这之后，我们将 \mathrm{left}left 向右移动一位。
     *
     * Java
     *
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/3sum-smaller/solution/jiao-xiao-de-san-shu-zhi-he-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public int threeSumSmaller(int[] nums, int target) {
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            int v = target - nums[i];
            while (l < r) {
                //最右侧的满足，所以从l 到 r 的全部满足
                int sum = nums[l] + nums[r];
                if (sum < v) {
                    res += (r - l);
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        // -2 0 1 3
        int[] nums = {3, 1, 0, -2};
        // -2 1 3
        // -2 0 3
        // -2 0 1
        int target = 4;
        System.out.println(threeSumSmaller(nums, target));
    }
}
