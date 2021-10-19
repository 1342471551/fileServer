package com.algorithm.means.Recursion;

/**
 * @author wangyj
 * @Description: (汉诺塔)
 * 实现吧a中的盘中移动到c 但是大盘子必须在小盘子下面
 * @date 2021/6/1 10:36
 */
public class Hanoi {

    /**
     * 将n个盘子从p1挪动到p3
     *
     * @param n  盘子数量
     * @param p1 起始位置
     * @param p2 中间过度使用
     * @param p3 结束位置
     */
    void hanoi(int n, String p1, String p2, String p3) {
        //n=1 直接挪动到c
        if (n == 1) {
            move(n, p1, p3);
            return;
        }
        //1.把n-1个盘子从a挪动到b
        hanoi(n - 1, p1, p3, p2);
        //2.把第n个盘子从a挪动到c
        move(n, p1, p3);
        //3.把n-1个盘子从b挪动到c
        hanoi(n - 1, p2, p1, p3);

    }

    void move(int number, String from, String to) {
        System.out.println("盘中" + number + "从" + from + "移动到" + to);
    }

}
