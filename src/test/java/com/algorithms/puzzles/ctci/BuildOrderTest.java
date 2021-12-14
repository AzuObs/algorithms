package com.algorithms.puzzles.ctci;

import com.algorithms.utils.tuples.Tuple2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildOrderTest {

  @Test
  public void solve() {
    var dependencies = new ArrayList<Tuple2<Integer, Integer>>();
    dependencies.add(Tuple2.apply(0, 3));
    dependencies.add(Tuple2.apply(5, 1));
    dependencies.add(Tuple2.apply(1, 3));
    dependencies.add(Tuple2.apply(5, 0));
    dependencies.add(Tuple2.apply(3, 2));
    assert Arrays.equals(BuildOrder.get(6, dependencies), new int[] { 5, 4, 1, 0, 3, 2 });
  }
}
