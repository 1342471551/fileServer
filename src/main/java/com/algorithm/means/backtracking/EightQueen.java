package com.algorithm.means.backtracking;

/**
 * @author wangyj
 * @Description: (八皇后问题)
 * 在8*8棋盘中摆放8个皇后,让每个皇后都不能互相攻击(米字型都不能相遇)一共有多少种情况
 * @date 2021/6/2 9:36
 */
public class EightQueen {

    //数组索引是行号,数组元素是列 cols[4]=5 元素在第4行第5列
    //数组中记录的是当前情况中皇后的摆放情况,用来进行剪枝处理
    int[] cols;
    int counts;

    //回溯法,第一行选择一个皇后,在能选择的位置第二行选择第二个皇后,可以走就继续不行就回溯 算出所有可能性
    //剪枝 选择第一个皇后之后排除第二行已经不可能的的情况 只选择还可能的情况
    public int placeQueen(int n) {
        if (n < 1) return counts;
        cols = new int[n];
        place(0);
        return counts;
    }

    //从第row行开始摆放
    void place(int row) {
        if (row == cols.length) {
            //说明找到了一种情况
            counts++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                place(row + 1);
                //当上面的place下一行(row+1)摆放完成来到这里其实就是回溯,之后让col++继续可能性
            }
        }
    }

    //判断第row行第col列能否摆放
    boolean isValid(int row, int col) {
        //拿到前面行摆放的皇后
        for (int i = 0; i < row; i++) {
            //列 此列有皇后了返回false
            if (cols[i] == col) return false;
            //斜线 在同一个斜线上返回false
            if (row - i == Math.abs(col - cols[i])) return false;
        }
        return true;
    }

    //打印找到的这种情况
    void show() {
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        System.out.println(eightQueen.placeQueen(5)+"种");
    }

}
