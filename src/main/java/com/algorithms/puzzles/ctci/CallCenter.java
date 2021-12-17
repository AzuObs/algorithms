package com.algorithms.puzzles.ctci;

import java.util.ArrayList;
import java.util.Optional;

public class CallCenter {
  private interface Call { }
  private static class IncomingCall implements Call { }
  private enum CallResult { Handled, Unhandled }

  private interface Employee { }
  private static abstract class Responder implements Employee {
    public IncomingCall incoming;
    public ResponderAvailability responderAvailability;
    public Responder() {
      this.responderAvailability = ResponderAvailability.AVAILABLE;
    }
    public boolean isAvailable() {
      return responderAvailability == ResponderAvailability.AVAILABLE;
    }
    public abstract void handleCall(IncomingCall incoming);
    public abstract void terminateCall();
  }
  private enum ResponderAvailability { AVAILABLE, BUSY }
  private static class Respondent extends Responder {
    public void handleCall(IncomingCall incoming) {
      this.responderAvailability = ResponderAvailability.BUSY;
      this.incoming = incoming;
    }
    public void terminateCall() { }
  }
  private static class Manager extends Responder {
    public void handleCall(IncomingCall incoming) {
      this.responderAvailability = ResponderAvailability.BUSY;
      this.incoming = incoming;
    }
    public void terminateCall() { }
  }
  private static class Director extends Responder {
    public void handleCall(IncomingCall incoming) {
      this.responderAvailability = ResponderAvailability.BUSY;
      this.incoming = incoming;
    }
    public void terminateCall() { }
  }

  private static class EmployeeRepository {
    public ArrayList<Respondent> respondents;
    public ArrayList<Manager> managers;
    public ArrayList<Director> directors;

    public EmployeeRepository(
        ArrayList<Respondent> respondents,
        ArrayList<Manager> managers,
        ArrayList<Director> directors
    ) {
      this.respondents = respondents;
      this.managers = managers;
      this.directors = directors;
    }
  }

  private static class CallDispatcher {
    private final EmployeeRepository employeeRepository;
    public CallDispatcher(EmployeeRepository employeeRepository) {
      this.employeeRepository = employeeRepository;
    }

    public CallResult dispatchCall(IncomingCall incoming) {
      if (dispatchCallTo(new ArrayList<>(employeeRepository.respondents), incoming).isPresent()) {
        return CallResult.Handled;
      } else if (dispatchCallTo(new ArrayList<>(employeeRepository.managers), incoming).isPresent()) {
        return CallResult.Handled;
      } else if (dispatchCallTo(new ArrayList<>(employeeRepository.directors), incoming).isPresent()) {
        return CallResult.Handled;
      } else {
        return CallResult.Unhandled;
      }
    }

    public Optional<Responder> dispatchCallTo(ArrayList<Responder> responders, IncomingCall call) {
      var available = responders.stream().filter(Responder::isAvailable).findFirst();
      available.ifPresent((r) -> r.handleCall(call));
      return available;
    }
  }

  private final EmployeeRepository employeeRepository;
  private final CallDispatcher callDispatcher;

  public CallCenter(
      ArrayList<Respondent> respondents,
      ArrayList<Manager> managers,
      ArrayList<Director> directors
  ) {
    this.employeeRepository = new EmployeeRepository(respondents, managers, directors);
    this.callDispatcher = new CallDispatcher(employeeRepository);
  }

  public CallResult dispatchCall(IncomingCall incoming) {
    return callDispatcher.dispatchCall(incoming);
  }
}