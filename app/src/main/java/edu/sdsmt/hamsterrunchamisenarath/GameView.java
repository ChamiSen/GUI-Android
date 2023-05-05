package edu.sdsmt.hamsterrunchamisenarath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * The GameView class is responsible for drawing the game board and objects on it, including the
 * hamster, food, tubes, and barriers.
 */
public class GameView extends View {

    private Bitmap hamsterPic;
    private Paint hamsterPaint;
    private Paint gridPaint;
    private Paint gridBackPaint;
    private Paint tubePaint;
    private Paint foodPaint;
    private Paint zoomPaint;
    private Paint personPaint;
    private Paint barPaint;
    private Paint homePaint;
    private Paint barrierPaint;
    private int hamsterColo;
    private Game g;
    public static final int LOC_INCT = 5;

    /**
     * The function initializes various Paint objects and sets their colors for use in a game, and also
     * initializes a Game object.
     *
     * @param context The context of the application or activity that is using this method. It is used
     * to access resources and services related to the application or activity.
     */
    public void init(Context context) {
        hamsterPic = BitmapFactory.decodeResource(getResources(), R.drawable.hamster);
        hamsterPaint = new Paint();

        gridPaint = new Paint();
        gridPaint.setColor(Color.BLUE);
        gridPaint.setStrokeWidth(4);

        gridBackPaint = new Paint();
        gridBackPaint.setColor(Color.LTGRAY);

        tubePaint = new Paint();
        tubePaint.setColor(Color.BLUE);

        foodPaint = new Paint();
        foodPaint.setColor(Color.rgb(225, 165, 0));

        zoomPaint = new Paint();
        zoomPaint.setColor(Color.rgb(160, 32, 240));

        personPaint = new Paint();
        personPaint.setColor(Color.RED);

        barPaint = new Paint();
        barPaint.setColor(Color.GRAY);

        homePaint = new Paint();
        homePaint.setColor(Color.GREEN);

        barrierPaint = new Paint();
        barrierPaint.setColor(Color.BLACK);

        if(isInEditMode()) {
            g = new Game();
        }else{
            g = ((MainActivity)getContext()).getGame();
        }

        if(isInEditMode()) {
            return;
        }

        hamsterColo = Color.WHITE;
    }

    public GameView(Context context) {
        super(context);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    // `public void onDraw(Canvas c)` is a method that is called when the view needs to be drawn. It
    // takes a Canvas object as a parameter, which is used to draw the various objects on the view,
    // such as the game board, hamster, food, tubes, and barriers. The method uses various Paint
    // objects to set the colors and styles of the objects being drawn, and also calls other helper
    // methods to draw specific objects, such as circles and the hamster image.
    public void onDraw(Canvas c) {
        float size = (float)Math.max(getWidth(), getHeight());
        float offset = size/Game.GRID_SIZE;

        c.drawRect(0, 0, size, size, gridBackPaint);

        for (int i = 0; i <= Game.GRID_SIZE; i++) {
            c.drawLine(0, i * offset, size, i * offset, gridPaint);
            c.drawLine(i * offset, 0, i * offset, size, gridPaint);
        }

        for(int i = 0; i < Game.GRID_SIZE; i++) {
            for(int j = 0; j < Game.GRID_SIZE; j++) {
                c.drawCircle(i, j, 0, tubePaint);
            }
        }

        drawCircle(c, 0, 0, LOC_INCT, tubePaint);
        drawCircle(c, 1, 0, LOC_INCT, tubePaint);
        drawCircle(c, 1, 1, LOC_INCT, tubePaint);
        drawCircle(c, 1, 2, LOC_INCT, tubePaint);
        drawCircle(c, 1, 3, LOC_INCT, tubePaint);
        drawCircle(c, 1, 4, LOC_INCT, tubePaint);
        drawCircle(c, 0, 2, LOC_INCT, tubePaint);
        drawCircle(c, 3, 1, LOC_INCT, tubePaint);
        drawCircle(c, 3, 2, LOC_INCT, tubePaint);
        drawCircle(c, 3, 4, LOC_INCT, tubePaint);
        drawCircle(c, 4, 0, LOC_INCT, tubePaint);
        drawCircle(c, 4, 1, LOC_INCT, tubePaint);
        drawCircle(c, 4, 2, LOC_INCT, tubePaint);

        drawCircle(c, 3, 3,LOC_INCT, tubePaint);

        drawCircle(c, 2, 3, LOC_INCT, personPaint);

        drawCircle(c, 0, 4, LOC_INCT, zoomPaint);
        drawCircle(c, 2, 1, LOC_INCT, zoomPaint);

        drawCircle(c, 0, 1, LOC_INCT, foodPaint);
        drawCircle(c, 0, 3, LOC_INCT, foodPaint);
        drawCircle(c, 2, 2, LOC_INCT, foodPaint);
        drawCircle(c, 3, 0, LOC_INCT, foodPaint);

//        drawCircle(c, 2, 0, LOC_INCT, barPaint);
//        drawCircle(c, 3, 3, LOC_INCT, barPaint);
//        drawCircle(c, 4, 3, LOC_INCT, barPaint);
//        drawCircle(c, 2, 4, LOC_INCT, barPaint);
        drawCircle(c, 3, 2, LOC_INCT, barPaint);

        drawCircle(c, 4, 4, LOC_INCT, homePaint);

        drawCircle(c, 2, 0, LOC_INCT, barrierPaint);
        drawCircle(c, 2, 4, LOC_INCT, barrierPaint);
        drawCircle(c, 4, 3, LOC_INCT, barrierPaint);

        drawHamster(c);
    }

    /**
     * This function draws a circle on a canvas at a specified position with a specified size and
     * color.
     *
     * @param c The Canvas object on which the circle will be drawn.
     * @param posX The x-coordinate of the center of the circle to be drawn.
     * @param posY The parameter posY represents the y-coordinate of the center of the circle to be
     * drawn on the canvas.
     * @param inc The "inc" parameter is an integer value that represents the amount by which the
     * radius of the circle should be increased from its default size. This allows for the circle to be
     * drawn with varying sizes depending on the value of "inc".
     * @param p p is a Paint object that specifies the style and color of the circle to be drawn on the
     * Canvas. It contains information such as stroke width, color, and style.
     */
    private void drawCircle(Canvas c, int posX, int posY, int inc, Paint p) {
        float size = (float)Math.max(getWidth(), getHeight());
        float offset = size/(Game.GRID_SIZE);

        float x = posX * offset + offset/2;
        float y = posY * offset + offset/2;
        float r = (offset/2) - 30 + inc;

        c.drawCircle(x, y, r, p);
    }

    /**
     * The function draws a hamster image on a canvas with a specific size and location based on the
     * player's position in a game grid.
     *
     * @param c The Canvas object on which the hamster image will be drawn.
     */
    private void drawHamster(Canvas c) {
        float size = (float)Math.max(getWidth(), getHeight());
        size = size * 0.15f;

        int areaWidth = getWidth()/Game.GRID_SIZE;
        int areaHeight = getHeight()/Game.GRID_SIZE;

        float imageWidth = hamsterPic.getWidth();
        float imageHeight = hamsterPic.getHeight();

        Position loc = g.getPlayerLocation();
        float scaleFactor = size/Math.max(imageWidth, imageHeight);

        float x = loc.x * areaWidth + areaWidth/2 - imageWidth/2*scaleFactor;
        float y = loc.y * areaHeight + areaHeight/2 - imageHeight/2*scaleFactor;

        c.save();
        c.translate(x, y);
        c.scale(scaleFactor, scaleFactor);
        c.drawBitmap(hamsterPic, 0, 0, hamsterPaint);
        c.restore();
    }

    /**
     * This function sets the color of a hamster and applies a color filter to its paint.
     *
     * @param color The parameter "color" is an integer value that represents the color that the
     * hamster should be colored with. It is used to set the value of the "hamsterColo" variable and to
     * create a new color filter using the "PorterDuffColorFilter" class. The color filter is
     */
    public void coloHamster(int color) {
        hamsterColo = color;
        hamsterPaint.setColorFilter(new PorterDuffColorFilter(hamsterColo, PorterDuff.Mode.MULTIPLY));
    }

    /**
     * The function returns the value of the variable "hamsterColo".
     *
     * @return The method is returning an integer value which represents the hamster's color.
     */
    public int getHamsterColo() {
        return hamsterColo;
    }
}
