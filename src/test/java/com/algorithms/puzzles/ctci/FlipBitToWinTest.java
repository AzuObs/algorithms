package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class FlipBitToWinTest {

  @Test
  public void zeros() {
    assert FlipBitToWin.sequence(0x00000000) == 1;
  }

  @Test
  public void ones() {
    assert FlipBitToWin.sequence(0x0000000f) == 5;
  }

  @Test
  public void oneGap() {
    assert FlipBitToWin.sequence(0x0ef11111) == 8;
  }

  @Test
  public void severalGaps() {
    assert FlipBitToWin.sequence(0xef00ee00) == 8;
  }
}
