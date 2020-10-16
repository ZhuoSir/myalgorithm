package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <p>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $42 {

    /**
     * 解法二：按列求
     * 求每一列的水，我们只需要关注当前列，以及左边最高的墙，右边最高的墙就够了。
     * <p>
     * 装水的多少，当然根据木桶效应，我们只需要看左边最高的墙和右边最高的墙中较矮的一个就够了。
     * <p>
     * 所以，根据较矮的那个墙和当前列的墙的高度可以分为三种情况。
     * <p>
     * 较矮的墙的高度大于当前列的墙的高度
     * <p>
     * <p>
     * 把正在求的列左边最高的墙和右边最高的墙确定后，然后为了方便理解，我们把无关的墙去掉。
     * <p>
     * <p>
     * <p>
     * 这样就很清楚了，现在想象一下，往两边最高的墙之间注水。正在求的列会有多少水？
     * <p>
     * 很明显，较矮的一边，也就是左边的墙的高度，减去当前列的高度就可以了，也就是 2 - 1 = 1，可以存一个单位的水。
     * <p>
     * 较矮的墙的高度小于当前列的墙的高度
     * <p>
     * <p>
     * 同样的，我们把其他无关的列去掉。
     * <p>
     * <p>
     * <p>
     * 想象下，往两边最高的墙之间注水。正在求的列会有多少水？
     * <p>
     * 正在求的列不会有水，因为它大于了两边较矮的墙。
     * <p>
     * 较矮的墙的高度等于当前列的墙的高度。
     * <p>
     * 和上一种情况是一样的，不会有水。
     * <p>
     * <p>
     * <p>
     * 明白了这三种情况，程序就很好写了，遍历每一列，然后分别求出这一列两边最高的墙。找出较矮的一端，和当前列的高度比较，结果就是上边的三种情况。
     * <p>
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-8/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 时间复杂度：O(n²）O(n²），遍历每一列需要 nn，找出左边最高和右边最高的墙加起来刚好又是一个 nn，所以是 n²n²。
     * <p>
     * 空间复杂度：O(1）O(1）。
     */
    public int trap(int[] height) {
        int res = 0;
        for (int i = 1; i < height.length; i++) {
            int left = 0, right = 0;
            for (int j = i; j >= 0; j--) {
                left = Math.max(left, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                right = Math.max(right, height[j]);
            }
            res += Math.min(left, right) - height[i];
        }
        return res;
    }

    /**
     * 方法 2：动态编程
     * 直观想法
     * <p>
     * 在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。但是我们可以提前存储这个值。因此，可以通过动态编程解决。
     * <p>
     * 算法
     * <p>
     * 找到数组中从下标 i 到最左端最高的条形块高度 \text{left\_max}left_max。
     * 找到数组中从下标 i 到最右端最高的条形块高度 \text{right\_max}right_max。
     * 扫描数组 \text{height}height 并更新答案：
     * 累加 \min(\text{max\_left}[i],\text{max\_right}[i]) - \text{height}[i]min(max_left[i],max_right[i])−height[i] 到 ansans 上
     * 复杂性分析
     * <p>
     * 时间复杂度：O(n)O(n)。
     * <p>
     * 存储最大高度数组，需要两次遍历，每次 O(n)O(n) 。
     * 最终使用存储的数据更新\text{ans}ans ，O(n)O(n)。
     * 空间复杂度：O(n)O(n) 额外空间。
     * <p>
     * 和方法 1 相比使用了额外的 O(n)O(n) 空间用来放置 \text{left\_max}left_max 和 \text{right\_max}right_max 数组。
     * <p>
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int trap2(int[] height) {

        if (height.length == 0) return 0;

        int res = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        left[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        right[right.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }

        for (int i = 1; i < height.length; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }

    /**
     * 方法 4：使用双指针
     * 直观想法
     * <p>
     * 和方法 2 相比，我们不从左和从右分开计算，我们想办法一次完成遍历。
     * 从动态编程方法的示意图中我们注意到，只要 \text{right\_max}[i]>\text{left\_max}[i]right_max[i]>left_max[i] （元素 0 到元素 6），积水高度将由 left_max 决定，类似地 \text{left\_max}[i]>\text{right\_max}[i]left_max[i]>right_max[i]（元素 8 到元素 11）。
     * 所以我们可以认为如果一端有更高的条形块（例如右端），积水的高度依赖于当前方向的高度（从左到右）。当我们发现另一侧（右侧）的条形块高度不是最高的，我们则开始从相反的方向遍历（从右到左）。
     * 我们必须在遍历时维护 \text{left\_max}left_max 和 \text{right\_max}right_max ，但是我们现在可以使用两个指针交替进行，实现 1 次遍历即可完成。
     * <p>
     * 算法
     * <p>
     * 初始化 \text{left}left 指针为 0 并且 \text{right}right 指针为 size-1
     * While \text{left}< \text{right}left<right, do:
     * If \text{height[left]}height[left] < \text{height[right]}height[right]
     * If \text{height[left]} \geq \text{left\_max}height[left]≥left_max, 更新 \text{left\_max}left_max
     * Else 累加 \text{left\_max}-\text{height[left]}left_max−height[left] 到 \text{ans}ans
     * \text{left}left = \text{left}left + 1.
     * Else
     * If \text{height[right]} \geq \text{right\_max}height[right]≥right_max, 更新 \text{right\_max}right_max
     * Else 累加 \text{right\_max}-\text{height[right]}right_max−height[right] 到 \text{ans}ans
     * \text{right}right = \text{right}right - 1.
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water/solution/jie-yu-shui-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * <p>
     * 复杂性分析
     * <p>
     * 时间复杂度：O(n)O(n)。单次遍历的时间O(n)O(n)。
     * 空间复杂度：O(1)O(1) 的额外空间。\text{left}left, \text{right}right, \text{left\_max}left_max 和 \text{right\_max}right_max 只需要常数的空间。
     */
    public int trap3(int[] height) {
        if (height.length == 0) return 0;
        int res = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    res += (leftMax - height[left]);
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    res += (rightMax - height[right]);
                }
                right--;
            }
        }
        return res;
    }


    @Test
    public void test() {

        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(trap3(height));
    }
}
