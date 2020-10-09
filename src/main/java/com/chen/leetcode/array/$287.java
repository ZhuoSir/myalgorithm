package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $287 {

    /**
     * 方法三：快慢指针
     * 预备知识
     * <p>
     * 本方法需要读者对 「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解，它是一个检测链表是否有环的算法，LeetCode 中相关例题有 141. 环形链表，142. 环形链表 II。
     * <p>
     * 思路和算法
     * <p>
     * 我们对 \textit{nums}[]nums[] 数组建图，每个位置 ii 连一条 i\rightarrow \textit{nums}[i]i→nums[i] 的边。由于存在的重复的数字 \textit{target}target，因此 \textit{target}target 这个位置一定有起码两条指向它的边，因此整张图一定存在环，且我们要找到的 \textit{target}target 就是这个环的入口，那么整个问题就等价于 142. 环形链表 II。
     * <p>
     * 我们先设置慢指针 \textit{slow}slow 和快指针 \textit{fast}fast ，慢指针每次走一步，快指针每次走两步，根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时我们再将 \textit{slow}slow 放置起点 00，两个指针每次同时移动一步，相遇的点就是答案。
     * <p>
     * <p>
     * 1 / 25
     * <p>
     * 这里简单解释为什么后面将 \textit{slow}slow 放置起点后移动相遇的点就一定是答案了。假设环长为 LL，从起点到环的入口的步数是 aa，从环的入口继续走 bb 步到达相遇位置，从相遇位置继续走 cc 步回到环的入口，则有 b+c=Lb+c=L，其中 LL、aa、bb、cc 都是正整数。根据上述定义，慢指针走了 a+ba+b 步，快指针走了 2(a+b)2(a+b) 步。从另一个角度考虑，在相遇位置，快指针比慢指针多走了若干圈，因此快指针走的步数还可以表示成 a+b+kLa+b+kL，其中 kk 表示快指针在环上走的圈数。联立等式，可以得到
     * <p>
     * 2(a+b)=a+b+kL
     * 2(a+b)=a+b+kL
     * <p>
     * 解得 a=kL-ba=kL−b，整理可得
     * <p>
     * a=(k-1)L+(L-b)=(k-1)L+c
     * a=(k−1)L+(L−b)=(k−1)L+c
     * <p>
     * 从上述等式可知，如果慢指针从起点出发，快指针从相遇位置出发，每次两个指针都移动一步，则慢指针走了 aa 步之后到达环的入口，快指针在环里走了 k-1k−1 圈之后又走了 cc 步，由于从相遇位置继续走 cc 步即可回到环的入口，因此快指针也到达环的入口。两个指针在环的入口相遇，相遇点就是答案。
     * <p>
     * C++JavaScriptJavaGolang
     * <p>
     * class Solution {
     * public:
     * int findDuplicate(vector<int>& nums) {
     * int slow = 0, fast = 0;
     * do {
     * slow = nums[slow];
     * fast = nums[nums[fast]];
     * } while (slow != fast);
     * slow = 0;
     * while (slow != fast) {
     * slow = nums[slow];
     * fast = nums[fast];
     * }
     * return slow;
     * }
     * };
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n)O(n)。「Floyd 判圈算法」时间复杂度为线性的时间复杂度。
     * <p>
     * 空间复杂度：O(1)O(1)。我们只需要常数空间存放若干变量。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findDuplicate(int[] nums) {

        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * 方法：二分查找
     * 思路：
     * <p>
     * 这道题要求我们查找的数是一个整数，并且给出了这个整数的范围（在 11 和 nn 之间，包括 1 和 n），并且给出了一些限制，于是可以使用二分查找法定位在一个区间里的整数；
     * <p>
     * 二分法的思路是先猜一个数（有效范围 [left, right]里的中间数 mid），然后统计原始数组中小于等于这个中间数的元素的个数 cnt，如果 cnt 严格大于 mid，（注意我加了着重号的部分「小于等于」、「严格大于」）。根据抽屉原理，重复元素就在区间 [left, mid] 里；
     * <p>
     * 与绝大多数二分法问题的不同点是：正着思考是容易的，即：思考哪边区间存在重复数是容易的，因为有抽屉原理做保证。我们通过一个具体的例子来分析应该如何编写代码；
     * <p>
     * 以 [2, 4, 5, 2, 3, 1, 6, 7] 为例，一共 8 个数，n + 1 = 8，n = 7，根据题目意思，每个数都在 1 和 7 之间。
     * <p>
     * 例如：区间 [1, 7][1,7] 的中位数是 4，遍历整个数组，统计小于等于 4 的整数的个数，如果不存在重复元素，最多为 44 个。等于 44 的时候区间 [1, 4][1,4] 内也可能有重复元素。但是，如果整个数组里小于等于 4 的整数的个数严格大于 44 的时候，就可以说明重复的数存在于区间 [1, 4][1,4]。
     * <p>
     * 说明：由于这个算法是空间敏感的，「用时间换空间」是反常规做法，算法的运行效率肯定不会高。
     * <p>
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findDuplicate2(int[] nums) {

        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = (right + left) >>> 1;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt += 1;
                }
            }

            if (cnt > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 3, 4, 2, 2};
        System.out.println(findDuplicate2(nums));
    }


}
