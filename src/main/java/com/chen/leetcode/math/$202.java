package com.chen.leetcode.math;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 202. 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 *  
 *
 * 示例：
 *
 * 输入：19
 * 输出：true
 * 解释：
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/happy-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $202 {

    /**
     * 复杂度分析
     *
     * 确定这个问题的时间复杂度对于一个 “简单” 级别的问题来说是一个挑战。如果您对这些问题还不熟悉，可以尝试只计算 getNext(n) 函数的时间复杂度。
     *
     * 时间复杂度：O(243 \cdot 3 + \log n + \log\log n + \log\log\log n)...O(243⋅3+logn+loglogn+logloglogn)... = O(\log n)O(logn)。
     * 查找给定数字的下一个值的成本为 O(\log n)O(logn)，因为我们正在处理数字中的每位数字，而数字中的位数由 \log nlogn 给定。
     * 要计算出总的时间复杂度，我们需要仔细考虑循环中有多少个数字，它们有多大。
     * 我们在上面确定，一旦一个数字低于 243243，它就不可能回到 243243 以上。因此，我们就可以用 243243 以下最长循环的长度来代替 243243，不过，因为常数无论如何都无关紧要，所以我们不会担心它。
     * 对于高于 243243 的 nn，我们需要考虑循环中每个数高于 243243 的成本。通过数学运算，我们可以证明在最坏的情况下，这些成本将是 O(\log n) + O(\log \log n) + O(\log \log \log n)...O(logn)+O(loglogn)+O(logloglogn)...。幸运的是，O(\log n)O(logn) 是占主导地位的部分，而其他部分相比之下都很小（总的来说，它们的总和小于\log nlogn），所以我们可以忽略它们。
     * 空间复杂度：O(\log n)O(logn)。与时间复杂度密切相关的是衡量我们放入 HashSet 中的数字以及它们有多大的指标。对于足够大的 nn，大部分空间将由 nn 本身占用。我们可以很容易地优化到 O(243 \cdot 3) = O(1)O(243⋅3)=O(1)，方法是只保存集合中小于 243243 的数字，因为对于较高的数字，无论如何都不可能返回到它们。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * */
    public boolean isHappy(int n) {

        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     *
     * 方法二：快慢指针法
     * 通过反复调用 getNext(n) 得到的链是一个隐式的链表。隐式意味着我们没有实际的链表节点和指针，但数据仍然形成链表结构。起始数字是链表的头 “节点”，链中的所有其他数字都是节点。next 指针是通过调用 getNext(n) 函数获得。
     *
     * 意识到我们实际有个链表，那么这个问题就可以转换为检测一个链表是否有环。因此我们在这里可以使用弗洛伊德循环查找算法。这个算法是两个奔跑选手，一个跑的快，一个跑得慢。在龟兔赛跑的寓言中，跑的快的称为 “乌龟”，跑得快的称为 “兔子”。
     *
     * 不管乌龟和兔子在循环中从哪里开始，它们最终都会相遇。这是因为兔子每走一步就向乌龟靠近一个节点（在它们的移动方向上）。
     *
     * 算法：
     *
     * 我们不是只跟踪链表中的一个值，而是跟踪两个值，称为快跑者和慢跑者。在算法的每一步中，慢速在链表中前进 1 个节点，快跑者前进 2 个节点（对 getNext(n) 函数的嵌套调用）。
     *
     * 如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1。
     *
     * 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(\log n)O(logn)。和上面一样。
     * 空间复杂度：O(1)O(1)，我们没有保留我们所遇到的数字的历史记录。硬编码哈希集的大小是固定的。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/kuai-le-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public boolean isHappy2(int n) {

        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(n);
            fast = getNext(getNext(fast));
        }

        return fast == 1;
    }


    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    @Test
    public void test() {

        System.out.println(isHappy2(82));

    }
}
