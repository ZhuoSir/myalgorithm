package com.chen.leetcode.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 * <p>
 * 示例:
 * <p>
 * 输入: "aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $131 {

    private List<List<String>> res = new ArrayList<>();

    /**
     * https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/
     *
     * 回溯算法
     *
     * */
    public List<List<String>> partition(String s) {

        if (s == null)
            return null;

        dfs(0, s.length(), s, new ArrayList<>());

        return res;
    }

    private void dfs(int start, int len, String s, List<String> temp) {

        if (start == len) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < len; i++) {
            if (!isHuiWen(s, start, i)) continue;
            temp.add(s.substring(start, i + 1));
            dfs(i + 1, len, s, temp);
            temp.remove(temp.size() - 1);
        }
    }

    private boolean isHuiWen(String str, int left, int right) {
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Test
    public void test() {

        System.out.println(partition("aab"));
    }
}
