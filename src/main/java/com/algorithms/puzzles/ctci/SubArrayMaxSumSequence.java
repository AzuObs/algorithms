package com.algorithms.puzzles.ctci;

public class SubArrayMaxSumSequence {

  public static int get(int[] arr) {
    var maxSum = Integer.MIN_VALUE;
    var runningSum = new int[arr.length];
    for (var i = 0; i < arr.length; i++) {
      if (i == 0) runningSum[0] = arr[0];
      else runningSum[i] = runningSum[i - 1] > 0 ? runningSum[i - 1] + arr[i]  : arr[i]; }
    for (var i = 0; i < arr.length; i++) {
      maxSum = Math.max(runningSum[i], maxSum);
    }
    return maxSum;
  }
}
