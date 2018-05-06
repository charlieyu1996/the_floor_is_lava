package com.example.charlieyu.jump;

import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

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
        boolean darkMode = model.getDarkMode();
        if (darkMode){
            setBackgroundColor(Color.BLACK);
            paint.setColor(Color.WHITE);
        }else{
            setBackgroundColor(Color.WHITE);
            paint.setColor(Color.BLACK);
        }
        paint.setStrokeWidth(5);

        float maxX = model.getMaxX();
        float maxY = model.getMaxY();
        float middleX = maxX/2;
        float middleY = maxY/2;
        double theta=.11;
        float offset=200;
        float initialPointLeftX=-middleX - middleX;
        float initialPointRightX=middleX*3 + middleX;
        float initialPointY=maxY - middleX - middleX - 750;

        canvas.drawLine(middleX,maxY-200, middleX, maxY,paint);

        //Left and Right Initial points with offset
        canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY-200, paint);
        canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY-200,paint);

        //Lef and Right Initial points without offset
        canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY, paint);
        canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY,paint);

        canvas.drawRect(middleX, maxY-middleX,middleX,maxY-200,paint);

        float x, x2 ;
        float y, y2 ;

        x=x2=middleX;
        y=y2=maxY-200;

        float initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
        float initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));

        double dec = .01;

        for (int i = 0; i < 9; i++){

            float offsetLengthLeft= (float) (initialLengthLeft*Math.tan(theta));
            float newoffsetLeft= (float) (offsetLengthLeft/Math.sqrt(2));

            float offsetLengthRight= (float) (initalLengthRight*Math.tan(theta));
            float newoffsetRight =(float) (offsetLengthRight/Math.sqrt(2));

            x+=newoffsetLeft;
            y-=newoffsetLeft;
            x2-=newoffsetRight;
            y2-=newoffsetRight;

            canvas.drawLine(initialPointLeftX,initialPointY,x,y, paint);
            canvas.drawLine(initialPointRightX,initialPointY,x2,y2, paint);
            dec *= 0.95;
            theta = theta - dec;
            //Log.d("fucktheta: ",Float.toString((float) theta));
            Log.d("newoffset: ",Float.toString((float) initialLengthLeft));
            //og.d("fucktheta: ",Float.toString((float) theta));
        }
    }

    @Override
    public void update(Observable o, Object arg){}
}
