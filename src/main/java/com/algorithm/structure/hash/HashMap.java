package com.algorithm.structure.hash;

import com.algorithm.structure.map.map;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author wangyj
 * @Description: (使用hash表实现map)
 * @date 2021/4/19 10:35
 */
public class HashMap<K, V> implements map<K, V> {
    //定义红黑树节点颜色
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    //默认数组元素
    private static final int DEFAULT_CAPACITY = 1 << 4;
    //定义负载因子(节点总数/hash表桶数组长度)
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;
    private MapNode<K, V>[] table;

    public HashMap() {
        table = new MapNode[DEFAULT_CAPACITY];
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        Arrays.fill(table, null);
    }

    @Override
    public V put(K key, V value) {
        //判断扩容
        resize();
        int index = index(key);
        MapNode<K, V> root = table[index];
        if (root == null) {
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }
        //hash冲突,添加新的节点到红黑树
        //从根节点开始向下进行比较
        MapNode<K, V> node = root;
        //定义根节点为默认父节点
        MapNode<K, V> parent;
        //记录元素和根节点的比较情况
        int compare = 0;
        K k1 = key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        //标记,是否搜索过这个key
        boolean searched = false;
        do {
            //保存查找到的父节点
            parent = node;
            MapNode<K, V> result = null;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                compare = 1;
            } else if (h1 < h2) {
                compare = -1;
            } else if (Objects.equals(k1, k2)) {
                compare = 0;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (compare = ((Comparable) k1).compareTo(k2)) != 0) {
                //compare=0的情况无法判断对象相等,还是需要扫描 剩下大于小于0的情况判断的时候赋值给compare了直接走下面具体处理逻辑
            } else if (searched) {
                //searched==true的情况(已经扫描过了没有相同key,进行内存比较存放元素)
                compare = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else {
                //先扫描是否有相同的key进行覆盖
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    node = result;
                    compare = 0;
                } else {
                    //不存在这个key
                    searched = true;
                    compare = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }
            //具体逻辑操作
            if (compare > 0) {
                node = node.right;
            } else if (compare < 0) {
                node = node.left;
            } else {
                //若插入值和二叉树值相等进行覆盖
                V oldValue = node.value;
                node.value = value;
                node.key = key;
                //进行hash值的覆盖,由于只有Objects.equals=0才会来这里 前后对象hash值必然相等
                node.hash = h1;
                return oldValue;
            }
        } while (node != null);
        //新定义插入节点,放置元素位置
        MapNode<K, V> newNode = createNode(key, value, parent);
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
        return node != null ? node.value : null;
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
        //需要遍历整个hashmap中所有红黑树直到找到为止
        if (size == 0) return false;
        Queue<MapNode<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                MapNode<K, V> poll = queue.poll();
                if (Objects.equals(value, poll.value)) return true;
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
        }
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        //需要遍历整个hashmap中所有红黑树直到找到为止
        if (size == 0 || visitor == null) return;
        Queue<MapNode<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) continue;
            queue.offer(table[i]);
            while (!queue.isEmpty()) {
                MapNode<K, V> poll = queue.poll();
                if (visitor.visit(poll.key, poll.value)) return;
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
            }
        }
    }

    //父类定义创建对象类,可被子类覆盖
    protected MapNode<K, V> createNode(K key, V value, MapNode<K, V> parent) {
        return new MapNode<>(key, value, parent);
    }

    protected void LinkedAfterRemove(MapNode<K, V> willNode, MapNode<K, V> node) {

    }

    private void resize() {
        if (size / table.length <= DEFAULT_LOAD_FACTOR) return;
        MapNode<K, V>[] oldTable = table;
        table = new MapNode[table.length << 1];
        Queue<MapNode<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                MapNode<K, V> poll = queue.poll();
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                //遍历红黑树挪动节点(由于需要重置节点,先左右节点入队再进行挪动)
                moveNode(poll);
            }
        }
    }

    //进行节点的挪动(放入新的table数组中)
    private void moveNode(MapNode<K, V> newNode) {
        //重置节点
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = RED;

        int index = index(newNode);
        MapNode<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            afterPut(root);
            return;
        }
        //hash冲突,添加新的节点到红黑树
        //从根节点开始向下进行比较
        MapNode<K, V> node = root;
        //定义根节点为默认父节点
        MapNode<K, V> parent;
        //记录元素和根节点的比较情况
        int compare = 0;
        K k1 = newNode.key;
        int h1 = k1 == null ? 0 : k1.hashCode();
        do {
            //保存查找到的父节点
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            if (h1 > h2) {
                compare = 1;
            } else if (h1 < h2) {
                compare = -1;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (compare = ((Comparable) k1).compareTo(k2)) != 0) {
                //compare=0的情况无法判断对象相等,还是需要扫描 剩下大于小于0的情况判断的时候赋值给compare了直接走下面具体处理逻辑
            } else {
                compare = System.identityHashCode(k1) - System.identityHashCode(k2);
            }
            //具体逻辑操作
            if (compare > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        } while (node != null);
        //设置新节点的父节点
        newNode.parent = parent;
        if (compare > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        //添加之后处理
        afterPut(newNode);
    }

    protected V remove(MapNode<K, V> node) {
        //判断传入没有此节点
        if (node == null) return null;
        //定义一下一开始要删除的节点(当度为2并且是linkedHashMap删除的时候需要改变linked指针指向)
        MapNode<K, V> willNode = node;

        V oldValue = node.value;
        int index = index(node);

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
            //覆盖替代节点hash值
            node.hash = preNode.hash;
        }
        //删除node节点(度为1或0)
        MapNode<K, V> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            //度为1节点 1.更改子节点指向的parent
            replacement.parent = node.parent;
            //2.更改删除节点父节点(left,right)指向->删除节点的子节点
            if (node.parent == null) {
                //node是度为1的节点并且是根节点
                table[index] = replacement;
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
            table[index] = null;
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
        //交给linkedHashMap处理的删除后序(度为二节点删除维护链表性质)
        LinkedAfterRemove(willNode, node);

        return oldValue;
    }

    private MapNode<K, V> node(K key) {
        MapNode<K, V> node = table[index(key)];
        return node == null ? null : node(node, key);
    }


    private MapNode<K, V> node(MapNode<K, V> node, K k1) {
        int h1 = k1 != null ? k1.hashCode() : 0;
        int cmp = 0;
        while (node != null) {
            MapNode<K, V> result = null;
            K k2 = node.key;
            int h2 = node.hash;
            //比较hash值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    //Comparable比较为0不代表两个对象相等 只有Objects.equals才能确认
                    && ((Comparable) k1).compareTo(k2) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } //hash相等不具备可比较性 进行遍历查找
            else if (node.right != null && (result = node(node.right, k1)) != null) {
                return result;
            } else {
                //右边未找到,把node左边赋值给当前node重新进行循环
                node = node.left;
            }
        }
        return null;
    }

    //根据key生成hashcode(索引)
    private int index(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        //高16位与低16位做混合运算(无符号右移16位)再与数组长度进行与运算
        hash = hash ^ (hash >>> 16);
        return hash & (table.length - 1);
    }

    //根据node返回hash(索引)
    private int index(MapNode<K, V> node) {
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }

    /**
     * 比较冲突元素红黑树存放规则
     *
     * @param k1 元素1
     * @param k2 元素2
     * @param h1 元素1hash值
     * @param h2 元素2hash值
     * @return >0 K1>K2 =0 K1=K2 <0 K1<K2
     */
    private int compare(K k1, K k2, int h1, int h2) {
        int result = h1 - h2;
        if (result != 0) return result;
        //比较equals
        if (Objects.equals(k1, k2)) return 0;
        //hash相等,equals不等 比较类名
        if (k1 != null && k2 != null) {
            String k1ClassName = k1.getClass().getName();
            String k2ClassName = k2.getClass().getName();
            result = k1ClassName.compareTo(k2ClassName);
            if (result != 0) return result;
            //类名相同 尝试使用自带比较方法
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        //1.hash值一样,同一种类型,k1不为空k2为空
        //2.k1为null k2不为空
        //3.k1不为空 k2为null

        //使用内存地址进行比较大小
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }


    protected static class MapNode<K, V> {
        K key;
        V value;
        //节点颜色
        boolean color = RED;
        //节点hashcode
        int hash;

        MapNode<K, V> left;
        MapNode<K, V> right;
        //记录树的父节点
        MapNode<K, V> parent;

        public MapNode(K key, V value, MapNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.hash = key == null ? 0 : key.hashCode();
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
            table[index(grade.key)] = parent;
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
            table[index(grade.key)] = parent;
        }
        //改变grade父节点
        grade.parent = parent;
        if (grade.left != null) {
            grade.left.parent = grade;
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

}
