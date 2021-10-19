package com.algorithm.means.greedy;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (兑换硬币) 贪心算法从面值大的向下递减兑换
 * @date 2021/6/4 14:49
 */
public class CoinChange {

    Integer[] faces = {25, 10, 5, 1};

    int coinChange(int money) {
        int count = 0;
        //从大到小进行排序(默认从小到大)
        Arrays.sort(faces, (Integer f1, Integer f2) -> f2 - f1);
        for (int i = 0; i < faces.length; i++) {
            while (money >= faces[i]) {
                money -= faces[i];
                count++;
            }
        }
        return count;
    }
}
