package com.algorithm.structure.tree;

/**
 * @author wangyj
 * @Description: (自定义比较器,让二叉搜索树加入此对象 用户使用可以创建不同的满足个性化需求)
 * @date 2021/4/2 14:40
 */
public interface Comparator<E>{
    int compare(E e1, E e2);
}
