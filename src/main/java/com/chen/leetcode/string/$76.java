package com.chen.leetcode.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * <p>
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 *  
 * <p>
 * 提示：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $76 {

    /**
     * 题解
     * 滑动窗口的思想：
     * 用i,j表示滑动窗口的左边界和右边界，通过改变i,j来扩展和收缩滑动窗口，可以想象成一个窗口在字符串上游走，当这个窗口包含的元素满足条件，即包含字符串T的所有元素，记录下这个滑动窗口的长度j-i+1，这些长度中的最小值就是要求的结果。
     *
     * 步骤一
     * 不断增加j使滑动窗口增大，直到窗口包含了T的所有元素
     *
     * 步骤二
     * 不断增加i使滑动窗口缩小，因为是要求最小字串，所以将不必要的元素排除在外，使长度减小，直到碰到一个必须包含的元素，这个时候不能再扔了，再扔就不满足条件了，记录此时滑动窗口的长度，并保存最小值
     *
     * 步骤三
     * 让i再增加一个位置，这个时候滑动窗口肯定不满足条件了，那么继续从步骤一开始执行，寻找新的满足条件的滑动窗口，如此反复，直到j超出了字符串S范围。
     *
     * 面临的问题：
     * 如何判断滑动窗口包含了T的所有元素？
     * 我们用一个字典need来表示当前滑动窗口中需要的各元素的数量，一开始滑动窗口为空，用T中各元素来初始化这个need，当滑动窗口扩展或者收缩的时候，去维护这个need字典，例如当滑动窗口包含某个元素，我们就让need中这个元素的数量减1，代表所需元素减少了1个；当滑动窗口移除某个元素，就让need中这个元素的数量加1。
     * 记住一点：need始终记录着当前滑动窗口下，我们还需要的元素数量，我们在改变i,j时，需同步维护need。
     * 值得注意的是，只要某个元素包含在滑动窗口中，我们就会在need中存储这个元素的数量，如果某个元素存储的是负数代表这个元素是多余的。比如当need等于{'A':-2,'C':1}时，表示当前滑动窗口中，我们有2个A是多余的，同时还需要1个C。这么做的目的就是为了步骤二中，排除不必要的元素，数量为负的就是不必要的元素，而数量为0表示刚刚好。
     * 回到问题中来，那么如何判断滑动窗口包含了T的所有元素？结论就是当need中所有元素的数量都小于等于0时，表示当前滑动窗口不再需要任何元素。
     * 优化
     * 如果每次判断滑动窗口是否包含了T的所有元素，都去遍历need看是否所有元素数量都小于等于0，这个会耗费O(k)O(k)的时间复杂度，k代表字典长度，最坏情况下，k可能等于len(S)。
     * 其实这个是可以避免的，我们可以维护一个额外的变量needCnt来记录所需元素的总数量，当我们碰到一个所需元素c，不仅need[c]的数量减少1，同时needCnt也要减少1，这样我们通过needCnt就可以知道是否满足条件，而无需遍历字典了。
     * 前面也提到过，need记录了遍历到的所有元素，而只有need[c]>0大于0时，代表c就是所需元素
     *
     * 作者：Mcdull0921
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring/solution/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public String minWindow(String s, String t) {

        int[] need = new int[128];

        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();

        for (int i = 0; i < tchars.length; i++) {
            need[tchars[i]]++;
        }

        int l = 0, r = 0, size = Integer.MAX_VALUE;
        int count = tchars.length;
        int start = 0;
        while (r < schars.length) {
            char c = schars[r];
            if (need[c] > 0) {
                count--;
            }

            need[c]--;
            if (count == 0) {
                //缩减窗口，排除不必要的元素
                while (l < r && need[schars[l]] < 0) {
                    need[schars[l++]]++;
                }
                //此时已经满足要求，记录大小；
                if (r - l + 1 < size) {
                    size = r - l + 1;
                    start = l;
                }
                //继续向右找，l 向右移;
                need[schars[l]]++;//左边界右移之前需要释放need[s[l]]
                l++;
                count++;
            }
            r++;
        }

        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }

    @Test
    public void test() {
        System.out.println(minWindow("cabwefgewcwaefgcf", "cae"));
    }
}
