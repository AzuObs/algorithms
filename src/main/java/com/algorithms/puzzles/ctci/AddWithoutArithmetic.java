package com.algorithms.puzzles.ctci;

public class AddWithoutArithmetic {

  static public int add(int a, int b) {
    if (b == 0) return a;
    var sum = a ^ b;
    var carry = (a & b) << 1;
    return add(sum, carry);
  }
}
