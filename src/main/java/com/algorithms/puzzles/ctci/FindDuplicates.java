package com.algorithms.puzzles.ctci;

import java.util.ArrayList;

public class FindDuplicates {

  private static class BitSet {
    private final byte[] bitset;

    public BitSet(int size) {
      bitset = new byte[size / 8 + 1];
    }

    public boolean get(int bitIndex) {
      var byteIndex = bitIndex / 8;
      var bitOffset = bitIndex % 8;
      byte mask = (byte)(1 << bitOffset);
      return (bitset[byteIndex] & mask) != 0;
    }
    public void set(int bitIndex) {
      var byteIndex = bitIndex / 8;
      var bitOffset = bitIndex % 8;
      byte mask = (byte)(1 << bitOffset);
      bitset[byteIndex] |= mask;
    }
  }

  public static ArrayList<Integer> in(int max, int[] input) {
    var duplicates = new ArrayList<Integer>();
    var seen = new BitSet(max);

    for (int i : input) {
      if (seen.get(i)) duplicates.add(i);
      else seen.set(i);
    }

    return duplicates;
  }
}
