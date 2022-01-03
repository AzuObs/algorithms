package com.algorithms.puzzles.ctci;

import com.algorithms.puzzles.ctci.NumberSwapper.Result;
import org.junit.Test;

public class NumberSwapperTest {

  @Test public void aBiggerDiff() { assert NumberSwapper.swapWithDiff(10, 5).equals(new Result(5, 10)); }
  @Test public void bBiggerDiff() { assert NumberSwapper.swapWithDiff(5, 10).equals(new Result(10, 5)); }
  @Test public void aBiggerXor() { assert NumberSwapper.swapWithXor(10, 5).equals(new Result(5, 10)); }
  @Test public void bBiggerXor() { assert NumberSwapper.swapWithXor(5, 10).equals(new Result(10, 5)); }
  
}
