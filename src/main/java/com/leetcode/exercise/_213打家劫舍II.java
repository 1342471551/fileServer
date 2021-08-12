package com.leetcode.exercise;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author wangyj
 * @Description: (打家劫舍II)
 * https://leetcode-cn.com/problems/house-robber-ii/
 * @date 2021/4/15 9:07
 */
public class _213打家劫舍II {

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int length = nums.length;
        int[] nums2 = new int[length - 1];
        //把nums数组从索引为1的开始复制到nums2的索引为0的位置,复制length-1个元素
        System.arraycopy(nums, 1, nums2, 0, length - 1);
        return Math.max(dpRob(nums, 0, length - 1), dpRob(nums2, 0, length - 1));
    }

    //使用动态规划解决相邻最大值
    private int dpRob(int[] nums, int start, int end) {
        int[] dp = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= end; i++) {
            //当前的值是前一个值大还是前两个加上前一个nums中的值大
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }
        return dp[end];
    }

}
