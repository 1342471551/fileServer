package com.algorithm.means.Recursion;

/**
 * @author wangyj
 * @Description: (斐波那契数列)
 * @date 2021/5/31 17:23
 */
public class Fibonacci {
    //时间2^n 空间 n
    int fib0(int n) {
        if (n <= 2) return 1;
        return fib0(n - 1) + fib0(n - 2);
    }

    //时间n 空间 n 对每个数只会算一次放入数组中
    int fib1(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        return fib1(n, array);
    }

    int fib1(int n, int[] array) {
        if (array[n] == 0) {
            array[n] = fib1(n - 1, array) + fib1(n - 2, array);
        }
        return array[n];
    }

    //时间n 空间 n 减少了递归调用
    int fib2(int n) {
        if (n <= 2) return 1;
        int[] array = new int[n + 1];
        array[1] = array[2] = 1;
        for (int i = 3; i <= n; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n];
    }

    //使用滚动数组 减少数组开辟的空间
    int fib3(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i % 2] = array[(i - 1) % 2] + array[(i - 2) % 2];
        }
        return array[n % 2];
    }

    //基于fib3对2取模其实是看位运算最后一位是0还是1 = 当前数与1进行&运算
    int fib4(int n) {
        if (n <= 2) return 1;
        int[] array = new int[2];
        array[0] = array[1] = 1;
        for (int i = 3; i <= n; i++) {
            array[i & 1] = array[(i - 1) & 1] + array[(i - 2) & 1];
        }
        return array[n & 1];
    }

    //使用尾递归完成
    int fib5(int n) {
        return fib5(n, 1, 1);
    }

    int fib5(int n, int first, int second) {
        if (n <= 1) return first;
        return fib5(n - 1, second, first + second);
    }
}
