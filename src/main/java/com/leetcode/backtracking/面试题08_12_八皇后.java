package com.leetcode.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * @author wangyj
 * @Description: (八皇后)
 * https://leetcode-cn.com/problems/eight-queens-lcci/
 * @date 2021/6/3 11:00
 */
public class 面试题08_12_八皇后 {

    int[] cols;
    List<List<String>> lists;
    int sum;

    public List<List<String>> solveNQueens(int n) {
        cols = new int[n];
        lists = new LinkedList<>();
        queen(0);
        return lists;
    }

    void queen(int row) {
        if (row == cols.length) {
            sum++;
            show();
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
            //判断列
            if (cols[i] == col) return false;
            //判断斜线
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    void show() {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < cols.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < cols.length; j++) {
                if (cols[i] == j) {
                    stringBuilder.append("Q");
                } else {
                    stringBuilder.append(".");
                }
            }
            list.add(stringBuilder.toString());
        }
        lists.add(list);
    }

    public static void main(String[] args) {
        面试题08_12_八皇后 s = new 面试题08_12_八皇后();
        System.out.println(s.solveNQueens(4));
        System.out.println(s.sum);
    }
}
