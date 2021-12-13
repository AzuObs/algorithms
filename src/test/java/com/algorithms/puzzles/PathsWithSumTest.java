package com.algorithms.puzzles;

import com.algorithms.puzzles.PathsWithSum.Node;
import org.junit.Test;

public class PathsWithSumTest {

  @Test
  public void empty() {
    var paths = new PathsWithSum(null, 10);
    assert paths.getPaths() == 0;
  }

  @Test
  public void one() {
    var root = new Node(0);
    var paths = new PathsWithSum(root, 10);
    assert paths.getPaths() == 0;
  }

  @Test
  public void linkedList() {
    var root = new Node(0, new Node(2, new Node(1, new Node(4, new Node(1, new Node(3))))));
    var paths = new PathsWithSum(root, 3);
    assert paths.getPaths() == 2;
  }

  @Test
  public void many() {
    var root =
        new Node(0,
            new Node(2,
                new Node(1,
                    new Node(3),
                    new Node(1)),
                new Node(-1)),
            new Node(1,
                new Node(4,
                    new Node(-2),
                    new Node(1)),
                new Node(-1)));

    var paths = new PathsWithSum(root, 4);
    assert paths.getPaths() == 3;
  }
}
