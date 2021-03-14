package com.algorithms.collections.set;

import com.algorithms.controls.option.None;
import com.algorithms.controls.option.Some;
import org.junit.Test;

public class SetListTest {

    @Test
    public void isApplicative() {
        SetList.apply(1, 2, 3);
    }

    @Test
    public void get() {
        var set = SetList.apply(1, 2, 3);
        assert set.get(1).equals(Some.apply(1));
        assert set.get(2).equals(Some.apply(2));
        assert set.get(3).equals(Some.apply(3));
        assert set.get(4).equals(None.apply());
    }

    @Test
    public void add() {
        var set = SetList.apply(1, 2, 3);
        set.add(5);
        set.add(6);
        set.add(10);
        set.add(14);
        set.add(15);
        assert set.get(1).equals(Some.apply(1));
        assert set.get(2).equals(Some.apply(2));
        assert set.get(3).equals(Some.apply(3));
        assert set.get(5).equals(Some.apply(5));
        assert set.get(6).equals(Some.apply(6));
        assert set.get(10).equals(Some.apply(10));
        assert set.get(14).equals(Some.apply(14));
        assert set.get(15).equals(Some.apply(15));
        assert set.get(20).equals(None.apply());
    }

    @Test
    public void remove() {
        var set = SetList.apply(1, 2, 3, 5, 6, 10);
        set.remove(3).remove(4);
        assert set.get(1).equals(Some.apply(1));
        assert set.get(2).equals(Some.apply(2));
        assert set.get(3).equals(None.apply());
        assert set.get(5).equals(Some.apply(5));
        assert set.get(6).equals(Some.apply(6));
        assert set.get(10).equals(Some.apply(10));
    }

    @Test
    public void rehashes() {
        var set = SetList.apply(1);
        set.add(5);
        set.add(6);
        set.add(10);
        set.add(14);
        set.add(15);
        assert set.get(1).equals(Some.apply(1));
        assert set.get(5).equals(Some.apply(5));
        assert set.get(6).equals(Some.apply(6));
        assert set.get(10).equals(Some.apply(10));
        assert set.get(14).equals(Some.apply(14));
        assert set.get(15).equals(Some.apply(15));
        assert set.get(20).equals(None.apply());
    }
}
