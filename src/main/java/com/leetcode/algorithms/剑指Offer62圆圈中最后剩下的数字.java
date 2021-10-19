package com.leetcode.algorithms;

/**
 * @author wangyj
 * @Description: (圆圈中最后剩下的数字)
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 * @date 2021/7/7 10:41
 */
public class 剑指Offer62圆圈中最后剩下的数字 {

    /**
     * f(n,m)=(f(n-1,m)+m)%n n数字 m间隔几个删除
     */
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;
        //递归调用,算出n-1规模的答案,再加上m个人对人数进行取模
        return (lastRemaining(n - 1, m) + m) % n;
    }
}
