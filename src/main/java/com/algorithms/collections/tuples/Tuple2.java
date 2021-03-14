package com.algorithms.collections.tuples;

public class Tuple2<K, V> {
    public final K key;
    public final V value;

    private Tuple2(K key, V value) {
        this.key = key;
        this.value = value;
    }

    private static <K, V> Tuple2<K, V> apply(K key, V value) {
        return new Tuple2(key, value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() + value.hashCode();
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
