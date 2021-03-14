package com.algorithms.collections.set;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.collections.list.Nil;
import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Option;

public class SetLinear<T> implements Set<T> {
    private ArrayFixed<T> underlying;
    private int population;

    private SetLinear(T[] elements) {
        this.underlying = ArrayFixed.fill(elements.length * 2);
        for (var i = 0; i < elements.length; i++) {
            this.add(elements[i]);
        }
    }

    @SafeVarargs
    public static <T> SetLinear<T> apply(T... elements) {
        return new SetLinear(elements);
    }

    @Override
    public int getLength() {
        return this.population;
    }

    @Override
    public Option<T> get(T element) {
        var hash = hashFor(this.underlying, element);

        while(true) {
            var entry = this.underlying.get(hash);
            if (entry.exists()) {
                if (entry.get().equals(element)) {
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
    public Set<T> remove(T element) {
        var hash = hashFor(this.underlying, element);

        while(true) {
            var entry = this.underlying.get(hash);

            if (entry.exists()) {
                if (entry.get().equals(element)) {
                    this.underlying.clear(hash);
                    this.population--;
                }
            } else {
                break;
            }

            hash = (hash + 1) % this.underlying.getLength(); // daniel should use modulo prime
        }

        var removed = Nil.<T>apply();
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
    public Set<T> add(T element) {
        var hash = hashFor(this.underlying, element);

        while(true) {
            var entry = this.underlying.get(hash);

            if (entry.exists()) {
                if (entry.get().equals(element)) {
                    break;
                }
                hash = (hash + 1) % this.underlying.getLength(); // daniel should use modulo prime
            } else {
                this.underlying.set(hash, element);
                this.population++;
                break;
            }
        }
        return this.rehash();
    }

    private Set<T> rehash() {
        if (isTooFull()) {
            var tmp = ArrayFixed.<T>fill(this.population * 2);
            for (var i = 0; i < this.underlying.getLength(); i++) {
                var element = this.underlying.get(i);
                if (element.exists()) {
                    var hash = this.hashFor(tmp, element.get());
                    while(true) {
                        var entry = tmp.get(hash);

                        if (entry.exists()) {
                            hash = (hash + 1) % tmp.getLength(); // daniel should use modulo prime
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

    // daniel should use modulo prime
    private int hashFor(ArrayFixed<?> space, T element) {
        return element.hashCode() % space.getLength();
    }

    private boolean isTooFull() {
        if (this.population == 0) return false;
        else return (double) this.underlying.getLength() / this.population > 0.75;
    }
}
