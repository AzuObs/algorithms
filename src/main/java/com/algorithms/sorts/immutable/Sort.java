package com.algorithms.sorts.immutable;

import com.algorithms.collections.immutable.List;

public interface Sort<T extends Comparable<T>> {
    List<T> sort(List<T> input);
}
