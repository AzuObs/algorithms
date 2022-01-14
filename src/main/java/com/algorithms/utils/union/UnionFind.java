package com.algorithms.utils.union;

public interface UnionFind {
  void join(int idA, int idB);
  int groupId(int entry);
  int groups();
  int[] groupIds();
}
