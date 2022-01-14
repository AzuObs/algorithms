package com.algorithms.puzzles.ctci;

import org.junit.Test;

import java.util.HashMap;

public class BabyNamesTest {

  @Test
  public void mixed() {
    var names = new HashMap<String, Integer>();
    var synonyms = new HashMap<String, String>();
    names.put("John",   15);
    names.put("Jon",    12);
    names.put("Chris",  13);
    names.put("Kris",   4);
    names.put("Kristo", 19);
    synonyms.put("Jon",   "John");
    synonyms.put("Chris", "Kris");
    synonyms.put("Kris",  "Kristo");
    var frequencies = BabyNames.create(names, synonyms);
    assert frequencies.getFrequency("Jon") == 27;
    assert frequencies.getFrequency("Kris") == 36;
  }
}
