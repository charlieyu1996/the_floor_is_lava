package com.example.charlieyu.jump;

import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by charlieyu on 2018-05-04.
 */

public class background extends View implements Observer{
    // Private Variables
    Model model;
    Paint paint = new Paint();




//
//    public background(Context context, AttributeSet attrs) {
//        super(context, attrs);
//
//    }
//
//    public background(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//
//    }
    public background(Context context) {
        super(context);
        model = Model.getInstance();
        model.addObserver(this);
    }

    @Override
    public void onDraw(Canvas canvas) {

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
//        canvas.drawRect(30, 30, 80, 80, paint);
//        paint.setStrokeWidth(0);
//        paint.setColor(Color.CYAN);
//        canvas.drawRect(33, 60, 77, 77, paint );
//        paint.setColor(Color.YELLOW);
//        canvas.drawRect(33, 33, 77, 60, paint );

//        canvas.drawLine(0, 0, 20, 20, paint);
//        canvas.drawLine(20, 0, 0, 20, paint);
        int maxX = model.getMaxX();
        int maxY = model.getMaxY();
        int middleX = maxX/2;
        int middleY = maxY/2;
        int theta=2;
        canvas.drawLine(middleX,maxY-200, middleX, maxY,paint);

//        canvas.drawLine(0,middleY,middleX,maxY-200, paint);
//        canvas.drawLine(maxX,middleY,middleX,maxY-200, paint);

        canvas.drawLine(-683, 0,middleX, maxY-200, paint);
        canvas.drawLine(maxX+683, 0, middleX, maxY-200, paint);

        canvas.drawLine(-683, 0,middleX, maxY, paint);
        canvas.drawLine(maxX+683, 0, middleX, maxY, paint);

        double x = middleX;
        double y = maxY-200;
        double initialLength = Math.sqrt((middleX-x)*(middleX-x)+(maxY-200)*(maxY-200));
        double offsetLength=initialLength*Math.tan(theta);
        double newoffset=offsetLength/Math.sqrt(2);
        double factor = 0;
        for (int i = 0; i < 10; i++){
            x+=newoffset;
            y-=newoffset;
            float x2 = (float) x;
            float y2 = (float) y;
            canvas.drawLine(-683,0,x2,y2, paint);
        }
    }

    @Override
    public void update(Observable o, Object arg){}
}
