package com.algorithms.utils;

public class Bitwise {

  public static boolean getBit(int num, int bit) {
    return (num & (1 << bit)) != 0;
  }

  public static int setBit(int num, int bit) {
    return num | (1 << bit);
  }

  public static int clearBit(int num, int bit) {
    return num & ~(1 << bit);
  }

  public static int updateBit(int num, int bit, boolean bitIs1) {
    var value = bitIs1 ? 1 : 0;
    var mask = ~(1 << bit);
    return (num & mask) | (value << bit);
  }


}
