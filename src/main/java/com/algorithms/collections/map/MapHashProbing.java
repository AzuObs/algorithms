package com.algorithms.collections.map;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.collections.list.Cons;
import com.algorithms.collections.list.List;
import com.algorithms.collections.list.Nil;
import com.algorithms.collections.tuples.Tuple2;
import com.algorithms.controls.eval.Done;
import com.algorithms.controls.eval.Eval;
import com.algorithms.controls.eval.More;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;

public class MapHashProbing<K, V> implements Map<K, V> {

    private ArrayFixed<Tuple2<K, V>> underlying;
    private int population;

    private MapHashProbing(Tuple2<K,V>[] elements) {
        this.underlying = ArrayFixed.fill(nextPrime(elements.length * 2));
        for (Tuple2<K,V> element: elements) {
            this.add(element);
        }
    }

    @SafeVarargs
    public static <K, V> MapHashProbing<K, V> apply(Tuple2<K, V>... elements) {
        return new MapHashProbing<K, V>(elements);
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
    public MapHashProbing<K, V> remove(K key) {
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
    public MapHashProbing<K, V> add(Tuple2<K, V> element) {
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

    private MapHashProbing<K, V> rehash() {
        if (isTooFull()) {
            var tmp = ArrayFixed.<Tuple2<K, V>>fill(nextPrime(this.underlying.getLength() * 2));

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
        return key.hashCode() % space.getLength();
    }

    private int nextPrime(int after) {
        return nextPrimeInner(Cons.apply(2, Nil.apply()), after, 3).run();
    }

    // daniel extract into memoized utility
    private Eval<Integer> nextPrimeInner(List<Integer> primes, int after, int candidate) {
        var isPrime = primes.none(prime -> candidate == prime || candidate % prime == 0);

        if (isPrime && candidate > after) {
            return Done.apply(candidate);
        } else if (isPrime) {
            return More.apply(() -> nextPrimeInner(Cons.apply(candidate, primes), after, candidate + 1));
        } else {
            return More.apply(() -> nextPrimeInner(primes, after, candidate + 1));
        }
    }
}
