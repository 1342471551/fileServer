package com.algorithm.structure.union;

import java.util.Arrays;

/**
 * @author wangyj
 * @Description: (QuickUnion实现)
 * find O(logn) union O(logn)
 * @date 2021/5/7 9:59
 */
public class UnionFind_QU extends UnionFind {

    //存储元素节点长度,用于union优化
    private int[] sizes;

    public UnionFind_QU(int capacity) {
        super(capacity);
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

    @Override
    //把节点少的元素嫁接到元素多的上面
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = p2;
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = p1;
            sizes[p1] += sizes[p2];
        }
    }

    @Override
    public int find(int v) {
        rangeCheck(v);
        if (parents[v]!=v){
            //进行路径压缩,递归把v到根节点的指向全部改为根节点
            parents[v]=find(parents[v]);
        }
        return parents[v];
    }
}
