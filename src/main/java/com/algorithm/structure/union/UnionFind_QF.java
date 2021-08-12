package com.algorithm.structure.union;

/**
 * @author wangyj
 * @Description: (QucikFind操作)
 * find O(1) union O(n)
 * @date 2021/5/7 9:55
 */
public class UnionFind_QF extends UnionFind{
    public UnionFind_QF(int capacity) {
        super(capacity);
    }

    //合并两个集合,把v2根节点赋值给v1所在集合
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) return;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == p1) {
                parents[i] = p2;
            }
        }
    }

    //查找V所属集合(根节点)
    public int find(int v) {
        rangeCheck(v);
        return parents[v];
    }
}
