package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class CalculatorTest {

  @Test public void empty() { assert Calculator.compute("") == 0; }
  @Test public void single() { assert Calculator.compute("1") == 1; }
  @Test public void add() { assert Calculator.compute("1+1") == 2; }
  @Test public void sub() { assert Calculator.compute("2-1") == 1; }
  @Test public void multiply() { assert Calculator.compute("2*3") == 6; }
  @Test public void div() { assert Calculator.compute("4/2") == 2; }
  @Test public void addThenDiv() { assert Calculator.compute("9+4/2") == 11; }
  @Test public void divThenAdd() { assert Calculator.compute("4/2+9") == 11; }

}
