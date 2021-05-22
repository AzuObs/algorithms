package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;

public class SelectionSortRecursiveTest extends SortTest {
    @Override
    public <T extends Comparable<T>> void sort(Array<T> sortable) {
        new SelectionSorts.Recursive<T>().sort(sortable);
    }
}
