package com.algorithms.collections.mutable;

public class IndexedMinHeap<K extends Comparable<K>> {
    private int n;
    private final K[] keys; // indexed keys
    private final int[] pq; // heap containing the indexed of the underlying keys

    @SuppressWarnings("unchecked")
    public IndexedMinHeap(int maxN) {
        keys = (K[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        for (int i = 0; i < maxN; i++) {
            pq[i] = -1;
        }
    }

    public boolean contains(int i) {
        return keys[i] == null;
    }

    public int minIndex() {
        return pq[1];
    }

    public K minKey() {
        return keys[pq[1]];
    }

    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        keys[min] = null;
        return min;
    }

    public void changeKey(int i, K key) {
        keys[i] = key;
        swim(i);
        sink(i);
    }

    private void swim(int i) {
        while (i > 1 && greater(i / 2, i)) {
            exch(i, i / 2);
            i = i / 2;
        }
    }

    private void sink(int i) {
        while (2 * i <= n) {
            int j = 2 * i;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(i, j)) break;
            exch(i, j);
            i = j;
        }
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

}
