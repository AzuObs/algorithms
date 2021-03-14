package com.algorithms.collections.set;

import com.algorithms.collections.array.ArrayFixed;
import com.algorithms.collections.list.Cons;
import com.algorithms.collections.list.List;
import com.algorithms.collections.list.Nil;
import com.algorithms.controls.option.Option;

public class SetList<T> implements Set<T> {
    private ArrayFixed<List<T>> underlying;
    private int population;

    private SetList(T[] elements) {
        this.underlying = ArrayFixed.fill(List.empty(), elements.length * 2);
        for (var i = 0; i < elements.length; i++) {
            this.add(elements[i]);
        }
    }

    @SafeVarargs
    public static <T> SetList<T> apply(T... elements) {
        return new SetList(elements);
    }

    @Override
    public int getLength() {
        return this.population;
    }

    @Override
    public Option<T> get(T element) {
        var entries = this.underlying.get(this.elementHash(this.underlying, element));
        var entry = entries.get().flatMap(e ->
            e.equals(element)
                ? Cons.apply(e, Nil.<T>apply())
                : Nil.<T>apply()
        );
        return entry.getHead();
    }

    @Override
    public SetList<T> remove(T element) {
        var index = this.elementHash(this.underlying, element);
        var entries = this.underlying.get(index);
        var entriesMinus =
            entries.get().<T>flatMap(entry ->
                entry.equals(element)
                    ? Nil.<T>apply()
                    : Cons.apply(entry, Nil.<T>apply())
            );
        this.underlying.set(index, entriesMinus);
        return this;
    }

    @Override
    public SetList<T> add(T element) {
        var hash = this.elementHash(this.underlying, element);
        var entries = this.underlying.get(hash).get();
        if (!entries.contains(element)) { // daniel
            this.underlying.set(hash, entries.prepend(element));
            this.population++;
        }
        return this.rehash();
    }

    // daniel should use modulo prime
    private int elementHash(ArrayFixed<?> space, T element) {
        return element.hashCode() % space.getLength();
    }

    private SetList<T> rehash() {
        if (this.population == 0) {
            return this;
        } else if (((float) this.underlying.getLength() / (float) this.population) > 1.2) {
            return this;
        } else {
            var tmp = ArrayFixed.fill(List.<T>empty(), this.population * 2);

            for (var i = 0; i < this.underlying.getLength(); i++) {
                var elements = this.underlying.get(i).get();
                elements.forEach(element -> {
                    var hash = this.elementHash(tmp, element);
                    var entries = tmp.get(hash).get();
                    tmp.set(hash, entries.prepend(element));
                });
            }

            this.underlying = tmp;
            return this;
        }
    }
}
