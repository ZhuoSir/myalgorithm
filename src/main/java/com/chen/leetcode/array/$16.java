package com.chen.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $16 {

    /**
     * 双指针法
     * 先让数组有序，也就是需要先对数组进行排序
     * 然后每次固定一个元素，再去寻找另外两个元素，也就是双指针
     * 双指针法的代码实现
     * 利用 Arrays.sort(nums) 对数组进行排序。
     * 初始化一个用于保存结果的值 result = nusm[0] + nums[1] + nums[2] （不要自己设初值，直接从数组中抽取三个元素，假设这是最接近的三数之和，然后再更新就是了）。
     * 利用下标 i 对数组进行遍历，此时就是在固定第一个元素，注意，下标 i 的边界为 i < nums.length-2，否则设置指针的时候会出现数组越界。
     * 每次遍历的过程中设置两个指针，分别是 left = i + 1、right = nums.length - 1。
     * 检查 sum = nums[i] + nums[left] + nums[right]与 target 的距离，如果该距离比之前保存的 result 与 target 的距离更小，就更新 result。
     * 然后就是移动双指针。
     * 如果 sum 的值比 target 大，那么我们让 right--，因为数组是有序的，right --会使得下一次的 sum 更小，也就更接近 target 的值
     * 同理，如果 sum 的值 target 小，那么我们让 left++。·
     * left++ 和 right-- 的界限自然是 left != right，如果 left == right，说明我们已经将所有的元素都遍历过一遍了。
     * 重复上面的操作，直到i循环结束为止，返回 result。
     *
     * 作者：kurna
     * 链接：https://leetcode-cn.com/problems/3sum-closest/solution/dui-shuang-zhi-zhen-fa-jin-xing-yi-dian-you-hua-da/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public int threeSumClosest(int[] nums, int target) {

        int res = Integer.MAX_VALUE;
        int sum = 0;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int l = i + 1;
            int r = nums.length - 1;
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (l < r) {
                    boolean lOrR = true;
                    int sumTemp = nums[i] + nums[l] + nums[r];
                    int t = sumTemp - target;
                    if (t < 0) {
                        t = -t;
                        lOrR = false;
                    }
                    if (t < res) {
                        sum = sumTemp;
                        res = t;
                    }

                    if (!lOrR) {
                        while (l + 1 < nums.length && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        l++;
                    } else {
                        while (r-1 > 0 && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }
            }
        }

        return sum;
    }

    public  int threeSumClosest2(int[] nums, int target) {
        int result;
        int leftTarget = target;
        int rightTarget = target;
        Arrays.sort(nums);

        while (true) {
            leftTarget--;
            rightTarget++;
            if ((result = threeSum(nums, target)) != Integer.MIN_VALUE) {
                return result;
            } else if ((result = threeSum(nums, leftTarget)) != 0x80000000) {
                return result;
            } else if (((result = threeSum(nums, rightTarget)) != 0x80000000)) {
                return result;
            }
        }
    }

    public int threeSum(int[] nums, int target) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                if (nums[l] + nums[r] == complement) {
                    result = nums[i] + nums[l] + nums[r];
                    return result;
                } else if (nums[l] + nums[r] > complement) {
                    r--;
                } else if (nums[l] + nums[r] < complement) {
                    l++;
                }
            }
        }
        return 0x80000000;
    }

    @Test
    public void test() {
//        int[] nums = {0, 2, 1, -3};[]
//3
        int[] nums = {-1,0,1,1,55};
        // -3 0 1 2
        int target = 3;
        System.out.println(threeSumClosest(nums, target));
    }
}
