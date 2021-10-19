package com.algorithm.structure.tree;

/**
 * @author wangyj
 * @Description: (平衡二叉树)
 * @date 2021/4/12 20:32
 */
public class BBST<E> extends BinarySearchTree<E> {

    public BBST(Comparator<E> comparator) {
        super(comparator);
    }

    public BBST() {
    }

    //节点左旋转
    protected void rotateLeft(Node<E> grade) {
        //旋转右子变父
        Node<E> parent = grade.right;
        grade.right = parent.left;
        parent.left = grade;
        //让parent成为grade 改变parent父节点指向,和现在父节点对他的指向
        parent.parent = grade.parent;
        if (grade.isLeftChild()) {
            grade.parent.left = parent;
        } else if (grade.isRightChild()) {
            grade.parent.right = parent;
        } else {
            //原来的grade就是根节点
            rootNode = parent;
        }
        //让grade成为parent 改变grade父节点指向,和现在父节点对他的指向
        grade.parent = parent;
        if (grade.right != null) {
            grade.right.parent = grade;
        }
    }

    //节点右旋转
    protected void rotateRight(Node<E> grade) {
        //旋转
        Node<E> parent = grade.left;
        grade.left = parent.right;
        parent.right = grade;
        //改变parent父节点
        parent.parent = grade.parent;
        if (grade.isLeftChild()) {
            grade.parent.left = parent;
        } else if (grade.isRightChild()) {
            grade.parent.right = parent;
        } else {
            rootNode = parent;
        }
        //改变grade父节点
        grade.parent = parent;
        if (grade.left != null) {
            grade.left.parent = grade;
        }
    }


    //不考虑分布的旋转排序
    protected void uniteRotate(Node<E> r, //原始的根节点
                             Node<E> a, Node<E> b, Node<E> c,
                             Node<E> d,
                             Node<E> e, Node<E> f, Node<E> g) {
        //d成为这个子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            rootNode = d;
        }
        //处理a-b-c
        b.left = a;
        b.right = c;
        if (a != null) a.parent = b;
        if (c != null) c.parent = b;
        //处理e-f-g
        f.left = e;
        f.right = g;
        if (e != null) e.parent = f;
        if (g != null) g.parent = f;
        //处理b-d-f
        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }
}
