package com.algorithms.searches.graphs;

import com.algorithms.collections.immutable.List;
import com.algorithms.collections.mutable.Graph;

public class DepthFirstPaths {
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
