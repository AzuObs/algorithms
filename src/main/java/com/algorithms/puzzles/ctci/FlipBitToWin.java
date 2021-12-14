package com.algorithms.puzzles.ctci;

public class FlipBitToWin {

  public static int sequence(int num) {
    int previousLength = 0, currentLength = 0, maxLength = 0, mask = 1;
    for (var i = 0; i < 32; i++) {
      var currentIsOne = (num & mask) != 0;
      previousLength = currentIsOne ? previousLength : currentLength;
      currentLength = currentIsOne ? currentLength + 1 : 0;
      var candidate = previousLength + currentLength + 1;
      maxLength = Math.max(candidate, maxLength);
      mask <<= 1;
    }
    return maxLength;
  }
}
