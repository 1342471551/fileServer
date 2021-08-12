package com.algorithm.structure.set;

import com.algorithm.structure.tree.BinaryTree;
import com.algorithm.structure.tree.Comparator;
import com.algorithm.structure.tree.RBTree;

/**
 * @author wangyj
 * @Description: (使用红黑树实现set集合)
 * @date 2021/4/15 10:58
 */
public class TreeSet<E> implements Set<E> {

    public TreeSet() {
    }

    public TreeSet(Comparator<E> comparator) {
        tree = new RBTree<>(comparator);
    }

    private RBTree<E> tree = new RBTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorderTraversal(new BinaryTree.Visitor<E>() {
            @Override
            protected boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}