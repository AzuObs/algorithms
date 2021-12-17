package com.algorithms.puzzles.ctci;

import org.junit.Test;
import com.algorithms.puzzles.ctci.RobotInAGrid.Grid;

public class RobotInAGridTest {

  @Test
  public void empty() {
    var grid = new Grid(0, 0);
    assert !RobotInAGrid.pathExists(grid, 0, 0);
  }

  @Test
  public void one() {
    var grid = new Grid(1, 1);
    assert RobotInAGrid.pathExists(grid, 0, 0);
  }

  @Test
  public void noBlocks() {
    var grid = new Grid(4, 4);
    assert RobotInAGrid.pathExists(grid, 3, 3);
  }

  @Test
  public void someBlocks() {
    var grid = new Grid(4, 4);
    grid.markCell(1, 0, true);
    grid.markCell(3, 2, true);
    assert RobotInAGrid.pathExists(grid, 3, 3);
  }

  @Test
  public void completeBlocks() {
    var grid = new Grid(4, 4);
    grid.markCell(2, 3, true);
    grid.markCell(3, 2, true);
    assert !RobotInAGrid.pathExists(grid, 3, 3);
  }
}
