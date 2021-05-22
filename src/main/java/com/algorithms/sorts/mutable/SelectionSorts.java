package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;
import com.algorithms.controls.Eval;

interface SelectionSorts {

    class Inline<T extends Comparable<T>> implements Sort<T> {
        public Inline() {}

        @Override
        public void sort(Array<T> sortable) {
            if (sortable.getLength() <= 1) return;
            for(var i = 0; i < sortable.getLength(); i++) {
                var smallest = i;
                for (var j = i; j < sortable.getLength(); j++) {
                    if (sortable.get(smallest).get().compareTo(sortable.get(j).get()) >= 0) {
                        smallest = j;
                    }
                }
                sortable.swap(i, smallest);
            }
        }
    }

    class Recursive<T extends Comparable<T>> implements Sort<T> {
        public Recursive() {}

        @Override
        public void sort(Array<T> sortable) {
            recursiveInner(0, 0, 0, sortable).run();
        }

        private Eval<Void> recursiveInner(int pivot, int runner, int smallest, Array<T> sortable) {
            if (pivot >= sortable.getLength()) return Eval.done(null);
            else if (runner >= sortable.getLength())
                return Eval.more(() -> {
                    var newPivot = pivot + 1;
                    return recursiveInner(newPivot, newPivot + 1, newPivot, sortable.swap(pivot, smallest));
                });
            else
                return Eval.more(() -> {
                    if (sortable.get(smallest).get().compareTo(sortable.get(runner).get()) >= 0) {
                        return recursiveInner(pivot, runner + 1, runner, sortable);
                    } else {
                        return recursiveInner(pivot, runner + 1, smallest, sortable);
                    }
                });
        }
    }
}
