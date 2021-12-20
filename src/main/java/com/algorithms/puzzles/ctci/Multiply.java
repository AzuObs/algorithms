package com.algorithms.puzzles.ctci;

public class Multiply {

  public static int by(int a, int b) {
    var smaller = Math.min(a, b);
    var bigger = Math.max(a, b);
    return byInner(smaller, bigger);
  }

  private static int byInner(int smaller, int bigger) {
    if (smaller == 0) return 0;
    if (smaller == 1) return bigger;
    var s = smaller >> 1;
    var sum = byInner(s, bigger);
    return smaller % 2 == 1
        ? sum + sum + bigger
        : sum + sum;
  }

}
