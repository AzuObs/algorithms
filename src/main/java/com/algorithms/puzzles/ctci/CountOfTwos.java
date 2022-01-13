package com.algorithms.puzzles.ctci;

public class CountOfTwos {

  public static int get(int n) {
    if (n < 0) return -1;
    var count = 0;
    var powerOf10 = 1;
    var twosInCurrentPowerOf10 = 0;
    var twosInPreviousPowerOf10 = 0;
    do {
      var digit = n % 10;
      count += digit * twosInCurrentPowerOf10 + twosInPreviousPowerOf10;
      if (digit >= 2) count++;
      n /= 10;
      twosInPreviousPowerOf10 += twosInCurrentPowerOf10;
      twosInCurrentPowerOf10 = (int)Math.pow(10, powerOf10 - 1);
      powerOf10++;
    } while (n != 0); // daniel
    return count;
  }

  // digit * (powerOf10 == 1? 0 : Math.pow(10, powerOf10 - 2)) + Math.pow(10, powerOf10 - 3) * 10 + ...
}
