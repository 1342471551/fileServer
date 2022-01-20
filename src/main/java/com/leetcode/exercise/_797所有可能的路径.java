package com.leetcode.exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @Description: (所有可能的路径)
 * https://leetcode-cn.com/problems/all-paths-from-source-to-target/
 * @date 2021/8/25 9:39
 */
public class _797所有可能的路径 {

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(ans, path, graph, 0);
        return ans;
    }

    //深度优先算法
    private void dfs(List<List<Integer>> ans, List<Integer> path, int[][] graph, int i) {
        path.add(i);
        //发现当前遍历节点为空[](没有下属节点了) 添加数组结束循环
        if (i == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            return;
        }
        //遍历当前i节点下面的子节点
        for (int item : graph[i]) {
            dfs(ans, path, graph, item);
            //回溯还原path长度
            path.remove(path.size() - 1);
        }
    }
}
