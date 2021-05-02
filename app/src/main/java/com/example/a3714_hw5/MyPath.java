package com.example.a3714_hw5;

import android.graphics.Paint;
import android.graphics.Path;

public class MyPath {
    Path path;
    Paint paint;
    int id;

    MyPath(Path path, Paint paint, int id) {
        this.path = path;
        this.paint = paint;
        this.id = id;
    }
}
