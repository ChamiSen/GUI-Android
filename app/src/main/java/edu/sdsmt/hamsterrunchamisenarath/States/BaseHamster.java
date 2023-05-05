package edu.sdsmt.hamsterrunchamisenarath.States;

import edu.sdsmt.hamsterrunchamisenarath.Game;
import edu.sdsmt.hamsterrunchamisenarath.StateMachine;

/**
 * The BaseHamster class is a state that sets the energy to move, performs maintenance tasks, and
 * transitions to other states based on game conditions.
 */
public class BaseHamster extends State {
    public BaseHamster(Game g, StateMachine machine) {
        super(g, machine);
    }

    @Override
    public void endTask() {

    }

    @Override
    // The `startTask()` method is setting the energy required to move the hamster to 1. This is done
    // in the `BaseHamster` state of the game.
    public void startTask() {
        // GRADING: ENERGY
        g.setEnergyToMove(1);
    }

    @Override
    // The `maintenanceTask(boolean moved)` method is performing maintenance tasks in the `BaseHamster`
    // state of the game. The `moved` parameter is used to determine if the hamster has moved during
    // the current turn. If the hamster has moved, the `pickup()` method is called to pick up any food
    // on the current tile. Then, the method checks if the game has been won or lost, and transitions
    // to the `EndedGame` state if necessary. If the hamster has moved more than one tile, it
    // transitions to the `ZoomingHamster` state. If the hamster has collected 15 or more food, it
    // transitions to the `HeavyHamster` state.
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

        if(g.getFood() >= 15) {
            // GRADINGL TO_HEAVY
            machine.setState(StateMachine.StateEnum.HeavyHamster);
            return;
        }
    }
}
