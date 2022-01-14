package com.algorithms.puzzles.ctci;

import com.algorithms.utils.union.WeighedQuickUnion;

import java.util.HashMap;

public class BabyNames {
  private HashMap<String, Integer> groupFrequencies;

  private BabyNames(HashMap<String, Integer> groupFrequencies) {
    this.groupFrequencies = groupFrequencies;
  }

  public int getFrequency(String groupId) {
    return groupFrequencies.getOrDefault(groupId, 0);
  }

  public static BabyNames create(HashMap<String, Integer> names, HashMap<String, String> synonyms) {
    // map names to ids
    var nameIdCount = 0;
    var nameIdMap = new HashMap<String, Integer>();
    var idNameMap = new HashMap<Integer, String>();
    for (String name: names.keySet()) {
      nameIdMap.put(name, nameIdCount);
      idNameMap.put(nameIdCount, name);
      nameIdCount++;
    }

    // union synonyms
    var unions = new WeighedQuickUnion(nameIdMap.size());
    for (String name: synonyms.keySet()) {
      var synonym = synonyms.get(name);
      unions.join(nameIdMap.get(name), nameIdMap.get(synonym));
    }

    // reduce to group frequencies
    var groupIdFrequencies = new HashMap<Integer, Integer>();
    for (int id: idNameMap.keySet()) {
      var groupId = unions.groupId(id);
      var groupFrequency = groupIdFrequencies.getOrDefault(groupId, 0);
      var name = idNameMap.get(id);
      var idFrequency = names.get(name);
      groupIdFrequencies.put(groupId, groupFrequency + idFrequency);
    }
    var groupNameFrequencies = new HashMap<String, Integer>();
    for (int groupId: groupIdFrequencies.keySet()) {
      var groupFrequency = groupIdFrequencies.get(groupId);
      var groupName = idNameMap.get(groupId);
      groupNameFrequencies.put(groupName, groupFrequency);
    }

    return new BabyNames(groupNameFrequencies);
  }
}
