package com.algorithms.collections.mutable;

import com.algorithms.collections.immutable.List;
import com.algorithms.utils.tuples.Tuple2;
import com.algorithms.controls.Option;
import com.algorithms.utils.Primes;

public class MapProbe<K, V> implements Map<K, V> {

    private ArrayFixed<Tuple2<K, V>> underlying;
    private int population;

    private MapProbe(Tuple2<K,V>[] elements) {
        this.underlying = ArrayFixed.fill(Primes.nextPrime(elements.length * 2));
        for (Tuple2<K,V> element: elements) {
            this.add(element);
        }
    }

    @SafeVarargs
    public static <K, V> MapProbe<K, V> apply(Tuple2<K, V>... elements) {
        return new MapProbe<K, V>(elements);
    }

    @Override
    public int getLength() {
        return this.population;
    }

    @Override
    public Option<Tuple2<K, V>> get(K key) {
        var hash = this.hash(this.underlying, key);

        while(true) {
            var entry = this.underlying.get(hash);
            if (entry.exists()) {
                if (entry.get().key.equals(key)) {
                    return entry;
                } else {
                    hash++;
                }
            } else {
                return Option.none();
            }
        }
    }

    @Override
    public MapProbe<K, V> remove(K key) {
        var hash = this.hash(this.underlying, key);

        while(true) {
            var entry = this.underlying.get(hash);

            if (entry.exists()) {
                if (entry.get().key.equals(key)) {
                    this.underlying.clear(hash);
                    this.population--;
                }
            } else {
                break;
            }

            hash = (hash + 1) % this.underlying.getLength();
        }

        var removed = List.Nil.<Tuple2<K, V>>apply();
        while(true) {
            var entry = this.underlying.get(hash);

            if (entry.exists()) {
                removed.prepend(entry.get());
            } else {
                break;
            }

            hash = (hash + 1) % this.underlying.getLength();
        }

        removed.forEach(this::add);
        return this;
    }

    @Override
    public MapProbe<K, V> add(Tuple2<K, V> element) {
        var hash = this.hash(this.underlying, element.key);

        while(true) {
            var entry = this.underlying.get(hash);

            if (entry.exists()) {
                if (entry.get().equals(element)) {
                    break;
                }
                hash = (hash + 1) % this.underlying.getLength();
            } else {
                this.underlying.set(hash, element);
                this.population++;
                break;
            }
        }

        return this.rehash();
    }

    private MapProbe<K, V> rehash() {
        if (isTooFull()) {
            var tmp = ArrayFixed.<Tuple2<K, V>>fill(Primes.nextPrime(this.underlying.getLength() * 2));

            for (var i = 0; i < this.underlying.getLength(); i++) {
                var element = this.underlying.get(i);
                if (element.exists()) {
                    var hash = this.hash(tmp, element.get().key);
                    while(true) {
                        var entry = tmp.get(hash);

                        if (entry.exists()) {
                            hash = (hash + 1) % tmp.getLength();
                        } else {
                            tmp.set(hash, element.get());
                            break;
                        }
                    }
                }
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
