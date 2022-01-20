package com.algorithm.structure.tree;

/**
 * @author wangyj
 * @Description: (AVL平衡二叉树)
 * @date 2021/4/8 10:18
 */
public class AVLTree<E> extends BBST<E> {

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    public AVLTree() {
    }

    //重写父类添加之后操作,使树保持平衡
    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                //通过while循环更新添加节点所有父节点高度
                updateHeight(node);
            } else {
                //恢复平衡
                reBalance(node);
//                uniteReBalance(node); 另外一种旋转方式
                //到此节点树已经恢复平衡,上面的父节点高度不需要更新了
                break;
            }
        }
    }

    //重写父类删除之后操作,恢复树的平衡
    @Override
    protected void afterRemove(Node<E> node,Node<E> replacement) {
        while (node.parent!=null){
            if (isBalanced(node)){
                //更新高度
                updateHeight(node);
            }else {
                //恢复平衡
                reBalance(node);
            }
        }
    }

    //重写左旋操作,更新高度
    @Override
    protected void rotateLeft(Node<E> grade) {
        super.rotateLeft(grade);
        //更新高度
        updateHeight(grade);
        updateHeight(grade.right);
    }

    //重写右旋操作,更新高度
    @Override
    protected void rotateRight(Node<E> grade) {
        super.rotateRight(grade);
        //更新高度
        updateHeight(grade);
        updateHeight(grade.left);
    }

    //重写统一处理平衡方法,进行更新高度
    @Override
    protected void uniteRotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.uniteRotate(r, a, b, c, d, e, f, g);
        //按顺序从低到高更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    //重写父类创建节点方法,创建有高度的AVLNode
    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    //绝对值小于1判断是否失衡
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
    }

    //核心代码
    //统一处理失衡旋转不考虑左右子树分布情况
    private void uniteReBalance(Node<E> grand) {
        AVLNode<E> parent = ((AVLNode<E>) grand).tallChild();
        AVLNode<E> node = ((AVLNode<E>) grand).tallChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL情况
                uniteRotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else {
                //LR情况
                uniteRotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else {
            if (node.isLeftChild()) {
                //RL情况
                uniteRotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else {
                //RR情况
                uniteRotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }


    //核心恢复平衡
    //传入节点为高度最低的不平衡节点
    private void reBalance(Node<E> grand) {
        AVLNode<E> parent = ((AVLNode<E>) grand).tallChild();
        AVLNode<E> node = ((AVLNode<E>) grand).tallChild();
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL情况
                rotateRight(grand);
            } else {
                //LR情况
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                //RL情况
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR情况
                rotateLeft(grand);
            }
        }
    }




    //更新节点高度
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>) node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        //定义节点高度(默认添加的叶子节点高度)
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        //返回此节点的平衡因子
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        //更新此节点高度
        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        //返回左右子树最高的节点
        private AVLNode<E> tallChild() {
            AVLNode<E> left = ((AVLNode<E>) this.left);
            AVLNode<E> right = ((AVLNode<E>) this.right);
            if (left.height > right.height) return left;
            if (left.height < right.height) return right;
            //如果高度一样,判读是父节点的左/右节点,子节点也返回左/右 这样可以是LL/RR情况少旋转一次
            return isLeftChild() ? left : right;
        }
    }
}
