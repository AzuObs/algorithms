package com.algorithms.puzzles.ctci;

import java.util.HashMap;

public class SubArrayTargetSumPairs {

  public static int count(int[] ns, int target) {
    var total = 0;
    var tracker = new HashMap<Integer, Integer>();
    for (int n: ns) {
      total += tracker.getOrDefault(target - n, 0);
      tracker.put(n, tracker.getOrDefault(n, 0) + 1);
    }
    return total;
  }

}
