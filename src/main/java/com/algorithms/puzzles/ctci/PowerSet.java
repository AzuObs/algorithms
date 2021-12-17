package com.algorithms.puzzles.ctci;

import java.util.ArrayList;

public class PowerSet {

  public static ArrayList<String> get(String from) {
    var results = new ArrayList<String>();
    results.add("");
    return getInner(results, from, 0);
  }

  @SuppressWarnings("unchecked")
  private static ArrayList<String> getInner(ArrayList<String> results, String from, int index) {
    if (index == from.length()) return results;
    var newResults = (ArrayList<String>) results.clone();
    for (String result: results) { newResults.add(result + from.charAt(index)); }
    return getInner(newResults, from, index + 1);
  }

}
