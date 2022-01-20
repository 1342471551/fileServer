package com.algorithm.means.dynamic;

import java.util.Arrays;

/**
 * @author wangyj
 * 300. 最长递增子序列
 * @Description: (最长上升子序列) 动态规划
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @date 2021/6/7 17:13
 */
public class LIS {

    /**
     * 上升子序列不需要连续
     * dp[i] 已i为结尾的元素最长的递增子序列
     * dp[0]=1
     * dp[i]=循环判断当前元素是否大于前面的0~j中最长序列 >则dp[i]=dp[j]++与dp[i]取最大的
     */
    public static int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 二分搜索 有多少堆牌上升子序列就是多少
     * 循环数组发现牌大于所有牌顶则新起一堆牌(新起的牌顶是自身)
     * 小于则放置牌顶
     */
    public static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        //牌堆数量
        int len = 0;
        //牌顶数组
        int[] top = new int[nums.length];
        //遍历所有的牌
        for (int num : nums) {
            int j = 0;
            //由于top数组有序,可进行二分搜索优化时间为：O(nlogn)
            for (; j < len; j++) {
                if (top[j] >= num) {
                    //发现循环的数组是小于目前牌顶的则直接吧这个数放在这个牌顶处
                    top[j] = num;
                    break;
                }
            }
            if (j == len) {
                len++;
                top[j] = num;
            }
        }

        return len;
    }


    public static void main(String[] args) {
        int[] nums = {5, 9, 6, 5, 3, 7, 101, 18};
        lengthOfLIS(nums);
    }
}
