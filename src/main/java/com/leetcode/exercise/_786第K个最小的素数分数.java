package com.leetcode.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangyj
 * @Description: (第K个最小的素数分数)
 * https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/
 * @date 2021/11/29 9:08
 */
public class _786第K个最小的素数分数 {

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        List<int[]> frac = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                frac.add(new int[]{arr[i], arr[j]});
            }
        }
        // a/b<c/d = a*d<b*c 可以这么等价
        Collections.sort(frac, (x, y) -> x[0] * y[1] - y[0] * x[1]);
        return frac.get(k - 1);
    }
}
