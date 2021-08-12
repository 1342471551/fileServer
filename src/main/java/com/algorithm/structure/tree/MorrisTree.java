package com.algorithm.structure.tree;

import com.algorithm.structure.tree.printer.BinaryTrees;

import java.util.Random;

/**
 * @author wangyj
 * @Description: (Morris实现遍历方法的空间优化)
 * @date 2021/6/28 11:54
 */
public class MorrisTree extends BinarySearchTree<Integer> {

    //使用空间O(1)的形式进行中序遍历 时间O(2n)
    public void inorder() {
        Node<Integer> node = rootNode;
        while (node != null) {
            if (node.left != null) {
                //找到前驱节点(predecessor)
                Node<Integer> pred = node.left;
                //防止第二次查找的时候由于指针改变,循环查到当前node自己
                while (pred.right != null && pred.right != node) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    //第一次拿到前驱节点
                    pred.right = node;
                    node = node.left;
                } else {
                    //第二次获取到了 pred.right=node
                    System.out.print(node.element + " ");
                    pred.right = null;
                    node = node.right;
                }
            } else {
                System.out.print(node.element + " ");
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        MorrisTree morrisTree = new MorrisTree();
        for (int i = 0; i < 10; i++)
            morrisTree.add(new Random().nextInt(100));
        BinaryTrees.println(morrisTree);
    }

}
