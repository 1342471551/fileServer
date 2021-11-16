package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (最小操作次数使数组元素相等)
 * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/
 * @date 2021/10/20 8:56
 */
public class _453最小操作次数使数组元素相等 {

    public int minMoves(int[] nums) {
        //假设总数sum,移动次数m,数组个数n,最终的元素值x    x=min_nums+m
        // sum+m(n-1)=x*n --> sum+m(n-1)=(min_nums+m)*n --> m=sum-min_nums*n
        if (nums.length == 0) return 0;
        int min_nums = nums[0], sum = 0, n = nums.length;
        for (int val : nums) {
            if (min_nums > val) min_nums = val;
            sum += val;
        }
        return sum - min_nums * n;
    }
}
