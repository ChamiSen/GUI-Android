package edu.sdsmt.hamsterrunchamisenarath;

import edu.sdsmt.hamsterrunchamisenarath.Areas.Barrier;
import edu.sdsmt.hamsterrunchamisenarath.Areas.Bars;
import edu.sdsmt.hamsterrunchamisenarath.Areas.Food;
import edu.sdsmt.hamsterrunchamisenarath.Areas.GameArea;
import edu.sdsmt.hamsterrunchamisenarath.Areas.Home;
import edu.sdsmt.hamsterrunchamisenarath.Areas.Person;
import edu.sdsmt.hamsterrunchamisenarath.Areas.Tube;
import edu.sdsmt.hamsterrunchamisenarath.Areas.Zoom;

/**
 * The Game class contains methods and variables for a game with a grid, where the player can move,
 * collect food, and interact with various objects while trying to reach a winning condition.
 */
public class Game {
    public static final int GRID_SIZE = 5;
    public static final int MAX_FOOD = 20;
    public static final int START_ENERGY = 10;
    public static final int MAX_ENERGY = 15;
    public static final int WIN_STORES = 15;
    private int moves;
    private int energy;
    private int food;
    private int stores;
    private int zoom;
    private int zoomMove;
    private int energyToMove;
    private Position pos;
    private boolean lost;
    private boolean won;
    private GameArea[][] gameArea;

    public Game() {
        reset();
    }

    /**
     * The reset function resets all game variables to their initial values and sets the gaming area.
     */
    public void reset() {
        moves = 0;
        energy = START_ENERGY;
        food = 0;
        stores = 0;
        zoom = 0;
        zoomMove = 0;
        energyToMove = 1;
        pos = new Position();
        lost = false;
        won = false;

        setGamingArea();
    }

    /**
     * The function sets up a game area with various objects such as tubes, a person, zooms, bars,
     * food, a home, and barriers.
     */
    public void setGamingArea() {
        gameArea = new GameArea[GRID_SIZE][GRID_SIZE];

        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++) {
                gameArea[i][j] = new Tube();
            }
        }

        gameArea[2][3] = new Person();

        gameArea[0][4] = new Zoom();
        gameArea[2][1] = new Zoom();

//        gameArea[2][0] = new Bars();
//        gameArea[3][3] = new Bars();
//        gameArea[4][3] = new Bars();
//        gameArea[2][4] = new Bars();
        gameArea[3][2] = new Bars();

        gameArea[0][1] = new Food(1);
        gameArea[0][3] = new Food(2);
        gameArea[2][2] = new Food(5);
        gameArea[3][0] = new Food(10);

        gameArea[4][4] = new Home();

        gameArea[2][0] = new Barrier();
        gameArea[2][4] = new Barrier();
        gameArea[4][3] = new Barrier();
    }

    /**
     * This function sets the number of home stores.
     *
     * @param stores an integer value representing the number of home stores to be set. The method sets
     * the value of the instance variable "stores" to the provided value.
     */
    public void setHomeStores(int stores) {
        this.stores = stores;
    }

    /**
     * The function returns the number of home stores.
     *
     * @return The method `getHomeStores()` is returning an integer value, which is the value of the
     * variable `stores`.
     */
    public int getHomeStores(){
        return stores;
    }

    /**
     * This function sets the energy level of an object.
     *
     * @param energy energy is a variable of type int that represents the amount of energy being set
     * for an object. The method setEnergy() is used to set the value of this variable.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * The function returns the value of the energy variable.
     *
     * @return The method is returning the value of the variable "energy".
     */
    public int getEnergy(){
        return energy;
    }

    /**
     * The function sets the value of the "food" variable.
     *
     * @param food The "food" parameter is an integer variable that represents the amount of food. The
     * "setFood" method is used to set the value of the "food" variable to a new value passed as an
     * argument to the method.
     */
    public void setFood(int food) {
        this.food = food;
    }

    /**
     * The function returns the value of the "food" variable.
     *
     * @return The method `getFood()` is returning an integer value, which is the value of the variable
     * `food`.
     */
    public int getFood() {
        return food;
    }

    /**
     * The function returns the value of the moves variable.
     *
     * @return The method is returning an integer value which represents the number of moves.
     */
    public int getMoves() {
        return moves;
    }

    /**
     * The function returns the number of zooms left.
     *
     * @return The method `getZoomsLeft()` is returning the value of the variable `zoom`.
     */
    public int getZoomsLeft() {
        return zoom;
    }

    /**
     * The function returns the current position of the player.
     *
     * @return The method is returning the current position of the player. The return type is a
     * `Position` object.
     */
    public Position getPlayerLocation() {
        return pos;
    }

    /**
     * This function sets the position of an object to the specified position.
     *
     * @param pos pos is a parameter of type Position. The method sets the value of the instance
     * variable "pos" to the value of the parameter "pos".
     */
    public  void setPosition(Position pos) {
        this.pos = pos;
    }

    /**
     * This function moves an object by a specified amount and checks for barriers and energy levels.
     *
     * @param deltaX The amount of movement in the x-direction (horizontal movement) that the object
     * should make.
     * @param deltaY The amount of movement in the y-axis (vertical direction) that the object will
     * make.
     */
    public void move(int deltaX, int deltaY) {
        int originalX = pos.x;
        int originalY = pos.y;

        int newX = originalX + deltaX;
        int newY = originalY + deltaY;

        if (newX < 0 || newX > GRID_SIZE - 1 || newY < 0 || newY > GRID_SIZE - 1)
            return;

        // EXTENSION: 2i-i
        if(gameArea[newX][newY] instanceof Barrier)
            return;

        energy -= energyToMove;
        moves++;

        if (energy < 0)
            lost = true;

        pos.x = newX;
        pos.y = newY;

        gameArea[pos.x][pos.y].enter(this);
    }

    /**
     * The "eat" function decreases the "food" variable by 1 and increases the "energy" variable by 5,
     * up to a maximum value of "MAX_ENERGY".
     */
    public void eat() {
        if(food == 0)
            return;
        food--;

        energy = Math.min(MAX_ENERGY, energy + 5);
    }

    /**
     * The function "pickup" calls the "pickup" method of the game area object at the current position
     * with the current object as a parameter.
     */
    public void pickup() {
        gameArea[pos.x][pos.y].pickup(this);
    }

    /**
     * The function returns a boolean value indicating whether the object is lost or not.
     *
     * @return A boolean value is being returned. The value indicates whether the object is lost or
     * not.
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * The function returns a boolean value indicating whether the game has been won or not.
     *
     * @return The method `isWon()` is returning a boolean value, which indicates whether the game has
     * been won or not. The value of `won` is being returned, which is likely a variable that is set to
     * `true` when the game has been won and `false` otherwise.
     */
    public boolean isWon() {
        return won;
    }

    /**
     * The function increments the value of the variable "zoom" by 1.
     */
    public void addZoom() {
        zoom++;
    }

    /**
     * This function adds a specified amount of food to a variable and ensures that the variable does
     * not exceed a maximum value.
     *
     * @param foodCount The parameter `foodCount` is an integer value representing the amount of food
     * to be added to the current `food` value of the object.
     */
    public void addFood(int foodCount) {
        this.food += foodCount;
        this.food = Math.min(this.food, MAX_FOOD);
    }

    /**
     * The function stores a given amount of food and sets a boolean flag to true if the total stored
     * food reaches a certain threshold.
     *
     * @param storage The parameter "storage" is an integer value representing the amount of food that
     * is being added to the "stores" variable.
     */
    public void storeFood(int storage) {
        stores += storage;
        if(stores >= WIN_STORES)
            won = true;
    }

    /**
     * The function sets the boolean variable "lost" to true, indicating that the game has been lost.
     */
    public void loseGame() {
        lost = true;
    }

    /**
     * This function sets the energy required to move for an object.
     *
     * @param energy The energy parameter is an integer value that represents the amount of energy
     * required to move an object. The setEnergyToMove method sets the energyToMove instance variable
     * to the value of the energy parameter.
     */
    public void setEnergyToMove(int energy) {
        this.energyToMove = energy;
    }

    /**
     * This Java function sets the value of the zoomMove variable.
     *
     * @param zoomMove The parameter `zoomMove` is an integer value that represents the amount of zoom
     * movement. It is used to set the value of the `zoomMove` instance variable in the class.
     */
    public void setZoomyMove(int zoomMove) {
        this.zoomMove = zoomMove;
    }

    /**
     * The function returns the value of the variable "zoomMove".
     *
     * @return The method is returning the value of the variable `zoomMove`, which is an integer.
     */
    public int getZoomyMove() {
        return zoomMove;
    }

    /**
     * The function reduces the zoom level by one.
     */
    public void reduceZoom() {
        zoom--;
    }
}
