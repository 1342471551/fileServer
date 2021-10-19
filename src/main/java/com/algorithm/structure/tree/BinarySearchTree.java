package com.algorithm.structure.tree;

import com.algorithm.structure.tree.printer.BinaryTrees;

/**
 * @author wangyj
 * @Description: (二叉搜索数)
 * @date 2021/4/2 13:30
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    //传入比较器进行树的比较
    private Comparator<E> comparator;

    //构造方法需要用户传入比较器
    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    //定义不带比较器的搜索树,需要用户传入对象E默认继承了Comparable
    public BinarySearchTree() {
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    //提供外部删除接口
    public void remove(E element) {
        remove(node(element));
    }

    //真正的删除操作
    private void remove(Node<E> node) {
        //判断传入没有此节点
        if (node == null) return;
        size--;
        //判断度为二的删除操作(找到前驱或后继节点替换 删除其前驱或者后继)
        if (node.hasTwoChildren()) {
            //找到前驱节点
            Node<E> preNode = predecessor(node);
            //进行值的覆盖
            node.element = preNode.element;
            //删除前驱节点(把前驱节点当作要删除的节点)
            node = preNode;
        }
        //删除node节点(度为1或0)
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //度为1节点 1.更改子节点指向的parent
            replacement.parent = node.parent;
            //2.更改删除节点父节点(left,right)指向->删除节点的子节点
            if (node.parent == null) {
                //node是度为1的节点并且是根节点
                rootNode = replacement;
            } else if (node == node.parent.left) {
                //父节点左指向删除节点的子节点
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                //父节点右指向删除节点的子节点
                node.parent.right = replacement;
            }
            //删除节点后序操作(恢复平衡)
            afterRemove(node,replacement);
        } else if (node.parent == null) {
            //度为0节点 1.是叶子节点且父节点为空(只有一个根节点)
            rootNode = null;
            //删除节点后序操作(恢复平衡)
            afterRemove(node,null);
        } else {
            if (node == node.parent.left) {
                //度为0节点 2.是父节点的左叶子节点
                node.parent.left = null;
            } else {
                //度为0节点 3.是父节点的右叶子节点
                node.parent.right = null;
            }
            //删除节点后序操作(恢复平衡)
            afterRemove(node,null);
        }
    }

    //用户传入元素返回节点
    private Node<E> node(E element) {
        Node<E> node = rootNode;
        while (node != null) {
            int compare = compare(element, node.element);
            if (compare == 0) return node;
            if (compare > 0) node = node.right;
            if (compare < 0) node = node.left;
        }
        return null;
    }

    public void add(E element) {
        elementNotNullCheck(element);
        //判断添加的是否是根节点
        if (rootNode == null) {
            rootNode = createNode(element, null);
            size++;
            //添加之后处理
            afterAdd(rootNode);
            return;
        }
        //从根节点开始向下进行比较
        Node<E> node = rootNode;
        //定义根节点为默认父节点
        Node<E> parent = rootNode;
        //记录元素和根节点的比较情况
        int compare = 0;
        while (node != null) {
            //保存查找到的父节点
            parent = node;
            compare = compare(element, node.element);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                //若插入值和二叉树值相等进行覆盖
                node.element = element;
                return;
            }
        }
        //新定义插入节点,放置元素位置
        Node<E> newNode = createNode(element, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        //添加之后处理
        afterAdd(newNode);
        size++;
    }

    //默认交给子类实现,添加之后操作 node新添加的节点
    protected void afterAdd(Node<E> node) {
    }

    //默认交给子类实现,删除之后操作 node删除的节点
    protected void afterRemove(Node<E> node,Node<E> replacement) {
    }



    /**
     * 返回值 e1添加元素 e2父节点元素
     * 0 两个元素相等
     * 大于0 e1>e2
     * 小于0 e1<e2
     */
    private int compare(E e1, E e2) {
        //若传入构造器则使用默认构造器的元素
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        //没用传入构造器,默认传入元素E继承了构造方法Comparable
        //一般内置的类型(int)等,都默认实现了Comparable接口
        return ((Comparable<E>) e1).compare(e2);
    }

    //检测插入元素不能为空
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not null!");
        }
    }


    public static void main(String[] args) {
        int[] element = {15, 23, 43, 634, 654, 123, 1235, 21, 57, 352};
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer e1, Integer e2) {
                return e1 - e2;
            }
        });
        for (int i = 0; i < element.length; i++) {
            binarySearchTree.add(element[i]);
        }
        BinaryTrees.print(binarySearchTree);
    }
}
