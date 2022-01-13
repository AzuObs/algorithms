package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class CountOfTwosTest {

  @Test public void neg() { assert CountOfTwos.get(-10) == -1; }
  @Test public void zero() { assert CountOfTwos.get(0) == 0; }
  @Test public void one() { assert CountOfTwos.get(1) == 0; }
  @Test public void two() { assert CountOfTwos.get(2) == 1; }
  @Test public void eight() { assert CountOfTwos.get(8) == 1; }
  @Test public void ten() { assert CountOfTwos.get(10) == 1; }
  @Test public void twelve() { assert CountOfTwos.get(12) == 2; }
  @Test public void fourteen() { assert CountOfTwos.get(14) == 2; }
  @Test public void twentySix() { assert CountOfTwos.get(26) == 4; }
  @Test public void ninetyNine() { assert CountOfTwos.get(99) == 11; }
  @Test public void hundred() { assert CountOfTwos.get(100) == 11; }
  @Test public void hundredTwenty() { assert CountOfTwos.get(120) == 14; }
  @Test public void hundredTwentyTwo() { assert CountOfTwos.get(122) == 15; }

}
