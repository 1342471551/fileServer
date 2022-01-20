package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (可怜的小猪)
 * https://leetcode-cn.com/problems/poor-pigs/
 * @date 2021/11/25 16:28
 */
public class _458可怜的小猪 {

    /**
     * @param buckets 桶数
     * @param minutesToDie 死亡时间
     * @param minutesToTest 多少分钟内
     * @return 最少猪数
     */
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        int maxRound = minutesToTest/minutesToDie+1;
        while(Math.pow(maxRound,pigs) < buckets){
            pigs++;
        }
        return pigs;
    }
}
