package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (按权重随机选择)
 * https://leetcode-cn.com/problems/random-pick-with-weight/
 * @date 2021/8/30 15:48
 */
public class _528按权重随机选择 {

    int[] pre;
    int total;

    public _528按权重随机选择(int[] w) {
        pre = new int[w.length];
        pre[0] = w[0];
        for (int i = 1; i < w.length; ++i) {
            pre[i] = pre[i - 1] + w[i];
        }
        //按照数字
        total = Arrays.stream(w).sum();
    }

    public int pickIndex() {
        int x = (int) (Math.random() * total) + 1;
        return binarySearch(x);
    }

    private int binarySearch(int x) {
        int low = 0, high = pre.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (pre[mid] < x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
