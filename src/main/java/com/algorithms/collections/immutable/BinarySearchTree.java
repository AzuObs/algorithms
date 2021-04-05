package com.algorithms.collections.immutable;

import com.algorithms.controls.Eval;
import com.algorithms.controls.Option;
import com.algorithms.utils.tuples.Tuple2;

public class BinarySearchTree<T extends Comparable<T>> {
    private final Tree<T> underlying;

    private BinarySearchTree(Tree<T> input) {
        this.underlying = input;
    }

    public static <R extends Comparable<R>> BinarySearchTree<R> apply(R... values) {
        var bst = new BinarySearchTree<R>(Tree.nil());
        for (R value: values) {
            bst = bst.add(value);
        }
        return bst;
    }

    public int getLength() {
        return underlying.getLength();
    }

    public Option<T> get(T value) {
        return getInner(value, underlying).run();
    }

    private Eval<Option<T>> getInner(T value, Tree<T> from) {
        if (from.isEmpty()) return Eval.done(Option.none());
        var cmp = value.compareTo(from.getValue().get());
        if      (cmp == 0) return Eval.done(Option.some(value));
        else if (cmp < 0)  return Eval.more(() -> getInner(value, from.getBranch(0)));
        else               return Eval.more(() -> getInner(value, from.getBranch(1)));
    }

    public BinarySearchTree<T> add(T value) {
        return new BinarySearchTree<>(addInner(value, underlying, List.nil()).run());
    }

    private Eval<Tree<T>> addInner(T value, Tree<T> current, List<Tree<T>> traversed) {
        if (current.isEmpty()) return Eval.more(() -> relink(traversed, Tree.tree2(value, Tree.nil(), Tree.nil())));
        var cmp = current.getValue().get().compareTo(value);
        if      (cmp > 0) return Eval.more(() -> addInner(value, current.getBranch(0), List.cons(current, traversed)));
        else if (cmp < 0) return Eval.more(() -> addInner(value, current.getBranch(1), List.cons(current, traversed)));
        else              return Eval.more(() -> relink(traversed, current));
    }

    public BinarySearchTree<T> delete(T value) {
        return new BinarySearchTree<>(deleteInner(value, underlying, List.nil()).run());
    }

    private Eval<Tree<T>> deleteInner(T value, Tree<T> current, List<Tree<T>> traversed) {
        if (current.isEmpty())
            return Eval.more(() -> relink(traversed, current));
        var cmp = current.getValue().get().compareTo(value);
        if (cmp > 0)
            return Eval.more(() -> deleteInner(value, current.getBranch(0), List.cons(current, traversed)));
        else if (cmp < 0)
            return Eval.more(() -> deleteInner(value, current.getBranch(1), List.cons(current, traversed)));
        else {
            if (current.getBranch(0).isEmpty() && current.getBranch(1).isEmpty()) {
                return traversed.isEmpty()
                    ? Eval.done(Tree.nil())
                    : Eval.more(() -> {
                        // remove current branch from previous branch
                        var previous = traversed.getHead().get();
                        var cmpPrevious = previous.getValue().get().compareTo(value);
                        var newPrevious = cmpPrevious > 0
                            ? Tree.tree2(previous.getValue().get(), Tree.nil(), previous.getBranch(1))
                            : Tree.tree2(previous.getValue().get(), previous.getBranch(0), Tree.nil());
                        return deleteInner(value, Tree.nil(), List.cons(newPrevious, traversed.getTail()));
                    });
            }
            else if (current.getBranch(0).isEmpty())
                return Eval.more(() -> deleteInner(value, Tree.nil(), List.cons(current.getBranch(1), traversed)));
            else if (current.getBranch(1).isEmpty())
                return Eval.more(() -> deleteInner(value, Tree.nil(), List.cons(current.getBranch(0), traversed)));
            else
                return Eval.more(() -> {
                    // T. Hibbard's method
                    if (current.getBranch(1).getLength() > current.getBranch(0).getLength()) {
                        var deleted = deleteMinInner(current.getBranch(1), Option.none(), List.nil()).run();
                        var newCurrent = Tree.tree2(deleted.key.get(), current.getBranch(0), deleted.value);
                        return deleteInner(value, Tree.nil(), List.cons(newCurrent, traversed));
                    } else {
                        var deleted = deleteMaxInner(current.getBranch(0), Option.none(), List.nil()).run();
                        var newCurrent = Tree.tree2(deleted.key.get(), deleted.value, current.getBranch(1));
                        return deleteInner(value, Tree.nil(), List.cons(newCurrent, traversed));
                    }
                });
        }
    }

    public BinarySearchTree<T> deleteMin() {
        return new BinarySearchTree<>(deleteMinInner(underlying, Option.none(), List.nil()).run().value);
    }

    private Eval<Tuple2<Option<T>, Tree<T>>> deleteMinInner(Tree<T> current, Option<T> deleted, List<Tree<T>> traversed) {
        if (current.isEmpty())
            return Eval.done(Tuple2.apply(deleted, relink(traversed, Tree.nil()).run()));
        else if (current.getBranch(0).exists())
            return Eval.more(() -> deleteMinInner(current.getBranch(0), Option.none(), List.cons(current, traversed)));
        else {
            return traversed.isEmpty()
                ? Eval.done(Tuple2.apply(current.getValue(), current.getBranch(1)))
                : Eval.more(() -> {
                    // remove current branch from previous branch
                    var previous = traversed.getHead().get();
                    var previousMinusCurrent = Tree.tree2(previous.getValue().get(), Tree.nil(), previous.getBranch(1));
                    var newTraversed = List.cons(current.getBranch(1), List.cons(previousMinusCurrent, traversed.getTail()));
                    return Eval.more(() -> deleteMinInner(Tree.nil(), current.getValue(), newTraversed));
                });
        }
    }

    public BinarySearchTree<T> deleteMax() {
        return new BinarySearchTree<>(deleteMaxInner(underlying, Option.none(), List.nil()).run().value);
    }

    private Eval<Tuple2<Option<T>, Tree<T>>> deleteMaxInner(Tree<T> current, Option<T> deleted, List<Tree<T>> traversed) {
        if (current.isEmpty())
            return Eval.done(Tuple2.apply(deleted, relink(traversed, Tree.nil()).run()));
        else if (current.getBranch(1).exists())
            return Eval.more(() -> deleteMaxInner(current.getBranch(1), Option.none(), List.cons(current, traversed)));
        else {
            return traversed.isEmpty()
                ? Eval.done(Tuple2.apply(current.getValue(), current.getBranch(0)))
                : Eval.more(() -> {
                    // remove current branch from previous branch and link to a child branch instead
                    var previous = traversed.getHead().get();
                    var previousMinusCurrent = Tree.tree2(previous.getValue().get(), previous.getBranch(0), Tree.nil());
                    var newTraversed = List.cons(current.getBranch(0), List.cons(previousMinusCurrent, traversed.getTail()));
                    return deleteMaxInner(Tree.nil(), current.getValue(), newTraversed);
                });
            }
    }

    private Eval<Tree<T>> relink(List<Tree<T>> traversed, Tree<T> result) {
        if      (traversed.isEmpty()) return Eval.done(result);
        else if (result.isEmpty())    return Eval.more(() -> relink(traversed.getTail(), traversed.getHead().get()));
        else return Eval.more(() -> {
            var head = traversed.getHead().get();
            var cmp = head.getValue().get().compareTo(result.getValue().get());
             return cmp > 0
                ? relink(traversed.getTail(), Tree.tree2(head.getValue().get(), result, head.getBranch(1)))
                : relink(traversed.getTail(), Tree.tree2(head.getValue().get(), head.getBranch(0), result));
        });
    }
}
