package com.example.a3714_hw5;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;


public class TouchListener implements View.OnTouchListener {
    CanvasActivity canvasActivity;
    GestureDetectorCompat gestureDetectorCompat;

    public TouchListener(CanvasActivity ma) {
        this.canvasActivity = ma;
        gestureDetectorCompat = new GestureDetectorCompat(this.canvasActivity, new MyGestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetectorCompat.onTouchEvent(motionEvent);
        int maskedAction = motionEvent.getActionMasked();
        switch(maskedAction){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for(int i = 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    canvasActivity.addPath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
                    int id = motionEvent.getPointerId(i);
                    canvasActivity.updatePath(id, motionEvent.getX(i), motionEvent.getY(i));
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
               // for(int i= 0, size = motionEvent.getPointerCount(); i< size; i++){
               //     int id = motionEvent.getPointerId(i);
               //     canvasActivity.removePath(id);
               // }
               // break;
        }
        return true;
    }



    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            canvasActivity.onDoubleTap(e.getX(), e.getY());
            return super.onDoubleTap(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            canvasActivity.onLongPress(e.getX(), e.getY());
            super.onLongPress(e);
        }
    }
}