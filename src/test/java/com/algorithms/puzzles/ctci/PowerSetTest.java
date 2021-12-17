package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class PowerSetTest {

  class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      if (o1.length() < o2.length()) return -1;
      if (o1.length() > o2.length()) return +1;
      return o1.compareTo(o2);
    }
  }

  @Test
  public void empty() {
    var input = "";
    var result = new ArrayList<>();
    result.add("");
    assert PowerSet.get(input).equals(result);
  }

  @Test
  public void one() {
    var input = "A";
    var result = new ArrayList<>();
    result.add("");
    result.add("A");
    assert PowerSet.get(input).equals(result);
  }

  @Test
  public void two() {
    var input = "AB";
    var result = new ArrayList<>();
    result.add("");
    result.add("A");
    result.add("B");
    result.add("AB");
    assert PowerSet.get(input).equals(result);
  }

  @Test
  public void three() {
    var input = "ABC";
    var result = new ArrayList<>();
    result.add("");
    result.add("A");
    result.add("B");
    result.add("C");
    result.add("AB");
    result.add("AC");
    result.add("BC");
    result.add("ABC");
    var get = PowerSet.get(input);
    get.sort(new StringCompare());
    assert get.equals(result);
  }

  @Test
  public void four() {
    var input = "ABCD";
    var result = new ArrayList<>();
    result.add("");
    result.add("A");
    result.add("B");
    result.add("C");
    result.add("D");
    result.add("AB");
    result.add("AC");
    result.add("AD");
    result.add("BC");
    result.add("BD");
    result.add("CD");
    result.add("ABC");
    result.add("ABD");
    result.add("ACD");
    result.add("BCD");
    result.add("ABCD");
    var get = PowerSet.get(input);
    get.sort(new StringCompare());
    assert get.equals(result);
  }
}
