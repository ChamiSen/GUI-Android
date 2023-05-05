package edu.sdsmt.hamsterrunchamisenarath.Areas;

import edu.sdsmt.hamsterrunchamisenarath.Game;

public class Home implements GameArea {

    @Override
    public void pickup(Game g){

    }

    @Override
    // The `enter` method is implementing the `enter` method of the `GameArea` interface. It takes a
    // `Game` object as a parameter and performs some actions related to the `Home` game area.
    // Specifically, it retrieves the amount of food the player has and stores it in the player's
    // storage, then removes all the food from the player's inventory. This suggests that the `Home`
    // game area is a place where the player can store their collected food.
    public void enter(Game g) {
        int food = g.getFood();
        g.storeFood(food);
        g.addFood(-1 * food);
    }
}
