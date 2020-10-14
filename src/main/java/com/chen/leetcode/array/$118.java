package com.chen.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * <p>
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $118 {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> r = new ArrayList<>();
            int j = 0;
            do {
                if (j == 0 || j == i) {
                    r.add(1);
                } else {
                    List<Integer> lr = res.get(i - 1);
                    int t = lr.get(j - 1) + lr.get(j);
                    r.add(t);
                }
                j++;
            } while (j < i + 1);
            res.add(r);
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(generate(5));
    }
}
