package com.algorithm.structure.union;

/**
 * @author wangyj
 * @Description: (并查集)
 * @date 2021/5/7 9:41
 */
public abstract class UnionFind implements UF{
    protected int[] parents;

    public UnionFind(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity is not <=0");
        }
        parents = new int[capacity];
    }

    public abstract void union(int v1, int v2);

    //查找V所属集合(根节点)
    public abstract int find(int v);

    //查找v1,v2是否属于相同集合
    public boolean isSame(int v1, int v2) {
        return find(v1) == find(v2);
    }

    protected void rangeCheck(int v) {
        if (v < 0 || v >= parents.length) {
            throw new IllegalArgumentException("v is out of bounds");
        }
    }
}
