package com.algorithms.puzzles.ctci;

import java.util.ArrayList;

public class Queens {

  public static ArrayList<Integer[]> ways(int gridSize) {
    var results = new ArrayList<Integer[]>();
    waysInner(gridSize, 0, new Integer[gridSize], results);
    return results;
  }

  private static void waysInner(int gridSize, int row, Integer[] columns, ArrayList<Integer[]> results) {
    if (row == gridSize) { results.add(columns.clone()); return; }
    for (int col = 0; col < gridSize; col++) {
      if (checkValid(columns, row, col)) {
        columns[row] = col;
        waysInner(gridSize, row + 1, columns, results);
      }
    }
  }

  private static boolean checkValid(Integer[] columns, int row1, int col1) {
    for (int row2 = 0; row2 < row1; row2++) {
      int col2 = columns[row2];
      if (col1 == col2) return false;

      int colDist = Math.abs(col2 - col1);
      int rowDist = row1 - row2;
      if (colDist == rowDist) return false;
    }
    return true;
  }
}
