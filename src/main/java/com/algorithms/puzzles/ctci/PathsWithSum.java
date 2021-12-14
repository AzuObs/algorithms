package com.algorithms.puzzles.ctci;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PathsWithSum {
  public static class Node {
    private int val;
    private Node left, right;
    public Node(int val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
    public Node(int val, Node left) { this(val, left, null); }
    public Node(int val) { this(val, null, null); }
  }

   private Map<Integer, Set<Integer>> runningSums;
   private int paths, targetSum;

   public PathsWithSum(Node root, int targetSum) {
     this.runningSums = new HashMap<>();
     this.paths = 0;
     this.targetSum = targetSum;
     findPath(root, 0);
   }

   public int getPaths() {
     return paths;
   }

   public void findPath(Node current, int parentRunningSum) {
     if (current == null) return;

     var runningSum = parentRunningSum + current.val;
     addToRunningSums(current.val, runningSum);
     checkPaths(runningSum);
     findPath(current.left, runningSum);
     findPath(current.right, runningSum);
     removeFromRunningSums(current.val, runningSum);
   }

   private void checkPaths(int runningSum) {
     var remainder = runningSum - targetSum;
     var matches = runningSums.getOrDefault(remainder, new HashSet<>());
     paths += matches.size();
   }

   private void addToRunningSums(int vertex, int runningSum) {
     var existing = runningSums.getOrDefault(runningSum, new HashSet<>());
     existing.add(vertex);
     runningSums.put(runningSum, existing);
   }

   private void removeFromRunningSums(int vertex, int runningSum) {
     var existing = runningSums.getOrDefault(runningSum, new HashSet<>());
     existing.remove(vertex);
   }
}
