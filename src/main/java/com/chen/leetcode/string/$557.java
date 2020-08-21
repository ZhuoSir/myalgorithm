package com.chen.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 557. 反转字符串中的单词 III
 *
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 * 示例：
 *
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $557 {

    /**
     *方法 1：简单的解法 [Accepted]
     * 第一种方法非常简单，我们将输入字符串中按照空白字符串分开，然后把所有单词放到一个字符串列表中，然后我们逐一遍历每一个字符串并把反转结果连接起来。最后，我们将删除了额外空白字符的字符串返回。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/solution/fan-zhuan-zi-fu-chuan-zhong-de-dan-ci-iii-by-leetc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 时间复杂度
     *
     * 时间复杂度： O(n)O(n) 。其中nn 是字符串的长度。
     *
     * 空间复杂度： O(n)O(n) 。使用了大小为 nn 的 resres
     *
     * */
    public String reverseWords(String s) {

        String[] words = split(s);
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(reverse(word) + " ");
        }

        return builder.toString().trim();
    }

    private String[] split(String s) {
        ArrayList<String> words = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                word.append(s.charAt(i));
            } else {
                words.add(word.toString());
                word = new StringBuilder();
            }
        }

        return words.toArray(new String[words.size()]);
    }

    private String reverse(String s) {
        StringBuilder res=new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            res.insert(0,s.charAt(i));
        return res.toString();
    }


    @Test
    public void test() {
        String word = "Let's take LeetCode contest";
        System.out.println(reverseWords(word));
    }
}
