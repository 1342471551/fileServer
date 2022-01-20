package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (有效的数独)
 * https://leetcode-cn.com/problems/valid-sudoku/
 * @date 2021/9/17 9:15
 */
public class _36有效的数独 {

    public boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][] block = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    //数字减一从上面三个数组中查找是否有相同的(数组是从0开始的所以要减一)
                    int num = board[i][j] - '1';
                    //判断此元素应该在3*3的哪一行
                    int blockIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || block[blockIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        block[blockIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }
}
