package com.chen.leetcode.string;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $6 {

    /**
     * 解题思路：
     * 题目理解：
     * 字符串 s 是以 ZZ 字形为顺序存储的字符串，目标是按行打印。
     * 设 numRows 行字符串分别为 s_1s
     * 1
     * ​
     * , s_2s
     * 2
     * ​
     * ,..., s_ns
     * n
     * ​
     * ，则容易发现：按顺序遍历字符串 s 时，每个字符 c 在 ZZ 字形中对应的 行索引 先从 s_1s
     * 1
     * ​
     * 增大至 s_ns
     * n
     * ​
     * ，再从 s_ns
     * n
     * ​
     * 减小至 s_1s
     * 1
     * ​
     * …… 如此反复。
     * 因此，解决方案为：模拟这个行索引的变化，在遍历 s 中把每个字符填到正确的行 res[i] 。
     * 算法流程： 按顺序遍历字符串 s；
     * res[i] += c： 把每个字符 c 填入对应行 s_is
     * i
     * ​
     * ；
     * i += flag： 更新当前字符 c 对应的行索引；
     * flag = - flag： 在达到 ZZ 字形转折点时，执行反向。
     * 复杂度分析：
     * 时间复杂度 O(N)O(N) ：遍历一遍字符串 s；
     * 空间复杂度 O(N)O(N) ：各行字符串共占用 O(N)O(N) 额外空间。
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public String convert(String s, int numRows) {

        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) flag = -flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }

    public String convert3(String s, int numRows) {
        if (numRows < 2) return s;
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) rows.add(new StringBuilder());
        int i = 0;
        int thres = 2 * numRows - 2;
        int k = 0;
        for (char c : s.toCharArray()) {
            k = i % thres;
            if (k <= numRows - 1)
                rows.get(k).append(c);

            if (k > numRows - 1)
                rows.get(thres - k).append(c);

            i++;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) res.append(row);
        return res.toString();
    }


    public String convert2(String s, int numRows) {

        if (s == null || s.equals("")) return s;
        char[] cOld = s.toCharArray();
        char[] cNew = new char[s.length()];

        int bigGap = (numRows > 1) ? 2 * (numRows - 1) : 1;
        int smallGap = bigGap;

        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < cOld.length; ) {
                cNew[index] = cOld[i + j];
                index++;
                if (smallGap > 0 && smallGap < bigGap) {
                    j += smallGap;
                    if (i + j >= cOld.length) break;
                    cNew[index] = cOld[i + j];
                    index++;
                    j += bigGap - smallGap;
                } else j += bigGap;
            }
            smallGap -= 2;
        }

        return new String(cNew);
    }

    @Test
    public void test() {

        String s = "LEETCODEISHIRING";

        System.out.println(convert3(s, 4));

    }
}
