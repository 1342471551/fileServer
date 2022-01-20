package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (救生艇)
 * https://leetcode-cn.com/problems/boats-to-save-people/
 * @date 2021/8/26 9:21
 */
public class _881救生艇 {

    public int numRescueBoats(int[] people, int limit) {
        int sum = 0;
        Arrays.sort(people);
        int right = people.length - 1;
        int left = 0;
        while (left <= right) {
            if (left == right) {
                sum++;
                break;
            } else if (people[left] + people[right] > limit) {
                sum++;
                right--;
            } else {
                //只需要考虑最重和最轻的一起就好,因为只能两个人做一个船
                sum++;
                right--;
                left++;
            }
        }
        return sum;
    }
}
