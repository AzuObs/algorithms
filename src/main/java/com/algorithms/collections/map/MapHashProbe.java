package com.algorithms.collections.map;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.collections.list.Nil;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;
import com.algorithms.utils.Primes;

public class MapHashProbe<K, V> implements Map<K, V> {

    private ArrayFixed<Tuple2<K, V>> underlying;
    private int population;

    private MapHashProbe(Tuple2<K,V>[] elements) {
        this.underlying = ArrayFixed.fill(Primes.nextPrime(elements.length * 2));
        for (Tuple2<K,V> element: elements) {
            this.add(element);
        }
    }

    @SafeVarargs
    public static <K, V> MapHashProbe<K, V> apply(Tuple2<K, V>... elements) {
        return new MapHashProbe<K, V>(elements);
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
                return None.apply();
            }
        }
    }

    @Override
    public MapHashProbe<K, V> remove(K key) {
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

        var removed = Nil.<Tuple2<K, V>>apply();
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
    public MapHashProbe<K, V> add(Tuple2<K, V> element) {
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

    private MapHashProbe<K, V> rehash() {
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
        return Math.abs(key.hashCode()) % space.getLength();
    }
}
