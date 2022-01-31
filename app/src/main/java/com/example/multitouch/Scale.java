package com.example.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;

public class Scale extends View {

    Paint paint = new Paint();
    int x1,x2,y1,y2;

    ArrayList<Rect> arrR;

    public Scale(Context context) {
        super(context);

        arrR=new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);
        for(int i=0;i<arrR.size();i++){
            canvas.drawRect(arrR.get(i), paint);
        }
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_MOVE:
                if(event.getPointerCount()==2){
                    x1=(int)event.getX(0);
                    x2=(int)event.getX(1);
                    y1=(int)event.getY(0);
                    y2=(int)event.getY(1);
                    if(x2<x1){
                        int z=x1;
                        x1=x2;
                        x2=z;
                    }
                    if(y2<y1){
                        int z=y1;
                        y1=y2;
                        y2=z;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Rect rect = new Rect(x1,y1,x2,y2);
                arrR.add(rect);
        }

        invalidate();
        return true;
    }


}
