package com.algorithms.collections.immutable;

import com.algorithms.controls.Option;
import org.junit.Test;

public class BinarySearchTreeTest {

    @Test
    public void apply() {
        BinarySearchTree.apply(3, 2, 4, 1, 5);
    }

    @Test
    public void get() {
        var bst = BinarySearchTree.apply(3, 2, 4, 1, 5);
        assert bst.get(1).equals(Option.some(1));
        assert bst.get(2).equals(Option.some(2));
        assert bst.get(3).equals(Option.some(3));
        assert bst.get(4).equals(Option.some(4));
        assert bst.get(5).equals(Option.some(5));
        assert bst.get(6).equals(Option.none());
    }

    @Test
    public void getLength() {
        var bst = BinarySearchTree.apply(3, 4, 1, 2, 7, -1, 5, 8, -10);
        assert bst.getLength() == 9;
    }

    @Test
    public void deleteMinEmpty() {
        var bst = BinarySearchTree.<Integer>apply().deleteMin();
        assert bst.getLength() == 0;
    }

    @Test
    public void deleteMinSingle() {
        var bst = BinarySearchTree.apply(1).deleteMin();
        assert bst.getLength() == 0;
        assert bst.get(1).equals(Option.none());
    }

    @Test
    public void deleteMinLast() {
        var bst = BinarySearchTree.apply(5, 4, 3).deleteMin();
        assert bst.getLength() == 2;
        assert bst.get(5).equals(Option.some(5));
        assert bst.get(4).equals(Option.some(4));
        assert bst.get(3).equals(Option.none());
    }

    @Test
    public void deleteMinFirst() {
        var bst = BinarySearchTree.apply(5, 6, 7).deleteMin();
        assert bst.getLength() == 2;
        assert bst.get(6).equals(Option.some(6));
        assert bst.get(7).equals(Option.some(7));
        assert bst.get(5).equals(Option.none());
    }

    @Test
    public void deleteMinMixed() {
        var bst = BinarySearchTree.apply(10, 5, 3, 4, 6).deleteMin();
        assert bst.getLength() == 4;
        assert bst.get(4).equals(Option.some(4));
        assert bst.get(5).equals(Option.some(5));
        assert bst.get(6).equals(Option.some(6));
        assert bst.get(10).equals(Option.some(10));
        assert bst.get(3).equals(Option.none());
    }

    @Test
    public void deleteMaxEmpty() {
        var bst = BinarySearchTree.<Integer>apply().deleteMax();
        assert bst.getLength() == 0;
    }

    @Test
    public void deleteMaxSingle() {
        var bst = BinarySearchTree.apply(1).deleteMax();
        assert bst.getLength() == 0;
        assert bst.get(1).equals(Option.none());
    }

    @Test
    public void deleteMaxLast() {
        var bst = BinarySearchTree.apply(5, 6, 7).deleteMax();
        assert bst.getLength() == 2;
        assert bst.get(5).equals(Option.some(5));
        assert bst.get(6).equals(Option.some(6));
        assert bst.get(7).equals(Option.none());
    }

    @Test
    public void deleteMaxFirst() {
        var bst = BinarySearchTree.apply(7, 6, 5).deleteMax();
        assert bst.getLength() == 2;
        assert bst.get(6).equals(Option.some(6));
        assert bst.get(5).equals(Option.some(5));
        assert bst.get(7).equals(Option.none());
    }

    @Test
    public void deleteMaxMixed() {
        var bst = BinarySearchTree.apply(-10, -5, -3, -4, -6).deleteMax();
        assert bst.getLength() == 4;
        assert bst.get(-4).equals(Option.some(-4));
        assert bst.get(-5).equals(Option.some(-5));
        assert bst.get(-6).equals(Option.some(-6));
        assert bst.get(-10).equals(Option.some(-10));
        assert bst.get(-3).equals(Option.none());
    }

    @Test
    public void deleteEmpty() {
        var bst = BinarySearchTree.<Integer>apply().delete(0);
        assert bst.getLength() == 0;
    }

    @Test
    public void deleteSingle() {
        var bst = BinarySearchTree.apply(1).delete(1);
        assert bst.getLength() == 0;
        assert bst.get(1).equals(Option.none());
    }

    @Test
    public void deleteLast() {
        var bst = BinarySearchTree.apply(1, 2).delete(2);
        assert bst.getLength() == 1;
        assert bst.get(1).equals(Option.some(1));
        assert bst.get(2).equals(Option.none());
    }

    @Test
    public void deleteMiss() {
        var bst = BinarySearchTree.apply(1, 2).delete(3);
        assert bst.getLength() == 2;
        assert bst.get(1).equals(Option.some(1));
        assert bst.get(2).equals(Option.some(2));
    }

    @Test
    public void deleteInRightListLikeBst() {
        var bst = BinarySearchTree.apply(1, 2, 3, 4).delete(2);
        assert bst.getLength() == 3;
        assert bst.get(1).equals(Option.some(1));
        assert bst.get(3).equals(Option.some(3));
        assert bst.get(4).equals(Option.some(4));
        assert bst.get(2).equals(Option.none());
    }

    @Test
    public void deleteInLeftListLikeBst() {
        var bst = BinarySearchTree.apply(-1, -2, -3, -4).delete(-3);
        assert bst.getLength() == 3;
        assert bst.get(-1).equals(Option.some(-1));
        assert bst.get(-2).equals(Option.some(-2));
        assert bst.get(-4).equals(Option.some(-4));
        assert bst.get(-3).equals(Option.none());
    }

    @Test
    public void deleteMixedLeftWeighed() {
        var bst = BinarySearchTree.apply(10, 8, 9, 6, 7, 3, 4, 2).delete(8);
        assert bst.getLength() == 7;
        assert bst.get(10).equals(Option.some(10));
        assert bst.get(9).equals(Option.some(9));
        assert bst.get(6).equals(Option.some(6));
        assert bst.get(7).equals(Option.some(7));
        assert bst.get(4).equals(Option.some(4));
        assert bst.get(3).equals(Option.some(3));
        assert bst.get(2).equals(Option.some(2));
        assert bst.get(8).equals(Option.none());
    }

    @Test
    public void deleteMixedRightWeighed() {
        var bst = BinarySearchTree.apply(0, 10, 5, 8, 7, 6, 9, 2).delete(5);
        assert bst.getLength() == 7;
        assert bst.get(0).equals(Option.some(0));
        assert bst.get(10).equals(Option.some(10));
        assert bst.get(2).equals(Option.some(2));
        assert bst.get(8).equals(Option.some(8));
        assert bst.get(7).equals(Option.some(7));
        assert bst.get(6).equals(Option.some(6));
        assert bst.get(9).equals(Option.some(9));
        assert bst.get(5).equals(Option.none());
    }
}
