package com.chen.leetcode.bp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句子。
 * <p>
 * 说明：
 * <p>
 * 分隔时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * 示例 2：
 * <p>
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $140 {

    List<List<String>> strs = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        strs = dfs(new ArrayList<>(), s, wordDict, 0);
        if (strs == null) return null;
        for (int i = 0; i < strs.size(); i++) {
            StringBuilder str = new StringBuilder();
            for (int j = 0; j < strs.get(i).size(); j++) {
                str.append(strs.get(j)).append(" ");
            }
            res.add(str.toString());
        }
        return res;
    }

    private List<List<String>> dfs(List<String> strs, String word, List<String> wordDict, int start) {
        if (start == word.length()) {
            return null;
        }

        StringBuilder tmp = new StringBuilder();
        for (int i = start; i < word.length(); i++) {
            tmp.append(word.charAt(i));
            if (wordDict.contains(tmp.toString())) {
                strs.add(tmp.toString());
                dfs(strs, word, wordDict, i + 1);
                strs.remove(strs.size() - 1);
            }
        }

        return null;
    }

    @Test
    public void test() {
        String word = "";
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(wordBreak(word, dict));
    }
}
