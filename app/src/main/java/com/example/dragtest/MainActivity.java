package com.example.dragtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private String selectedItem=null;
    private Button circle=null;
    private Button rectangle=null;
    private Button triangle=null;

    private MyTouchListener touch= new MyTouchListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rectangle=(Button)findViewById(R.id.rectangle);
        rectangle.setOnTouchListener(touch);
        rectangle.setOnDragListener(new MyDragListener());

        circle=(Button)findViewById(R.id.circle);
        circle.setOnTouchListener(touch);
        circle.setOnDragListener(new MyDragListener());

        triangle=(Button)findViewById(R.id.triangle);
        triangle.setOnTouchListener(touch);
        triangle.setOnDragListener(new com.example.dragtest.MainActivity.MyDragListener());

    }

//    private final class MyTouchListener implements View.OnTouchListener {
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                // selectedButton
//                final int id = view.getId();
//                switch(id){
//                    case R.id.circle:
//                        selectedItem="circle";
//                        break;
//                    case R.id.rectangle:
//                        selectedItem="rectangle";
//                        break;
//                    case R.id.triangle:
//                        selectedItem="triangle";
//                        break;
//                }
//
//                return true;
//            }
//        }
//    }
//
//    private final class MyDragListener implements View.OnDragListener {
//        public boolean onDrag(View v, DragEvent event) {
//            final int id = v.getId();
//            switch (event.getAction()) {
//                case DragEvent.ACTION_DRAG_STARTED:
//                    // do nothing
//                    break;
//                case DragEvent.ACTION_DRAG_ENTERED:
//                    break;
//                case DragEvent.ACTION_DRAG_EXITED:
//                    break;
//                case DragEvent.ACTION_DROP:
//                    // Dropped, reassign View to ViewGroup
//                    if(id != R.id.dropzone || !(v instanceof Canvas)){
//                        return true;
//                    }
//                    final float x = v.getX();
//                    final float y = v.getY();
//                    final Canvas c = (Canvas)v;
//                    switch(selectedItem){
//                        case "rectangle":
//                            Paint p = new Paint();
//                            p. setARGB(0, 255, 0, 0);
//                            c.drawRect(x,y,x+5,y+5,p);
//                            break;
//                        case "circle":
//                            Paint p = new Paint();
//                            p. setARGB(0, 255, 0, 128);
//                            c.drawCircle(x,y,100,p);
//                            break;
//                        case "triangle":
//                            Paint p = new Paint();
//                            p. setARGB(0, 255, 144, 128);
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
//                            c.drawPath(path, p);
//                            break;
//                    }
//
//                    break;
//                case DragEvent.ACTION_DRAG_ENDED:
//
//                default:
//                    break;
//            }
//            return true;
//
//        }
//    }

}