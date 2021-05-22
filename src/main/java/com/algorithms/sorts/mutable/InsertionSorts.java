package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;

public interface InsertionSorts {

    class Inline<T extends Comparable<T>> implements Sort<T> {
        @Override
        public void sort(Array<T> sortable) {
            if (sortable.getLength() <= 1) return;
            for (var i = 1; i < sortable.getLength(); i++) {
                var cmp = sortable.get(i).get().compareTo(sortable.get(i - 1).get());
                if (cmp < 0) {
                    for (var j = i; j >= 1; j--) {
                        var innerCmp = sortable.get(j).get().compareTo(sortable.get(j - 1).get());
                        if (innerCmp < 0) {
                            sortable.swap(j, j - 1);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }
}
