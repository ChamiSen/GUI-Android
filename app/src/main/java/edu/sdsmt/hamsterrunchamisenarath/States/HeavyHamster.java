package edu.sdsmt.hamsterrunchamisenarath.States;

import edu.sdsmt.hamsterrunchamisenarath.Game;
import edu.sdsmt.hamsterrunchamisenarath.StateMachine;

/**
 * The HeavyHamster class is a state in a game that sets the energy required to move, performs
 * maintenance tasks, and transitions to other states based on game conditions.
 */
public class HeavyHamster extends State {
    public HeavyHamster(Game g, StateMachine machine) {
        super(g, machine);
    }

    @Override
    public void endTask() {

    }

    @Override
    // The `startTask()` method is setting the energy required to move to 2 for the game. This is
    // specific to the `HeavyHamster` state in the game.
    public void startTask() {
        // GRADING: ENERGY
        g.setEnergyToMove(2);
    }

    @Override
    // The `maintenanceTask(boolean moved)` method is performing maintenance tasks for the game. It
    // takes a boolean parameter `moved` which indicates whether the hamster has moved or not. If the
    // hamster has moved, it calls the `pickup()` method on the game object `g`. It then checks if the
    // game has been won or lost, and if so, transitions to the `EndedGame` state. If the hamster has
    // moved more than once, it transitions to the `ZoomingHamster` state. If the food level is less
    // than 15, it transitions to the `BaseHamster` state.
    public void maintenanceTask(boolean moved) {
        if(moved)
            g.pickup();

        if(g.isWon() || g.isLost()) {
            machine.setState(StateMachine.StateEnum.EndedGame);
            return;
        }

        if(g.getZoomyMove() > 1) {
            // GRADING: TO_ZOOM
            machine.setState(StateMachine.StateEnum.ZoomingHamster);
            return;
        }

        if(g.getFood() < 15) {
            machine.setState(StateMachine.StateEnum.BaseHamster);
        }
    }
}
