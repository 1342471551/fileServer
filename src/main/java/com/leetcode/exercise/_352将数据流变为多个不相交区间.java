package com.leetcode.exercise;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author wangyj
 * @Description: (将数据流变为多个不相交区间)
 * https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals/
 * @date 2021/10/9 9:49
 */
public class _352将数据流变为多个不相交区间 {

    TreeMap<Integer, Integer> intervals;

    public _352将数据流变为多个不相交区间() {
        intervals = new TreeMap<Integer, Integer>();
    }

    public void addNum(int val) {
        // 找到 l1 最小的且满足 l1 > val 的区间 interval1 = [l1, r1]
        // 如果不存在这样的区间，interval1 为尾迭代器
        Map.Entry<Integer, Integer> interval1 = intervals.ceilingEntry(val + 1);
        // 找到 l0 最大的且满足 l0 <= val 的区间 interval0 = [l0, r0]
        // 在有序集合中，interval0 就是 interval1 的前一个区间
        // 如果不存在这样的区间，interval0 为尾迭代器
        Map.Entry<Integer, Integer> interval0 = intervals.floorEntry(val);

        if (interval0 != null && interval0.getKey() <= val && val <= interval0.getValue()) {
            // 情况一
            return;
        } else {
            boolean leftAside = interval0 != null && interval0.getValue() + 1 == val;
            boolean rightAside = interval1 != null && interval1.getKey() - 1 == val;
            if (leftAside && rightAside) {
                // 情况四
                int left = interval0.getKey(), right = interval1.getValue();
                intervals.remove(interval0.getKey());
                intervals.remove(interval1.getKey());
                intervals.put(left, right);
            } else if (leftAside) {
                // 情况二
                intervals.put(interval0.getKey(), interval0.getValue() + 1);
            } else if (rightAside) {
                // 情况三
                int right = interval1.getValue();
                intervals.remove(interval1.getKey());
                intervals.put(val, right);
            } else {
                // 情况五
                intervals.put(val, val);
            }
        }
    }

    public int[][] getIntervals() {
        int size = intervals.size();
        int[][] ans = new int[size][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            int left = entry.getKey(), right = entry.getValue();
            ans[index][0] = left;
            ans[index][1] = right;
            ++index;
        }
        return ans;
    }
}
