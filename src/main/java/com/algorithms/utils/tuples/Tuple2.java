package com.algorithms.utils.tuples;

import com.algorithms.utils.Prime;

public class Tuple2<K, V> implements Tuple {
    public final K key;
    public final V value;

    private Tuple2(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static <K, V> Tuple2<K, V> apply(K key, V value) {
        return new Tuple2<K, V>(key, value);
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.getValue();
    }

    // Horner method
    @Override
    public int hashCode() {
        var R = Prime.THIRTY_ONE;
        var hash = 1;
        hash = R * hash + key.hashCode();
        hash = R * hash + value.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Tuple2<?, ?>) {
            return this.key.equals(((Tuple2<?, ?>) other).key) && this.value.equals(((Tuple2<?, ?>) other).value);
        } else {
            return false;
        }
    }
}
