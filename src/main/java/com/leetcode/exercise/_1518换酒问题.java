package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (换酒问题)
 * https://leetcode-cn.com/problems/water-bottles/
 * @date 2021/12/17 8:57
 */
public class _1518换酒问题 {

    public int numWaterBottles(int numBottles, int numExchange) {
        int res = numBottles;
        while (numBottles / numExchange >= 1) {
            int change = numBottles / numExchange;
            numBottles = change + numBottles % numExchange;
            res += change;
        }
        return res;
    }
}
