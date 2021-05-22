package com.algorithms.collections.mutable;

import com.algorithms.collections.immutable.List;

public class Graph {
    private final List<Integer>[] adjacencies;
    private final int V;
    private int E;

    @SuppressWarnings("unchecked")
    public Graph(int V) {
        this.adjacencies = (List<Integer>[]) new List[V];
        this.V = V;
        this.E = 0;
        for (var v = 0; v < V; v++) {
            this.adjacencies[v] = List.empty();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public List<Integer> adj(int v) {
        return this.adjacencies[v];
    }

    public Graph addEdge(int v, int w) {
        this.adjacencies[v] = this.adjacencies[v].prepend(w);
        this.adjacencies[w] = this.adjacencies[w].prepend(v);
        this.E += 1;
        return this;
    }

    public String toString() {
        StringBuilder s = new StringBuilder(V + "vertices, " + E + " edges\n");
        for (int v = 0; v < V(); v++) {
            s.append(v).append(": ");
            this.adj(v).forEach(w -> s.append(w).append(" "));
            s.append("\n");
        }
        return s.toString();
    }

    public static int degree(Graph G, int v) {
        return G.adj(v).getLength();
    }

    public static int maxDegree(Graph G) {
        var max = 0;
        for (var v = 0; v < G.V(); v++) {
            if (degree(G, v) > max) {
                max = degree(G, v);
            }
        }
        return max;
    }

    public static int averageDegree(Graph G) {
        return 2 * G.E() / G.V();
    }

    public static int numberOfSelfLoops(Graph G) {
        int count = 0;
        for (int v = 0; v < G.V(); v++) {
            if (G.adj(v).contains(v)) count ++;
        }
        return count;
    }
}
