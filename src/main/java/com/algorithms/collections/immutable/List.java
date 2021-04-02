package com.algorithms.collections.immutable;

import com.algorithms.controls.Eval;
import com.algorithms.controls.Option;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Interface for list-like data structures. These data structures cannot have empty elements
 */
public interface List<T> {
    int getLength();
    boolean isEmpty();
    Option<T> getHead();
    List<T> getTail();
    List<T> reverse();
    List<T> concat(List<T> other);
    <R> List<R> flatMap(Function<T, List<R>> mapper);

    default boolean exists() { return !isEmpty(); }
    default boolean none(Function<T, Boolean> predicate) { return find(predicate).isEmpty(); }
    default boolean any(Function<T, Boolean> predicate) { return !none(predicate); }
    default List<T> prepend(T element) { return cons(element, this); }
    default List<T> dropHead() { return getTail(); }
    default boolean contains(T element) { return contains(element::equals); }
    default boolean contains(Function<T, Boolean> predicate) { return find(predicate).exists(); }
    default List<T> forEach(Consumer<T> task) { return map(element -> { task.accept(element); return element; }) ;}
    default <R> List<R> map(Function<T, R> mapper) { return flatMap(element -> cons(mapper.apply(element), nil())); }
    default List<T> remove(T element) { return remove(element::equals); }
    default List<T> remove(Function<T, Boolean> predicate) {
        return flatMap(n -> predicate.apply(n) ? nil() : cons(n, nil())); }
    default List<T> find(Function<T, Boolean> predicate) {
        return flatMap(element -> predicate.apply(element) ? cons(element, nil()) : nil()); }

    static <T> List<T> nil() { return Nil.apply(); }
    static <T> List<T> empty() { return nil(); }
    static <T> List<T> cons(T head, List<T> tail){ return Cons.<T>apply(head, tail); }

    class Cons<T> implements List<T> {
        private final T head;
        private final List<T> tail;
        private final int length;

        private Cons(T head, List<T> tail) {
            this.head = head;
            this.tail = tail;
            this.length = tail.getLength() + 1;
        }

        public static <T> List<T> apply(T head, List<T> tail) {
            return new Cons<T>(head, tail);
        }

        @Override
        public int getLength() {
            return this.length;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Option<T> getHead() {
            return Option.some(this.head);
        }

        @Override
        public List<T> getTail() {
            return this.tail;
        }

        @Override
        public List<T> reverse() {
            return reverseInner(this, Nil.apply()).run();
        }

        private Eval<List<T>> reverseInner(List<T> from, List<T> to) {
            if (from.isEmpty()) {
                return Eval.done(to);
            } else {
                return Eval.more(() -> reverseInner(from.getTail(), Cons.apply(from.getHead().get(), to)));
            }
        }

        @Override
        public List<T> concat(List<T> other) {
            return concatInner(this, other, Nil.apply()).run();
        }

        private Eval<List<T>> concatInner(List<T> as, List<T> bs, List<T> to) {
            if (!bs.isEmpty()) {
                return Eval.more(() -> concatInner(as, bs.getTail(), to.prepend(bs.getHead().get())));
            } else if (!as.isEmpty()) {
                return Eval.more(() -> concatInner(as.getTail(), bs, to.prepend(as.getHead().get())));
            } else {
                return Eval.done(to.reverse());
            }
        }

        @Override
        public <R> List<R> flatMap(Function<T, List<R>> mapper) {
            var mapped = this.mapInner(this, Nil.apply(), mapper).run();
            return flattenInner(mapped, Nil.apply()).run();
        }

        private <R> Eval<List<R>> mapInner(List<T> from, List<R> to, Function<T, R> mapper) {
            if (from.isEmpty()) {
                return Eval.done(to.reverse());
            } else {
                return Eval.more(() -> {
                    var mapped = mapper.apply(from.getHead().get());
                    return mapInner(from.getTail(), Cons.apply(mapped, to), mapper);
                });
            }
        }

        private <R> Eval<List<R>> flattenInner(List<List<R>> from, List<R> to) {
            if (from.isEmpty()) {
                return Eval.done(to);
            } else {
                return Eval.more(() -> flattenInner(from.getTail(), from.getHead().get().concat(to)));
            }
        }
    }

    class Nil<T> implements List<T> {
        private static final Nil<?> INSTANCE = new Nil();

        private Nil() {}

        @SuppressWarnings("unchecked")
        public static <T> Nil<T> apply() {
            return (Nil<T>) Nil.INSTANCE;
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public Option<T> getHead() {
            return Option.none();
        }

        @Override
        public List<T> getTail() {
            return this;
        }

        @Override
        public List<T> reverse() {
            return this;
        }

        @Override
        public List<T> concat(List<T> other) {
            return other;
        }

        @Override
        public <R> List<R> flatMap(Function<T, List<R>> mapper) {
            return Nil.<R>apply();
        }
    }
}
