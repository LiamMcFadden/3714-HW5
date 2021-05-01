package com.example.a3714_hw5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class CanvasActivity extends AppCompatActivity {

    private MyCanvas myCanvas;
    private Button red, green, blue, undo, clear, done;
    private Random rd = new Random();
    private TouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        myCanvas = findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);
        Intent intent = getIntent();
        String path = intent.getStringExtra("path");
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        myCanvas.setBackground(new BitmapDrawable(getResources(), myBitmap));

        red = findViewById(R.id.red_button);
        blue = findViewById(R.id.blue_button);
        green = findViewById(R.id.green_button);
        undo = findViewById(R.id.undo_button);
        clear = findViewById(R.id.clear_button);
        done = findViewById(R.id.done_button);

        View.OnClickListener clickListener = view -> {
            Button temp = (Button) view;
            switch ((String)temp.getText()) {
                case "Red":

                    break;
                case "Blue":
                    break;
                case "Green":
                    break;
                case "Undo":
                    break;
                case "Clear":
                    break;
                case "Done":
                    break;
            }
        };
    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap() {
    }

    public void onLongPress() {
    }

}