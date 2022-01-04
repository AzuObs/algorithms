package com.algorithms.puzzles.ctci;

import java.util.Arrays;

public class SmallestDifference {

  public static int get(int[] as, int[] bs) {
    if (as.length == 0 || bs.length == 0) throw new ArithmeticException("Can't find diff between empty arrays");
    Arrays.sort(bs);
    var difference = Integer.MAX_VALUE;
    for (int a: as) {
      var diff = findClosestB(a, bs, 0, bs.length - 1);
      if (diff < difference) difference = diff;
    }
    return difference;
  }

  private static int findClosestB(int a, int[] sortedBs, int lb, int ub) {
    if (lb == ub) return Math.abs(a - sortedBs[lb]);
    var medianIndex = (ub + lb) / 2;
    var median = sortedBs[medianIndex];
    return a <= median
        ? findClosestB(a, sortedBs, lb, medianIndex)
        : findClosestB(a, sortedBs, medianIndex + 1, ub);
  }
}
