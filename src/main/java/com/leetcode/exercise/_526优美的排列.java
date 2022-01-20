package com.leetcode.exercise;

/**
 * @author wangyj
 * @Description: (优美的排列)
 * https://leetcode-cn.com/problems/beautiful-arrangement/
 * @date 2021/8/16 4:12 下午
 */
public class _526优美的排列 {

    int count = 0;

    public int countArrangement(int N) {
        int[] visited = new int[N + 1];
        countArrangement(1, N, visited);
        return count;
    }

    public void countArrangement(int step, int N, int[] visited) {
        //step为N+1说明找到了一种满足情况的数组
        if (step == N + 1) {
            count++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            //用过的不能再次使用，避免重复
            if (visited[i] == 0) {
                visited[i] = 1;
                //限制条件进行，剪枝
                if (i % step == 0 || step % i == 0) {
                    countArrangement(step + 1, N, visited);
                }
                visited[i] = 0;
            }
        }

    }
}
