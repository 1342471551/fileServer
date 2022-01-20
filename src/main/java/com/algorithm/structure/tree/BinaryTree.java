package com.algorithm.structure.tree;

import com.algorithm.structure.tree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author wangyj
 * @Description: (公共二叉树)
 * @date 2021/4/7 10:50
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    //记录根节点
    protected Node<E> rootNode;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        rootNode = null;
    }

    //使用队列层序遍历
    public void levelOrderTraversal(Visitor<E> visitor) {
        if (rootNode == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            //把元素传给用户
            if (visitor.visit(poll.element)) return;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
    }

    //前序遍历递归调用
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        preorderTraversal(rootNode, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        //把元素传给用户
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }


    //前序遍历使用非递归方式！(使用栈结构)
    public void preorderTraversalNonRecursive(Visitor<E> visitor) {
        if (visitor == null || rootNode == null) return;
        Node<E> node = rootNode;
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                //取出此节点元素
                if (visitor.visit(node.element)) return;
                //把右子树入栈
                if (node.right != null) stack.push(node.right);
                //继续向左子树走
                node = node.left;
            } else if (stack.isEmpty()) {
                //栈为空,遍历结束
                return;
            } else {
                //弹出栈顶元素,继续循环操作
                node = stack.pop();
            }
        }
    }

    //中序遍历递归调用
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        inorderTraversal(rootNode, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left, visitor);
        //双重判断stop是否为true停止递归调用
        if (visitor.stop) return;
        //把元素传给用户
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    //中序遍历非递归方法！ (栈)
    public void inorderTraversalNonRecursive(Visitor<E> visitor) {
        if (visitor == null || rootNode == null) return;
        Node<E> node = rootNode;
        Stack<Node<E>> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                //栈为空返回
                return;
            } else {
                //node为空,栈不为空 弹出元素
                node = stack.pop();
                if (visitor.visit(node.element)) return;
                //遍历当前节点的右子树循环此操作
                node = node.right;
            }
        }
    }

    //后序遍历递归调用
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) return;
        postorderTraversal(rootNode, visitor);
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        //双重判断stop是否为true停止递归调用
        if (visitor.stop) return;
        //把元素传给用户
        visitor.stop = visitor.visit(node.element);
    }

    //后序遍历非递归方式 (栈)
    public void postorderTraversalNonRecursive(Visitor<E> visitor) {
        if (visitor == null || rootNode == null) return;
        Node<E> prev = null;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(rootNode);
        while (!stack.isEmpty()) {
            Node<E> top = stack.peek();
            //当栈顶是叶子节点,或者上个弹出元素他的父节点是本次弹出节点则本元素弹出不再执行左右入栈操作(避免重复入栈造成死循环)
            if (top.isLeaf() || (prev != null && prev.parent == top)) {
                prev = stack.pop();
                if (visitor.visit(prev.element)) return;
            } else {
                if (top.right != null) {
                    stack.push(top.right);
                }
                if (top.left != null) {
                    stack.push(top.left);
                }
            }
        }
    }

    //寻找传入节点的前驱节点
    protected Node<E> predecessor(Node<E> node) {
        if (node == null) return null;
        //查找此节点左子树的最右节点
        if (node.left != null) {
            Node<E> left = node.left;
            while (left.right != null) {
                left = left.right;
            }
            return left;
        }
        //查找此节点的第一个左父节点
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        //1.node.parent为null
        //2.node==node.parent.right
        return node.parent;
    }

    //寻找传入节点的后继节点
    protected Node<E> successor(Node<E> node) {
        if (node == null) return null;
        //查找此节点右子树的最左节点
        if (node.right != null) {
            Node<E> right = node.right;
            while (right.left != null) {
                right = right.right;
            }
            return right;
        }
        //查找此节点的第一个右父节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        //1.node.parent为null
        //2.node==node.parent.left
        return node.parent;
    }


    //返回二叉树高度(递归)
    public int recursionHeight() {
        return recursionHeight(rootNode);
    }

    private int recursionHeight(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(recursionHeight(node.left), recursionHeight(node.right));
    }

    //使用迭代计算高度(层序遍历)
    public int height() {
        if (rootNode == null) return 0;
        //默认访问高度
        int height = 0;
        //定义队列中的节点
        int levelSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            levelSize--;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    //使用层序遍历判断一棵树是否是完全二叉树
    public boolean isComplete() {
        if (rootNode == null) return false;
        //判断队列中是否只剩叶子节点
        boolean leaf = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            Node<E> poll = queue.poll();
            //判断队列中元素只有叶子节点,否则不是完全二叉树
            if (leaf && !poll.isLeaf()) {
                return false;
            }
            if (poll.left != null && poll.right != null) {
                //左右节点都不为空,入队
                queue.offer(poll.left);
                queue.offer(poll.right);
            } else if (poll.left == null && poll.right != null) {
                //左节点为空,右节点不为空说明不是完全二叉树
                return false;
            } else {
                //没有左右节点或者只有左节点则之后都是叶子节点了
                //若满足完全二叉树则之后都不会有入队的元素
                leaf = true;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
            }
        }
        return true;
    }

    //使用打印方法类自定义实现
    @Override
    public Object root() {
        return rootNode;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    //自定义接口,前中后层序遍历后把元素交给用户,让用户决定如何使用
    public static abstract class Visitor<E> {
        //定义停止标识符
        boolean stop;

        /**
         * 用户自定义实现类,使用此元素
         *
         * @return false继续 true停止
         */
        protected abstract boolean visit(E element);
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    protected static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        //记录树的父节点
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        //判断当前节点是否是叶子节点
        public boolean isLeaf() {
            return left == null && right == null;
        }

        //判断当前节点有两个叶子节点
        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        //判断自己是左子树
        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        //判断自己是右子树
        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        //返回兄弟节点
        public Node<E> sibling() {
            if (isLeftChild()) return parent.right;
            if (isRightChild()) return parent.left;
            return null;
        }
    }
}
