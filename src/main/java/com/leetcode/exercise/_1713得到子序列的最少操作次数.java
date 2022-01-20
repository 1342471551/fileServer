package com.leetcode.exercise;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author wangyj
 * @Description: (得到子序列的最少操作次数)
 * https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/
 * @date 2021/7/20 19:11
 */
public class _1713得到子序列的最少操作次数 {

    public int minOperations(int[] target, int[] arr) {
        //将arr数组中的值替换成target数组对应元素的下表，如果没有则用-1代替。
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; ++i) {
            map.put(target[i], i);
        }
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = map.getOrDefault(arr[i], -1);
        }
        //使用二分版本的最长上升子序列避免超时，遇到-1元素直接跳过。
        int[] num = new int[arr.length];
        //定义最大上升序列的个数
        int tot = 0;
        //循环一轮算出arr中target最大上升序列的个数
        for (int i = 0; i < arr.length; ++i) {
            //t获取的是target中对应元素的下标
            int t = arr[i];
            if (t == -1) continue;
            int l = 0, r = tot;
            //查找t对应下标(target中元素的位置)放到数组num合适位置
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (num[mid] < t) l = mid;
                else r = mid - 1;
            }
            num[l + 1] = t;
            tot = Math.max(l + 1, tot);
        }
        //原来的值减去最大上升序列个数就是需要挪动的值
        return target.length - tot;
    }

    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
