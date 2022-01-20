package com.leetcode.backtracking;

/**
 * @author wangyj
 * @Description: (N皇后II)
 * @date 2021/6/3 11:58
 */
public class _52N皇后II {

    int[] cols;
    int count;

    public int totalNQueens(int n) {
        cols = new int[n];
        queen(0);
        return count;
    }

    void queen(int row) {
        if (row == cols.length) {
            count++;
            return;
        }
        for (int i = 0; i < cols.length; i++) {
            if (isTrue(row, i)) {
                cols[row] = i;
                queen(row + 1);
            }
        }
    }

    boolean isTrue(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) return false;
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }
}
