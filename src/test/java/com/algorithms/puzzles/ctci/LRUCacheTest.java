package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class LRUCacheTest {
  
  @Test
  public void addingElements() {
    var cache = new LRUCache<Character, Integer>(3);
    cache.put('A', 1);
    cache.put('B', 2);
    cache.put('C', 3);
    assert cache.get('A') == 1;
    assert cache.get('B') == 2;
    assert cache.get('C') == 3;
    assert cache.get('D') == null;
  }

  @Test
  public void updatingElements() {
    var cache = new LRUCache<Character, Integer>(3);
    cache.put('A', 1);
    cache.put('B', 2);
    cache.put('C', 3);
    cache.put('A', 4);
    assert cache.get('A') == 4;
    assert cache.get('B') == 2;
    assert cache.get('C') == 3;
  }

  @Test
  public void evictingOldestElements() {
    var cache = new LRUCache<Character, Integer>(3);
    cache.put('A', 1);
    cache.put('B', 2);
    cache.put('C', 3);
    cache.put('D', 4);
    assert cache.get('A') == null;
    assert cache.get('B') == 2;
    assert cache.get('C') == 3;
    assert cache.get('D') == 4;
  }
}
