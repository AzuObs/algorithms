package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.ArrayList;

public class ParensTest {

  @Test public void one() {
    var expected = new ArrayList<String>();
    expected.add("()");
    assert Parens.by(1).equals(expected);
  }

  @Test public void two() {
    var expected = new ArrayList<String>();
    expected.add("(())");
    expected.add("()()");
    assert Parens.by(2).equals(expected);
  }

  @Test public void three() {
    var expected = new ArrayList<String>();
    expected.add("((()))");
    expected.add("(()())");
    expected.add("(())()");
    expected.add("()(())");
    expected.add("()()()");
    assert Parens.by(3).equals(expected);
  }
}
