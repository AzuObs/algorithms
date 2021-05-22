package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;

public interface Sort<T extends Comparable<T>> {
    void sort(Array<T> sortable);
}
