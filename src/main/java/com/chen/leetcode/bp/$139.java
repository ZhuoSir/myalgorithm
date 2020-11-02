package com.chen.leetcode.bp;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $139 {

    /**
     * 方法一：动态规划
     * <p>
     * <p>
     * 初始化 dp=[False,\cdots,False]dp=[False,⋯,False]，长度为 n+1n+1。nn 为字符串长度。dp[i]dp[i] 表示 ss 的前 ii 位是否可以用 wordDictwordDict 中的单词表示。
     * <p>
     * 初始化 dp[0]=Truedp[0]=True，空字符可以被表示。
     * <p>
     * 遍历字符串的所有子串，遍历开始索引 ii，遍历区间 [0,n)[0,n)：
     * <p>
     * 遍历结束索引 jj，遍历区间 [i+1,n+1)[i+1,n+1)：
     * 若 dp[i]=Truedp[i]=True 且 s[i,\cdots,j)s[i,⋯,j) 在 wordlistwordlist 中：dp[j]=Truedp[j]=True。解释：dp[i]=Truedp[i]=True 说明 ss 的前 ii 位可以用 wordDictwordDict 表示，则 s[i,\cdots,j)s[i,⋯,j) 出现在 wordDictwordDict 中，说明 ss 的前 jj 位可以表示。
     * 返回 dp[n]dp[n]
     * <p>
     * 复杂度分析
     * 时间复杂度：O(n^{2})O(n
     * 2
     * )
     * 空间复杂度：O(n)O(n)
     * <p>
     * 作者：wu_yan_zu
     * 链接：https://leetcode-cn.com/problems/word-break/solution/dong-tai-gui-hua-ji-yi-hua-hui-su-zhu-xing-jie-shi/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean wordBreak(String word, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            if (!dp[i]) continue;
            for (int j = i + 1; j < n + 1; j++) {
                if (set.contains(word.substring(i, j))) {
                    dp[j] = true;
                }
            }
        }
        return dp[n];
    }

    @Test
    public void test() {
        String word = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(wordBreak(word, wordDict));
    }
}
