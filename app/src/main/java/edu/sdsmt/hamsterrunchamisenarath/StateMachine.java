package edu.sdsmt.hamsterrunchamisenarath;

import edu.sdsmt.hamsterrunchamisenarath.States.BaseHamster;
import edu.sdsmt.hamsterrunchamisenarath.States.EndedGame;
import edu.sdsmt.hamsterrunchamisenarath.States.HeavyHamster;
import edu.sdsmt.hamsterrunchamisenarath.States.State;
import edu.sdsmt.hamsterrunchamisenarath.States.ZoomingHamster;

/**
 * The StateMachine class is used to manage different states of a game and transition between them.
 */
public class StateMachine {
    public enum StateEnum {BaseHamster, HeavyHamster, ZoomingHamster, EndedGame}

    private StateEnum currentState = StateEnum.BaseHamster;
    private State[] states;

    public StateMachine(Game g, MainActivity context) {
        states = new State[] {
                new BaseHamster(g, this), new HeavyHamster(g, this), new ZoomingHamster(g, this), new EndedGame(g, this, context)
        };
    }

    /**
     * The onUpdate function calls the maintenanceTask method of the current state's object with a
     * boolean parameter.
     *
     * @param moved The "moved" parameter is a boolean value that indicates whether or not the object
     * being updated has moved. It is used as an input to the "onUpdate" method to determine whether or
     * not to perform certain maintenance tasks on the object's current state.
     */
    public void onUpdate(boolean moved) {
        states[currentState.ordinal()].maintenanceTask(moved);
    }

    /**
     * This function sets the state of an object and starts or ends a task based on the new state.
     *
     * @param state The parameter "state" is an instance of the StateEnum enumeration class, which
     * represents the new state that the object will transition to. The method sets the current state
     * of the object to the new state and calls the startTask() method of the corresponding state
     * object. It also calls the endTask()
     */
    public void setState(StateEnum state) {
        states[this.currentState.ordinal()].endTask();

        currentState = state;

        states[this.currentState.ordinal()].startTask();
    }

    /**
     * This function returns the name of the current state in a Java program.
     *
     * @return The method `getCurrentStateName()` returns the name of the class of the current state in
     * the form of a String.
     */
    public String getCurrentStateName() {
        return states[currentState.ordinal()].getClass().getName();
    }
}
