package com.algorithms.puzzles.ctci;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AnimalShelter {
  private static class Animal {
    private final long order;
    private final String name;

    public Animal(long order, String name) {
      this.order = order;
      this.name = name;
    }
  }

  private long order;
  private LinkedList<Animal> dogs;
  private LinkedList<Animal> cats;

  public AnimalShelter() {
    this.order = 0;
    this.cats = new LinkedList<>();
    this.dogs = new LinkedList<>();
  }

  public String enqueueDog(String name) {
    dogs.push(new Animal(order++, name));
    return name;
  }

  public String enqueueCat(String name) {
    cats.push(new Animal(order++, name));
    return name;
  }

  public String dequeue() throws NoSuchElementException {
    var cat = cats.peek();
    var dog = dogs.peek();
    if (cat == null) return dogs.pop().name;
    if (dog == null) return cats.pop().name;
    return cat.order > dog.order ? cats.pop().name : dogs.pop().name;
  }

  public String dequeueDog() throws NoSuchElementException {
    return dogs.pop().name;
  }

  public String dequeueCat() throws NoSuchElementException {
    return cats.pop().name;
  }
}
