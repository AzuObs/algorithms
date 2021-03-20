package com.algorithms.collections.map;

import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.option.Option;

public interface Map<K, V> {
    int getLength();
    Option<Tuple2<K, V>> get(K key);
    Map<K, V> remove(K key);
    Map<K, V> add(Tuple2<K, V> element);
}
