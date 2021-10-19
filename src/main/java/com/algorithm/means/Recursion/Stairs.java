package com.algorithm.means.Recursion;

/**
 * @author wangyj
 * @Description: (上楼梯)
 * 一次可以上1阶或2阶楼梯,请问上n阶楼梯有多少种做法
 * @date 2021/6/1 9:15
 */
public class Stairs {

    //缩减楼梯规模,使用递归完成
    //若走一步则有f(N-1)种 若走两步则有f(n-2)种 即:f(n)=f(n-1)+f(n-2)
    int climbStairs(int n) {
        if (n <= 2) return n;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}
