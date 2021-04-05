package com.algorithms.collections.immutable;

import com.algorithms.controls.Option;
import org.junit.Test;

public class TreeTest {
    private final Tree<Integer> tree =
            Tree.tree2(1,
                Tree.tree2(2,
                    Tree.tree2( 3,
                        Tree.tree2(4,
                            Tree.nil(),
                            Tree.nil()),
                        Tree.nil()),
                    Tree.tree2(5,
                        Tree.nil(),
                        Tree.nil())),
                Tree.nil());

    @Test
    public void topologicalSort() {
        var sorted = tree.topologicalSort();
        assert sorted.getHead().equals(Option.some(1));
        assert sorted.getTail().getHead().equals(Option.some(2));
        assert sorted.getTail().getTail().getHead().equals(Option.some(3));
        assert sorted.getTail().getTail().getTail().getHead().equals(Option.some(4));
        assert sorted.getTail().getTail().getTail().getTail().getHead().equals(Option.some(5));
        assert sorted.getTail().getTail().getTail().getTail().getTail().getHead().equals(Option.none());
    }

    @Test
    public void length() {
        assert tree.getLength() == 5;
        assert tree.getBranch(0).getLength() == 4;
        assert tree.getBranch(0).getBranch(1).getLength() == 1;
        assert tree.getBranch(1).getLength() == 0;
    }
}
