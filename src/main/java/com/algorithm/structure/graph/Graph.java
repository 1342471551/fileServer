package com.algorithm.structure.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wangyj
 * @Description: (图)
 * G=(V,E) G=graph(图) V=vertex(顶点,非空) E=edge(边,可空)
 * @date 2021/5/7 17:29
 */
public abstract class Graph<V, E> {

    protected WeightManager<E> weightManager;

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
    }

    public Graph() {
    }

    public abstract int edgesSize();

    public abstract int verticesSize();

    public abstract void addVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeVertex(V v);

    public abstract void removeEdge(V from, V to);

    //拓扑排序 (依次排序相互依赖顶点) 依次找到度为0的,删除顶点继续寻找 有向无环图
    public abstract List<V> topologicalSort();

    //最小生成树接口
    public abstract Set<EdgeInfo<V, E>> mst();

    //图中到其他顶点的最短路径
    public abstract Map<V, PathInfo<V, E>> shortestPath(V begin);

    //求出任意两点之间的最短路径
    public abstract Map<V, Map<V, PathInfo<V, E>>> floyd(V begin);

    //广度优先搜索
    public abstract void bfs(V begin, Visitor<V> visitor);

    //深度优先搜索
    public abstract void dfs(V begin, Visitor<V> visitor);

    public interface WeightManager<E> {
        int compare(E e1, E e2);

        E add(E w1, E w2);

        //让用户初始化默认值的类型
        E zero();
    }

    //让调用者实现此方法自定义遍历结果
    public interface Visitor<V> {
        boolean stop = false;

        boolean visit(V value);
    }

    //存放返回的路径信息
    public static class PathInfo<V, E> {
        protected E weight;
        //存放返回有序的路径信息
        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();

        public PathInfo(E weight) {
            this.weight = weight;
        }

        public PathInfo() {
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }

        @Override
        public String toString() {
            return "PathInfo{" +
                    "weight=" + weight +
                    ", edgeInfos=" + edgeInfos +
                    '}';
        }
    }

    //存放最小生成树和类
    public static class EdgeInfo<V, E> {
        private V from;
        private V to;
        private E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }
    }

}
