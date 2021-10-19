package com.algorithm.structure.union;

/**
 * @author wangyj
 * @Description: (并查集接口)
 * @date 2021/5/7 9:52
 */
public interface UF {
    int find(int v);

    void union(int v1, int v2);

    boolean isSame(int v1, int v2);
}
