package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class CoinsTest {

  @Test public void _00() { assert Coins.ways( 0) == 0; }
  @Test public void _01() { assert Coins.ways( 1) == 1; }
  @Test public void _05() { assert Coins.ways( 5) == 6; }
  @Test public void _10() { assert Coins.ways(10) == 32; }
  @Test public void _25() { assert Coins.ways(25) == 393; }
  @Test public void _50() { assert Coins.ways(50) == 2343; }

}
