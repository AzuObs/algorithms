package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class PermutationTest {

  static class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
      return o1.compareTo(o2);
    }
  }

  @Test
  public void empty() {
    assert Permutation.get("").equals(new ArrayList<>());
  }

  @Test
  public void one() {
    var expected = new ArrayList<>();
    expected.add("A");
    assert Permutation.get("A").equals(expected);
  }

  @Test
  public void two() {
    var expected = new ArrayList<>();
    expected.add("AB");
    expected.add("BA");
    var results = Permutation.get("AB");
    results.sort(new StringCompare());
    assert results.equals(expected);
  }

  @Test
  public void three() {
    var expected = new ArrayList<>();
    expected.add("ABC");
    expected.add("ACB");
    expected.add("BAC");
    expected.add("BCA");
    expected.add("CAB");
    expected.add("CBA");
    var results = Permutation.get("ABC");
    results.sort(new StringCompare());
    assert results.equals(expected);
  }
}
