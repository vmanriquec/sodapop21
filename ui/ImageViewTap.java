package com.food.sistemas.sodapopapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.food.sistemas.sodapopapp.Constantes;

public class ImageViewTap extends ImageView {

    private String TAG = getClass().getSimpleName();

    GestureDetector gestureDetector;
    Context context;
    Handler handler;

    public ImageViewTap(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.context = context;
        this.handler = new Handler();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                handler.postDelayed(timer, 3000);
                break;

            case MotionEvent.ACTION_UP:
                handler.removeCallbacks(timer);
                break;
        }
        return gestureDetector.onTouchEvent(event);
    }

    Runnable timer = new Runnable() {
        @Override
        public void run() {
            Intent i = new Intent();
            i.setAction(Constantes.BR_LONG_TAP);
            context.sendBroadcast(i);
        }
    };

    private class GestureListener extends GestureDetector.SimpleOnGestureListener  {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Intent i = new Intent();
            i.setAction(Constantes.BR_SINGLE_TAP);
            context.sendBroadcast(i);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Intent i = new Intent();
            i.setAction(Constantes.BR_DOUBLE_TAP);
            context.sendBroadcast(i);
            return true;
        }
    }

}
