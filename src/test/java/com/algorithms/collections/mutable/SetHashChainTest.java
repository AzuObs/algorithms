package com.algorithms.collections.mutable;

import com.algorithms.controls.Option;
import org.junit.Test;

public class SetHashChainTest {

    @Test
    public void isApplicative() {
        SetHashChain.apply(1, 2, 3);
    }

    @Test
    public void get() {
        var set = SetHashChain.apply(1, 2, 3);
        assert set.get(1).equals(Option.some(1));
        assert set.get(2).equals(Option.some(2));
        assert set.get(3).equals(Option.some(3));
        assert set.get(4).equals(Option.none());
    }

    @Test
    public void add() {
        var set = SetHashChain.apply(1, 2, 3);
        set.add(5);
        set.add(6);
        set.add(10);
        set.add(14);
        set.add(15);
        assert set.get(1).equals(Option.some(1));
        assert set.get(2).equals(Option.some(2));
        assert set.get(3).equals(Option.some(3));
        assert set.get(5).equals(Option.some(5));
        assert set.get(6).equals(Option.some(6));
        assert set.get(10).equals(Option.some(10));
        assert set.get(14).equals(Option.some(14));
        assert set.get(15).equals(Option.some(15));
        assert set.get(20).equals(Option.none());
    }

    @Test
    public void remove() {
        var set = SetHashChain.apply(1, 2, 3, 5, 6, 10);
        set.remove(3).remove(4);
        assert set.get(1).equals(Option.some(1));
        assert set.get(2).equals(Option.some(2));
        assert set.get(3).equals(Option.none());
        assert set.get(5).equals(Option.some(5));
        assert set.get(6).equals(Option.some(6));
        assert set.get(10).equals(Option.some(10));
    }

    @Test
    public void rehashes() {
        var set = SetHashChain.apply(1);
        set.add(5);
        set.add(6);
        set.add(10);
        set.add(14);
        set.add(15);
        assert set.get(1).equals(Option.some(1));
        assert set.get(5).equals(Option.some(5));
        assert set.get(6).equals(Option.some(6));
        assert set.get(10).equals(Option.some(10));
        assert set.get(14).equals(Option.some(14));
        assert set.get(15).equals(Option.some(15));
        assert set.get(20).equals(Option.none());
    }
}
