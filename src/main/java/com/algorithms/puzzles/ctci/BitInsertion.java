package com.algorithms.puzzles.ctci;

public class BitInsertion {
  public static int merge(int n, int m, int from, int to) {
    // calculate gap size and the gap mask
    var length = from - to;

    // clear the bits of N where M will go
    var nMask = _32bitMask(length, from);
    n &= nMask;

    // clear the bits of M which don't matter
    var mMask = ~_32bitMask(length, from);
    m &= mMask;

    return n | m;
  }

  private static int _32bitMask(int length, int from) {
    return ~((int)(Math.pow(2, length + 1) - 1) << (from - length));
  }
}
