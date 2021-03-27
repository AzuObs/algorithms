package com.algorithms.collections.map;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.collections.list.Cons;
import com.algorithms.collections.list.List;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.option.Option;
import com.algorithms.utils.Primes;

public class MapHashChain<K, V> implements Map<K, V> {
    private ArrayFixed<List<Tuple2<K, V>>> underlying;
    private int population;

    private MapHashChain(Tuple2<K, V>[] elements) {
        this.underlying = ArrayFixed.fill(List.empty(), Primes.nextPrime(elements.length * 2));
        for (Tuple2<K, V> element: elements) {
            this.add(element);
        }
    }

    @SafeVarargs
    public static <K, V> MapHashChain<K, V> apply(Tuple2<K, V>... elements) {
        return new MapHashChain<K, V>(elements);
    }

    @Override
    public int getLength() {
        return this.population;
    }

    @Override
    public Option<Tuple2<K, V>> get(K key) {
        var chain = this.underlying.get(this.hash(this.underlying, key));
        var matches = chain.get().find(e -> e.key.equals(key));
        return matches.getHead();
    }

    @Override
    public Map<K, V> remove(K key) {
        var index = this.hash(this.underlying, key);
        var chain = this.underlying.get(index).get();

        if (chain.contains(element -> element.key.equals(key))) {
            var newChain = chain.remove(element -> element.key.equals(key));
            this.underlying.set(index, newChain);
            this.population--;
        }
        return this;
    }

    @Override
    public Map<K, V> add(Tuple2<K, V> element) {
        var hash = this.hash(this.underlying, element.key);
        var chain = this.underlying.get(hash).get();

        if (!chain.contains(element)) {
            this.underlying.set(hash, Cons.apply(element, chain));
            this.population++;
        }
        return this.rehash();
    }

    private Map<K, V> rehash() {
        if (isTooFull()) {
            var newLength = Primes.nextPrime(this.underlying.getLength() * 2);
            var tmp = ArrayFixed.<List<Tuple2<K, V>>>fill(List.empty(), newLength);

            for (var i = 0; i < this.underlying.getLength(); i++) {
                var elements = this.underlying.get(i).get();
                elements.forEach(element -> {
                    var hash = this.hash(tmp, element.key);
                    var entries = tmp.get(hash).get();
                    tmp.set(hash, entries.prepend(element));
                });
            }
            this.underlying = tmp;
        }
        return this;
    }

    private boolean isTooFull() {
        double LOAD_FACTOR = 0.75;
        return (double) this.population / this.underlying.getLength() > LOAD_FACTOR;
    }

    private int hash(ArrayFixed<?> space, K key) {
        return (key.hashCode() & 0x7fffffff) % space.getLength();
    }
}
