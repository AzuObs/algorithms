package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class MultiplyTest {

  @Test public void zero() {          assert Multiply.by(0, 10) == 0; }
  @Test public void one() {           assert Multiply.by(1, 10) == 10; }
  @Test public void odd() {           assert Multiply.by(8, 10) == 80; }
  @Test public void even() {          assert Multiply.by(9, 10) == 90; }
  @Test public void smallerFirst() {  assert Multiply.by(3, 10) == 30; }
  @Test public void biggerFirst() {   assert Multiply.by(10, 6) == 60; }

}
