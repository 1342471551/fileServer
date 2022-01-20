package com.algorithm.structure.map;

import com.algorithm.structure.tree.Comparable;
import com.algorithm.structure.tree.Comparator;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author wangyj
 * @Description: (使用红黑树实现映射map)
 * @date 2021/4/15 11:39
 */
public class TreeMap<K, V> implements map<K, V> {

    //传入比较器进行树的比较
    private Comparator<K> comparator;

    //构造方法需要用户传入比较器
    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    //定义不带比较器的搜索树,需要用户传入对象E默认继承了Comparable
    public TreeMap() {
    }

    //定义红黑树节点颜色
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    protected int size;
    //记录根节点
    protected MapNode<K, V> rootNode;

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

    @Override
    public V put(K key, V value) {
        keyNotNullCheck(key);
        //判断添加的是否是根节点
        if (rootNode == null) {
            rootNode = new MapNode<>(key, value, null);
            size++;
            //添加之后处理
            afterPut(rootNode);
            return null;
        }
        //从根节点开始向下进行比较
        MapNode<K, V> node = rootNode;
        //定义根节点为默认父节点
        MapNode<K, V> parent = rootNode;
        //记录元素和根节点的比较情况
        int compare = 0;
        while (node != null) {
            //保存查找到的父节点
            parent = node;
            compare = compare(key, node.key);
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                //若插入值和二叉树值相等进行覆盖
                V oldValue = node.value;
                node.value = value;
                node.key = key;
                return oldValue;
            }
        }
        //新定义插入节点,放置元素位置
        MapNode<K, V> newNode = new MapNode<>(key, value, parent);
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        //添加之后处理
        afterPut(newNode);
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        MapNode<K, V> node = node(key);
        if (node != null) return node.value;
        return null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (rootNode == null) return false;
        Queue<MapNode<K, V>> queue = new LinkedList<>();
        queue.offer(rootNode);
        while (!queue.isEmpty()) {
            MapNode<K, V> poll = queue.poll();
            if (valEquals(poll.value, value)) return true;
            if (poll.left != null) queue.offer(poll.left);
            if (poll.right != null) queue.offer(poll.right);
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        traversal(rootNode, visitor);
    }

    public void traversal(MapNode<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;
        traversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    //检测插入元素不能为空
    private void keyNotNullCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("element can not null!");
        }
    }

    private void afterPut(MapNode<K, V> node) {
        MapNode<K, V> parent = node.parent;
        //添加的是根节点
        if (parent == null) {
            black(node);
            return;
        }
        //1.父节点为黑色不需要处理
        if (isBlack(parent)) return;
        //2.uncle节点是红色
        MapNode<K, V> uncle = parent.sibling();
        MapNode<K, V> grand = parent.parent;
        if (isRed(uncle)) {
            //父节点,叔父节点染黑
            black(parent);
            black(uncle);
            //祖父节点当做新添加节点,递归调用
            afterPut(red(grand));
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


    //给传入节点进行染色
    private MapNode<K, V> color(MapNode<K, V> node, boolean color) {
        if (node == null) return null;
        node.color = color;
        return node;
    }

    private MapNode<K, V> red(MapNode<K, V> node) {
        return color(node, RED);
    }

    private MapNode<K, V> black(MapNode<K, V> node) {
        return color(node, BLACK);
    }

    //判断当前节点颜色
    private boolean colorOf(MapNode<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(MapNode<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(MapNode<K, V> node) {
        return colorOf(node) == RED;
    }

    //节点左旋转
    private void rotateLeft(MapNode<K, V> grade) {
        //旋转右子变父
        MapNode<K, V> parent = grade.right;
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
    private void rotateRight(MapNode<K, V> grade) {
        //旋转
        MapNode<K, V> parent = grade.left;
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

    //真正的删除操作
    private V remove(MapNode<K, V> node) {
        //判断传入没有此节点
        if (node == null) return null;

        V oldValue = node.value;

        size--;
        //判断度为二的删除操作(找到前驱或后继节点替换 删除其前驱或者后继)
        if (node.hasTwoChildren()) {
            //找到前驱节点
            MapNode<K, V> preNode = predecessor(node);
            //进行值的覆盖
            node.key = preNode.key;
            node.value = preNode.value;
            //删除前驱节点(把前驱节点当作要删除的节点)
            node = preNode;
        }
        //删除node节点(度为1或0)
        MapNode<K, V> replacement = node.left != null ? node.left : node.right;
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
            afterRemove(node, replacement);
        } else if (node.parent == null) {
            //度为0节点 1.是叶子节点且父节点为空(只有一个根节点)
            rootNode = null;
            //删除节点后序操作(恢复平衡)
            afterRemove(node, null);
        } else {
            if (node == node.parent.left) {
                //度为0节点 2.是父节点的左叶子节点
                node.parent.left = null;
            } else {
                //度为0节点 3.是父节点的右叶子节点
                node.parent.right = null;
            }
            //删除节点后序操作(恢复平衡)
            afterRemove(node, null);
        }
        return oldValue;
    }

    private MapNode<K, V> node(K key) {
        MapNode<K, V> node = rootNode;
        while (node != null) {
            int compare = compare(key, node.key);
            if (compare == 0) return node;
            if (compare > 0) node = node.right;
            if (compare < 0) node = node.left;
        }
        return null;
    }

    //重新定义删除之后操作
    private void afterRemove(MapNode<K, V> node, MapNode<K, V> replacement) {
        //如果删除节点为红色则直接返回
        if (isRed(node)) return;

        //用于取代node的子节点是红色(删除有一个红色子节点的黑色节点)
        if (isRed(replacement)) {
            //替代节点染黑即可,连线等操作二叉搜索树已经完成
            black(replacement);
            return;
        }

        //删除没有子节点的黑色节点
        MapNode<K, V> parent = node.parent;
        //1.删除的是根节点
        if (parent == null) return;
        //判断删除是左还是右(删除节点是叶子节点,指向父节点线已经断了,不能用sibling判断)
        //后面的或者代表删除元素兄弟节点为黑,并且没用元素可借,并且父节点为黑下溢导致父节点也下溢重新调用的情况-那时候线没有断判断兄弟节点的情况
        boolean left = parent.left == null || node.isLeftChild();
        MapNode<K, V> sibling = left ? parent.right : parent.left;
        if (left) {
            //被删除节点在左边,兄弟节点在右边
            if (isRed(sibling)) {
                //兄弟节点为红色,旋转让兄弟子节点变成当前节点兄弟节点左旋 | 兄弟节点变红,父节点变黑(旋转之前的)
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
                //兄弟节点为红色,旋转让兄弟子节点变成当前节点兄弟节点右旋 | 兄弟节点变红,父节点变黑(旋转之前的)
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

    //寻找传入节点的前驱节点
    private MapNode<K, V> predecessor(MapNode<K, V> node) {
        if (node == null) return null;
        //查找此节点左子树的最右节点
        if (node.left != null) {
            MapNode<K, V> left = node.left;
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
    private MapNode<K, V> successor(MapNode<K, V> node) {
        if (node == null) return null;
        //查找此节点右子树的最左节点
        if (node.right != null) {
            MapNode<K, V> right = node.right;
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

    private boolean valEquals(V v1, V v2) {
        return Objects.equals(v1, v2);
    }

    public static void main(String[] args) {
        TreeMap treeMap=new TreeMap();
        boolean b = treeMap.valEquals(null, null);
        System.out.println(b);
    }

    /**
     * 返回值 e1添加元素 e2父节点元素
     * 0 两个元素相等
     * 大于0 e1>e2
     * 小于0 e1<e2
     */
    private int compare(K e1, K e2) {
        //若传入构造器则使用默认构造器的元素
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        //没用传入构造器,默认传入元素E继承了构造方法Comparable
        //一般内置的类型(int)等,都默认实现了Comparable接口
        return ((Comparable<K>) e1).compare(e2);
    }

    private static class MapNode<K, V> {
        K key;
        V value;
        //节点颜色
        boolean color = RED;

        MapNode<K, V> left;
        MapNode<K, V> right;
        //记录树的父节点
        MapNode<K, V> parent;

        public MapNode(K key, V value, MapNode<K, V> parent) {
            this.key = key;
            this.value = value;
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
        public MapNode<K, V> sibling() {
            if (isLeftChild()) return parent.right;
            if (isRightChild()) return parent.left;
            return null;
        }
    }
}
