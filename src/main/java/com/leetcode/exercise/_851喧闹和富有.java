package com.leetcode.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangyj
 * @Description: (喧闹和富有)
 * https://leetcode-cn.com/problems/loud-and-rich/
 * @date 2021/12/15 9:11
 */
public class _851喧闹和富有 {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<Integer>();
        }
        //记录比自己有钱的list集合数组
        for (int[] r : richer) {
            g[r[1]].add(r[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            dfs(i, quiet, g, ans);
        }
        return ans;
    }

    public void dfs(int x, int[] quiet, List<Integer>[] g, int[] ans) {
        if (ans[x] != -1) {
            return;
        }
        ans[x] = x;
        for (int y : g[x]) {
            dfs(y, quiet, g, ans);
            if (quiet[ans[y]] < quiet[ans[x]]) {
                ans[x] = ans[y];
            }
        }
    }


    public int[] loudAndRich1(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] lists = new List[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int[] var : richer) {
            lists[var[1]].add(var[0]);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            dfs1(lists, res, quiet, i);
        }
        return res;
    }

    public void dfs1(List<Integer>[] lists, int[] res, int[] quiet, int n) {
        if (res[n] != -1) return;
        res[n] = n;
        for (Integer list : lists[n]) {
            dfs1(lists, res, quiet, list);
            if (quiet[res[list]] < quiet[res[n]]) {
                res[n] = res[list];
            }
        }
    }
}
