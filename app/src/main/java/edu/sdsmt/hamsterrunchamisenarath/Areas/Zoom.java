package edu.sdsmt.hamsterrunchamisenarath.Areas;

import edu.sdsmt.hamsterrunchamisenarath.Game;

/**
 * The Zoom class implements the GameArea interface and allows the player to pick up a zoom item in the
 * game.
 */
public class Zoom implements GameArea {
    private boolean left;
    public Zoom() {
        left = true;
    }

    @Override
    // The `pickup` method is implementing the `pickup` method of the `GameArea` interface. It takes a
    // `Game` object as a parameter and adds a zoom item to the game if the `left` boolean is true. It
    // then sets `left` to false to indicate that the zoom item has been picked up.
    public void pickup(Game g){
        if(left){
            g.addZoom();
            left = false;
        }
    }

    @Override
    public void enter(Game g) {

    }
}
