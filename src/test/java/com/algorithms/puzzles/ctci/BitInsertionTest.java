package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class BitInsertionTest {

  @Test
  public void with0s() {
    assert BitInsertion.merge(0x7fffffff, 0x00000000, 7, 4) == 0x7fffff0f;
  }

  @Test
  public void with1s() {
    assert BitInsertion.merge(0x7fffffff, 0xffffffff, 7, 4) == 0x7fffffff;
  }

  @Test
  public void withElse() {
    assert BitInsertion.merge(0x7fffffff, 0x11111111, 7, 4) == 0x7fffff1f;
  }
}
