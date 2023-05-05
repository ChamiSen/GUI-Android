package edu.sdsmt.hamsterrunchamisenarath.States;

import edu.sdsmt.hamsterrunchamisenarath.Game;
import edu.sdsmt.hamsterrunchamisenarath.StateMachine;

/**
 * The abstract class State defines methods for ending, starting, and maintaining tasks in a game state
 * machine.
 */
public abstract class State {
    protected Game g;
    protected StateMachine machine;

    public State(Game g, StateMachine machine) {
        this.g = g;
        this.machine = machine;
    }

    public abstract void endTask();

    public abstract void startTask();

    public abstract void maintenanceTask(boolean moved);
}
