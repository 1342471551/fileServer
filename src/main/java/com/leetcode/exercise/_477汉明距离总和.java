package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (汉明距离总和)
 * https://leetcode-cn.com/problems/total-hamming-distance/
 * @date 2021/5/28 16:29
 */
public class _477汉明距离总和 {

    //根据int类型32位进行位循环 每一位中不同的和为C*(N-C) n为总数c为1的个数,n-c为0的个数
    //整数val二进制的第i位,我们可以用代码(val >> i) & 1表示
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        int len = nums.length;
        for (int i = 0; i < 30; i++) {
            int oneCount = 0;
            int temp = 0;
            for (int j = 0; j < len; j++) {
                oneCount += nums[j] & 1;
                nums[j] >>= 1;
                temp += nums[j] == 0 ? 1 : 0;
            }
            res += oneCount * (len - oneCount);
            if (temp == len) break;
        }
        return res;
    }


}
