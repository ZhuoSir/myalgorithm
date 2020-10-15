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

    /**
     * 回溯算法
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {

        if (nums.length < 4) return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, target, res, 0, new ArrayList<>());

        return res;
    }

    private void dfs(int[] nums, int target, List<List<Integer>> res, int level, List<Integer> temp) {

        if (temp.size() == 4) {
            if (0 == target) res.add(new ArrayList<>(temp));
            return;
        }

        Integer last = null;
        for (int i = level; i < nums.length; i++) {
            if (last != null && nums[i] == last) continue;
            if (nums.length - i < 4 - level) return;
            temp.add(nums[i]);
            dfs(nums, target - nums[i], res, i + 1, temp);
            last = temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test() {

//        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
//        int[] nums = new int[]{-495, -482, -455, -447, -400, -388, -381, -360, -350, -340, -330, -317, -308, -300, -279, -235, -209, -206, -188, -186, -171, -145, -143, -141, -137, -129, -121, -115, -97, -56, -47, -28, -20, -19, 8, 11, 35, 41, 46, 50, 69, 84, 85, 86, 88, 91, 135, 160, 171, 172, 177, 190, 226, 234, 238, 244, 249, 253, 254, 272, 281, 284, 294, 296, 300, 303, 307, 313, 320, 320, 327, 334, 355, 362, 367, 401, 426, 436, 456, 467, 473, 473, 484};
//        int[] nums = new int[]{1, -2, -5, -4, -3, 3, 3, 5};
        int[] nums = new int[]{91277418, 66271374, 38763793, 4092006, 11415077, 60468277, 1122637, 72398035, -62267800, 22082642, 60359529, -16540633, 92671879, -64462734, -55855043, -40899846, 88007957, -57387813, -49552230, -96789394, 18318594, -3246760, -44346548, -21370279, 42493875, 25185969, 83216261, -70078020, -53687927, -76072023, -65863359, -61708176, -29175835, 85675811, -80575807, -92211746, 44755622, -23368379, 23619674, -749263, -40707953, -68966953, 72694581, -52328726, -78618474, 40958224, -2921736, -55902268, -74278762, 63342010, 29076029, 58781716, 56045007, -67966567, -79405127, -45778231, -47167435, 1586413, -58822903, -51277270, 87348634, -86955956, -47418266, 74884315, -36952674, -29067969, -98812826, -44893101, -22516153, -34522513, 34091871, -79583480, 47562301, 6154068, 87601405, -48859327, -2183204, 17736781, 31189878, -23814871, -35880166, 39204002, 93248899, -42067196, -49473145, -75235452, -61923200, 64824322, -88505198, 20903451, -80926102, 56089387, -58094433, 37743524, -71480010, -14975982, 19473982, 47085913, -90793462, -33520678, 70775566, -76347995, -16091435, 94700640, 17183454, 85735982, 90399615, -86251609, -68167910, -95327478, 90586275, -99524469, 16999817, 27815883, -88279865, 53092631, 75125438, 44270568, -23129316, -846252, -59608044, 90938699, 80923976, 3534451, 6218186, 41256179, -9165388, -11897463, 92423776, -38991231, -6082654, 92275443, 74040861, 77457712, -80549965, -42515693, 69918944, -95198414, 15677446, -52451179, -50111167, -23732840, 39520751, -90474508, -27860023, 65164540, 26582346, -20183515, 99018741, -2826130, -28461563, -24759460, -83828963, -1739800, 71207113, 26434787, 52931083, -33111208, 38314304, -29429107, -5567826, -5149750, 9582750, 85289753, 75490866, -93202942, -85974081, 7365682, -42953023, 21825824, 68329208, -87994788, 3460985, 18744871, -49724457, -12982362, -47800372, 39958829, -95981751, -71017359, -18397211, 27941418, -34699076, 74174334, 96928957, 44328607, 49293516, -39034828, 5945763, -47046163, 10986423, 63478877, 30677010, -21202664, -86235407, 3164123, 8956697, -9003909, -18929014, -73824245};
//        int[] nums = new int[]{-1, -5, -5, -3, 2, 5, 0, 4};
        System.out.println(fourSum2(nums, -236727523));
    }

}
