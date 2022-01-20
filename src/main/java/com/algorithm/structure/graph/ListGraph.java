package com.algorithm.structure.graph;

import com.algorithm.structure.union.GenericUnionFind;

import java.util.*;

/**
 * @author wangyj
 * @Description: (使用集合实现图)
 * @date 2021/5/7 17:35
 */
public class ListGraph<V, E> extends Graph<V, E> {

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

    public ListGraph() {
    }

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();
    private Set<Edge<V, E>> edges = new HashSet<>();
    private Comparator<Edge<V, E>> edgeComparator = (Edge<V, E> e1, Edge<V, E> e2) -> {
        return weightManager.compare(e1.weight, e2.weight);
    };

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //检查传入传出顶点是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(to, toVertex);
        }
        //检查顶点是否存在这个边,添加全新边
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        edge.weight = weight;
        //尝试删除起始顶点边,有则删除到达顶点边
        if (fromVertex.toEdges.remove(edge)) {
            toVertex.fromEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.toEdges.add(edge);
        toVertex.fromEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;
        //使用迭代器删除(顶点出去的边)
        for (Iterator<Edge<V, E>> iterator = vertex.toEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //删除这个边指向->顶点(from)的边
            edge.to.fromEdges.remove(edge);
            //删除当前遍历元素(删除的顶点(to)的边)
            iterator.remove();
            edges.remove(edge);
        }
        //使用迭代器删除(顶点进来的边)
        for (Iterator<Edge<V, E>> iterator = vertex.fromEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            //删除这个边指向->顶点(from)的边
            edge.from.toEdges.remove(edge);
            //删除当前遍历元素(删除的顶点(to)的边)
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void removeEdge(V from, V to) {
        //判断顶点是否存在
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;
        Edge<V, E> edge = new Edge<>(fromVertex, toVertex);
        //假设有from到to的边则进行删除
        if (fromVertex.toEdges.remove(edge)) {
            toVertex.fromEdges.remove(edge);
            edges.remove(edge);
        }

    }

    @Override
    public List<V> topologicalSort() {
        List<V> list = new ArrayList<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Map<Vertex<V, E>, Integer> isn = new HashMap<>();
        //初始化队列,把度为0节点入队
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            if (vertex.fromEdges.size() == 0) {
                //入度为0,入队
                queue.offer(vertex);
            } else {
                //入度不为0,记录入度
                isn.put(vertex, vertex.fromEdges.size());
            }
        });

        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            //放入返回结果
            list.add(poll.value);
            poll.toEdges.forEach(v -> {
                int toIn = isn.get(v.to) - 1;
                if (toIn == 0) {
                    queue.offer(v.to);
                } else {
                    isn.put(v.to, toIn);
                }
            });
        }
        return list;
    }

    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
//        return Dijkstra(begin);
        return BellmanFord(begin);
    }

    @Override
    //多元最短路径算法,能求出任意两个顶点路径支持负权边 O(V^3)
    public Map<V, Map<V, PathInfo<V, E>>> floyd(V begin) {
        // 起点   终点  存放的路径信息
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        //初始化
        for (Edge<V, E> edge : edges) {
            //在paths中查找edge.from.value为key的map如果没有则创建并放入paths中
            Map<V, PathInfo<V, E>> map = paths.computeIfAbsent(edge.from.value, k -> new HashMap<>());
            PathInfo<V, E> pathInfo = new PathInfo<>(edge.weight);
            //初始化路径边
            pathInfo.edgeInfos.add(edge.Info());
            map.put(edge.to.value, pathInfo);
        }

        //查看v1->v3的距离 是否通过v2会更短
        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    if (v1.equals(v2) || v1.equals(v3) || v2.equals(v3)) return;

                    //v1 -> v2距离
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) return;
                    //v2 -> v3距离
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) return;
                    //v1 -> v3距离
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);

                    E newWeight = weightManager.add(path12.weight, path23.weight);
                    //13原来有路并且小于新的路径则退出此次循环
                    if (path13 != null && weightManager.compare(newWeight, path13.weight) >= 0) return;
                    //更新最短路径
                    if (path13 == null) {
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);
                    } else {
                        path13.edgeInfos.clear();
                    }
                    path13.weight = newWeight;
                    path13.edgeInfos.addAll(path12.edgeInfos);
                    path13.edgeInfos.addAll(path23.edgeInfos);
                });
            });
        });

        return paths;
    }

    //比较from到to的距离在paths中是否为空
    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(from);
        return map == null ? null : map.get(to);
    }


    //Bellman-Ford(对所有相邻进行松弛循环进行n-1次操作算出所有边的最短路径)
    //可以有负权边,可以检测负权环
    private Map<V, PathInfo<V, E>> BellmanFord(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        //初始化起点到本身的最短路径
        PathInfo<V, E> beginPath = new PathInfo<>();
        //初始化起点本身的weight的值
        beginPath.weight = weightManager.zero();
        selectedPaths.put(begin, beginPath);
        int count = vertices.size() - 1;
        for (int i = 0; i < count; i++) {
            //循环n-1次操作
            for (Edge<V, E> edge : edges) {
                //循环进行每条边的松弛,fromPath传的是松弛边起点的最短路径
                PathInfo<V, E> fromPath = selectedPaths.get(edge.from.value);
                //如果起点到edge.from顶点还没有最短路径则松弛失败
                if (fromPath == null) continue;
                relaxForBellmanFord(edge, fromPath, selectedPaths);
            }
        }
        return selectedPaths;
    }

    /**
     * @param edge     需要松弛的边
     * @param fromPath edge的from最短路径
     * @param paths    存放其他点目前最短信息
     *                 松弛操作
     */
    private void relaxForBellmanFord(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<V, PathInfo<V, E>> paths) {
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to.value, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        //必然新的路径是最小的,因为老路径小在上面已经return了
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.Info());
    }


    //顶点到其他顶点的最小权值(Dijkstra) 不支持负权边
    //类似提起操作,依次提起最小权值边算出紧绷边的路线就是顶点到其他边的最短路线
    private Map<V, PathInfo<V, E>> Dijkstra(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) return null;
        //最终返回的最短路径
        Map<V, PathInfo<V, E>> selectPaths = new HashMap<>();
        //当前的最短路径
        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>();
        for (Edge<V, E> edge : vertex.toEdges) {
            //初始化信息,存放开始起点能到达的点和具体路径
            PathInfo<V, E> path = new PathInfo<V, E>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.Info());
            paths.put(edge.to, path);
        }
        //循环获取最短顶点,得到最短路径直到所有顶点找到为止
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minPath = getMinPath(paths);
            //刚离开桌面的顶点
            Vertex<V, E> minVertex = minPath.getKey();
            //权值最小顶点离开桌面,加入返回map中
            selectPaths.put(minVertex.value, minPath.getValue());
            paths.remove(minVertex);
            //对path进行松弛操作(判断得出上个最小顶点之后到各顶点最 小路径)
            for (Edge<V, E> edge : minVertex.toEdges) {
                //如果edge.to顶点已经离开桌面||边的to顶点是起点 则不需要松弛操作
                if (selectPaths.containsKey(edge.to.value) || edge.to.equals(vertex)) continue;
                //松弛操作
                relaxForDijkstra(edge, minPath.getValue(), paths);
            }
        }
        return selectPaths;
    }


    /**
     * @param edge     需要松弛的边
     * @param fromPath edge的from最短路径
     * @param paths    存放其他点目前最短信息(Dijkstra存放的是没有离开桌面的点)
     *                 松弛操作
     */
    private void relaxForDijkstra(Edge<V, E> edge, PathInfo<V, E> fromPath, Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        E newWeight = weightManager.add(fromPath.weight, edge.weight);
        PathInfo<V, E> oldPath = paths.get(edge.to);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) return;
        if (oldPath == null) {
            oldPath = new PathInfo<>();
            paths.put(edge.to, oldPath);
        } else {
            oldPath.edgeInfos.clear();
        }
        //必然新的路径是最小的,因为老路径小在上面已经return了
        oldPath.weight = newWeight;
        oldPath.edgeInfos.addAll(fromPath.edgeInfos);
        oldPath.edgeInfos.add(edge.Info());
    }


    //从paths中挑选出一个最短路径的边
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> entry = null;
        //遍历取出最短顶点
        for (Map.Entry<Vertex<V, E>, PathInfo<V, E>> currentEntry : paths.entrySet()) {
            if (entry == null || weightManager.compare(currentEntry.getValue().weight, entry.getValue().weight) < 0) {
                entry = currentEntry;
            }
        }
        return entry;
    }


    @Override
    //最小生成树
    public Set<EdgeInfo<V, E>> mst() {
        return prim();
    }


    //prim算法
    //两个集合保存顶点和边,每次选择顶点集合中所有边权值最小的边加入对应集合(边的to,from顶点都加入集合了则忽略此边)
    //直到所有顶点都加入则是最小生成树
    private Set<EdgeInfo<V, E>> prim() {
        //记录最小生成树边返回给用户
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //记录已经加入集合的顶点
        Set<Vertex<V, E>> addVertices = new HashSet<>();
        //使用迭代器随意取一个顶点
        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        if (!iterator.hasNext()) return null;
        Vertex<V, E> vertex = vertices.values().iterator().next();
        addVertices.add(vertex);
        //传入比较器并且把此顶点出去的边放入优先队列(堆)中
        PriorityQueue<Edge<V, E>> heap = new PriorityQueue<>(edgeComparator);
        vertex.toEdges.forEach(heap::offer);
        int edgeSize = vertices.size() - 1;
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            //取出堆顶元素(最小边)
            Edge<V, E> lowEdge = heap.poll();
            //当最小堆取出的边指向顶点是已经加入集合的则忽略继续循环
            if (addVertices.contains(lowEdge.to)) continue;
            //把最小边指向的顶点加入集合,并循环把新的顶点的边加入堆中
            edgeInfos.add(lowEdge.Info());
            addVertices.add(lowEdge.to);
            lowEdge.to.toEdges.forEach(heap::offer);
        }
        return edgeInfos;
    }

    //kruskal算法
    //拿到图中所有边,从小到大排序把边(不能形成闭环的边)加入生成树 直到加入了V-1条边(V是顶点数量)
    private Set<EdgeInfo<V, E>> kruskal() {
        int edgeSize = vertices.size() - 1;
        if (edgeSize == -1) return null;

        //初始化并查集,把每个顶点都当作一个集合放入并查集
        GenericUnionFind<Vertex<V, E>> unionFind = new GenericUnionFind<>();
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            //把每个顶点都单独放入并查集
            unionFind.makeSet(vertex);
        });
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        //把所有边加入优先队列(堆)中
        PriorityQueue<Edge<V, E>> heap = new PriorityQueue<>(edgeComparator);
        edges.forEach(heap::offer);
        while (!heap.isEmpty() && edgeInfos.size() < edgeSize) {
            Edge<V, E> edge = heap.remove();
            //判断加入的边不能形成闭环(使用并查集查看两个顶点是否在一个集合)
            if (unionFind.isSame(edge.from, edge.to)) continue;
            edgeInfos.add(edge.Info());
            unionFind.union(edge.from, edge.to);
        }
        return edgeInfos;
    }

    @Override
    public void bfs(V begin, Visitor<V> visitor) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) return;
        //防止顶点重复添加到队列
        Set<Vertex<V, E>> visits = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        queue.offer(vertex);
        visits.add(vertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            //简单传递一下遍历结果
            visitor.visit(poll.value);
            vertex.toEdges.forEach(edge -> {
                if (!visits.contains(edge.to)) {
                    queue.offer(edge.to);
                    visits.add(edge.to);
                }
            });
        }
    }

    //非递归深度优先算法(栈)
    public void dfs1(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        //定义保存顶点,防止重复入栈
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();
        //起点入栈
        stack.push(beginVertex);
        visitedVertices.add(beginVertex);
        visitor.visit(beginVertex.value);
        while (!stack.isEmpty()) {
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.toEdges) {
                //判断这条边的指向顶点是否入栈
                if (visitedVertices.contains(edge.to)) continue;
                //把栈中pop的顶点重新入栈,下次再循环判断是否有额外的边
                stack.push(edge.from);
                //栈中存放下个顶点
                stack.push(edge.to);
                visitedVertices.add(edge.to);
                //打印此顶点
                visitor.visit(edge.to.value);
                break;
            }
        }
    }

    @Override
    public void dfs(V begin, Visitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        dfs(beginVertex, new HashSet<>(), visitor);
    }

    /**
     * 深度优先算法,类似前序遍历(递归)
     *
     * @param vertex   根顶点
     * @param vertices 记录访问过的顶点
     * @param visitor  让用户自定义的遍历器
     */
    private void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> vertices, Visitor<V> visitor) {
        visitor.visit(vertex.value);
        vertices.add(vertex);
        for (Edge<V, E> edge : vertex.toEdges) {
            if (vertices.contains(edge.to)) continue;
            dfs(edge.to, vertices, visitor);
        }
    }

    //定义顶点类
    private static class Vertex<V, E> {
        V value;
        //顶点出去的边
        Set<Edge<V, E>> fromEdges = new HashSet<>();
        //顶点进来的边
        Set<Edge<V, E>> toEdges = new HashSet<>();

        //重新equals,hashCode,判断顶点是否相同
        @Override
        public boolean equals(Object o) {
            return Objects.equals(value, ((Vertex<V, E>) o).value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        public Vertex(V value) {
            this.value = value;
        }
    }

    //定义边类
    private static class Edge<V, E> {
        Vertex<V, E> from;
        Vertex<V, E> to;
        E weight;

        EdgeInfo<V, E> Info() {
            return new EdgeInfo<>(from.value, to.value, weight);
        }

        //重新equals,hashCode方法确认边是否相同
        @Override
        public boolean equals(Object o) {
            Edge<E, V> edge = (Edge<E, V>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }


        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }
    }

}
