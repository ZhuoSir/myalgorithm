package com.chen.leetcode.dfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 394. 字符串解码
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $394 {

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        int multi = 0;
        for (int i = 0; i < s.length(); i++) {
            Character chara = s.charAt(i);
            if (chara.equals(']')) {
                //出栈
                StringBuilder word = new StringBuilder();
                while (true) {
                    StringBuilder c = new StringBuilder(stack.pop());
                    if (c.toString().equals("[")) {
                        break;
                    } else {
                        c.append(word);
                        word = c;
                    }
                }
                int num = Integer.parseInt(stack.pop());
                StringBuilder wordTemp = new StringBuilder();
                for (int j = 0; j < num; j++) {
                    wordTemp.append(word);
                }
                stack.push(wordTemp.toString());
            } else if (Character.isDigit(chara) ){
                multi = multi * 10 + Integer.valueOf(chara + "");
            } else if (chara.equals('[')){
                stack.push(String.valueOf(multi));
                stack.push(chara.toString());
                multi = 0;
            } else {
                stack.push(chara.toString());
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.removeLast());
        }
        return res.toString();
    }

    @Test
    public void test() {

        String t = "100[leetcode]";

        System.out.println(decodeString(t));

    }
}
