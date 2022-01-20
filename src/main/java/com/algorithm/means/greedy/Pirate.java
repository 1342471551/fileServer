package com.algorithm.means.greedy;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (海盗问题) 贪心算法
 * @date 2021/6/4 14:19
 */
public class Pirate {


    int pirate(int capacity) {
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);
        int weight = 0, count = 0;
        for (int i = 0; i < weights.length; i++) {
            int newWeight = weight + weights[i];
            if (newWeight <= capacity) {
                weight = newWeight;
                count++;
            }
        }
        return count;
    }
}
