package com.algorithms.puzzles.ctci;

public class RobotInAGrid {

  public static class Grid {
    public boolean[][] grid;
    public Grid(int rows, int columns) { this.grid = new boolean[rows][columns]; }
    public void markCell(int row, int column, boolean as) { this.grid[row][column] = as; }
    public boolean getCell(int row, int column) { return this.grid[row][column]; }
    public int maxRow() { return grid.length; }
    public int maxColumn() { return grid[0].length; }
  }

  public static boolean pathExists(Grid grid, int targetRow, int targetColumn) {
    if (grid.maxRow() == 0 || grid.maxColumn() == 0) return false;
    return pathExists(grid, 0, 0, targetRow, targetColumn);
  }

  private static boolean pathExists(Grid grid, int crow, int ccol, int trow, int tcol) {
    // check if currentRow and currentColumn is the target
    if (crow == trow && ccol == tcol) return true;

    // mark current row + column as visited
    grid.markCell(crow, ccol, true);

    // if we can go right && its not been computed before && we move closer
      // recurse with right
    if (crow < trow && crow < grid.maxRow() && !grid.getCell(crow + 1, ccol)) {
      if (pathExists(grid, crow + 1, ccol, trow, tcol)) return true;
    }

    // if we can go down && its not been computer before && we move closer
      // recurse with down
    if (ccol < tcol && ccol < grid.maxColumn() && !grid.getCell(crow, ccol + 1)) {
      if (pathExists(grid, crow, ccol + 1, trow, tcol)) return true;
    }

    return false;
  }
}
