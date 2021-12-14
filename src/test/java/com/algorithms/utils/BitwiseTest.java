package com.algorithms.utils;

import org.junit.Test;

public class BitwiseTest {
  @Test public void get0Pos() { assert !Bitwise.getBit(3, 2); }
  @Test public void get0Neg() { assert !Bitwise.getBit(-2, 0); }
  @Test public void get1Pos() { assert Bitwise.getBit(3, 1); }
  @Test public void get1Neg() { assert Bitwise.getBit(-1, 2); }

  @Test public void set0Pos() { assert Bitwise.setBit(2, 0) == 3; }
  @Test public void set0Neg() { assert Bitwise.setBit(-2, 0) == -1; }
  @Test public void set1Pos() { assert Bitwise.setBit(1, 0) == 1; }
  @Test public void set1Neg() { assert Bitwise.setBit(-1, 0) == -1; }

  @Test public void clear0Pos() { assert Bitwise.clearBit(2, 0) == 2; }
  @Test public void clear0Neg() { assert Bitwise.clearBit(-2, 0) == -2; }
  @Test public void clear1Pos() { assert Bitwise.clearBit(1, 0) == 0; }
  @Test public void clear1Neg() { assert Bitwise.clearBit(-1, 1) == -3; }

  @Test public void update0Pos() { assert Bitwise.updateBit(2, 1, true) == 2; }
  @Test public void update0Neg() { assert Bitwise.updateBit(-1, 3, true) == -1; }
  @Test public void update1Pos() { assert Bitwise.updateBit(2, 1, false) == 0; }
  @Test public void update1Neg() { assert Bitwise.updateBit(-1, 1, false) == -3; }
}
