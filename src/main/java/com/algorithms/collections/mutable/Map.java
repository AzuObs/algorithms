package com.algorithms.collections.mutable;

import com.algorithms.utils.tuples.Tuple2;
import com.algorithms.controls.Option;

public interface Map<K, V> {
    int getLength();
    Option<Tuple2<K, V>> get(K key);
    Map<K, V> remove(K key);
    Map<K, V> add(Tuple2<K, V> element);
}
