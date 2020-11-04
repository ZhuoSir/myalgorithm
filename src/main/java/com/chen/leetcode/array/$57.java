package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * <p>
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * <p>
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null) return null;
        if (intervals.length == 0) return new int[][]{newInterval};
        if (newInterval.length == 0) return intervals;
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 1) {
            if (newInterval[0] > intervals[0][1] || newInterval[1] < intervals[0][0]) {
                res.add(intervals[0]);
                res.add(newInterval);
                res.sort((a, b) -> a[0] - b[0]);
                return res.toArray(new int[res.size()][]);
            }
            intervals[0][0] = Math.min(newInterval[0], intervals[0][0]);
            intervals[0][1] = Math.max(newInterval[1], intervals[0][1]);
            return intervals;
        }

        for (int i = 0; i < intervals.length; i++) {
            if (newInterval[0] >= intervals[i][0]
                    && newInterval[0] <= intervals[i + 1][0]) {
                intervals[i][1] = Math.max(intervals[i][1], newInterval[1]);
                int[] merge = intervals[i];
                for (i = i + 1; i <= intervals.length; i++) {
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
            } else {
                res.add(intervals[i]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean placed = false;
        List<int[]> ansList = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] nums = new int[][]{
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        };
        int[][] res = insert(nums, new int[]{4, 8});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }

    @Test
    public void test2() {
        int[][] nums = new int[][]{
                {1, 3}, {6, 9}
        };
        int[][] res = insert(nums, new int[]{2, 5});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }

    @Test
    public void test3() {
        int[][] nums = new int[][]{
                {1, 5}
        };
        int[][] res = insert(nums, new int[]{2, 3});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }

    @Test
    public void test4() {
        int[][] nums = new int[][]{
                {1, 5}
        };
        int[][] res = insert(nums, new int[]{6, 8});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }

    @Test
    public void test5() {
        int[][] nums = new int[][]{
                {1, 5}
        };
        int[][] res = insert(nums, new int[]{0, 0});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }

    @Test
    public void test6() {
        int[][] nums = new int[][]{
                {1, 5}
        };
        int[][] res = insert(nums, new int[]{2, 7});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }

    @Test
    public void test7() {
        int[][] nums = new int[][]{
                {1, 5}, {6, 8}
        };
        int[][] res = insert(nums, new int[]{0, 9});
        for (int[] i : res) {
            System.out.println(i[0] + "-" + i[1]);
        }
    }
}
