package com.chen.leetcode;

import org.junit.Test;

/**
 * 1232. 缀点成线
 *
 * 在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
 *
 * 请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
 *
 *  输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * 输出：true
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $1232 {

    /**
     * 判断在不在一条直线上，主要是竖线，斜线或者横线；
     * 当为竖线时候，函数的斜率不存在，所以只需要判断横坐标是否都相同即可；
     * 当为斜线或者横线，只需要判断斜率是否相同；
     *
     * 时间复杂度O(n)；
     * 空间复杂度O(1);
     *
     * */
    public boolean checkStraightLine(int[][] coordinates) {

        boolean ret = false;

        // 没有斜率，也就是在一条竖线上；那么x 没有变化，只在y上存在变化
        // 判断x是否相同；
        int x0, x1;
        for (int i = 0; i < coordinates.length - 1; i++) {
            x0 = coordinates[i][0];
            x1 = coordinates[i+1][0];
            if (x0 != x1) {
                // 不在直线上，直接退出循环；
                ret = false;
                break;
            } else {
                // 在直线上，设置为true
                ret = true;
            }
        }

        if (ret) {
            return ret;
        }

        // 存在斜率，斜率有意义，在一条斜线或者横线上；
        // 求两点的斜率，(y1-y0)/(x1-x0);
        // x0 = coordinates[0,0], y0 = coordinates[0,1]
        // x1 = coordinates[1,0], y1 = coordinates[1,1]
        // xi = coordinates[i,0], yi = coordinates[i,1]
        float k, k1;

        float yt = (coordinates[1][1] - coordinates[0][1]);
        float xt = (coordinates[1][0] - coordinates[0][0]);

        // 说明这两个点是一条竖线，则整体肯定不在一条直线上；
        if (xt == 0)
            return false;

        k = yt / xt;
        for (int i = 1; i < coordinates.length; i++) {

            yt = (coordinates[i][1] - coordinates[i-1][1]);
            xt = (coordinates[i][0] - coordinates[i-1][0]);

            if (xt == 0) {
                // 说明这两个点是一条竖线，则整体肯定不在一条直线上；
                return false;
            }
            k1 = yt / xt;
            if (k != k1) {
                return false;
            }
        }

        return true;
    }



    /**
     * 直接根据公式计算
     *
     * 如果在一条直线上：
     *
     * (y2 - y1)/(x2 - x1) = (y1 - y0)/(x1 - x0);
     *
     * 可以推导：
     *
     * (y2 - y1) * (x1 - x0) = (x2 - x1) * (y1 - y0);
     *
     * 则后续坐标都根据这个公式计算，如果相等则都在一条直线上；
     *
     *      时间复杂度O(n)；
     *      空间复杂度O(1);
     *
     * */
    public boolean checkStraightLine2(int[][] coordinates) {

        float x0, x1, y0, y1;
        x0 = coordinates[0][0];
        x1 = coordinates[1][0];
        y0 = coordinates[0][1];
        y1 = coordinates[1][1];

        for (int[] coordinate : coordinates) {
            if (((coordinate[1] - y0) * (x1 - x0)) != ((y1 - y0) * (coordinate[0] - x0))) {
                return false;
            }
        }

        return true;
    }



    @Test
    public void test() {

        int[][] nums = new int[6][2];
        nums[0][0] = 1;
        nums[0][1] = 2;
        nums[1][0] = 2;
        nums[1][1] = 3;
        nums[2][0] = 3;
        nums[2][1] = 4;
        nums[3][0] = 4;
        nums[3][1] = 5;
        nums[4][0] = 5;
        nums[4][1] = 6;
        nums[5][0] = 6;
        nums[5][1] = 7;

//        int[][] nums = new int[3][2];
//        nums[0][0] = 0;
//        nums[0][1] = 2;
//        nums[1][0] = 0;
//        nums[1][1] = 3;
//        nums[2][0] = 0;
//        nums[2][1] = 4;

//        int[][] nums = new int[3][2];
//        nums[0][0] = 1;
//        nums[0][1] = 1;
//        nums[1][0] = 2;
//        nums[1][1] = 2;
//        nums[2][0] = 2;
//        nums[2][1] = 0;


        //[[-4,-3],[1,0],[3,-1],[0,-1],[-5,2]]

//        int[][] nums = new int[5][2];
//        nums[0][0] = -4;
//        nums[0][1] = -3;
//        nums[1][0] = 1;
//        nums[1][1] = 0;
//        nums[2][0] = 3;
//        nums[2][1] = -1;
//        nums[3][0] = 0;
//        nums[3][1] = -1;
//        nums[4][0] = -5;
//        nums[4][1] = 2;

        System.out.println(checkStraightLine2(nums));
    }

}
