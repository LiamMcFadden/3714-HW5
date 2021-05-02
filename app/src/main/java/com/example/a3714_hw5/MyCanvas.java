package com.example.a3714_hw5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;


public class MyCanvas extends View {
    HashMap <Integer, MyPath> activePaths;
    ArrayList<MyPath> activePathsList;
    ArrayList<MyBitmap> activeImages;
    Paint pathPaint;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activeImages = new ArrayList<>();
        activePathsList = new ArrayList<>();
        activePaths = new HashMap<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.RED);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (MyPath path : activePathsList) {
            canvas.drawPath(path.path, path.paint);
        }
        for (MyBitmap bitmap: activeImages) {
            canvas.drawBitmap(bitmap.bitmap, bitmap.x, bitmap.y, null);
        }
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        MyPath myPath = new MyPath(path, pathPaint, id);
        activePaths.put(id, myPath);
        activePathsList.add(myPath);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        MyPath myPath = activePaths.get(id);
        if(myPath != null){
            myPath.path.lineTo(x, y);
        }
        activePathsList.remove(myPath);
        activePathsList.add(myPath);
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
            activePathsList.remove(activePaths.get(id));
        }
        invalidate();
    }

    public void undo() {
        if (activePathsList.size() == 0) return;
        MyPath temp = activePathsList.get(activePathsList.size()-1);
        activePathsList.remove(temp);
        removePath(temp.id);
    }

    public void clear() {
        activePaths.clear();
        activePathsList.clear();
        activeImages.clear();
        invalidate();
    }

    public void addImage(MyBitmap bitmap) {
        activeImages.add(bitmap);
        invalidate();
    }
}
