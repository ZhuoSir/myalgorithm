package com.chen.leetcode.bfs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 542 01矩阵
 *
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 *
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 *
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 *
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/01-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */
public class $542 {

    /**
     *
     * 一、广度优先搜索
     * 思路：
     * 对于 「Tree 的 BFS」 （典型的「单源 BFS」） 大家都已经轻车熟路了：
     *
     * 首先把 root 节点入队，再一层一层无脑遍历就行了。
     * 对于 「图 的 BFS」 （「多源 BFS」） 做法其实也是一样滴～，与 「Tree 的 BFS」的区别注意以下两条就 ok 辣～
     *
     * Tree 只有 1 个 root，而图可以有多个源点，所以首先需要把多个源点都入队；
     * Tree 是有向的因此不需要标识是否访问过，而对于无向图来说，必须得标志是否访问过哦！并且为了防止某个节点多次入队，需要在其入队之前就将其设置成已访问！【 看见很多人说自己的 BFS 超时了，坑就在这里哈哈哈
     * 做法：
     * 根据上述思路，本题怎么做就很简单了：
     *
     * 首先把每个源点 00 入队，然后从各个 00 同时开始一圈一圈的向 11 扩散（每个 11 都是被离它最近的 00 扩散到的 ），扩散的时候可以设置 int[][] dist 来记录距离（即扩散的层次）并同时标志是否访问过。对于本题是可以直接修改原数组 int[][] matrix 来记录距离和标志是否访问的，这里要注意先把 matrix 数组中 1 的位置设置成 -1 （设成Integer.MAX_VALUE啦，m * n啦，10000啦都行，只要是个无效的距离值来标志这个位置的 1 没有被访问过就行辣~）
     * 复杂度分析：
     *
     * 每个点入队出队一次，所以时间复杂度：O(n * m)O(n∗m)
     * 虽然我们是直接原地修改的原输入数组来存储结果，但最差的情况下即全都是 00 时，需要把 m * nm∗n 个 00 都入队，因此空间复杂度是 O(n * m)O(n∗m)
     *
     * 作者：sweetiee
     * 链接：https://leetcode-cn.com/problems/01-matrix/solution/2chong-bfs-xiang-jie-dp-bi-xu-miao-dong-by-sweetie/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * */
    public int[][] updateMatrix(int[][] matrix) {

        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        //找到所有元素0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0];
            int y = point[1];
            //向四个方向遍历
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                if (newX >= 0 && newX < m
                        && newY >= 0 && newY < n && matrix[newX][newY] == -1) {
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        return matrix;
    }

    @Test
    public void test() {

        int[][] matrix = new int[][]{
                {1, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1}};

        int[][] res = updateMatrix2(matrix);

        int m = res.length, n = res[0].length;
        //找到所有元素0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private int row;
    private int col;
    private int[][] matrix;
    public int[][] updateMatrix2(int[][] matrix) {
        if (matrix==null || matrix.length<1 || matrix[0].length<1)
            return matrix;
        this.matrix = matrix;
        this.row = matrix.length;
        this.col = matrix[0].length;

        for (int i = this.row-1; i > -1; i--) {
            for (int j = this.col-1; j > -1; j--) {
                if (matrix[i][j] != 0){
                    method(i,j);
                }
            }
        }


        return matrix;
    }

    private void method(int x, int y) {

        int d = 1;
        to:for ( ;  ; d++) {

            int i = d;
            int j = 0;
            while (i > 0){
                if ( -1<x+i && x+i<row && -1<y+j && y+j<col && matrix[x+i][y+j] == 0){
                    break to;
                }
                i--;
                j++;
            }

            i = 0;
            j = d;
            while (j > 0){
                if ( -1<x+i && x+i<row && -1<y+j && y+j<col && matrix[x+i][y+j] == 0){
                    break to;
                }
                i--;
                j--;
            }

            i = -d;
            j = 0;
            while (i < 0){
                if ( -1<x+i && x+i<row && -1<y+j && y+j<col && matrix[x+i][y+j] == 0){
                    break to;
                }
                i++;
                j--;
            }

            i = 0;
            j = -d;
            while (j < 0){
                if ( -1<x+i && x+i<row && -1<y+j && y+j<col && matrix[x+i][y+j] == 0){
                    break to;
                }
                i++;
                j++;
            }

        }
        matrix[x][y] = d;
    }
}
