package com.algorithms.puzzles.ctci;

import org.junit.Test;

public class AnimalShelterTest {

  @Test
  public void enqueueDequeueCats() {
    var shelter = new AnimalShelter();
    shelter.enqueueCat("Lizzy");
    shelter.enqueueCat("Raven");
    assert shelter.dequeueCat().equals("Raven");
    assert shelter.dequeueCat().equals("Lizzy");
  }

  @Test
  public void enqueueDequeueDogs() {
    var shelter = new AnimalShelter();
    shelter.enqueueDog("Spot");
    shelter.enqueueDog("Ralf");
    assert shelter.dequeueDog().equals("Ralf");
    assert shelter.dequeueDog().equals("Spot");
  }

  @Test
  public void dequeues() {
    var shelter = new AnimalShelter();
    shelter.enqueueDog("Spot");
    shelter.enqueueDog("Ralf");
    shelter.enqueueCat("Lizzy");
    shelter.enqueueCat("Raven");
    assert shelter.dequeue().equals("Raven");
    assert shelter.dequeue().equals("Lizzy");
    assert shelter.dequeue().equals("Ralf");
    assert shelter.dequeue().equals("Spot");
  }
}
