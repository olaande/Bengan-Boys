package com.example.hyperion;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.example.hyperion.R;

/**
 * To-Write
 *
 * @author 		Ola Andersson
 * @version		0.2
 * @since		-
 */

public class Bus
{
    private final PowerComponent powerComponent = new PowerComponent ();

    /**
     * Public getter for power component.
     * @return - instance of power component.
     */
    public PowerComponent getPowerComponent() {
        return powerComponent;
    }

    private int height;
    private int width;

    private int left = 500;
    private final int top = 1400;
    private int right = 600;
    private final int bottom = 1600;

    private BusView view;


    /**
     * Is being called after moving the bus to update graphics.
     */
    public void update() {

        view.invalidate();
    }

    /**
     *
     * @return returns the lane.
     */
    public int getLane(){

        return (left-40)/200;
    }

    /**
     *
     * @param activity
     * @return creates a new Bus2View object if view is null.
     */
    public View getView(Activity activity){
        if(view == null){
            view = new BusView(activity);
        }
        return view;
    }

    /**
     * Moves the bus one step to the left if canMoveLeft returns true.
     */
    public void moveLeft(){
        if(canMoveLeft()){
            left -= 200;
            right -= 200;
            update();
        }
    }

    /**
     * Moves the bus one step to the right if canMoveRight returns true.
     */
    public void moveRight(){
        if(canMoveRight()){
            right += 200;
            left += 200;
            update();
        }
    }

    /**
     * Checks if the bus can move left.
     * @return true if it can, otherwise false.
     */
    private boolean canMoveLeft(){
        return left != 40? true : false;
    }

    /**
     * Checks if the bus can move right.
     * @return true if it can, otherwise false.
     */
    private boolean canMoveRight(){
        return right != 1040? true : false;
    }

    /**
     * Innerclass for drawing the bus.
     */
    private class BusView extends View {

        private Rect busRect = new Rect(left,top,right,bottom);
        private Paint paint = new Paint();
        private Drawable drawableBus;

        private BusView(Context context) {
            super(context);
            paint.setColor(Color.GREEN);
            drawableBus = getResources().getDrawable(R.drawable.bussstor);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        /**
         * Draws the bus with the given params.
         * @param canvas
         */
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            busRect.set(left, top, right, bottom);
            drawableBus.setBounds(busRect);
            drawableBus.draw(canvas);
        }
    }
}

