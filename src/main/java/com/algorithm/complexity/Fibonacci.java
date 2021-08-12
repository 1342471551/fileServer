package com.algorithm.complexity;

/**
 * @author wangyj
 * @Description: (斐波那契数列)
 * @date 2021/3/23 22:47
 */
public class Fibonacci {

    public static void main(String[] args) {
        /**
         * 下一个数是前面两个相加
         * 0 1 1 2 3 5 8 13 ... n
         * 计算第n个斐波那契数
         */
        System.out.println(fib(30));
        System.out.println(fib1(30));
        System.out.println(fib2(30));
    }

    //使用递归调用(当n很大时可能会栈溢出) O(2^n)
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    //使用for循环赋值调用 O(n)
    public static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0, second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    //改进fib1,省略一个变量
    public static int fib2(int n) {
        if (n <= 1) {
            return n;
        }
        int first = 0, second = 1;
        while (n-- > 1) {
            second += first;
            first = second - first;
        }
        return second;
    }
}
