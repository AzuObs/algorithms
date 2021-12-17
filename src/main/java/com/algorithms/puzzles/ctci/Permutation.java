package com.algorithms.puzzles.ctci;

import java.util.ArrayList;

public class Permutation {

  public static ArrayList<String> get(String of) {
    if (of.equals("")) return new ArrayList<>();
    return getInner(new ArrayList<>(), of, 0);
  }

  private static ArrayList<String> getInner(ArrayList<String> results, String of, int index) {
    if (index == of.length()) return results;
    if (index == 0) { results.add(String.valueOf(of.charAt(0))); return getInner(results, of, 1); }
    var newResults = new ArrayList<String>();
    for (String result: results) {
      for (var i = 0; i <= index; i++) {
        newResults.add(result.substring(0, i) + of.charAt(index) + result.substring(i));
      }
    }
    return getInner(newResults, of, index + 1);
  }
}
