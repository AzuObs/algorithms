package com.algorithms.utils.union;

import java.util.HashSet;

public class WeighedQuickUnion implements UnionFind {
  private final int[] ids;
  private final int[] sizes;
  private final HashSet<Integer> groupIds;

  public WeighedQuickUnion(int size) {
    ids = new int[size];
    sizes = new int[size];
    groupIds = new HashSet<>();
    for (var i = 0; i < size; i++) { ids[i] = i; groupIds.add(i); }
    for (var i = 0; i < size; i++) { sizes[i] = 1; }
  }

  @Override
  public void join(int idA, int idB) {
    var aGroupId = getGroupId(idA);
    var bGroupId = getGroupId(idB);
    var aSize = sizes[aGroupId];
    var bSize = sizes[bGroupId];
    if (aSize >= bSize) {
      ids[bGroupId] = aGroupId;
      sizes[aGroupId] = aSize + bSize;
      groupIds.remove(bGroupId);
    } else {
      ids[aGroupId] = bGroupId;
      sizes[bGroupId] = aSize + bSize;
      groupIds.remove(aGroupId);
    }
  }

  @Override
  public int groupId(int id) {
    return getGroupId(id);
  }

  @Override
  public int groups() {
    return groupIds.size();
  }

  @Override
  public int[] groupIds() {
    return groupIds.stream().mapToInt(n -> n).toArray();
  }

  protected int size(int id) {
    return sizes[id];
  }

  private int getGroupId(int id) {
    while (ids[id] != id) { id = ids[id]; }
    return id;
  }
}
