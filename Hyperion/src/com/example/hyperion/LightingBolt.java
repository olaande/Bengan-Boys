package com.example.hyperion;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * Created by Ola on 2015-10-02.
 * Version 0.2
 */
public class LightingBolt {

    private LightingBoltView view;

    public final int left;
    public int top = 1250;
    public final int right;
    public int bottom = 1350;

    public LightingBolt(int lane){
        left = (200*lane)+90;
        right = (200*lane)+190;
    }

    public void gone(View lbv){
        lbv.setVisibility(View.GONE);
    }

    public void update() {
        top = top - 20;
        bottom = bottom - 20;
        view.invalidate();
    }

    public View getView(Activity activity){
        if(view == null){
            view = new LightingBoltView(activity);
        }
        return view;
    }

    public Rect getRect(){
        return view.lightingRect;
    }

    private class LightingBoltView extends View {

        private Rect lightingRect = new Rect(left,top,right,bottom);
        private Paint paint = new Paint();
        private Drawable drawableLB;

        public LightingBoltView(Context context){
            super(context);
            paint.setColor(Color.YELLOW);
            drawableLB = getResources().getDrawable(R.drawable.lighting);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            lightingRect.set(left, top, right, bottom);
            drawableLB.setBounds(lightingRect);
            drawableLB.draw(canvas);
        }
    }
}
