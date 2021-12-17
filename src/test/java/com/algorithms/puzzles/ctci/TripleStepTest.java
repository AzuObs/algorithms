package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class TripleStepTest {

  @Test public void _neg() { assert TripleStep.ways(-2) == 0; }
  @Test public void _0() { assert TripleStep.ways(0) == 1; }
  @Test public void _1() { assert TripleStep.ways(1) == 2; }
  @Test public void _2() { assert TripleStep.ways(2) == 3; }
  @Test public void _5() { assert TripleStep.ways(5) == 1; }
  @Test public void _10() { assert TripleStep.ways(10) == 1; }

}
