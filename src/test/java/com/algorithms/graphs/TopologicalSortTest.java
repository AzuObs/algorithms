package com.algorithms.graphs;

import com.algorithms.collections.mutable.Graph;
import org.junit.Test;

import java.util.Arrays;

public class TopologicalSortTest {

  @Test
  public void empty() {
    var G = new Graph(0);
    var sort = new TopologicalSort(G);
    assert Arrays.equals(sort.get(), new int[0]);
  }

  @Test
  public void one() {
    var G = new Graph(1);
    var sort = new TopologicalSort(G);
    assert Arrays.equals(sort.get(), new int[] { 0 });
  }

  @Test
  public void linkedList() {
    var G = new Graph(3);
    G.addUnidirectionalEdge(0, 1);
    G.addUnidirectionalEdge(1, 2);
    var sort = new TopologicalSort(G);
    assert Arrays.equals(sort.get(), new int[] { 0, 1, 2 });
  }

  @Test
  public void bifurcation() {
    var G = new Graph(4);
    G.addUnidirectionalEdge(0, 2);
    G.addUnidirectionalEdge(2, 3);
    G.addUnidirectionalEdge(0, 1);
    var sort = new TopologicalSort(G);
    assert Arrays.equals(sort.get(), new int[] { 0, 2 , 3, 1 });
  }

  @Test
  public void merging() {
    var G = new Graph(4);
    G.addUnidirectionalEdge(0, 1);
    G.addUnidirectionalEdge(1, 2);
    G.addUnidirectionalEdge(0, 3);
    G.addUnidirectionalEdge(3, 2);
    var sort = new TopologicalSort(G);
    assert Arrays.equals(sort.get(), new int[] { 0, 1, 3, 2 });
  }

  @Test
  public void pendant() {
    var G = new Graph(4);
    G.addUnidirectionalEdge(0, 1);
    G.addUnidirectionalEdge(1, 2);
    G.addUnidirectionalEdge(3, 2);
    var sort = new TopologicalSort(G);
    assert Arrays.equals(sort.get(), new int[] { 3, 0, 1, 2 });
  }

  @Test
  public void loop() {
    var G = new Graph(3);
    G.addUnidirectionalEdge(0, 1);
    G.addUnidirectionalEdge(1, 2);
    G.addUnidirectionalEdge(2, 0);

    try {
      new TopologicalSort(G);
    } catch (IllegalStateException e) {
      assert e.getMessage().equals("Loop detected, cannot topologically sorted");
    };
  }
}
