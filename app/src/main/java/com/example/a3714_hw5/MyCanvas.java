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
import java.util.Map;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;
    ArrayList<MyBitmap> activeImages;
    Paint pathPaint;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activeImages = new ArrayList<>();
        activePaths = new HashMap<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.RED);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Path path: activePaths.values()){
            canvas.drawPath(path, pathPaint);
        }
        for (MyBitmap bitmap: activeImages) {
            canvas.drawBitmap(bitmap.bitmap, bitmap.x, bitmap.y, null);
        }
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }

    public void undo() {
        if (activePaths.size() == 0) return;
        int maxId = 0;
        for (Map.Entry<Integer, Path> entry : activePaths.entrySet()) {
            maxId = entry.getKey() > maxId ? entry.getKey() : maxId;
        }
        removePath(maxId);
    }

    public void clear() {
        for (Map.Entry<Integer, Path> entry : activePaths.entrySet())
            removePath(entry.getKey());

        activeImages.clear();
        invalidate();
    }

    public void addImage(MyBitmap bitmap) {
        activeImages.add(bitmap);
        invalidate();
    }
}
