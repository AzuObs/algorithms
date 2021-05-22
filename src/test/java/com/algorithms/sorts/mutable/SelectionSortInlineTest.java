package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;

public class SelectionSortInlineTest extends SortTest {
    @Override
    public <T extends Comparable<T>> void sort(Array<T> sortable) {
        new SelectionSorts.Inline<T>().sort(sortable);
    }
}
