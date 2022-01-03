package com.algorithms.puzzles.ctci;

public class NumberSwapper {
  public static class Result {
    public int a;
    public int b;
    public Result(int a, int b) { this.a = a; this.b = b; }
    @Override public boolean equals(Object other) {
      return (other instanceof Result) && ((Result) other).a == a && ((Result) other).b == b;
    }
  }

  public static Result swapWithDiff(int a, int b) {
    if (a > b) {
      a = a - b;
      b += a;
      a = b - a;
    } else if (b > a) {
      b = b - a;
      a += b;
      b = a - b;
    }
    return new Result(a, b);
  }

  public static Result swapWithXor(int a, int b) {
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    return new Result(a, b);
  }
}
