package com.algorithms.puzzles.ctci;

import java.util.ArrayList;

public class Parens {

  public static ArrayList<String> by(int pairs) {
    var str = new char[pairs*2];
    var list = new ArrayList<String>();
    byInner(list, pairs, pairs, str, 0);
    return list;
  }

  private static void byInner(ArrayList<String> list, int left, int right, char[] str, int index) {
    if (left < 0 || right < left) return;
    if (left == 0 && right == 0) { list.add(String.copyValueOf(str)); return; }

    str[index] = '(';
    byInner(list, left - 1, right, str, index + 1);
    str[index] = ')';
    byInner(list, left, right - 1, str, index + 1);
  }
}
