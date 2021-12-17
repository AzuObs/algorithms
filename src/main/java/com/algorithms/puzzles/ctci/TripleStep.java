package com.algorithms.puzzles.ctci;

public class TripleStep {
  
  public static int ways(int n) {
    if (n < 0) return 0;
    if (n == 0) return 1;
    return waysInner(0, 0, 1,  n - 1);
  }

  private static int waysInner(int p3, int p2, int p1, int remaining) {
    if (remaining == 0) return p1;
    return waysInner(p2, p1, p3 + p2 + p1, --remaining);
  }
}
