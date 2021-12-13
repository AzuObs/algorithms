package com.algorithms.graphs;

import com.algorithms.collections.mutable.Graph;

public class TopologicalSort {
  private final Graph G;
  private final int[] sorted;
  private final boolean[] marked;
  private final boolean[] onStack;
  private int pos;

  public TopologicalSort(Graph G) {
    this.G = G;
    this.pos = G.V() - 1;
    this.sorted = new int[G.V()];
    this.marked = new boolean[G.V()];
    this.onStack = new boolean[G.V()];

    for (var v = 0; v < G.V(); v++) sort(v);
  }

  private void sort(int v) {
    if (onStack[v]) throw new IllegalStateException("Loop detected, cannot topologically sorted");
    if (marked[v]) return;

    onStack[v] = true;
    G.adj(v).forEach(this::sort);
    sorted[pos] = v;
    pos--;
    marked[v] = true;
    onStack[v] = false;
  }

  public int[] get() {
    return sorted;
  }
}
