package com.chen.leetcode.array;

import org.junit.Test;

/**
 * 999. 可以被一步捕获的棋子数
 *
 * 在一个 8 x 8 的棋盘上，有一个白色的车（Rook），用字符 'R' 表示。棋盘上还可能存在空方块，白色的象（Bishop）以及黑色的卒（pawn），分别用字符 '.'，'B' 和 'p' 表示。不难看出，大写字符表示的是白棋，小写字符表示的是黑棋。
 *
 * 车按国际象棋中的规则移动。东，西，南，北四个基本方向任选其一，然后一直向选定的方向移动，直到满足下列四个条件之一：
 *
 * 棋手选择主动停下来。
 * 棋子因到达棋盘的边缘而停下。
 * 棋子移动到某一方格来捕获位于该方格上敌方（黑色）的卒，停在该方格内。
 * 车不能进入/越过已经放有其他友方棋子（白色的象）的方格，停在友方棋子前。
 * 你现在可以控制车移动一次，请你统计有多少敌方的卒处于你的捕获范围内（即，可以被一步捕获的棋子数）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/available-captures-for-rook
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * 输入：[['.','.','.','.','.','.','.','.'],['.','.','.','p','.','.','.','.'],['.','.','.','R','.','.','.','p'],['.','.','.','.','.','.','.','.'],['.','.','.','.','.','.','.','.'],['.','.','.','p','.','.','.','.'],['.','.','.','.','.','.','.','.'],['.','.','.','.','.','.','.','.']]
 * 输出：3
 * 解释：
 * 在本例中，车能够捕获所有的卒。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/available-captures-for-rook
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * */
public class $999 {


    public int numRookCaptures(char[][] board) {

        int captureNum = 0;

        int[] rook = new int[2];
        // 找到车的坐标
        for (int i = 0; i < board.length; i++) {
            char[] x = board[i];
            for (int j = 0; j < x.length; j++) {
                char point = x[j];
                if (point == 'R') {
                    rook[0] = i;
                    rook[1] = j;
                    break;
                }
            }
        }

        //向左遍历得到左侧的卒；
        int[] current = {rook[0], rook[1]};
        int res;

        for (; (res = checkCurrentPoint(current, board)) == 0;) {
            // 向左遍历，y 不变，x逐渐 减1；
            current[1] = current[1] - 1;
        }

        captureNum = res == 1 ? captureNum + 1 : captureNum;

        // 向右遍历得到右侧的卒；
        int[] current2 = {rook[0], rook[1]};
        for (; (res = checkCurrentPoint(current2, board)) == 0;) {
            // 向右遍历，y 不变，x逐渐 加1；
            current2[1] = current2[1] + 1;
        }

        captureNum = res == 1 ? captureNum + 1 : captureNum;


        // 向上遍历得到上侧的卒；
        int[] current3 = {rook[0], rook[1]};
        for (; (res = checkCurrentPoint(current3, board)) == 0;) {
            // 向上遍历，x 不变，x逐渐 加1；
            current3[0] = current3[0] + 1;
        }

        captureNum = res == 1 ? captureNum + 1 : captureNum;

        // 向下遍历得到下侧的卒；
        int[] current4 = {rook[0], rook[1]};
        for (; (res = checkCurrentPoint(current4, board)) == 0;) {
            // 向下遍历，x 不变，x逐渐 减1；
            current4[0] = current4[0] - 1;
        }

        captureNum = res == 1 ? captureNum + 1 : captureNum;

        return captureNum;
    }


    private int checkCurrentPoint(int[] current, char[][] board) {

        int x = current[0];
        int y = current[1];
        if (x < 0 || y < 0) {
            // 出界，则停止
            return -1;
        }

        if (x >= 8 || y >= 8) {
            // 出界，则停止
            return -1;
        }

        char point = board[x][y];
        if (point == 'B') {
            // 遇到象，则停止
            return -1;
        } else if (point == '.') {
            return 0;
        } else if (point == 'p') {
            return 1;
        } else {
            return 0;
        }
    }


    @Test
    public void test() {
//        char[][] board = {
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', 'B', '.', '.', '.', '.'},
//                {'.', '.', '.', 'R', 'p', 'B', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', 'B', '.', '.', '.', '.'},
//                {'.', '.', '.', 'p', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.'}};
        
        char[][] board = {
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','R','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'}};



        System.out.println(numRookCaptures(board));
    }
}
