package com.example.charlieyu.jump;

import android.content.Context;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewPropertyAnimator;

import java.lang.reflect.Array;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import static com.example.charlieyu.jump.Point.lineLineIntersection;

/**
 * Created by charlieyu on 2018-05-06.
 */

public class GameView extends SurfaceView implements Runnable, Observer {
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
    int counter;


    private startAnimation sa;

    // check if the game is playing
    volatile boolean playing = true;

    // game thread
    private Thread gameThread = null;

    // For drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    // Constructor
    public GameView(Context context){

        super(context);
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
                for (int i = 0; i < 9; i++) {
                    Point A = new Point(sa.getxNum()[i], sa.getyNum()[i]);
                    for (int j=0; j<9; j++) {
                        Point B = new Point(sa.getxNum2()[j], sa.getyNum2()[j]);
                        Point intersection = lineLineIntersection(A, C, B, D);
                        Point[] points = new Point[50];
                        points[i] = intersection;
                        Log.d("Intersection Points: ", String.valueOf(points[i].x + "," + points[i].y));
                    }
                }
                canvas.drawLine(0, 0, (float) 540.0000137369143,(float) 1265.8528292298472, paint);
                canvas.drawLine(0, 0, (float) 372.9033581921977,(float) 1131.1592251880948, paint);
                canvas.drawLine(0, 0, (float) 707.0966740832372,(float) 1131.1592190333165, paint);
                canvas.drawLine(0, 0, (float) 540.0000188855953,(float) 1021.6537898965632, paint);
            }



            for (int i = 0; i < sa.getCounter(); i++){
                canvas.drawLine(initialPointLeftX, initialPointY, sa.getxNum()[i], sa.getyNum()[i], paint);
                canvas.drawLine(initialPointRightX, initialPointY, sa.getxNum2()[i], sa.getyNum2()[i], paint);
            }



            surfaceHolder.unlockCanvasAndPost(canvas);
        }

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
}
