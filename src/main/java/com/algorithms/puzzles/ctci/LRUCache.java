package com.algorithms.puzzles.ctci;

import java.util.HashMap;

public class LRUCache<K, V> {
  private final int maxCapacity;
  private final HashMap<K, LinkedListNode<K, V>> elements;
  private LinkedListNode<K, V> listHead;
  private LinkedListNode<K, V> listTailEnd;

  private static class LinkedListNode<K, V> {
    public final K key;
    public final V value;
    public LinkedListNode<K, V> prev;
    public LinkedListNode<K, V> next;
    public LinkedListNode(K key, V value) {
      this.key = key;
      this.value = value;
      this.prev = null;
      this.next = null;
    }
  }

  public LRUCache(int maxCapacity) {
    this.maxCapacity = maxCapacity;
    this.elements = new HashMap<>();
  }

  public V get(K key) {
    var element = elements.get(key);
    if (element == null) return null;
    touch(element);
    return element.value;
  }

  public void put(K key, V value) {
    if (elements.containsKey(key)) {
      elements.remove(key);
      removeFromList(elements.get(key));
    }

    if (elements.size() == maxCapacity) {
      var oldest = listTailEnd;
      elements.remove(oldest.key);
      removeFromList(oldest);
    }

    var element = new LinkedListNode<>(key, value);
    elements.put(key, element);
    touch(element);
  }

  private void touch(LinkedListNode<K, V> element) {
    removeFromList(element);
    insertIntoList(element);
  }

  private void removeFromList(LinkedListNode<K, V> element) {
    if (element == null) return;
    if (element.prev != null) element.prev.next = element.next;
    if (element.next != null) element.next.prev = element.prev;
    if (element == listHead) listHead = element.next;
    if (element == listTailEnd) listTailEnd = element.prev;
  }

  private void insertIntoList(LinkedListNode<K, V> element) {
    if (listHead == null) {
      listHead = element;
      listTailEnd = element;
    } else {
      element.prev = null;
      element.next = listHead;
      listHead.prev = element;
      listHead = element;
    }
  }
}
