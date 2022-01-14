package com.algorithms.utils.union;

import org.junit.Test;

import java.util.Arrays;

public class WeighedQuickUnionTest {

  @Test
  public void empty() {
    var union = new WeighedQuickUnion(0);
    assert Arrays.equals(union.groupIds(), new int[0]);
  }

  @Test
  public void noJoinTwo() {
    var union = new WeighedQuickUnion(2);
    assert union.groupId(0) == 0;
    assert union.groupId(1) == 1;
    assert Arrays.equals(union.groupIds(), new int[] { 0, 1 });
  }

  @Test
  public void joinTwo() {
    var union = new WeighedQuickUnion(2);
    union.join(0, 1);
    assert union.groupId(0) == 0;
    assert union.groupId(1) == 0;
    assert Arrays.equals(union.groupIds(), new int[] { 0 });
  }

  @Test
  public void partJoinThree() {
    var union = new WeighedQuickUnion(3);
    union.join(0, 1);
    assert union.groupId(0) == 0;
    assert union.groupId(1) == 0;
    assert union.groupId(2) == 2;
    assert Arrays.equals(union.groupIds(), new int[] { 0, 2 });
  }

  @Test
  public void partJoinWeighed() {
    var union = new WeighedQuickUnion(5);
    union.join(0, 1);
    union.join(1, 2);
    union.join(3, 4);
    assert union.groupId(0) == 0;
    assert union.groupId(1) == 0;
    assert union.groupId(2) == 0;
    assert union.groupId(3) == 3;
    assert union.groupId(4) == 3;
    assert union.size(0) == 3;
    assert union.size(1) == 1;
    assert union.size(2) == 1;
    assert union.size(3) == 2;
    assert union.size(4) == 1;
    assert Arrays.equals(union.groupIds(), new int[] { 0, 3 });
  }
}
