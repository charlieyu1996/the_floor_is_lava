package com.example.charlieyu.jump;

import android.content.Context;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewPropertyAnimator;
import android.widget.RelativeLayout;

import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.example.charlieyu.jump.Point.lineLineIntersection;


/**
 * Created by charlieyu on 2018-05-06.
 */

public class GameView extends SurfaceView implements Runnable, Observer, SurfaceHolder.Callback {
    Model model;

    float maxX;
    float maxY;
    float middleX;
    double theta;
    float initialPointLeftX;
    float initialPointRightX;
    float initialPointY;
    float x, x2 ;
    float y, y2 ;
    float initialLengthLeft;
    float initalLengthRight;
    double dec;
    int limit;
    int counter=0;


    private startAnimation sa;

    // check if the game is playing
    volatile boolean playing = true;

    // game thread
    private Thread gameThread = null;

    // For drawing
    private Paint paint;
    private Paint taint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    Point[] points = new Point[2000];


    // Constructor
    public GameView(Context context){

        super(context);

        getHolder().addCallback(this);
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);

        //init sa
        sa = new startAnimation(context);



        maxX = model.getMaxX();
        maxY = model.getMaxY();
        middleX = maxX/2;
        theta=.11;
        initialPointLeftX=-middleX - middleX;
        initialPointRightX=middleX*3 + middleX;
        initialPointY=maxY - middleX - middleX - 750;
        x=x2=middleX;
        y=y2=maxY-200;

        initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
        initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));
        dec = .01;
        limit = 9;




        //Init observers
        model.initObservers();

        //initialize draw
        surfaceHolder = getHolder();
        paint = new Paint();
        taint =new Paint();

    }




    @Override
    public void run() {
        while(playing){
            update();
            draw();
            control();
        }
    }

    private void update(){
        sa.update();
    }

    public void update(java.util.Observable o, Object arg){

    }

    private void draw(){
        // check the surface if it is valid
        if (surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();


            boolean darkMode = model.getDarkMode();
            if (darkMode){
                canvas.drawColor(Color.BLACK);
                paint.setColor(Color.WHITE);
            }else{
                canvas.drawColor(Color.WHITE);
                paint.setColor(Color.BLACK);
            }


            paint.setStrokeWidth(5);


            float maxX = model.getMaxX();
            float maxY = model.getMaxY();
            float middleX = maxX/2;
            float middleY = maxY/2;
            double theta=.11;
            float offset=200;
            final float initialPointLeftX=-middleX - middleX;
            final float initialPointRightX=middleX*3 + middleX;
            final float initialPointY=maxY - middleX - middleX - 750;

            canvas.drawLine(middleX,maxY-200-sa.getOffset500(), middleX, maxY-sa.getOffset500(),paint);

            //Left and Right Initial points with offset
            canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY-200-sa.getOffset500(), paint);
            canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY-200-sa.getOffset500(),paint);

            //Lef and Right Initial points without offset
            canvas.drawLine(initialPointLeftX, initialPointY,middleX, maxY-sa.getOffset500(), paint);
            canvas.drawLine(initialPointRightX,initialPointY,middleX,maxY-sa.getOffset500(),paint);

            canvas.drawRect(middleX, maxY-middleX,middleX,maxY-200,paint);


            float x, x2 ;
            float y, y2 ;

            x=x2=middleX;
            y=y2=maxY-200;

            float initialLengthLeft = (float) Math.sqrt(Math.pow((middleX-initialPointLeftX),2)+Math.pow((maxY-200-initialPointY),2));
            float initalLengthRight = (float) Math.sqrt(Math.pow((middleX-initialPointRightX),2)+Math.pow((maxY-200-initialPointY),2));

            double dec = .01;

            int limit = 9;

/*
            for (int i = 0; i < limit; i++) {


                float offsetLengthLeft = (float) (initialLengthLeft * Math.tan(theta));
                float newoffsetLeft = (float) (offsetLengthLeft / Math.sqrt(2));

                float offsetLengthRight = (float) (initalLengthRight * Math.tan(theta));
                float newoffsetRight = (float) (offsetLengthRight / Math.sqrt(2));

                x += newoffsetLeft;
                y -= newoffsetLeft;
                x2 -= newoffsetRight;
                y2 -= newoffsetRight;

                dec *= 0.95;
                theta = theta - dec;
                canvas.drawLine(initialPointLeftX, initialPointY, x, y, paint);
                canvas.drawLine(initialPointRightX, initialPointY, x2, y2, paint);

            }
          */
            Point C = new Point(initialPointLeftX, initialPointY);
            Point D = new Point(initialPointRightX, initialPointY);

            if(sa.getCounter()>=8) {
                counter=0;
                for (int i = 0; i < 10; i++) {
                    Point A = new Point(sa.getxNum()[i], sa.getyNum()[i]);
                    for (int j=0; j<10; j++) {
                        Point B = new Point(sa.getxNum2()[j], sa.getyNum2()[j]);
                        Point intersection = lineLineIntersection(A, C, B, D);
                        points[counter] = intersection;
                        counter++;
                        Log.d("Index: ", String.valueOf(counter));
                        Log.d("Intersection Points: ", String.valueOf(points[0].x+","+points[0].y));
                    }
                }
                taint.setStrokeWidth(9);
                canvas.drawLine((float) points[0].x, (float)points[0].y, (float) points[10].x, (float)points[10].y, taint);
                canvas.drawLine((float) points[1].x, (float)points[1].y, (float) points[11].x, (float)points[11].y, taint);

            }



            for (int i = 0; i < sa.getCounter(); i++){
                canvas.drawLine(initialPointLeftX, initialPointY, sa.getxNum()[i], sa.getyNum()[i]-sa.getOffset500(), paint);
                canvas.drawLine(initialPointRightX, initialPointY, sa.getxNum2()[i], sa.getyNum2()[i]-sa.getOffset500(), paint);
            }


            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }




    // the touch event, when the user touches the screen
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:


                break;
            case MotionEvent.ACTION_DOWN:


                break;

        }
        return true;
    }


    private void control(){
        try{
            gameThread.sleep(17);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void pause(){
        playing = false;
        try{
            gameThread.join();
        }catch(InterruptedException e){

        }
    }

    public void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas c = getHolder().lockCanvas();
        draw(c);
        getHolder().unlockCanvasAndPost(c);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
