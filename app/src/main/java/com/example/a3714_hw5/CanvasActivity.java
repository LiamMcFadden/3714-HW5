package com.example.a3714_hw5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class CanvasActivity extends AppCompatActivity {

    private MyCanvas myCanvas;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);

        Button red, green, blue, undo, clear, done;
        TouchListener touchListener;

        myCanvas = findViewById(R.id.myCanvas);
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);
        intent = getIntent();
        String path = intent.getStringExtra("path");
        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        myCanvas.setBackground(new BitmapDrawable(getResources(), myBitmap));

        intent = new Intent(this, MainActivity.class);
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
                    myCanvas.pathPaint.setColor(Color.RED);
                    break;
                case "Blue":
                    myCanvas.pathPaint.setColor(Color.BLUE);
                    break;
                case "Green":
                    myCanvas.pathPaint.setColor(Color.GREEN);
                    break;
                case "Undo":
                    myCanvas.undo();
                    break;
                case "Clear":
                    myCanvas.clear();
                    break;
                case "Done":
                    switchActivity();
                    break;
            }
        };
        // set onclicklisteners
        blue.setOnClickListener(clickListener);
        red.setOnClickListener(clickListener);
        green.setOnClickListener(clickListener);
        undo.setOnClickListener(clickListener);
        clear.setOnClickListener(clickListener);
        done.setOnClickListener(clickListener);
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

    public void onDoubleTap(float x, float y) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.football);
        MyBitmap myBitmap = new MyBitmap(bitmap, x, y);
        myCanvas.addImage(myBitmap);
    }

    public void onLongPress(float x, float y) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.gloves);
        MyBitmap myBitmap = new MyBitmap(bitmap, x, y);
        myCanvas.addImage(myBitmap);
    }

    private void switchActivity() {
        startActivity(intent);
    }

}