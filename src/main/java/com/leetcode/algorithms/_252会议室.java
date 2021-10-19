package com.leetcode.algorithms;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (会议室)
 * https://leetcode-cn.com/problems/meeting-rooms/
 * @date 2021/7/8 10:51
 */
public class _252会议室 {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        //按照开始时间从小到大排序
        Arrays.sort(intervals, (m1, m2) -> {
            return m1[0] - m2[0];
        });
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
