package com.algorithms.searches.graphs;

import com.algorithms.collections.immutable.List;
import com.algorithms.collections.mutable.DoublyListDeque;
import com.algorithms.collections.mutable.Graph;

public class BreadthFirstPath {
    private final boolean[] marked;
    private final int[] pathTo;
    private final int source;

    public BreadthFirstPath(Graph G, int source) {
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

    public boolean hasPathTo(int v) {
        return pathTo(v).exists();
    }

    public List<Integer> pathTo(int vertex) {
        var path = List.<Integer>empty();
        var x = vertex;
        while (marked[x]) {
            path = List.cons(x, path);
            if (x == source) break;
            x = pathTo[x];
        }
        return path;
    }
}
