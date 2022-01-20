package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (分糖果)
 * https://leetcode-cn.com/problems/distribute-candies/
 * @date 2021/11/1 10:40
 */
public class _575分糖果 {

    public int distributeCandies(int[] candyType) {
        int count = candyType.length;
        int length = (int) Arrays.stream(candyType).distinct().count();
        return Math.min(count / 2, length);
    }
}
