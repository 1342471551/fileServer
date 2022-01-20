package com.leetcode.exercise;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author wangyj
 * @Description: (课程表III)
 * https://leetcode-cn.com/problems/course-schedule-iii/
 * @date 2021/12/14 9:09
 */
public class _630课程表III {

    //优先队列 + 贪心
    public static int scheduleCourse(int[][] courses) {
        //按照完成截止日期进行排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);

        //最大堆
        PriorityQueue<Integer> q = new PriorityQueue<Integer>((a, b) -> b - a);
        // 优先队列中所有课程的总时间
        int total = 0;

        //按照开始时间贪心加入最大堆中,发现结束时间小于堆中最大时间 进行结果替换
        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            if (total + ti <= di) {
                total += ti;
                q.offer(ti);
            } else if (!q.isEmpty() && q.peek() > ti) {
                //因为是按照最低完成期限排序的，要是用时少必然完成时间也满足
                total -= q.poll() - ti;
                q.offer(ti);
            }
        }

        return q.size();
    }

    public static int scheduleCourse1(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        int total = 0;
        for (int[] val : courses) {
            int t1 = val[0], d1 = val[1];
            if (total + t1 <= d1) {
                priorityQueue.offer(t1);
                total += t1;
            } else if (!priorityQueue.isEmpty() && priorityQueue.peek() > t1) {
                total -= priorityQueue.poll() - t1;
                priorityQueue.offer(t1);
            }
        }
        return priorityQueue.size();
    }

}
