package com.algorithms.puzzles.ctci;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class RouteBetweenNodes {
  public static class Edge {
    public final int from;
    public final int to;
    public Edge(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }

  @SuppressWarnings("unchecked")
  public static class Graph {
    private final ArrayList<Edge>[] adj;
    private final int V;
    public Graph(int V) {
      this.V = V;
      this.adj = (ArrayList<Edge>[]) new ArrayList[V];
      for (var i = 0; i < V; i++) {
        adj[i] = new ArrayList<>();
      }
    }
    public void addEdge(int from, int to) {
      var edgeAlreadyExists = adj[from].stream().anyMatch(e -> e.to == to);
      if (!edgeAlreadyExists) adj[from].add(new Edge(from, to));
    }
    public int V() {
      return V;
    }
    public ArrayList<Edge> adj(int v) {
      return adj[v];
    }
  }

  private final Graph G;
  public RouteBetweenNodes(Graph G) {
    this.G = G;
  }
  public boolean findRoute(int from, int to) {
    var marked = new boolean[G.V()];
    var queue = new ArrayDeque<Integer>();
    marked[from] = true;
    queue.add(from);
    while (!queue.isEmpty()) {
      if (queue.peek().equals(to)) return true;
      G.adj(queue.pop()).forEach(edge -> {
        if (!marked[edge.to]) {
          queue.add(edge.to);
          marked[edge.to] = true;
        }
      });
    }
    return false;
  }
}
