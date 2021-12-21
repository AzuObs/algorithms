package com.algorithms.puzzles.ctci;

public class Coins {

  public static int ways(int pence) {
    var cache = new int[pence + 1];
    for (var i = 0; i < pence + 1; i++) cache[i] = -1;
    return waysInner(pence, cache);
  }

  private static int waysInner(int pence, int[] cache) {
    if (pence == 0) return 0;
    if (cache[pence] != -1) return pence;

    var total = 0;
    if (pence - 25 >= 0) total += waysInner(pence - 25, cache) + 1;
    if (pence - 10 >= 0) total += waysInner(pence - 10, cache) + 1;
    if (pence - 5 >= 0)  total += waysInner(pence -  5, cache) + 1;
    if (pence - 1 >= 0)  total += waysInner(pence -  1, cache) + 1;

    cache[pence] = total;
    return total;
  }
}
