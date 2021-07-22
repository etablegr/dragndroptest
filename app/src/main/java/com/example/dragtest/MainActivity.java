package com.example.dragtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.Point;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button circle=null;
    private Button rectangle=null;
    private Button triangle=null;
    private View dropzone = null;

    private ButtonClickEvent touch= new ButtonClickEvent();
    private MyDragListener d =new MyDragListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rectangle=(Button)findViewById(R.id.rectangle);
        rectangle.setOnLongClickListener(touch);

        circle=(Button)findViewById(R.id.circle);
        circle.setOnLongClickListener(touch);

        triangle=(Button)findViewById(R.id.triangle);
        triangle.setOnLongClickListener(touch);

        dropzone=(View)findViewById(R.id.dropzone);
        dropzone.setOnDragListener(d);
    }


    private final class ImgTouchEvent implements View.OnTouchListener
    {

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int eventAction = event.getAction();

            if(eventAction == MotionEvent.ACTION_DOWN){
                ClipData.Item shape = new ClipData.Item((CharSequence) v.getTag());
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData draggedData = new ClipData(new ClipDescription("ClipData".toString(),mimeTypes),shape);
                v.startDragAndDrop(draggedData,shadowBuilder,v,0);
            }

            return false;
        }
    }


    private final class ButtonClickEvent implements View.OnLongClickListener {
        @Override
        public boolean onLongClick(View v) {
                final int id = v.getId();
                Drawable shadow = null;
                ClipData.Item shape = null;

                if(id == R.id.circle) {
                    shape = new ClipData.Item("circle");
                    shadow = getResources().getDrawable(R.drawable.circle,null);
                } else if( id == R.id.rectangle) {
                    shape = new ClipData.Item("rectangle");
                    shadow = getResources().getDrawable(R.drawable.rectangle, null);
                } else if( id == R.id.triangle ) {
                    shape = new ClipData.Item("triangle");
                    shadow = getResources().getDrawable(R.drawable.triangle, null);
                }
                ImageView shadowImage = new ImageView(v.getContext());
                shadowImage.setImageDrawable(shadow);

                View.DragShadowBuilder shadowBuilder = new ShapeShadowBuilder(v,shadow);

                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData draggedData = new ClipData(new ClipDescription("ClipData".toString(),mimeTypes),shape);
                v.startDragAndDrop(draggedData,shadowBuilder,v,0);

                return true;
        }
    }

    private final class ShapeShadowBuilder extends View.DragShadowBuilder
    {
            private Drawable d;
            public ShapeShadowBuilder(View v, Drawable d){
                super(v);
                if(d == null){
                    throw new NullPointerException("Drawable should not be NUll");
                }
                this.d = d;
            }

            @Override
            public void onDrawShadow(Canvas canvas)
            {
                this.d.draw(canvas);
            }
    }

    private final class MyDragListener implements View.OnDragListener {
        public boolean onDrag(View v, DragEvent event) {
            final int id = v.getId();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    Log.v("DROP","Coordinates: "+event.getX()+" , "+event.getY());
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    Log.v("DROP","item has been dropped");

                    ImageView img = null;

                    ClipData.Item item = event.getClipData().getItemAt(0);

                    String shape = item.getText().toString();
                    Log.v("DROP","Coordinates: "+event.getX()+" , "+event.getY());
                    Log.v("DROP","Shape: "+shape);

                    Drawable d = null;

                    switch (shape){
                        case "circle":
                            d = getResources().getDrawable(R.drawable.circle,null);
                            break;
                        case "rectangle":
                            d = getResources().getDrawable(R.drawable.rectangle,null);
                            break;
                        case "triangle":
                            d = getResources().getDrawable(R.drawable.triangle,null);
                            break;
                    }

                    Context ctx = v.getContext();
                    img = new ImageView(ctx);
                    img.setImageDrawable(d);
                    img.setX(event.getX());
                    img.setY(event.getY());
                    img.setTag(shape);
                    img.setOnTouchListener(new ImgTouchEvent());

                    ConstraintLayout layout = (ConstraintLayout)v;
                    layout.addView(img);

                    break;
                case DragEvent.ACTION_DRAG_ENDED:

                default:
                    break;
            }
            return true;

        }
    }
}