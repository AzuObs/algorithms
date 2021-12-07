package com.algorithms.searches.graphs;

import com.algorithms.collections.mutable.Graph;

public class Bipartite {
    private final boolean[] marked;
    private final boolean[] color;
    private boolean isBipartite = true;

    public Bipartite(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (var v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        G.adj(v).forEach(w -> {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(G, w);
            }
            else if (color[w] == color[v]) isBipartite = false;
        });
    }

    public boolean isBipartite() {
        return isBipartite;
    }
}
