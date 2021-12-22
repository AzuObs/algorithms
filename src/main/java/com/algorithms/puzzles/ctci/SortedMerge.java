package com.algorithms.puzzles.ctci;

public class SortedMerge {

  public static int[] sortInner(int[] as, int[] bs, int aCount, int bCount) {
    var indexA = aCount - 1;
    var indexB= bCount - 1;
    var indexMerged = aCount + bCount - 1;

    while (indexA >= 0) {
      if (indexB >= 0 && as[indexA] < bs[indexB]) { bs[indexMerged] = bs[indexB]; indexB--; }
      else { bs[indexMerged] = as[indexA]; indexA--; }
      indexMerged--;
    }

    return bs;
  }
}
