package com.leetcode.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @Description: (螺旋矩阵)
 * https://leetcode-cn.com/problems/spiral-matrix/
 * @date 2021/7/7 11:15
 */
public class _54螺旋矩阵 {

    //螺旋矩阵,按照圆圈顺时针逐行打印向中间靠拢 最内部需要判断列的奇偶
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            top++;
            for (int j = top; j <= bottom; j++) {
                res.add(matrix[j][right]);
            }
            right--;

            //代码在奇数行,偶数列有问题 判断退出即可
            if (top > bottom || left > right) break;

            for (int x = right; x >= left; x--) {
                res.add(matrix[bottom][x]);
            }
            bottom--;
            for (int y = bottom; y >= top; y--) {
                res.add(matrix[y][left]);
            }
            left++;
        }

        return res;
    }

    public static void main(String[] args) {
        spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}});
    }
}
