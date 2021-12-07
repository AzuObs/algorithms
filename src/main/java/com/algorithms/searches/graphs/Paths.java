package com.algorithms.searches.graphs;

import com.algorithms.collections.immutable.List;
import com.algorithms.collections.mutable.DoublyListDeque;
import com.algorithms.collections.mutable.Graph;

public interface Paths {
    boolean hasPathTo(int v);
    List<Integer> pathTo(int v);

    class DepthFirstPaths implements Paths {
        private final boolean[] marked;
        private final int[] pathTo;
        private final int source;

        public DepthFirstPaths(Graph G, int source) {
            this.marked = new boolean[G.V()];
            this.pathTo = new int[G.V()];
            this.source = source;
            dfs(G, source);
        }

        private void dfs(Graph G, int vertex) {
            this.marked[vertex] = true;
            G.adj(vertex).forEach(w -> {
                if (!this.marked[w]) {
                    this.pathTo[w] = vertex;
                    dfs(G, w);
                }
            });
        }

        @Override
        public boolean hasPathTo(int v) {
            return pathTo(v).exists();
        }

        @Override
        public List<Integer> pathTo(int v) {
            var path = List.<Integer>empty();
            var x = v;
            while (marked[x]) {
                path = List.cons(x, path);
                if (x == source) break;
                x = pathTo[x];
            }
            return path;
        }
    }

    class BreadthFirstPaths implements Paths {
        private final boolean[] marked;
        private final int[] pathTo;
        private final int source;

        public BreadthFirstPaths(Graph G, int source) {
            this.marked = new boolean[G.V()];
            this.pathTo = new int[G.V()];
            this.source = source;
            bfs(G, source);
        }

        private void bfs(Graph G, int source) {
            var deque = new DoublyListDeque<Integer>().append(source);
            this.marked[source] = true;
            while (deque.exists()) {
                var v = deque.pop().get();
                G.adj(v).forEach(w -> {
                    if (!this.marked[w]) {
                        this.marked[w] = true;
                        this.pathTo[w] = v;
                        deque.append(w);
                    }
                });
            }
        }

        @Override
        public boolean hasPathTo(int v) {
            return pathTo(v).exists();
        }

        @Override
        public List<Integer> pathTo(int v) {
            var path = List.<Integer>empty();
            var x = v;
            while (marked[x]) {
                path = List.cons(x, path);
                if (x == source) break;
                x = pathTo[x];
            }
            return path;
        }
    }
}
