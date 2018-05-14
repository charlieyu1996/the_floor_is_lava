package com.example.charlieyu.jump;

import android.content.Context;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
import java.util.Random;
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

    private Player player;
//
    // check if the game is playing
    volatile boolean playing = true;

    // game thread
    private Thread gameThread = null;

    // For drawing
    private Paint paint;
    private Paint taint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    //Point[] points = new Point[100];


    // Constructor
    public GameView(Context context){

        super(context);

        getHolder().addCallback(this);
        // Get model instance
        model = Model.getInstance();
        model.addObserver(this);

        //init sa
        sa = new startAnimation(context);
        player = new Player();



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
;
            if(sa.getCounter()>=8) {
                counter=0;

                Point points[] = model.getPoints();
                int colorOffset=0;
                int colorLimit = 7;
                for (int i = 0; i < 90; i++){
                    if (i != 9 && i != 19 && i != 29 && i != 39 && i != 49 && i != 59 && i != 69 && i!= 79 && i!=89 ) {
                        Path path = new Path();
                        path.moveTo( points[i].x, points[i].y);
                        path.lineTo( points[i + 10].x, points[i + 10].y);
                        path.lineTo( points[i + 11].x, points[i + 11].y);
                        path.lineTo( points[i + 1].x, points[i + 1].y);
                        path.lineTo( points[i].x, points[i].y);
                        if (colorOffset == 0) {
                            taint.setColor(Color.argb(255,128,0,0));
                        }else if (colorOffset == 1){
                            taint.setColor(Color.argb(255,178,34,34));
                        }else if (colorOffset == 2){
                            taint.setColor(Color.argb(255,165,42,42));
                        }else if (colorOffset == 3){
                            taint.setColor(Color.argb(255,205,92,92));
                        }else if (colorOffset == 4){
                            taint.setColor(Color.argb(255,255,0,0));
                        }else if (colorOffset == 5){
                            taint.setColor(Color.argb(255,255,69,0));
                        }else if (colorOffset == 6){
                            taint.setColor(Color.argb(255,255,127,80));
                        }else if (colorOffset == 7){
                            taint.setColor(Color.argb(255,240,128,128));
                        }else if (colorOffset == 8){
                            taint.setColor(Color.argb(255,233,150,122));
                        }else if (colorOffset == 9){
                            taint.setColor(Color.argb(255,255,160,122));
                        }else if (colorOffset == 10){
                            taint.setColor(Color.argb(255,255,165,0));
                        }else if (colorOffset == 11){
                            taint.setColor(Color.argb(255,255,215,0));
                        }else if (colorOffset == 12){
                            taint.setColor(Color.argb(255,240,230,140));
                        }else if (colorOffset == 13){
                            taint.setColor(Color.argb(255,238,232,170));
                        }else if (colorOffset == 14){
                            taint.setColor(Color.argb(255,165,42,42));
                        }else if (colorOffset == 15){
                            taint.setColor(Color.argb(255,165,42,42));
                        }else if (colorOffset == 16){
                            taint.setColor(Color.argb(255,165,42,42));
                        }
                        canvas.drawPath(path, taint);
                        colorOffset++;
                        if (colorLimit == colorOffset){
                            colorLimit++;
                            colorOffset = 0;
                        }
                    }
                }
            }

            for (int i = 0; i < sa.getCounter(); i++){
                canvas.drawLine(initialPointLeftX, initialPointY, model.getxNum()[i], model.getyNum()[i]-sa.getOffset500(), paint);
                canvas.drawLine(initialPointRightX, initialPointY, model.getxNum2()[i], model.getyNum2()[i]-sa.getOffset500(), paint);
            }

            if (sa.getCounter()>=8){
                paint.setColor(Color.BLACK);
                Point bot = player.getBot();
                Point left = player.getLeft();
                Point right = player.getRight();
                Point top = player.getTop();

                Path playerCell = new Path();
                playerCell.moveTo( bot.x, bot.y);
                playerCell.lineTo( right.x, right.y);
                playerCell.lineTo( top.x, top.y);
                playerCell.lineTo( left.x, left.y);
                playerCell.lineTo( bot.x, bot.y);
                canvas.drawPath(playerCell, paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    // the touch event, when the user touches the screen
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_UP:
                player.update();
                update();
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
