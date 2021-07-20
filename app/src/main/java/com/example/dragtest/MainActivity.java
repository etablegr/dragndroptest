package com.example.dragtest;

import androidx.appcompat.app.AppCompatActivity;

//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.Point;
import android.app.Fragment;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private String selectedItem=null;
    private Button circle=null;
    private Button rectangle=null;
    private Button triangle=null;
    private View dropzone = null;

    private MyTouchListener touch= new MyTouchListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rectangle=(Button)findViewById(R.id.rectangle);
        rectangle.setOnTouchListener(touch);

        circle=(Button)findViewById(R.id.circle);
        circle.setOnTouchListener(touch);

        triangle=(Button)findViewById(R.id.triangle);
        triangle.setOnTouchListener(touch);
    }

    private final class MyTouchListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
                final int id = v.getId();
                if(id == R.id.circle) {
                    selectedItem="circle";
                } else if( id == R.id.rectangle) {
                    selectedItem="rectangle";
                } else if( id == R.id.triangle ) {
                    selectedItem="triangle";
                }
                Log.v("Test",selectedItem);
        }
    }

    private final class MyDragListener implements View.OnDragListener {
        public boolean onDrag(View v, DragEvent event) {
            final int id = v.getId();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
//                    if(id != R.id.dropzone || !(v instanceof Canvas)){
//                        return true;
//                    }
//                    final float x = v.getX();
//                    final float y = v.getY();
//                    final Canvas c = (Canvas)v;
//                    switch(selectedItem){
//                        case "rectangle":
//                            Paint rp = new Paint();
//                            rp. setARGB(0, 255, 0, 0);
//                            c.drawRect(x,y,x+5,y+5,rp);
//                            break;
//                        case "circle":
//                            Paint cp = new Paint();
//                            cp. setARGB(0, 255, 0, 128);
//                            c.drawCircle(x,y,100,cp);
//                            break;
//                        case "triangle":
//                            Paint tp = new Paint();
//                            tp. setARGB(0, 255, 144, 128);
//
//                            Point a = new Point(x,y);
//                            Point b = new Point(x-5, y+5);
//                            Point c = new Point(x+5, y+5);
//
//                            Path path = new Path();
//                            path.setFillType(Path.FillType.EVEN_ODD);
//                            path.lineTo(b.x, b.y);
//                            path.lineTo(c.x, c.y);
//                            path.lineTo(a.x, a.y);
//                            path.close();
//
//                            c.drawPath(path, tp);

//                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }
            return true;

        }
    }
}