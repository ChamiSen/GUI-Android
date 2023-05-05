package edu.sdsmt.hamsterrunchamisenarath.Areas;

import edu.sdsmt.hamsterrunchamisenarath.Game;

/**
 * The Food class implements the GameArea interface and allows players to pick up food units in the
 * game.
 */
public class Food implements GameArea {
    private int foodUnits;

    public Food(int foodUnits) {
        this.foodUnits = foodUnits;
    }

    @Override
    // The `pickup` method is implementing the `pickup` method of the `GameArea` interface. It allows
    // the player to pick up food units in the game. If there are still food units available, it adds 5
    // units of food to the player's inventory and decrements the number of food units available. The
    // `Game` object `g` is passed as a parameter to allow the method to modify the game state.
    public void pickup(Game g){
        if(foodUnits > 0)
            g.addFood(5);
        foodUnits--;
    }

    @Override
    public void enter(Game g) {

    }
}
