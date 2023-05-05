package edu.sdsmt.hamsterrunchamisenarath.Areas;

import edu.sdsmt.hamsterrunchamisenarath.Game;

/**
 * The Bars class implements the GameArea interface and limits the amount of food a player can bring
 * into the area.
 */
public class Bars implements GameArea{
    @Override
    public void pickup(Game g){

    }

    @Override
    // The `enter` method is implementing the `enter` method of the `GameArea` interface. It is
    // limiting the amount of food a player can bring into the `Bars` area. If the player has more than
    // 5 units of food, the method subtracts the excess food from the player's inventory so that they
    // only have 5 units of food when entering the `Bars` area.
    public void enter(Game g) {
        if(g.getFood() > 5) {
            g.addFood(5 - g.getFood());
        }
    }
}
