package com.chen.leetcode.array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，请你判断一个人是否能够参加这里面的全部会议。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0,30],[5,10],[15,20]]
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/meeting-rooms
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $252 {

    public boolean canAttendMeetings(int[][] intervals) {

        if (intervals.length < 2) {
            return true;
        }

        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        boolean res = false;
        for (int i = 0; i < intervals.length; i++) {

            if (i + 1 >= intervals.length) {
                break;
            }

            if (intervals[i + 1][0] >= intervals[i][1]) {
                res = true;
            } else {
                res = false;
                break;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[][] nums = new int[][]{
                {7, 10},
                {2, 4}
        };

        System.out.println(canAttendMeetings(nums));
    }
}
