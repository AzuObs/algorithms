package com.algorithms.sorts.mutable;

import com.algorithms.collections.mutable.Array;
import com.algorithms.collections.mutable.ArrayFixed;
import org.junit.Test;

public abstract class SortTest {

    public abstract <T extends Comparable<T>> void sort(Array<T> sortable);

    @Test
    public void sortEmpty() {
        var sortable = ArrayFixed.<Character>fill();
        sort(sortable);
        assert sortable.getLength() == 0;
    }

    @Test
    public void sortSingle() {
        var sortable = ArrayFixed.fill('a', 1);
        sort(sortable);
        assert sortable.getLength() == 1;
        assert sortable.get(0).get() == 'a';
    }

    @Test
    public void sortPreSorted() {
        var sortable = ArrayFixed.fill('a', 'b', 'c');
        sort(sortable);
        assert sortable.getLength() == 3;
        assert sortable.get(0).get() == 'a';
        assert sortable.get(1).get() == 'b';
        assert sortable.get(2).get() == 'c';
    }

    @Test
    public void sortUnsorted() {
        var sortable = ArrayFixed.fill('a', 'd', 'c', 'e', 'm', 'l');
        sort(sortable);
        assert sortable.getLength() == 6;
        assert sortable.get(0).get() == 'a';
        assert sortable.get(1).get() == 'c';
        assert sortable.get(2).get() == 'd';
        assert sortable.get(3).get() == 'e';
        assert sortable.get(4).get() == 'l';
        assert sortable.get(5).get() == 'm';
    }

    @Test
    public void sortAllEqual() {
        var sortable = ArrayFixed.fill('a', 'a', 'a');
        sort(sortable);
        assert sortable.getLength() == 3;
        assert sortable.get(0).get() == 'a';
        assert sortable.get(1).get() == 'a';
        assert sortable.get(2).get() == 'a';
    }
}


