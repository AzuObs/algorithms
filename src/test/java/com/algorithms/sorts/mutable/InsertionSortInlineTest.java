package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;

public class InsertionSortInlineTest extends SortTest {
    @Override
    public <T extends Comparable<T>> void sort(Array<T> sortable) {
        new InsertionSorts.Inline<T>().sort(sortable);
    }
}
