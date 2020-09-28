package com.chen.leetcode.math;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *  
 * <p>
 * 提示：
 * <p>
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class $79 {

    int[] directx = new int[]{-1, 1, 0, 0};
    int[] directy = new int[]{0, 0, -1, 1};

    /**
     * 方法一：深度优先搜索
     * 思路与算法
     * <p>
     * 设函数 \text{check}(i, j, k)check(i,j,k) 判断以网格的 (i, j)(i,j) 位置出发，能否搜索到单词 \text{word}[k..]word[k..]，其中 \text{word}[k..]word[k..] 表示字符串 \text{word}word 从第 kk 个字符开始的后缀子串。如果能搜索到，则返回 \text{true}true，反之返回 \text{false}false。函数 \text{check}(i, j, k)check(i,j,k) 的执行步骤如下：
     * <p>
     * 如果 \text{board}[i][j] \neq s[k]board[i][j]
     * 
     * ​
     * =s[k]，当前字符不匹配，直接返回 \text{false}false。
     * 如果当前已经访问到字符串的末尾，且对应字符依然匹配，此时直接返回 \text{true}true。
     * 否则，遍历当前位置的所有相邻位置。如果从某个相邻位置出发，能够搜索到子串 \text{word}[k+1..]word[k+1..]，则返回 \text{true}true，否则返回 \text{false}false。
     * 这样，我们对每一个位置 (i,j)(i,j) 都调用函数 \text{check}(i, j, 0)check(i,j,0) 进行检查：只要有一处返回 \text{true}true，就说明网格中能够找到相应的单词，否则说明不能找到。
     * <p>
     * 为了防止重复遍历相同的位置，需要额外维护一个与 \text{board}board 等大的 \text{visited}visited 数组，用于标识每个位置是否被访问过。每次遍历相邻位置时，需要跳过已经被访问的位置。
     * <p>
     * <p>
     * 复杂度分析
     * <p>
     * 时间复杂度：一个非常宽松的上界为 O(MN \cdot 3^L)O(MN⋅3
     * L
     * )，其中 M, NM,N 为网格的长度与宽度，LL 为字符串 \text{word}word 的长度。在每次调用函数 \text{check}check 时，除了第一次可以进入 44 个分支以外，其余时间我们最多会进入 33 个分支（因为每个位置只能使用一次，所以走过来的分支没法走回去）。由于单词长为 LL，故 \text{check(i, j, 0)}check(i, j, 0) 的时间复杂度为 O(3^L)O(3
     * L
     * )，而我们要执行 O(MN)O(MN) 次检查。然而，由于剪枝的存在，我们在遇到不匹配或已访问的字符时会提前退出，终止递归流程。因此，实际的时间复杂度会远远小于 \Theta(MN \cdot 3^L)Θ(MN⋅3
     * L
     * )。
     * <p>
     * 空间复杂度：O(MN)O(MN)。我们额外开辟了 O(MN)O(MN) 的 \text{visited}visited 数组，同时栈的深度最大为 O(\min(L, MN))O(min(L,MN))。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/word-search/solution/dan-ci-sou-suo-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean exist(char[][] board, String word) {

        if (board == null)
            return false;

        int[][] visited = new int[board.length][board[0].length];

        int w = board.length;
        int h = board[0].length;

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                boolean res = dfs(board, word, i, j, visited, 0);
                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int w, int h, int[][] visited, int depth) {

        if (word.charAt(depth) != board[w][h]) {
            return false;
        }

        if (depth == word.length() - 1) {
            return true;
        }

        visited[w][h] = 1;

        boolean res = false;
        for (int i = 0; i < 4; i++) {
            int newX = h + directx[i];
            int newY = w + directy[i];
            if (newX >= 0 && newX < board[0].length && newY >= 0 && newY < board.length
                    && visited[newY][newX] == 0) {
                res = dfs(board, word, newY, newX, visited, depth + 1);
                if (res) {
                    break;
                }
            }
        }

        visited[w][h] = 0;
        return res;
    }

    @Test
    public void test() {

        char[][] board =
                {
                        {'A', 'B', 'C', 'E'},
                        {'D', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                };

        String word = "SEE";

        System.out.println(exist(board, word));
    }
}
