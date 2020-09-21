package com.chen.leetcode.string;

import org.junit.Test;

/**
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 *
 * 注意：整数序列中的每一项将表示为一个字符串。
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 *
 * 描述前一项，这个数是 1 即 “一个 1 ”，记作 11
 *
 * 描述前一项，这个数是 11 即 “两个 1 ” ，记作 21
 *
 * 描述前一项，这个数是 21 即 “一个 2 一个 1 ” ，记作 1211
 *
 * 描述前一项，这个数是 1211 即 “一个 1 一个 2 两个 1 ” ，记作 111221
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $38 {

    /**
     * 解题思路
     * 利用递归和StringBuffer来解析，通过StringBuffer可以大幅度明显感知到其的效率，把数组长度加1来
     *
     * 比如 n=6时,那么用递归得到上一层的字符串str=“111221”
     * 我们将start指向下标0,我们从下标1开始遍历,遍历到“2”下标3的时候,sb拼上(3-0)个1即sb.append(3).append(1),将start指针指向下标3,接着重复以上操作,直到到达str的最后一位,sb直接拼上即可。
     *
     *
     *
     * 作者：ruo-guang
     * 链接：https://leetcode-cn.com/problems/count-and-say/solution/1-ms-zai-suo-you-java-ti-jiao-zhong-ji-bai-liao-97/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public String countAndSay(int n) {

        if (n == 1)
            return "1";

        StringBuilder sb = new StringBuilder();
        String str = countAndSay(n - 1);
        int length = str.length();
        int start = 0;

        for (int i = 1; i < length + 1; i++) {

            if (i == length) {
                sb.append(i - start).append(str.charAt(start));
            }

            else if (str.charAt(i) != str.charAt(start)) {
                sb.append(i - start).append(str.charAt(start));
                start = i;
            }
        }

        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(countAndSay(5));
    }
}
