package edu.sdsmt.hamsterrunchamisenarath.Areas;

import edu.sdsmt.hamsterrunchamisenarath.Game;

/**
 * The Person class implements the GameArea interface and causes the game to be lost when a pickup
 * occurs.
 */
public class Person  implements GameArea {
    @Override
    // The `pickup` method is implementing the `pickup` method of the `GameArea` interface. It takes a
    // `Game` object as a parameter and calls the `loseGame` method of that object, which causes the
    // game to be lost. This means that when the player's character collides with the `Person` object,
    // the game will end.
    public void pickup(Game g){
        g.loseGame();
    }

    @Override
    public void enter(Game g) {

    }
}
