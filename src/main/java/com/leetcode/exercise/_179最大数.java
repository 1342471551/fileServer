package com.leetcode.exercise;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (最大数)
 * https://leetcode-cn.com/problems/largest-number/
 * @date 2021/4/12 15:11
 */
public class _179最大数 {
    public static String largestNumber(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .map(i -> Integer.toString(i))
                .sorted((s1, s2) -> {
                    String sum1 = s1 + s2;
                    String sum2 = s2 + s1;
                    //使用自定义Comparator方法排序,两个字符串前后相加从头比较大小
                    for (int i = 0; i < sum1.length(); i++) {
                        if (sum1.charAt(i) != sum2.charAt(i)) {
                            //返回从大到小,所以是sum2-sum1
                            return sum2.charAt(i) - sum1.charAt(i);
                        }
                    }
                    return 0;
                })
                //合并string数据,返回为Optional类型
                .reduce(String::concat)
                .filter(s -> !s.startsWith("0"))
                .orElse("0");
    }
}
