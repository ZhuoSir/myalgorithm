package com.chen.leetcode.string;

import org.junit.Test;

/**
 * 14. 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $14 {

    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0)
            return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j))
                    break;
            }
            ans = ans.substring(0, j);
            if (ans.equals(""))
                return ans;
        }
        return ans;
    }

    public String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0)
            return "";

        if (strs.length == 1)
            return strs[0];

        int size = Integer.MAX_VALUE;

        for (String str : strs) {
            size = Math.min(size, str.length());
        }

        boolean isBreak = false;
        StringBuilder res = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(strs[0].charAt(i));
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].startsWith(sb.toString())) {
                    isBreak = true;
                    break;
                }
            }

            if (isBreak) break;
            else res = new StringBuilder(sb);
        }

        return res.toString();
    }

    @Test
    public void test() {

//        String[] strs = new String[] {"dog","racecar","car"};
//        String[] strs = new String[] {"c", "c"};
        String[] strs = new String[]{"flower", "flow", "flight"};

        System.out.println(longestCommonPrefix2(strs));
    }
}
