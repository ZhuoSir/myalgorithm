package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $56 {

    /**
     * 方法一：排序
     * 思路
     * <p>
     * 如果我们按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的。如下图所示，标记为蓝色、黄色和绿色的区间分别可以合并成一个大区间，它们在排完序的列表中是连续的：
     * <p>
     * <p>
     * <p>
     * 算法
     * <p>
     * 我们用数组 merged 存储最终的答案。
     * <p>
     * 首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
     * <p>
     * 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
     * <p>
     * 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
     * <p>
     * <p>
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n\log n)O(nlogn)，其中 nn 为区间的数量。除去排序的开销，我们只需要一次线性扫描，所以主要的时间开销是排序的 O(n\log n)O(nlogn)。
     * <p>
     * 空间复杂度：O(\log n)O(logn)，其中 nn 为区间的数量。这里计算的是存储答案之外，使用的额外空间。O(\log n)O(logn) 即为排序所需要的空间复杂度。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/merge-intervals/solution/he-bing-qu-jian-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[][] merge(int[][] intervals) {

        int len = intervals.length;
        if (len < 2) {
            return intervals;
        }

        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int[] merge = intervals[0];
        for (int i = 1; i <= intervals.length; i++) {

            if (i == intervals.length) {
                res.add(merge);
                break;
            }

            if (intervals[i][0] >= merge[0] && intervals[i][0] <= merge[1]) {
                merge[1] = Math.max(intervals[i][1], merge[1]);
            } else {
                res.add(merge);
                merge = intervals[i];
            }
        }

        return res.toArray(new int[res.size()][]);
    }

    public int[][] merge2(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return res.toArray(new int[0][]);
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
        return res.toArray(new int[0][]);
    }

    @Test
    public void test() {
        int[][] nums = new int[][]{
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18},
        };

//        int[][] nums = new int[][]{
//                {1, 4},
//                {2, 3}
//        };

        int[][] res = merge2(nums);
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }
}
