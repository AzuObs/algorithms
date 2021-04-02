package com.algorithms.searches.generics;

import com.algorithms.controls.Option;

import java.util.function.Function;

public interface Search<T> {
    Option<T> find(Function<T, Boolean> predicate);

    default Option<T> find(T element) {
        return this.find(element::equals);
    }

    default boolean contains(Function<T, Boolean> predicate) {
        return this.find(predicate).exists();
    }

    default boolean contains(T element) {
        return this.contains(element::equals);
    }
}
