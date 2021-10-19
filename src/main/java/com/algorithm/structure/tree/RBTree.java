package com.algorithm.structure.tree;

/**
 * @author wangyj
 * @Description: (红黑树 等价四阶B树 2 - 3 - 4树)
 * @date 2021/4/12 11:30
 */
public class RBTree<E> extends BBST<E> {
    //定义红黑树节点颜色
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    public RBTree() {
    }

    //重新定义红黑树添加之后操作
    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        //添加的是根节点
        if (parent == null) {
            black(node);
            return;
        }
        //1.父节点为黑色不需要处理
        if (isBlack(parent)) return;
        //2.uncle节点是红色
        Node<E> uncle = parent.sibling();
        Node<E> grand = parent.parent;
        if (isRed(uncle)) {
            //父节点,叔父节点染黑
            black(parent);
            black(uncle);
            //祖父节点当做新添加节点,递归调用
            afterAdd(red(grand));
            return;
        }
        //3.uncle节点为黑色或空(左右旋转)
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                //LL 父节点染黑,祖父节点染红 右旋转
                black(parent);
                red(grand);
                rotateRight(grand);
            } else {
                //LR
                black(node);
                red(grand);
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else {
            if (node.isLeftChild()) {
                //RL
                black(node);
                red(grand);
                rotateRight(parent);
                rotateLeft(grand);
            } else {
                //RR 父节点染黑,祖父节点染红 左旋转
                black(parent);
                red(grand);
                rotateLeft(grand);
            }
        }
    }

    //重新定义删除之后操作
    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        //如果删除节点为红色则直接返回
        if (isRed(node)) return;

        //用于取代node的子节点是红色(删除有一个红色子节点的黑色节点)
        if (isRed(replacement)) {
            //替代节点染黑即可,连线等操作二叉搜索树已经完成
            black(replacement);
            return;
        }

        //删除没有子节点的黑色节点
        Node<E> parent = node.parent;
        //1.删除的是根节点
        if (parent == null) return;
        //判断删除是左还是右(删除节点是叶子节点,指向父节点线已经断了,不能用sibling判断)
        //后面的或者代表删除元素兄弟节点为黑,并且没用元素可借,并且父节点为黑下溢导致父节点也下溢重新调用的情况-那时候线没有断判断兄弟节点的情况
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) {
            //被删除节点在左边,兄弟节点在右边
            if (isRed(sibling)) {
                //兄弟节点为红色,旋转让兄弟子节点变成当前节点兄弟节点左旋 | 兄弟节点变黑,父节点变红(旋转之前的)
                black(sibling);
                red(parent);
                rotateLeft(parent);
                //更换兄弟(旋转之后了,当前兄弟是父节点的右边了重新指向一下)
                sibling = parent.right;
            }
            //兄弟节点为黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentBlack = isBlack(parent);
                //兄弟节点借不了,下溢(兄弟节点染红,父节点染黑)
                red(sibling);
                black(parent);
                if (parentBlack) {
                    //若父节点为黑色,则父节点也发生上溢
                    afterRemove(parent, null);
                }
            } else {
                //兄弟节点至少有一个红色的,可以借给我
                if (isBlack(sibling.right)) {
                    //左边为黑色节点(null)RL 需要先右再左
                    rotateRight(sibling);
                    //重新赋值兄弟节点,不影响下面兄弟节点染色
                    sibling = parent.right;
                }
                //进行染色 兄弟节点继承父节点颜色,旋转后的左右染黑
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                //统一对父节点左旋转
                rotateLeft(parent);
            }
        } else {
            //和上面相反,删除在右,兄弟在左
            if (isRed(sibling)) {
                //兄弟节点为红色,旋转让兄弟子节点变成当前节点兄弟节点右旋 | 兄弟节点变黑,父节点变红(旋转之前的)
                black(sibling);
                red(parent);
                rotateRight(parent);
                //更换兄弟(旋转之后了,当前兄弟是父节点的左边了重新指向一下)
                sibling = parent.left;
            }
            //兄弟节点为黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                boolean parentBlack = isBlack(parent);
                //兄弟节点借不了,下溢(兄弟节点染红,父节点染黑)
                red(sibling);
                black(parent);
                if (parentBlack) {
                    //若父节点为黑色,则父节点也发生上溢
                    afterRemove(parent, null);
                }
            } else {
                //兄弟节点至少有一个红色的,可以借给我
                if (isBlack(sibling.left)) {
                    //左边为黑色节点(null)LR 需要先左再右
                    rotateLeft(sibling);
                    //重新赋值兄弟节点,不影响下面兄弟节点染色
                    sibling = parent.left;
                }
                //进行染色 兄弟节点继承父节点颜色,旋转后的左右染黑
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                //统一对父节点右旋转
                rotateRight(parent);
            }
        }
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    //给传入节点进行染色
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return null;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    //判断当前节点颜色
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private static class RBNode<E> extends Node<E> {
        //节点颜色
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
