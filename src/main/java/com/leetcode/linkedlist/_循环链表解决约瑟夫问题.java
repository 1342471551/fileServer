package com.leetcode.linkedlist;

import com.algorithm.structure.list.linked.CircleLinkedList;

/**
 * @author wangyj
 * @Description: (1到8, 数三次删除指向元素 输出最后一个元素)
 * @date 2021/3/31 19:31
 */
public class _循环链表解决约瑟夫问题 {
    public static void main(String[] args) {
        CircleLinkedList<Integer> list = new CircleLinkedList<Integer>();
        for (int i = 1; i <= 8; i++) {
            list.add(i);
        }
        //指针指向头节点
        list.reset();
        //进行循环删除
        while (!list.isEmpty() && list.size() > 1) {
            list.next();
            list.next();
            System.out.println(list.remove());
        }
        System.out.println("最后存活元素为:" + list.getCurrent());
    }
}
