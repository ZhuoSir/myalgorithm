package com.chen.leetcode.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $17 {


    /**
     * 回溯解法
     * 这道题的解法是用回溯的方式，在循环里面套了递归调用。本来递归就不好理解了，再加上循环的递归，就更难理解了。
     * 我们先不考虑递归，先看看下面这个问题怎么解决。
     * 假设输入是2，只有一个字符，那么应该怎么解呢？
     * 按照题目要求2=“abc"，所以结果应该是["a","b","c"]
     * 先不用想着怎么去写递归，只思考下怎么打印出这个结果。
     * 这个太简单了，一个循环就搞定了：
     *
     * 作者：wang_ni_ma
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/tong-su-yi-dong-dong-hua-yan-shi-17-dian-hua-hao-m/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public List<String> letterCombinations(String digits) {

        if (digits == null)
            return null;

        if (digits.equals(""))
            return new ArrayList<>();

        List<String> res = new ArrayList<>();
        helper(new StringBuilder(), digits, 0, res);

        return res;
    }

    public void helper(StringBuilder prex, String digits, int start, List<String> list) {

        if (start == digits.length()) {
            list.add(prex.toString());
            return;
        }

        String letters = digitMap[digits.charAt(start++) - '0'];

        for (char c : letters.toCharArray()) {
            helper(prex.append(c), digits, start, list);
            prex.deleteCharAt(prex.length() - 1);
        }
    }


    List<String> res = new ArrayList<>();
    String[] digitMap = {
            "", "", "abc",
            "def", "ghi", "jkl",
            "mno", "pqrs", "tuv",
            "wxyz"
    };

    public List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) return res;
        StringBuilder currentStr = new StringBuilder();
        genTelStr(digits, 0, currentStr);

        return res;
    }

    private void genTelStr(String digits, int start, StringBuilder currentStr) {
        if (start == digits.length()) {
            res.add(currentStr.toString());
            return;
        }

        char[] charArr = digitMap[digits.charAt(start) - '0'].toCharArray();
        for (char c : charArr) {
            currentStr.append(c);
            genTelStr(digits, start + 1, currentStr);
//            currentStr.deleteCharAt(currentStr.length() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(letterCombinations("23"));
    }
}
