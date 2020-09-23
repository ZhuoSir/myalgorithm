package com.chen.leetcode.string;

import org.junit.Test;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $415 {

    /**
     * 解题思路：
     * 算法流程： 设定 i，j 两指针分别指向 num1，num2 尾部，模拟人工加法；
     *
     * 计算进位： 计算 carry = tmp // 10，代表当前位相加是否产生进位；
     * 添加当前位： 计算 tmp = n1 + n2 + carry，并将当前位 tmp % 10 添加至 res 头部；
     * 索引溢出处理： 当指针 i或j 走过数字首部后，给 n1，n2 赋值为 00，相当于给 num1，num2 中长度较短的数字前面填 00，以便后续计算。
     * 当遍历完 num1，num2 后跳出循环，并根据 carry 值决定是否在头部添加进位 11，最终返回 res 即可。
     * 复杂度分析：
     *
     * 时间复杂度 O(max(M,N))O(max(M,N))：其中 MM，NN 为 22 数字长度，按位遍历一遍数字（以较长的数字为准）；
     * 空间复杂度 O(1)O(1)：指针与变量使用常数大小空间。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/add-strings/solution/add-strings-shuang-zhi-zhen-fa-by-jyd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
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

    public String addStrings2(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();
        int len1 = n1.length;
        int len2 = n2.length;
        char[] res = new char[(len1 > len2 ? len1 : len2) + 1];
        int i = n1.length - 1;
        int j = n2.length - 1;
        int k = res.length - 1;
        int r, c = 0;
        for (; i >= 0 && j >= 0; ) {
            r = n1[i--] + n2[j--] + c - 96;
            c = r >= 10 ? 1 : 0;
            res[k--] = (char) ('0' + r % 10);
        }
        for (; i >= 0; ) {
            r = n1[i--] + c - 48;
            c = r == 10 ? 1 : 0;
            res[k--] = (char) (r % 10 + '0');
        }
        for (; j >= 0; j--) {
            r = n2[j] + c - 48;
            c = r == 10 ? 1 : 0;
            res[k--] = (char) (r % 10 + '0');
        }
        if (c == 1) {
            res[k--] = '1';
        }
        if (c == 1) {
            return new String(res);
        }
        return new String(res, 1, res.length - 1);
    }

    @Test
    public void test() {

        System.out.println(addStrings("11", "1"));

    }
}
