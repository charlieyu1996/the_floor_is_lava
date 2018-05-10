package com.example.charlieyu.jump;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by charlieyu on 2018-05-09.
 */

public class dynamicBackground extends View implements Observer{
    Model model;
    // For drawing
    private Paint paint;
    private Canvas canvas;
    private startAnimation sa;

    // check if the game is playing
    volatile boolean playing = true;

    // game thread
    private Thread gameThread = null;

    public dynamicBackground(Context context) {
        super(context);
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);

        model.initObservers();
        paint = new Paint();


        //init sa
        sa = new startAnimation(context);



    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setStrokeWidth(5);
        canvas.drawColor(Color.TRANSPARENT);
        paint.setColor(Color.BLACK);


       // canvas.drawLine(1,1,200,200,paint);



        float maxX = model.getMaxX();
        float maxY = model.getMaxY();
        float middleX = maxX/2;
        float middleY = maxY/2;
        double theta=.11;
        float offset=200;
        final float initialPointLeftX=-middleX - middleX;
        final float initialPointRightX=middleX*3 + middleX;
        final float initialPointY=maxY - middleX - middleX - 750;

        canvas.drawLine(middleX,maxY-700, middleX, maxY-500,paint);

        //Left and Right Initial points with offset
        canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY-700, paint);
        canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY-700,paint);

        //Lef and Right Initial points without offset
        canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY-500, paint);
        canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY-500,paint);

        canvas.drawRect(middleX, maxY-middleX,middleX,maxY-200,paint);

        float x, x2 ;
        float y, y2 ;

        x=x2=middleX;
        y=y2=maxY-200;

        float initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
        float initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));

        double dec = .01;

        for (int i = 0; i < 3; i++){
            float offsetLengthLeft= (float) (initialLengthLeft*Math.tan(theta));
            float newoffsetLeft= (float) (offsetLengthLeft/Math.sqrt(2));

            float offsetLengthRight= (float) (initalLengthRight*Math.tan(theta));
            float newoffsetRight =(float) (offsetLengthRight/Math.sqrt(2));

            x+=newoffsetLeft;
            y-=newoffsetLeft;
            x2-=newoffsetRight;
            y2-=newoffsetRight;

            dec *= 0.95;
            theta = theta - dec;
            canvas.drawLine(initialPointLeftX,initialPointY,x,y-500, paint);
            canvas.drawLine(initialPointRightX,initialPointY,x2,y2-500, paint);
        }


//        for (int i = 0; i < sa.getCounter(); i++){
//            canvas.drawLine(initialPointLeftX, initialPointY, sa.getxNum()[i], sa.getyNum()[i], paint);
//            canvas.drawLine(initialPointRightX, initialPointY, sa.getxNum2()[i], sa.getyNum2()[i], paint);
//        }


    }

    @Override
    public void update(Observable o, Object arg){
        Log.d("TEST", "updated");
    }


}
