package com.algorithms.puzzles.ctci;

import java.util.HashMap;

public class SubArrayTargetSumSequence {

  public static int count(int[] arr, int target) {
    var runningSum = new int[arr.length];
    for (var i = 0; i < arr.length; i++) {
      if (i == 0) runningSum[i] = arr[i];
      else runningSum[i] = arr[i] + runningSum[i - 1];
    }
    var sumTracker = new HashMap<Integer, Integer>();
    sumTracker.put(0, 1);
    var hits = 0;
    for (var i = 0; i < arr.length; i++) {
      sumTracker.put(runningSum[i], sumTracker.getOrDefault(runningSum[i], 0) + 1);
      hits += sumTracker.getOrDefault(runningSum[i] - target, 0);
    }
    return hits;
  }
}
