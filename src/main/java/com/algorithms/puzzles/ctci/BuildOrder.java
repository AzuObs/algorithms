package com.algorithms.puzzles.ctci;

import com.algorithms.collections.mutable.Graph;
import com.algorithms.graphs.TopologicalSort;
import com.algorithms.utils.tuples.Tuple2;

import java.util.ArrayList;

public class BuildOrder {
  public static int[] get(int projects, ArrayList<Tuple2<Integer, Integer>> dependencies) {
    var G = new Graph(projects);
    for (Tuple2<Integer, Integer> dependency : dependencies) {
      G.addUnidirectionalEdge(dependency.key, dependency.value);
    }
    return new TopologicalSort(G).get();
  }
}
