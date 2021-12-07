package com.algorithms.searches.graphs;

import com.algorithms.collections.mutable.Graph;

public class Connected {
    final private boolean[] marked;
    final private int[] ids;
    private int count;

    public Connected(Graph G) {
        marked = new boolean[G.V()];
        ids = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        ids[v] = count;
        G.adj(v).forEach(w -> {
           if (!marked[w]) dfs(G, w);
        });
    }

    public int getCount() {
        return this.count;
    }

    public int getId(int v) {
        return this.ids[v];
    }

    public boolean isConnected(int v, int w) {
        return this.ids[v] == this.ids[w];
    }
}
