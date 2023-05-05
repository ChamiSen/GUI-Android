package edu.sdsmt.hamsterrunchamisenarath.States;

import edu.sdsmt.hamsterrunchamisenarath.Game;
import edu.sdsmt.hamsterrunchamisenarath.StateMachine;

public class ZoomingHamster extends State {
    public ZoomingHamster(Game g, StateMachine machine) {
        super(g, machine);
    }

    @Override
    public void endTask() {

    }

    @Override
    // The `startTask()` method is setting the energy and zoomy move values of the game object `g` to
    // 2. This is likely done to initialize the game state when transitioning to the `ZoomingHamster`
    // state.
    public void startTask() {
        g.setEnergyToMove(2);
        g.setZoomyMove(2);
    }

    @Override
    // The `maintenanceTask` method is a task that is called repeatedly while the game is in the
    // `ZoomingHamster` state. It takes a boolean parameter `moved` which indicates whether the hamster
    // has moved during the current game loop iteration.
    public void maintenanceTask(boolean moved) {
        if(g.isWon() || g.isLost()) {
            machine.setState(StateMachine.StateEnum.EndedGame);
            return;
        }

        if(g.getZoomyMove() < 0) {
            if(g.getFood() >= 15)
                // GRADING: TO_HEAVY
                machine.setState(StateMachine.StateEnum.HeavyHamster);
            else
                machine.setState(StateMachine.StateEnum.BaseHamster);
        }
    }
}
