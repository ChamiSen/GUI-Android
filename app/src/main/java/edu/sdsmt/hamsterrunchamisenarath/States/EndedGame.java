package edu.sdsmt.hamsterrunchamisenarath.States;

import android.app.AlertDialog;
import android.content.DialogInterface;

import edu.sdsmt.hamsterrunchamisenarath.Game;
import edu.sdsmt.hamsterrunchamisenarath.MainActivity;
import edu.sdsmt.hamsterrunchamisenarath.StateMachine;

public class EndedGame extends State {

    private MainActivity context;
    public EndedGame(Game g, StateMachine machine, MainActivity context){
        this(g, machine);
        this.context = context;
    }

    public EndedGame(Game g, StateMachine machine) {
        super(g, machine);
    }

    @Override
    // The `endTask()` method is overriding the `endTask()` method of the parent `State` class. It is
    // called when the `EndedGame` state is exited and is used to perform any necessary cleanup or
    // finalization tasks. In this case, it calls the `reset()` method of the `MainActivity` context
    // object to reset the game.
    public void endTask() {
        context.reset(null);
    }

    @Override
    // `public void startTask()` is a method override that is called when the game has ended and the
    // `EndedGame` state is entered. It displays an alert dialog with a message indicating whether the
    // player has won or lost the game, and provides a "Reset" button to reset the game. The
    // `AlertDialog.Builder` class is used to create the dialog, and the `ResetClick` class is used to
    // handle the click event of the "Reset" button.
    public void startTask() {
        boolean won = g.isWon();

        String message;
        if (won) {
            message = "WON - Sufficient food in stores";
        } else {
            if (g.getEnergy() < 0) {
                message = "LOST - No energy";
            } else {
                message = "LOST - Caught";
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setMessage(message);
        builder.setPositiveButton("Reset", new ResetClick());

        // GRADING: DIALOG
        builder.create().show();
    }

    @Override
    public void maintenanceTask(boolean moved) {

    }

    /**
     * This is a private class that implements the onClick method of the
     * DialogInterface.OnClickListener interface to reset the state of a StateMachine object.
     */
    private class ResetClick implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // GRADING: RESET
            machine.setState(StateMachine.StateEnum.BaseHamster);
        }
    }
}
