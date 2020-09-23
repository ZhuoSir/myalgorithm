package com.chen.leetcode.string;

import org.junit.Test;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $43 {

    /**
     * 竖式求和
     *
     * 复杂度分析
     *
     * 时间复杂度：O(M N)。M,NM,N 分别为 num1 和 num2 的长度。
     * 空间复杂度：O(M+N)。用于存储计算结果。
     * */
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        String res = "0";
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            int n2 = num2.charAt(i) - '0';
            StringBuilder temp = new StringBuilder();

            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append("0");
            }

            for (int j = num1.length() - 1; j >= 0 || carry != 0 ; j--) {
                int n1 = j >= 0 ? num1.charAt(j) - '0' : 0;
                int r = n1 * n2 + carry;
                temp.append(r % 10);
                carry = r / 10;
            }

            res = addStrings(res, temp.reverse().toString());
        }

        return res;
    }

    /**
     * 竖式求和（优化）
     *
     * 复杂度分析
     *
     * 时间复杂度：O(M N)。M,NM,N 分别为 num1 和 num2 的长度。
     * 空间复杂度：O(M+N)。用于存储计算结果。
     * */
    public String multiply3(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        char[] num1CharArray = num1.toCharArray();
        char[] num2CharArray = num2.toCharArray();
        int[] result = new int[num1CharArray.length + num2CharArray.length];
        for (int i = num1CharArray.length - 1; i >= 0; i--) {
            int n1 = num1CharArray[i] - '0';
            for (int j = num2CharArray.length - 1; j >= 0; j--) {
                result[i + j + 1] += n1 * (num2CharArray[j] - '0');
            }
        }
        // rem 表示进位
        for (int pos = result.length - 1, rem = 0; pos >= 0; pos--) {
            result[pos] += rem;
            rem = result[pos] / 10;
            result[pos] %= 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int pos = 0; pos < num1CharArray.length + num2CharArray.length; pos++) {
            if (pos == 0 && result[pos] == 0) pos++;
            sb.append(result[pos]);
        }
        return sb.toString();
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int ans = n1 + n2 + add;
            res.append(ans % 10);
            add = ans / 10;
            i--;
            j--;
        }

        if (add > 0) {
            res.append(add);
        }

        res.reverse();
        return res.toString();
    }

    @Test
    public void test() {

        System.out.println(multiply3("11", "13"));
//        System.out.println(200 / 10);
    }
}
