package com.leetcode.algorithms;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author wangyj
 * @Description: (滑动窗口最大值)
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * @date 2021/7/15 9:34
 */
public class _239滑动窗口最大值 {

    //使用双端队列 1.队列存放索引 2.元素从头到位在队列递减(发现大于队尾元素删除队尾元素存放自己)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) return new int[0];
        if (k == 1) return nums;
        int[] maxes = new int[nums.length - k + 1];
        //定义双端队列
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int w = i - k + 1;
            //删除nums[队尾]<=nums[i]的值 并且入队
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            //判断w是否合法
            if (w < 0) continue;
            //检查队头合法性(是否在滑动窗口内)
            if (deque.peekFirst() < w) {
                deque.pollFirst();
            }
            maxes[w] = nums[deque.peekFirst()];
        }
        return maxes;
    }

    //使用动态规划
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int right = k - 1;
        int[] dp = new int[nums.length - right];
        for (int i = 0; i < k; i++) {
            dp[0] = Math.max(dp[0], nums[i]);
        }
        int p = k;
        for (int i = 1; i < dp.length; i++, p++) {
            if (nums[i - 1] == dp[i - 1]) {
                //删除的刚好之最大
                //需要重新找到最大值
                dp[i] = nums[i];
                for (int j = i + 1; j <= p; j++) {
                    dp[i] = Math.max(dp[i], nums[j]);
                }
            } else {
                //删除的不是最大值
                dp[i] = dp[i - 1] > nums[p] ? dp[i - 1] : nums[p];
            }
        }
        return dp;
    }
}
