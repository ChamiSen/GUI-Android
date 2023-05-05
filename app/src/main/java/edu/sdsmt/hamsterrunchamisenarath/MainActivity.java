/*
* Complete the following checklist. If you partially completed an item, put a note how it can be
* checked for what is working for partial credit.

DONE Followed the class OOP diagram
DONE *Grading tags completed


Tier 1: Model		50
	Move test 			DONE
	Food test			DONE
	Eat test 			DONE
	Home test 			DONE
	Zoom pickup test	DONE
	Bar test 			DONE --> PLEASE READ: All my tests pass without barriers. When I implemented
	                                          barriers according to the diagram provided in the
	                                          assignment handout, the bar test fails due to its
	                                          placement. If you interested in testing this, please
	                                          uncomment from 74-77 in Game.java, uncomment from
	                                          153-156 in GameView.java, comment out 78 in Game.java,
	                                          and comment out 157, 161-163 in GameView.java. 
	Caught test 		DONE
	No energy test		DONE
	Win test 			DONE

Tier 2: Connect Views		22
	All views present test	 	DONE
	Starting values test pass	DONE
	Move test 	 				DONE
	Food test	 				DONE
	Eat test  					DONE
	Bar test	 				DONE
	Home test	 				DONE
	Reset test	 				DONE

Tier 3a: State Machine/Event Rules	34
	Framework there	 			DONE
	Base to heavy*	 			DONE
	Heavy to zoom* 				DONE
	Base to zoom* 				DONE
	Caught*	 					DONE
	No energy*	 				DONE
	Win*	 					DONE
	Reset on close ***	 		DONE

Tier 3b: Floating Action	 		DONE
	All buttons there 		 		DONE
	Icons set and distinguishable	DONE
	Opens/closes properly 	 		DONE
	Tribble color updated.	 		DONE

Tier 3c: Layout **	26
	Custom’s View’s aspect ratio constant			DONE
	Relative size of objects in view maintained 	DONE
	Works in required screen sizes 	 				DONE


Tier 3d: Rotation		20
	Required state saved on rotation 	 		DONE

Tier 4: Extensions		30

Extension 1: 5a 5pts Disable to zoom button if there are no zooms available:
             Please search for EXTENSION: 5a to validate this extension.
Extension 2: 5b 5pts Get the floating action buttons open/close state saved on rotation:
             In the emulator, open the floating button, click on a preferred color. Then rotate the
             emulator to validate that the previous state stay the same.
Extension 3: 2i-i 20pts Barrier areas where the hamster may not enter:
             There are three barriers located at (2, 0), (2, 4), and (4, 3). The hamster is not
             allowed to enter these spots. Please search for EXTENSION: 2i-i and try to move the
             hamster into a barrier area in the emulator to validate this extension.


The grade you compute is the starting point for course staff, who reserve the right to change the
* grade if they disagree with your assessment and to deduct points for other issues they may
* encounter, such as errors in the submission process, naming issues, etc.
* */
package edu.sdsmt.hamsterrunchamisenarath;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * The MainActivity class is the main activity of a game app, responsible for managing the game state,
 * updating the UI, and handling user input.
 */
public class MainActivity extends AppCompatActivity {
    private Game g;
    private StateMachine machine;
    private GameView gameView;
    private static final String ENERGY_VALUE = "energy";
    private static final String FOOD_VALUE = "food";
    private static final String ZOOM_VALUE = "zoom";
    private static final String MOVE_VALUE = "moves";
    private static final String STORES_VALUE = "stores";
    private static final String POSITION_XVALUE = "pos_x";
    private static final String POSITION_YVALUE = "pos_y";
    private static final String HAMSTER_COLOR_VALUE = "color";
    private static final String FLOAT_VALUE = "float";
    private boolean showColos = false;
    private FloatingActionButton redC, greenC, yellowC;
    TextView addRed, addGreen, addYellow;

    @SuppressLint("MissingInflatedId")
    @Override
    // `protected void onCreate(Bundle savedInstanceState)` is a method in the `MainActivity` class
    // that is called when the activity is first created. It initializes the game state, sets the
    // content view to the game layout, and updates the UI. It also sets up the floating action buttons
    // and their corresponding text views. The `savedInstanceState` parameter is used to restore the
    // activity's state if it was previously destroyed and recreated, such as during a screen rotation.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        g = new Game();
        machine = new StateMachine(g, this);
        machine.setState(StateMachine.StateEnum.BaseHamster);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameArea);

        redC = findViewById(R.id.coloRed);
        greenC = findViewById(R.id.coloGreen);
        yellowC = findViewById(R.id.coloYellow);

        addRed = findViewById(R.id.redActionText);
        addGreen = findViewById(R.id.greenActionText);
        addYellow = findViewById(R.id.yellowActionText);

        updateUI(false);
    }

    /**
     * This function updates the user interface with the current game state.
     *
     * @param moved A boolean value indicating whether the game has been moved or not.
     */
    public void updateUI(boolean moved) {
        machine.onUpdate(moved);

        TextView food = findViewById(R.id.foodView);
        food.setText(String.format("%s", g.getFood()));

        TextView zoom = findViewById(R.id.zoomView);
        zoom.setText(String.format("%s", g.getZoomsLeft()));

        TextView zoomButton = findViewById(R.id.zoomBtn);
        // EXTENSION: 5a
        zoomButton.setEnabled(g.getZoomsLeft() > 0);

        TextView energy = findViewById(R.id.energyView);
        energy.setText(String.format("%s", g.getEnergy()));

        TextView moves = findViewById(R.id.moveView);
        moves.setText(String.format("%s", g.getMoves()));

        TextView stores = findViewById(R.id.storesView);
        stores.setText(String.format("%s", g.getHomeStores()));

        if(showColos) {
            redC.setVisibility(View.VISIBLE);
            greenC.setVisibility(View.VISIBLE);
            yellowC.setVisibility(View.VISIBLE);
            addRed.setVisibility(View.VISIBLE);
            addGreen.setVisibility(View.VISIBLE);
            addYellow.setVisibility(View.VISIBLE);
        } else {
            redC.setVisibility(View.GONE);
            greenC.setVisibility(View.GONE);
            yellowC.setVisibility(View.GONE);
            addRed.setVisibility(View.GONE);
            addGreen.setVisibility(View.GONE);
            addYellow.setVisibility(View.GONE);
        }

        gameView.invalidate();
    }

    @Override
    // `public void onSaveInstanceState(@NonNull Bundle savedInstanceState)` is a method in the
    // `MainActivity` class that is called when the activity is about to be destroyed, such as during a
    // screen rotation. It saves the current state of the activity's variables and data into a `Bundle`
    // object, which can be used to restore the state of the activity when it is recreated. The
    // `@NonNull` annotation indicates that the `savedInstanceState` parameter cannot be null.
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(ENERGY_VALUE, g.getEnergy());
        savedInstanceState.putInt(FOOD_VALUE, g.getFood());
        savedInstanceState.putInt(ZOOM_VALUE, g.getZoomsLeft());
        savedInstanceState.putInt(MOVE_VALUE, g.getMoves());
        savedInstanceState.putInt(STORES_VALUE, g.getHomeStores());

        Position position = g.getPlayerLocation();

        savedInstanceState.putInt(POSITION_XVALUE, position.x);
        savedInstanceState.putInt(POSITION_YVALUE, position.y);
        savedInstanceState.putInt(HAMSTER_COLOR_VALUE, gameView.getHamsterColo());
        savedInstanceState.putBoolean(FLOAT_VALUE, showColos);
    }

    @Override
    // `public void onRestoreInstanceState(Bundle savedInstanceState)` is a method in the
    // `MainActivity` class that is called when the activity is being restored after being destroyed
    // and recreated, such as during a screen rotation. It retrieves the saved state data from the
    // `Bundle` object passed as a parameter and restores the activity's variables and data to their
    // previous values. In this specific implementation, it restores the game state variables, the
    // hamster's position, the hamster's color, and the state of the floating action buttons.
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        int energy = savedInstanceState.getInt(ENERGY_VALUE);
        g.setEnergy(energy);

        int food = savedInstanceState.getInt(FOOD_VALUE);
        g.setFood(food);

        int zoom = savedInstanceState.getInt(ZOOM_VALUE);
        if(zoom == 0)
            g.addZoom();

        int stores = savedInstanceState.getInt(STORES_VALUE);
        g.setHomeStores(stores);

        Position position = new Position();
        position.x = savedInstanceState.getInt(POSITION_XVALUE);
        position.y = savedInstanceState.getInt(POSITION_YVALUE);
        g.setPosition(position);

        int color = savedInstanceState.getInt(HAMSTER_COLOR_VALUE);
        gameView.coloHamster(color);

        showColos = savedInstanceState.getBoolean(FLOAT_VALUE);

        updateUI(false);
    }

    /**
     * The function returns an object of type Game.
     *
     * @return The method is returning an object of type `Game`. The specific `Game` object being
     * returned is referenced by the variable `g`.
     */
    public Game getGame() {
        return g;
    }

    /**
     * The function returns a StateMachine object.
     *
     * @return The method is returning an object of type StateMachine.
     */
    public StateMachine getStateMachine() {
        return machine;
    }

    /**
     * This function moves the view up by adjusting the position and zoom level.
     *
     * @param view The parameter "view" is of type View and represents the view that triggered the
     * moveUp() method. It is commonly used in Android development to handle user interactions with UI
     * elements such as buttons, text fields, and images. In this case, it is likely that the moveUp()
     * method is called
     */
    public void moveUp(View view) {
        g.move(0, -1);
        g.setZoomyMove(g.getZoomyMove() - 1);
        updateUI(true);
    }

    /**
     * This function moves the view down by one unit and updates the UI.
     *
     * @param view The parameter "view" is of type View and represents the view that triggered the
     * method call. It is commonly used in Android development to handle user interactions with UI
     * elements such as buttons, text fields, etc. In this case, it is used to handle a button click
     * event to move an object down
     */
    public void moveDown(View view) {
        g.move(0, 1);
        g.setZoomyMove(g.getZoomyMove() - 1);
        updateUI(true);
    }

    /**
     * This function moves an object to the left and updates the user interface.
     *
     * @param view The parameter "view" is of type View and represents the view that triggered the
     * method call. It is commonly used in Android development to handle user interactions with UI
     * elements such as buttons, text fields, etc. In this case, the method "moveLeft" is likely
     * associated with a button that the
     */
    public void moveLeft(View view) {
        g.move(-1, 0);
        g.setZoomyMove(g.getZoomyMove() - 1);
        updateUI(true);
    }

    /**
     * This function moves an object to the right and updates the user interface.
     *
     * @param view The parameter "view" is of type View and represents the view that triggered the
     * method call. It is commonly used in Android development to handle user interactions with UI
     * elements such as buttons, text fields, etc. In this case, it is likely that the method is called
     * when the user taps a button
     */
    public void moveRight(View view) {
        g.move(1, 0);
        g.setZoomyMove(g.getZoomyMove() - 1);
        updateUI(true);
    }

    /**
     * This function calls the "eat" method of an object and updates the user interface.
     *
     * @param view The "view" parameter is an object of the View class that represents the UI element
     * that triggered the "eat" method. It is commonly used in Android development to handle user
     * interactions with UI elements such as buttons, text fields, and images. In this case, the "eat"
     * method is called
     */
    public void eat(View view) {
        g.eat();
        updateUI(false);
    }

    /**
     * The function resets the game and updates the user interface.
     *
     * @param view The "view" parameter is an object of the View class that represents the view that
     * triggered the reset method. It is typically used in Android app development to handle user
     * interface events and interact with the UI components. In this case, it is used to reset the game
     * and update the UI when the user
     */
    public void reset(View view) {
        g.reset();
        gameView.coloHamster(Color.WHITE);
        updateUI(false);
    }

    /**
     * The function activates zoom by adjusting the zoom level, reducing food, and updating the UI.
     *
     * @param view The "view" parameter is a reference to the view that triggered the method. It can be
     * used to access properties and methods of the view, such as its ID, visibility, or text. In this
     * case, it is not being used in the method, so it could potentially be removed without affecting
     */
    public void activateZoom(View view) {
        g.setZoomyMove(2);
        g.addFood(-2);
        g.reduceZoom();
        updateUI(false);
    }

    /**
     * This function toggles the visibility of a UI element and updates the UI accordingly.
     *
     * @param view The parameter "view" is of type View and represents the view that was clicked to
     * trigger the method. It is commonly used in Android development to handle user interactions with
     * UI elements.
     */
    public void openColos(View view) {
        showColos = !showColos;
        updateUI(false);
    }

    /**
     * This function changes the color of a hamster in a game view to red and updates the user
     * interface.
     *
     * @param view The "view" parameter is a reference to the View object that triggered the method
     * call. It is commonly used in Android development to handle user interactions with UI elements
     * such as buttons, text fields, and images. In this case, it is likely that the method is
     * associated with a button that the user
     */
    public void coloRed(View view) {
        gameView.coloHamster(Color.RED);
        updateUI(false);
    }

    /**
     * This function changes the color of a hamster in a game view to green and updates the user
     * interface.
     *
     * @param view The parameter "view" is of type View and represents the view that triggered the
     * method call. It is commonly used in Android development to handle user interactions with UI
     * elements such as buttons.
     */
    public void coloGreen(View view) {
        gameView.coloHamster(Color.GREEN);
        updateUI(false);
    }

    /**
     * This function changes the color of a hamster in a game view to yellow and updates the user
     * interface.
     *
     * @param view The parameter "view" is of type View and represents the view that was clicked or
     * interacted with to trigger the method coloYellow(). It is not used in the method implementation,
     * but it is a common parameter in Android development as it allows the method to be associated
     * with a specific UI element.
     */
    public void coloYellow(View view) {
        gameView.coloHamster(Color.YELLOW);
        updateUI(false);
    }
}