package com.leetcode.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyj
 * @Description: (找到小镇的法官)
 * https://leetcode-cn.com/problems/find-the-town-judge/
 * @date 2021/12/19 4:39 下午
 */
public class _997找到小镇的法官 {

    public static int findJudge(int n, int[][] trust) {
        HashMap<Integer, Integer> hashMap = new HashMap(n);
        int[] val = new int[n+1];
        for (int i = 0; i < trust.length; i++) {
            hashMap.put(trust[i][1], hashMap.getOrDefault(trust[i][1], 0) + 1);
            val[trust[i][0]] = 1;
        }
        for (int i = 1; i < val.length; i++) {
            if (val[i] == 0 && hashMap.getOrDefault(i, 0) == n - 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] trust = new int[][]{{1, 2}};
        System.out.println(findJudge(2, trust));
    }
}
