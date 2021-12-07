package com.algorithms.searches.graphs;

import com.algorithms.collections.mutable.Graph;

public class Cycle {
    final private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (var v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, -1, v);
            }
        }
    }

    private void dfs(Graph G, int u, int v) {
        this.marked[v] = true;
        G.adj(v).forEach(w -> {
           if (!marked[w]) { dfs(G, v, w); }
           else if (w != u) hasCycle = true;
        });
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
