package com.algorithms.puzzles;

import org.junit.Test;
import com.algorithms.puzzles.RouteBetweenNodes.*;

public class RouteBetweenNodesTest {

  @Test
  public void findsSimplePath() {
    var G = new Graph(2);
    G.addEdge(0, 1);
    assert new RouteBetweenNodes(G).findRoute(0, 1);
  }

  @Test
  public void findsMediumPath() {
    var G = new Graph(5);
    G.addEdge(0, 1);
    G.addEdge(1, 2);
    G.addEdge(2, 3);
    G.addEdge(3, 4);
    assert new RouteBetweenNodes(G).findRoute(0, 4);
  }

  @Test
  public void findsComplexPath() {
    var G = new Graph(5);
    G.addEdge(0, 1);
    G.addEdge(0, 2);
    G.addEdge(0, 3);
    G.addEdge(1, 2);
    G.addEdge(1, 0);
    G.addEdge(1, 3);
    G.addEdge(2, 3);
    G.addEdge(3, 4);
    assert new RouteBetweenNodes(G).findRoute(0, 4);
  }

  @Test
  public void doesNotFindRoute() {
    var G = new Graph(5);
    G.addEdge(0, 1);
    G.addEdge(3, 4);
    assert !(new RouteBetweenNodes(G).findRoute(0, 4));
  }
}
