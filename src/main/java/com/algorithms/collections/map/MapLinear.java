package com.algorithms.collections.map;

import com.algorithms.collections.set.Set;
import com.algorithms.collections.set.SetLinear;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.option.Option;

// daniel shiiiiit... Set must use Map... Map can't use Set as the underlying because
// Tuple2('A', 1) and Tuple2('A', 2) aren't unique in Set but need to be unique (because identical
// key) in Map
public class MapLinear<K, V> implements Map<K, V> {
    private final Set<Tuple2<K, V>> underlying;

    private MapLinear(Tuple2<K,V>[] elements) {
        this.underlying = SetLinear.apply(elements);
    }

    @Override
    public Option<Tuple2<K, V>> get(K key) {

    }

    public Map<K, V> remove(K key) {

    }

    public Map<K, V> add(Tuple2<K, V> element) {

    }
}
