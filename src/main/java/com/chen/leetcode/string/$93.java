package com.chen.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 93. 复原IP地址
 * <p>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * <p>
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 * <p>
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $93 {

    /**
     * 回溯法
     *
     * */
    public List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<>();

        if (s == null || s.length() == 0)
            return new ArrayList<>();

        helper(0, 0, s, "", res);


        return res;
    }

    private void helper(int start, int segment, String source, String s, List<String> res) {

        if (segment > 4) {
            return;
        }

        if (segment == 4 && start < source.length()) {
            return;
        }

        if (start == source.length() && segment == 4) {
            res.add(s);
            return;
        }

        if (!s.isEmpty()) {
            s += ".";
        }

        for (int i = 1; i <= 3; i++) {
            if (start + i <= source.length()) {
                String  subStr = source.substring(start, start + i);
                Integer subInt = Integer.valueOf(subStr);

                if (subInt > 255) continue;
                if (subStr.length() > 1 && subStr.startsWith("0")) continue;

                String newStr = s + subStr;
                helper(start + i, segment + 1, source, newStr, res);
            }
        }
    }

    @Test
    public void test() {
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
