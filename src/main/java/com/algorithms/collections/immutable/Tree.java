package com.algorithms.collections.immutable;

import com.algorithms.controls.Eval;
import com.algorithms.controls.Option;

import java.util.function.Function;

public interface Tree<T> {
    int getLength();
    int getArity();
    Option<T> getValue();
    Tree<T> getBranch(int index);
    List<Tree<T>> getBranches();

    static <R> Nil<R> nil() { return Nil.apply(); }
    static <R> Tree2<R> tree2(R value, Tree<R> b0, Tree<R> b1) { return Tree2.apply(value, b0, b1); }

    default boolean isEmpty() { return getLength() == 0; }
    default boolean exists() { return !isEmpty(); }
    default boolean contains(T value) { return contains(value::equals); }
    default boolean contains(Function<T, Boolean> predicate) { return find(predicate).exists(); }
    default List<T> find(T value) { return find(value::equals); }
    default List<T> find(Function<T, Boolean> predicate) { return topologicalSort().find(predicate); }
    default List<T> topologicalSort() { return topologicalSortInner(List.single(this), List.nil()).run(); }

    private Eval<List<T>> topologicalSortInner(List<Tree<T>> branches, List<T> result) {
        if (branches.isEmpty()) {
            return Eval.done(result.reverse());
        }
        var currentBranch = branches.getHead().get();
        if (currentBranch instanceof Tree.Nil<?>) {
            return Eval.more(() -> topologicalSortInner(branches.getTail(), result));
        } else {
            return Eval.more(() -> {
                var nextBranches = branches.getTail().concat(currentBranch.getBranches());
                var nextResult = result.concat(currentBranch.getValue().toList());
                return topologicalSortInner(nextBranches, nextResult);
            });
        }
    }

    class Nil<T> implements Tree<T> {
        public static final Nil<?> INSTANCE = new Nil<>();

        private Nil() { }

        @SuppressWarnings("unchecked")
        private static <R> Nil<R> narrow() {
            return (Nil<R>) INSTANCE;
        }

        public static <R> Nil<R> apply() {
            return narrow();
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public int getArity() {
            return 0;
        }

        @Override
        public Option<T> getValue() {
            return Option.none();
        }

        @Override
        public Nil<T> getBranch(int index) {
            return narrow();
        }

        @Override
        public List<Tree<T>> getBranches() {
            return List.nil();
        }
    }

    class Tree2<T> implements Tree<T> {
        private final int length;
        private final T value;
        private final Tree<T> branch0;
        private final Tree<T> branch1;

        protected Tree2(T value, Tree<T> b0, Tree<T> b1) {
            this.length = 1 + b0.getLength() + b1.getLength();
            this.value = value;
            this.branch0 = b0;
            this.branch1 = b1;
        }

        public static <R> Tree2<R> apply(R value, Tree<R> b0, Tree<R> b1) {
            return new Tree2<>(value, b0, b1);
        }

        @Override
        public int getLength() {
            return this.length;
        }

        @Override
        public int getArity() {
            return 2;
        }

        @Override
        public Option<T> getValue() {
            return Option.some(this.value);
        }

        @Override
        public Tree<T> getBranch(int index) {
            switch (index) {
                case 0: return branch0;
                case 1: return branch1;
                default: return Tree.nil();
            }
        }

        @Override
        public List<Tree<T>> getBranches() {
            return List.cons(branch0, List.cons(branch1, List.nil()));
        }
    }
}
