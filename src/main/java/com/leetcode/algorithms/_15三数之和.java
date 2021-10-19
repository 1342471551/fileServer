package com.leetcode.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangyj
 * @Description: (三数之和)
 * https://leetcode-cn.com/problems/3sum/
 * @date 2021/7/5 10:22
 */
public class _15三数之和 {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 3) return res;

        //数据排序
        Arrays.sort(nums);

        //i代表当前扫描元素 l左元素初始值 r右元素初始值
        for (int i = 0; i < nums.length - 2; i++) {
            //排除找到元素重复问题
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1, remain = -nums[i];
            while (l < r) {
                int sumLr = nums[l] + nums[r];
                if (remain == sumLr) {
                    //找到符合的元素
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //跳过相同的值(去重)
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sumLr < remain) {
                    //总数偏小 右移
                    l++;
                } else {
                    //总数偏大 左移
                    r--;
                }
            }
        }
        return res;
    }
}
